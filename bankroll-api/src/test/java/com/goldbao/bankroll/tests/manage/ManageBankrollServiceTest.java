package com.goldbao.bankroll.tests.manage;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.BankrollApply;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.bankroll.service.manage.sysuser.SysUserService;
import com.goldbao.bankroll.service.organize.OrganizeService;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.BankrollApplicantResult;
import com.goldbao.bankroll.vo.manage.ManageBankrollApplyResult;
import com.goldbao.bankroll.vo.manage.ManageBankrollCreatorResult;
import com.goldbao.bankroll.vo.manage.ManageBankrollRecordResult;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;

/**
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class ManageBankrollServiceTest {

    private ManageBankrollService manageBankrollService;
    private UserService userService;
    private SysUserService sysUserService;
	private OrganizeService organizeService;

    private final static Logger logger = LoggerFactory.getLogger(ManageBankrollServiceTest.class);

    // 获取申请列表
    @Test
    public void testGetApplyList() {

        PageableList<BankrollApply> r = manageBankrollService.getApplyList(EnumCycleUnit.MONTH, EnumBankrollApplyStatus.VERIFYING, 1, 10);

        Assert.assertTrue(r.getCount() > 0);


    }

    @Test
    public void testGetApplyById() {
        Long applyId = 1L;
        BankrollApply item = manageBankrollService.getApplyById(applyId);
        ManageBankrollApplyResult result = new ManageBankrollApplyResult();
        BeanUtils.copyProperties(item, result);
        BankrollApplicantResult applicant = new BankrollApplicantResult();
        BeanUtils.copyProperties(item.getApplicant(), applicant);
        applicant.setBalance(item.getApplicant().getUserFund().getBalance());
        result.setApplicant(applicant);
        result.setStatus(item.getStatus().ordinal());
        result.setCycleUnit(item.getCycleUnit().ordinal());
        logger.debug(CommonUtil.serializeJSON(result));
    }

    // 配资申请审核
    @Test
    public void testAuditBankrollApply() {
        Long id = 3L;
        String token = "4dabe4cbd74b9d6cc7bb8dc94a3d475e";
        Long orgId = 1L;
        try {
        	Organize organize = organizeService.getOrganizeById(orgId);
        	
            SysUserToken sysUserToken = sysUserService.getToken(token);

            SysUser sysUser = sysUserToken.getUser();

            // 1. 获取配资申请
            BankrollApply apply = manageBankrollService.getApplyById(id);
            if (apply == null) {
                throw new ServiceException(EnumServiceMessage.BANKROLL_APPLY_NOT_EXIST);
            }

            if (!apply.getStatus().equals(EnumBankrollApplyStatus.VERIFYING)) {
                throw new ServiceException(EnumServiceMessage.BANKROLL_APPLY_NOT_VERIFYING);
            }

            // 计算当期付款总金额
//            BigDecimal money = apply.getMoney(); // 配资金额
            BigDecimal deposit = apply.getDeposit();  // 保证金
            BigDecimal managementFee = getCurrentManagementFee(apply); // 当期应收服务费

            BigDecimal totalMoney = deposit; // MathUtil.add(money, deposit);
            totalMoney = MathUtil.add(totalMoney, managementFee); // 当期总付款金额

            // 2. 获取申请人信息
            User applicant = apply.getApplicant();
            // 获取申请人账户信息
            UserFund userFund = userService.getUserFundByUserId(applicant.getId());
            BigDecimal balance = userFund.getBalance();
            // 检查余额是否足够付当期总金额
            if (MathUtil.gt(totalMoney, balance)) {
                throw new ServiceException(EnumServiceMessage.BANKROLL_APPLY_USER_BALANCE_NOT_ENOUGH);
            }

            manageBankrollService.addBankrollRecord(userFund, apply, sysUser, organize);
        } catch (ServiceException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Test
    public void testGetBankrollRecordById() {
        long recordId = 2L;
        BankrollRecord record = manageBankrollService.getBankrollRecordById(recordId);

        ManageBankrollRecordResult result = new ManageBankrollRecordResult();

        BeanUtils.copyProperties(record, result, "applicant");

        result.setCycleUnit(record.getCycleUnit().ordinal());
        ManageBankrollCreatorResult creator = new ManageBankrollCreatorResult();
        BeanUtils.copyProperties(record.getCreator(), creator);
        result.setCreator(creator);
        result.setStartDate(CommonUtil.formatDate(record.getStartDate()));
        result.setEndDate(CommonUtil.formatDate(record.getEndDate()));
        result.setUpdateTime(CommonUtil.formatDate(record.getUpdateTime()));
        BankrollApplicantResult applicant = new BankrollApplicantResult();
        BeanUtils.copyProperties(record.getApplicant(), applicant);
        applicant.setBalance(record.getApplicant().getUserFund().getBalance());

        result.setApplicant(applicant);

        logger.debug(CommonUtil.serializeJSON(result));

    }

    @Test
    public void testGetBankrollRecordList() {
        PageableList<BankrollRecord> recordList = manageBankrollService.getBankrollRecordList(EnumCycleUnit.DAY, null, 1, 10);
        Assert.assertTrue(recordList.getCount() > 0);
    }

    // 分配homs帐号
    @Test
    public void testAllocationHoms() {
        long recordId = 3L;
        String token = "4dabe4cbd74b9d6cc7bb8dc94a3d475e";
        try {

            SysUserToken sysUserToken = sysUserService.getToken(token);
            if (sysUserToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            String homsOperator = "111";
            String homsPwd = "2222";

            Date startDate = new Date();
            Date endDate = new Date();
            manageBankrollService.addAllocationHoms(recordId, homsOperator, homsPwd, startDate, endDate, sysUserToken.getUser());


        } catch (ServiceException ex) {
            logger.error(ex.getMessage());
        }


    }
    @Test
    public void testBillRepaymentByMonth() {

    }

    private BigDecimal getCurrentManagementFee(BankrollApply apply) {
        if (apply.getCycleUnit().equals(EnumCycleUnit.MONTH))
            return apply.getManagementFee();
        else {
            BigDecimal managementFee = MathUtil.format(apply.getManagementFee(), 1) ;
            BigDecimal totalManagementFee = MathUtil.format(MathUtil.multiply(managementFee, apply.getCycle()), 1);
            return totalManagementFee;
        }
    }

    @Test
    public void testUpdateBankrollRecordToFinish() {

        String token = "4dabe4cbd74b9d6cc7bb8dc94a3d475e";

        SysUserToken userToken = sysUserService.getToken(token);
        if (userToken == null) {
            return;
        }

        Long recordId = 1L;
        try {
            manageBankrollService.updateBankrollRecordToFinish(recordId, userToken.getUser());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateBankrollRecordToRepayment() {
        String token = "4dabe4cbd74b9d6cc7bb8dc94a3d475e";

        BigDecimal repaymentMoney = MathUtil.format("10000");

        SysUserToken userToken = sysUserService.getToken(token);
        if (userToken == null) {
            return;
        }

        Long recordId = 1L;
        try {
            manageBankrollService.updateBankrollRecordToRepayment(recordId, repaymentMoney, userToken.getUser());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setManageBankrollService(ManageBankrollService manageBankrollService) {
        this.manageBankrollService = manageBankrollService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
    @Autowired
	public void setOrganizeService(OrganizeService organizeService) {
		this.organizeService = organizeService;
	}
    
}

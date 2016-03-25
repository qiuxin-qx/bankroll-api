package com.goldbao.bankroll.manage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.service.manage.bankroll.ManageTradingDayService;
import com.goldbao.bankroll.service.organize.OrganizeService;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.*;
import com.goldbao.bankroll.vo.manage.*;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.bankroll.service.manage.sysuser.SysUserService;

/**
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/admin/bankroll")
public class ManageBankrollController {

    private ManageBankrollService manageBankrollService;
    private SysUserService sysUserService;
    private UserService userService;
    private OrganizeService organizeService;

    private ManageTradingDayService manageTradingDayService;

    private final static Logger logger = LoggerFactory.getLogger(ManageBankrollController.class);


    @RequestMapping("/getApplyList")
    public @ResponseBody ModelTemplate<Pages<ManageBankrollApplyResult>> getApplyList(String token, int unit, int status, int fundStat, int index, int size) {
        ModelTemplate<Pages<ManageBankrollApplyResult>> r = null;
        try {

            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            EnumCycleUnit eUnit = EnumCycleUnit.parse(unit);
            EnumBankrollApplyStatus eStatus = EnumBankrollApplyStatus.parse(status);
            PageableList<BankrollApplyDTO> list = manageBankrollService.getApplyDTOList(eUnit, eStatus,fundStat, index, size);
            Pages<ManageBankrollApplyResult> pages = new Pages<ManageBankrollApplyResult>();

            BeanUtils.copyProperties(list, pages, "list");
            if (list.getList().size() > 0) {
                List<ManageBankrollApplyResult> l = new ArrayList<ManageBankrollApplyResult>();
                for (BankrollApplyDTO item: list.getList()) {
                    ManageBankrollApplyResult result = new ManageBankrollApplyResult();
                    BeanUtils.copyProperties(item, result);
                    result.setId(item.getId().longValue());

                    BankrollApplicantResult applicant = new BankrollApplicantResult();
                    applicant.setId(item.getApplicantId().longValue());
                    BeanUtils.copyProperties(item, applicant);
                    result.setApplicant(applicant);
                    result.setAddTime(CommonUtil.formatDate(item.getAddTime()));
                    l.add(result);
                }
                pages.setList(l);
                pages.setPageCount(list.getPageCount());
            }
            r = new ModelTemplate<Pages<ManageBankrollApplyResult>>(pages);
        } catch (ServiceException ex) {
            r = new ModelTemplate<Pages<ManageBankrollApplyResult>>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<ManageBankrollApplyResult>>(ex);
        }

        return r;
    }
    @RequestMapping("/getBankrollApplyById")
    public @ResponseBody ModelTemplate<ManageBankrollApplyResult> getBankrollApplyById(String token, long applyId) {
        ModelTemplate<ManageBankrollApplyResult> r = null;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            BankrollApply item = manageBankrollService.getApplyById(applyId);

            ManageBankrollApplyResult result = new ManageBankrollApplyResult();
            BeanUtils.copyProperties(item, result);
            BankrollApplicantResult applicant = new BankrollApplicantResult();
            BeanUtils.copyProperties(item.getApplicant(), applicant);
            applicant.setBalance(item.getApplicant().getUserFund().getBalance());
            result.setApplicant(applicant);
            result.setStatus(item.getStatus().ordinal());
            result.setCycleUnit(item.getCycleUnit().ordinal());

            if (item.getBankrollRecord() != null) {
                result.setBankrollRecordId(item.getBankrollRecord().getId());
            }

            r = new ModelTemplate<ManageBankrollApplyResult>(result);
        } catch (Exception ex) {
            r = new ModelTemplate<ManageBankrollApplyResult>(ex);
        }
        return r;
    }

    @RequestMapping("/getBankrollRecordList")
    public @ResponseBody ModelTemplate<Pages<ManageBankrollRecordResult>> getBankrollRecordList(String token, int unit, int status, int index, int size) {

        ModelTemplate<Pages<ManageBankrollRecordResult>> r = null;
        try {

            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            EnumCycleUnit munit = EnumCycleUnit.parse(unit);
            EnumBankrollRecordStatus mStatus = EnumBankrollRecordStatus.parse(status);
            PageableList<BankrollRecord> p = manageBankrollService.getBankrollRecordList(munit, mStatus, index, size);

            Pages<ManageBankrollRecordResult> result = new Pages<ManageBankrollRecordResult>();

            BeanUtils.copyProperties(p, result, "list");

            List<ManageBankrollRecordResult> list = new ArrayList<ManageBankrollRecordResult>();

            for (BankrollRecord item: p.getList()) {
                ManageBankrollRecordResult record = new ManageBankrollRecordResult();
                BeanUtils.copyProperties(item, record);
                record.setCycleUnit(item.getCycleUnit().ordinal());
                ManageBankrollCreatorResult creator = new ManageBankrollCreatorResult();
                BeanUtils.copyProperties(item.getCreator(), creator);
                record.setCreator(creator);
                record.setStartDate(CommonUtil.formatDate(item.getStartDate()));
                record.setEndDate(CommonUtil.formatDate(item.getEndDate()));
                record.setUpdateTime(CommonUtil.formatDate(item.getUpdateTime()));
                record.setStatus(item.getStatus().ordinal());
                BankrollApplicantResult applicant = new BankrollApplicantResult();
                BeanUtils.copyProperties(item.getApplicant(), applicant);
                applicant.setBalance(item.getApplicant().getUserFund().getBalance());

                record.setApplicant(applicant);

                list.add(record);
            }

            result.setList(list);
            result.setPageCount(p.getPageCount());

            r = new ModelTemplate<Pages<ManageBankrollRecordResult>>(result);
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<ManageBankrollRecordResult>>(ex);
            ex.printStackTrace();
        }

        return r;

    }

    @RequestMapping("/getBankrollRecordById")
    public @ResponseBody ModelTemplate<ManageBankrollRecordResult> getBankrollRecordById(String token, long recordId) {
        ModelTemplate<ManageBankrollRecordResult> r = null;
        try {

            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

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
            result.setAddTime(CommonUtil.formatDate(record.getAddTime()));
            BankrollApplicantResult applicant = new BankrollApplicantResult();
            BeanUtils.copyProperties(record.getApplicant(), applicant);
            applicant.setBalance(record.getApplicant().getUserFund().getBalance());
            result.setStatus(record.getStatus().ordinal());
            result.setApplicant(applicant);

            r = new ModelTemplate<ManageBankrollRecordResult>(result);
        } catch (Exception ex) {
            r = new ModelTemplate<ManageBankrollRecordResult>(ex);
        }
        return r;
    }

    // 审核某笔配资申请
    @RequestMapping("/auditBankrollApply")
    public @ResponseBody ModelTemplate<AuditBankrollApplyResult> auditBankrollApply(String token, long applyId, long orgId) {
        ModelTemplate<AuditBankrollApplyResult> r = null;
        try {
            SysUserToken sysUserToken = sysUserService.getToken(token);
            if (sysUserToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            SysUser sysUser = sysUserToken.getUser();

            Organize organize = organizeService.getOrganizeById(orgId);

            // 1. 获取配资申请
            BankrollApply apply = manageBankrollService.getApplyById(applyId);
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

            BigDecimal totalMoney = deposit;// MathUtil.add(money, deposit);
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

            BankrollRecord record =  manageBankrollService.addBankrollRecord(userFund, apply, sysUser, organize);
            AuditBankrollApplyResult result = new AuditBankrollApplyResult();
            result.setBankrollRecordId(record.getId());
            r = new ModelTemplate<AuditBankrollApplyResult>(result);
        } catch (ServiceException ex) {
            r = new ModelTemplate<AuditBankrollApplyResult>(ex);
            logger.error(ex.getMessage());
        }
        return r;
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

    @RequestMapping("/getRangeTradingDays")
    public @ResponseBody ModelTemplate<ManageRangeTradingDayResult> getRangeTradingDays(String token, String date, Integer days) {
        ModelTemplate<ManageRangeTradingDayResult> r = null;
        try {

            SysUserToken sysUserToken = sysUserService.getToken(token);
            if (sysUserToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            String format = "yyyy-MM-dd";
            Date startDate = CommonUtil.parseDate(date, format);

            RangeTradingDayDTO rangeTradingDay = manageTradingDayService.getRangeTradingDay(startDate, days);
            ManageRangeTradingDayResult re = new ManageRangeTradingDayResult();

            re.setStartDate(CommonUtil.formatDate(rangeTradingDay.getStartDate(), format));
            re.setEndDate(CommonUtil.formatDate(rangeTradingDay.getEndDate(), format));
            r = new ModelTemplate<ManageRangeTradingDayResult>(re);
        } catch (ServiceException e) {
            r = new ModelTemplate<ManageRangeTradingDayResult>(e);
            e.printStackTrace();
        } catch (Exception e) {
            r = new ModelTemplate<ManageRangeTradingDayResult>(e);
            e.printStackTrace();
        }

        return r;
    }

    // 分配homs帐号
    @RequestMapping("/allocateHoms")
    public @ResponseBody ModelTemplate<Empty> allocateHoms(String token, Long recordId, String homsOperator, String homsPwd, String startDate, String endDate, Long orgId) {
        ModelTemplate<Empty> r = null;

        try {
            SysUserToken sysUserToken = sysUserService.getToken(token);
            if (sysUserToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            String format = "yyyy-MM-dd";
            Date mStartDate = CommonUtil.parseDate(startDate, format);
            Date mEndDate = CommonUtil.parseDate(endDate, format);
            manageBankrollService.addAllocationHoms(recordId, homsOperator, homsPwd, mStartDate, mEndDate, sysUserToken.getUser());

            r = new ModelTemplate<Empty>(Empty.empty());

        } catch (ServiceException ex) {
            r = new ModelTemplate<Empty>(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            r = new ModelTemplate<Empty>(ex);
        }

        return r;
    }

    @RequestMapping("/getBankrollHomsInfo")
    public @ResponseBody ModelTemplate<BankrollHomsInfoResult> getBankrollHomsInfo(String token, long recordId) {
        ModelTemplate<BankrollHomsInfoResult> r = null;
        try {
            SysUserToken sysUserToken = sysUserService.getToken(token);
            if (sysUserToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            BankrollHomsInfo homsInfo = manageBankrollService.getBankrollHomsInfo(recordId);
//            if (homsInfo == null) {
//                throw new ServiceException("暂未同步");
//            }
            BankrollHomsInfoResult result = null;
            if (homsInfo != null) {
                result = new BankrollHomsInfoResult();
                BeanUtils.copyProperties(homsInfo, result);
                result.setUpdateTime(CommonUtil.formatDate(homsInfo.getUpdateTime()));
                result.setRecordId(homsInfo.getRecord().getId());
            }
            r = new ModelTemplate<BankrollHomsInfoResult>(result);
        } catch (ServiceException ex) {
            r = new ModelTemplate<BankrollHomsInfoResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<BankrollHomsInfoResult>(ex);
        }
        return r;
    }

    @RequestMapping("/getBankrollHomsInfoLog")
    public @ResponseBody ModelTemplate<List<BankrollHomsInfoLogResult>> getBankrollHomsInfoLog(String token, long recordId) {
        ModelTemplate<List<BankrollHomsInfoLogResult>> r = null;
        try {
            SysUserToken sysUserToken = sysUserService.getToken(token);
            if (sysUserToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            List<BankrollHomsInfoLog> logs = manageBankrollService.getBankrollHomsInfoLog(recordId);
//            if (logs == null || logs.size() == 0) {
//                throw new ServiceException("暂未同步");
//            }
            List<BankrollHomsInfoLogResult> list = new ArrayList<BankrollHomsInfoLogResult>();
            if (logs != null) {
                for (BankrollHomsInfoLog item : logs) {
                    BankrollHomsInfoLogResult result = new BankrollHomsInfoLogResult();
                    BeanUtils.copyProperties(item, result);
                    result.setRecordId(item.getRecord().getId());
                    result.setAddTime(CommonUtil.formatDate(item.getAddTime()));
                    list.add(result);
                }
            }

            r = new ModelTemplate<List<BankrollHomsInfoLogResult>>(list);
        } catch (ServiceException ex) {
            r = new ModelTemplate<List<BankrollHomsInfoLogResult>>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<List<BankrollHomsInfoLogResult>>(ex);
        }
        return r;
    }

    @RequestMapping("/updateBankrollRecordToFinish")
    public @ResponseBody ModelTemplate<Empty> updateBankrollRecordToFinish(String token, long recordId) {
        ModelTemplate<Empty> r = null;
        try {
            SysUserToken userToken = sysUserService.getToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            manageBankrollService.updateBankrollRecordToFinish(recordId, userToken.getUser());
            r = new ModelTemplate<Empty>(Empty.empty());
        } catch (ServiceException e) {
            r = new ModelTemplate<Empty>(e);
        }

        return r;
    }

    @RequestMapping("/updateBankrollRecordToRepayment")
    public @ResponseBody ModelTemplate<Empty> updateBankrollRecordToRepayment(String token, String repaymentMoney, long recordId) {
        ModelTemplate<Empty> r = null;
        try {
            SysUserToken userToken = sysUserService.getToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            BigDecimal money = MathUtil.format(repaymentMoney);

            manageBankrollService.updateBankrollRecordToRepayment(recordId, money, userToken.getUser());
            r = new ModelTemplate<Empty>(Empty.empty());
        } catch (ServiceException e) {
            r = new ModelTemplate<Empty>(e);
        }

        return r;
    }

    @Autowired
    public void setManageBankrollService(ManageBankrollService manageBankrollService) {
        this.manageBankrollService = manageBankrollService;
    }
    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setOrganizeService(OrganizeService organizeService) {
        this.organizeService = organizeService;
    }
    @Autowired
    public void setManageTradingDayService(ManageTradingDayService manageTradingDayService) {
        this.manageTradingDayService = manageTradingDayService;
    }
}

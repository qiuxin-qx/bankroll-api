package com.goldbao.bankroll.schedule;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfo;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoDTO;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.homs.HomsCombAsset;
import com.goldbao.bankroll.model.homs.HomsCombInfo;
import com.goldbao.bankroll.model.homs.HomsFutureLoan;
import com.goldbao.bankroll.model.homs.HomsUser;
import com.goldbao.bankroll.service.homs.HomsService;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.homs.HomsEvent;
import com.goldbao.homs.HomsException;
import com.goldbao.homs.result.HomsCombInfoResult;
import com.goldbao.homs.result.HomsLoginResult;
import com.goldbao.homs.result.HomsQueryCombAssetResult;
import com.goldbao.homs.result.HomsQueryFutureLoanResult;
import com.goldbao.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * homs同步数据任务
 * resources/bankroll-api-manage-job.xml
 * @author shuyu.fang
 */
public class BankrollHomsSchedule {

    private ManageBankrollService manageBankrollService;

    private HomsService homsService;
    private HomsEvent homsEvent;

    private String operatorNo = "";
    private String operatorPwd = "";

    private final static Logger logger = LoggerFactory.getLogger(BankrollHomsSchedule.class);


    // 这几个同步未来如果很耗时的话，可以拆分成若干小任务分开执行，按照顺序执行即可
    public void syncHomsJob() {
        // 1. 获取登录信息
        String homsToken = loginHoms();
        // 2. 获取并同步组合信息（暂时甚至很久都不会超过10M，当这个值超过10M时，代码需重写）
        List<HomsCombInfoResult> combInfos = null;
        try {
            combInfos = homsEvent.queryCombInfo(homsToken);
            // 添加或者修改数据库
            if (combInfos.size() > 0) {
                List<HomsCombInfo> list = new ArrayList<HomsCombInfo>();
                for (HomsCombInfoResult item : combInfos) {
                    HomsCombInfo combInfo = new HomsCombInfo();
                    BeanUtils.copyProperties(item, combInfo);
                    list.add(combInfo);
                }
                homsService.addCombInfos(list);
            }
        } catch (HomsException ex) {
            logger.debug("query and sync combifo fail:{} - {} ", ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            logger.debug("query and sync combifo fail:{} ", ex.getMessage());
        }

        // 3. 获取并同步组合资产信息
        List<HomsCombInfo> list = homsService.getCombInfoList();
        if (list != null) {
            for (HomsCombInfo combInfo : list) {
                HomsQueryCombAssetResult combAsset = null;
                try {
                    combAsset = homsEvent.queryCombAsset(homsToken, combInfo.getFundAccount(), combInfo.getCombineId());

                    if (combAsset != null) {
                        HomsCombAsset asset = new HomsCombAsset();
                        BeanUtils.copyProperties(combAsset, asset);
                        homsService.addOrUpdateCombAsset(asset);
                    }
                } catch (HomsException ex) {
                    logger.debug("query and sync combasset fail:{} - {} ", ex.getCode(), ex.getMessage());
                } catch (Exception ex) {
                    logger.debug("query and sync combasset fail:{} ", ex.getMessage());
                }
            }
        }

        // 4. 获取并同步借贷信息
        List<HomsQueryFutureLoanResult> futureLoans = null;
        try {
            futureLoans = homsEvent.queryFutureLoan(homsToken);

            if (futureLoans != null) {
                List<HomsFutureLoan> loans = new ArrayList<HomsFutureLoan>();
                for (HomsQueryFutureLoanResult futureLoan : futureLoans) {
                    HomsFutureLoan loan = new HomsFutureLoan();
                    BeanUtils.copyProperties(futureLoan, loan);
                    loans.add(loan);
                }
                homsService.addOrUpdateFutureLoan(loans);
            }
        } catch (HomsException ex) {
            logger.debug("query and sync futureLoans fail:{} - {} ", ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            logger.debug("query and sync futureLoans fail:{} ", ex.getMessage());
        }
    }

    private String loginHoms() {
        HomsLoginResult result = homsEvent.login(operatorNo, operatorPwd);
        HomsUser homsUser = homsService.login(operatorNo, operatorPwd, result.getUserToken());
        return homsUser.getUserToken();
    }

    // 将同步的借贷，组合，组合资产同步到配资记录并记录日志
    public void syncHomsBankrollRecordJob() {
        List<BankrollRecord> records = manageBankrollService.getBankrollRecordBySatus(EnumBankrollRecordStatus.ALLOCATIONED_HOMS, 1L);
        if (records != null) {
            for (BankrollRecord record: records) {
                BankrollHomsInfoDTO dto = homsService.getHomsInfoDTO(record.getHomsOperatorNo());
                if (dto != null) {
                    BankrollHomsInfo homsInfo = manageBankrollService.getBankrollHomsInfo(record.getId());
                    try {
                        if (homsInfo == null) {
                            homsInfo = new BankrollHomsInfo();
                            BeanUtils.copyProperties(dto, homsInfo);
                            homsInfo.setRecord(record);
                            homsInfo.setAssetTotalValue(MathUtil.format(dto.getAssetTotalValue(), 3));
                            homsInfo.setAssetValue(MathUtil.format(dto.getAssetValue(), 3));
                            homsInfo.setCurLoan(MathUtil.format(dto.getCurLoan(), 3));
                            homsInfo.setCurrentCash(MathUtil.format(dto.getCurrentCash(), 3));
                            homsInfo.setOpenValue(MathUtil.format(dto.getOpenValue(), 3));
                            homsInfo.setWarningValue(MathUtil.format(dto.getWarningValue(), 3));
                            homsInfo.setBondAsset(MathUtil.format(dto.getBondAsset(), 3));
                            homsInfo.setStockAsset(MathUtil.format(dto.getStockAsset(), 3));
                            homsInfo.setFundAsset(MathUtil.format(dto.getFundAsset(), 3));
                            homsInfo.setHgAsset(MathUtil.format(dto.getHgAsset(), 3));
                            homsInfo.setFutureAsset(MathUtil.format(dto.getFutureAsset(), 3));
                            homsInfo.setUpdateTime(new Date());
                            manageBankrollService.addBankrollHomsInfo(homsInfo);
                        } else {
                            BeanUtils.copyProperties(dto, homsInfo);
                            homsInfo.setAssetTotalValue(MathUtil.format(dto.getAssetTotalValue(), 3));
                            homsInfo.setAssetValue(MathUtil.format(dto.getAssetValue(), 3));
                            homsInfo.setCurLoan(MathUtil.format(dto.getCurLoan(), 3));
                            homsInfo.setCurrentCash(MathUtil.format(dto.getCurrentCash(), 3));
                            homsInfo.setOpenValue(MathUtil.format(dto.getOpenValue(), 3));
                            homsInfo.setWarningValue(MathUtil.format(dto.getWarningValue(), 3));
                            homsInfo.setBondAsset(MathUtil.format(dto.getBondAsset(), 3));
                            homsInfo.setStockAsset(MathUtil.format(dto.getStockAsset(), 3));
                            homsInfo.setFundAsset(MathUtil.format(dto.getFundAsset(), 3));
                            homsInfo.setHgAsset(MathUtil.format(dto.getHgAsset(), 3));
                            homsInfo.setFutureAsset(MathUtil.format(dto.getFutureAsset(), 3));
                            homsInfo.setUpdateTime(new Date());
                            manageBankrollService.updateBankrollHomsInfo(homsInfo);
                        }

                    } catch (ServiceException ex) {

                    } catch (Exception ex) {

                    }
                }
            }
        }
    }

    /**
     *
     * @param homsService
     */

    @Autowired
    public void setHomsService(HomsService homsService) {
        this.homsService = homsService;
    }

    @Autowired
    public void setHomsEvent(HomsEvent homsEvent) {
        this.homsEvent = homsEvent;
    }
    @Autowired
    public void setManageBankrollService(ManageBankrollService manageBankrollService) {
        this.manageBankrollService = manageBankrollService;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getOperatorPwd() {
        return operatorPwd;
    }

    public void setOperatorPwd(String operatorPwd) {
        this.operatorPwd = operatorPwd;
    }
}

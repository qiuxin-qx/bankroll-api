package com.goldbao.bankroll.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.user.UserToken;
import com.goldbao.bankroll.service.bankroll.BankrollService;
import com.goldbao.bankroll.service.bankroll.BankrollSolutionDayService;
import com.goldbao.bankroll.service.bankroll.BankrollSolutionService;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.manage.BankrollHomsInfoResult;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;

/**
 * 配资交易
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/bankroll")
public class BankrollController {

    private BankrollSolutionService bankrollSolutionService;
    private BankrollSolutionDayService bankrollSolutionDayService;
    private UserService userService;
    private BankrollService bankrollService;

    // 按月返回配资方案
    @RequestMapping("/bankrollByMonth")
    public @ResponseBody ModelTemplate<BankrollSolutionResult> bankrollByMonth() {
        ModelTemplate<BankrollSolutionResult> r = null;
        try {
            Long solutionId = 1L;

            BankrollSolution solution = bankrollSolutionService.getSolutionById(solutionId);

            BankrollSolutionResult result = new BankrollSolutionResult();

            // 忽略rules属性复制
            BeanUtils.copyProperties(solution, result, "rules");

            if (solution.getRules() != null && solution.getRules().size() > 0) {
                List<BankrollRuleResult> ruleList = new ArrayList<BankrollRuleResult>();
                for (BankrollRule rule: solution.getRules()) {
                    BankrollRuleResult ruleResult = new BankrollRuleResult();
                    if (rule.getInterests() != null && rule.getInterests().size() > 0) {
                        List<BankrollInterestResult> interestList = new ArrayList<BankrollInterestResult>();
                        for (BankrollInterest i: rule.getInterests()) {
                            BankrollInterestResult interestResult = new BankrollInterestResult();
                            BeanUtils.copyProperties(i, interestResult);
                            interestList.add(interestResult);
                        }
                        ruleResult.setInterests(interestList);
                    }
                    ruleResult.setLimitPosition(rule.getLimitPosition().toString());
                    // 忽略interests属性复制
                    BeanUtils.copyProperties(rule, ruleResult, "interests");
                    ruleResult.setLimitPosition(rule.getLimitPosition().toString());
                    ruleList.add(ruleResult);
                }

                result.setRules(ruleList);
            }

            r = new ModelTemplate<BankrollSolutionResult>(result);
        } catch (Exception ex) {
            r = new ModelTemplate<BankrollSolutionResult>(ex);
        }

        return r;
    }

    // 按天返回配资方案
    @RequestMapping("/bankrollByDay")
    public @ResponseBody ModelTemplate<BankrollSolutionDayResult> bankrollByDay() {
        Long solutionId = 1L;
        ModelTemplate<BankrollSolutionDayResult> r = null;
        try {
            BankrollSolutionDay solution = bankrollSolutionDayService.getSolutionById(solutionId);
            BankrollSolutionDayResult solutionResult = new BankrollSolutionDayResult();
            // 忽略rules属性复制
            BeanUtils.copyProperties(solution, solutionResult, "rules");

            if (solution.getRules() != null && solution.getRules().size() > 0) {
                Iterator<BankrollRuleDay> rules = solution.getRules().iterator();
                List<BankrollRuleDayResult> ruleResults = new ArrayList<BankrollRuleDayResult>();
                while (rules.hasNext()) {
                    BankrollRuleDay rule = rules.next();
                    BankrollRuleDayResult ruleResult = new BankrollRuleDayResult();
                    BeanUtils.copyProperties(rule, ruleResult);
                    ruleResults.add(ruleResult);
                }
                solutionResult.setRules(ruleResults);
            }
            r= new ModelTemplate<BankrollSolutionDayResult>(solutionResult);

        } catch (Exception ex) {
            r= new ModelTemplate<BankrollSolutionDayResult>(ex);
        }
        return r;
    }

    // 申请配资【按月】
    @RequestMapping("/applyByMonth")
    public @ResponseBody ModelTemplate<BankrollApplyResult> applyByMonth(Integer cycle, String principal, Integer lever, String token, String remark) {
    	ModelTemplate<BankrollApplyResult> r = null;

        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            BigDecimal zero = MathUtil.ZERO;
            BigDecimal convertPrincipal = MathUtil.format(principal);

            BankrollRule rule =  bankrollSolutionService.getRule(convertPrincipal, lever, cycle);
            BankrollInterest interest = rule.getInterests().get(0);

            // 保证金
            BigDecimal deposit = convertPrincipal;
            // 配资金额
            BigDecimal money = MathUtil.multiply(convertPrincipal, lever);
            // 总金额（保证金+配资金额）
//            BigDecimal total = MathUtil.add(deposit, money);
            // 平仓线金额
            BigDecimal openLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getOpenLine()));
            // 警告线金额
            BigDecimal warningLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getWarningLine()));
            //  每期服务费
            BigDecimal realInterest = MathUtil.divide(interest.getInterest(), "100");
            BigDecimal interestMoney = MathUtil.format(MathUtil.multiply(money, realInterest));

            BankrollApply apply = new BankrollApply();
            apply.setApplicant(userToken.getUser());
            apply.setCycle(cycle);
            apply.setCycleUnit(EnumCycleUnit.MONTH);
            apply.setDeposit(deposit);
            apply.setMoney(money);
            apply.setOpenLineMoney(openLineMoney);
            apply.setWarningLineMoney(warningLineMoney);
            apply.setManagementFee(interestMoney);
            apply.setRemark(remark);

            apply.setLever(lever);
            apply.setPrepDeposit(zero);

            bankrollService.addApplyBankroll(apply);
            BankrollApplyResult applyResult = new BankrollApplyResult();
            BeanUtils.copyProperties(apply, applyResult);
            BankrollApplicantResult applicant = new BankrollApplicantResult();
            BeanUtils.copyProperties(userToken.getUser(), applicant);
            applicant.setBalance(userToken.getUser().getUserFund().getBalance());
            applyResult.setApplicant(applicant);
            applyResult.setStatus(apply.getStatus().ordinal());
            applyResult.setCycleUnit(apply.getCycleUnit().ordinal());

            r = new ModelTemplate<BankrollApplyResult>(applyResult);
        
        } catch (ServiceException ex) {
        	r = new ModelTemplate<BankrollApplyResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<BankrollApplyResult>(ex);
        }

        return r;
    }

    // 申请配资【按天】
    @RequestMapping("/applyByDay")
    public @ResponseBody ModelTemplate<BankrollApplyResult> applyByDay(Integer cycle, String money, Integer lever, String token, String remark) {
        ModelTemplate<BankrollApplyResult> r = null;

        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            BigDecimal convertMoney = MathUtil.format(money);
            BankrollRuleDay rule = bankrollSolutionDayService.getRule(convertMoney, lever, cycle);

            BigDecimal zero = MathUtil.ZERO;
            // 保证金
            BigDecimal deposit = MathUtil.floor(MathUtil.divide(money, lever));
            // 总金额（保证金+配资金额）
//            BigDecimal total = MathUtil.add(deposit, money);
            // 平仓线金额
            BigDecimal openLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getOpenLine()));
            // 警告线金额
            BigDecimal warningLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getWarningLine()));
            //  每期服务费
            BigDecimal realInterest = MathUtil.divide(rule.getManageFeeRate(), "100");
            BigDecimal interestMoney = MathUtil.format(MathUtil.multiply(money, realInterest), 1); // 保留一位小数

            BankrollApply apply = new BankrollApply();
            apply.setApplicant(userToken.getUser());
            apply.setCycle(cycle);
            apply.setCycleUnit(EnumCycleUnit.DAY);

            apply.setDeposit(deposit);
            apply.setMoney(convertMoney);
            apply.setOpenLineMoney(openLineMoney);
            apply.setWarningLineMoney(warningLineMoney);
            apply.setManagementFee(interestMoney);

            apply.setLever(lever);
            apply.setPrepDeposit(zero);
            apply.setRemark(remark);

            bankrollService.addApplyBankroll(apply);

            BankrollApplyResult applyResult = new BankrollApplyResult();
            BeanUtils.copyProperties(apply, applyResult);
            BankrollApplicantResult applicant = new BankrollApplicantResult();
            BeanUtils.copyProperties(userToken.getUser(), applicant);
            applicant.setBalance(userToken.getUser().getUserFund().getBalance());
            applyResult.setApplicant(applicant);
            applyResult.setStatus(apply.getStatus().ordinal());
            applyResult.setCycleUnit(apply.getCycleUnit().ordinal());

            r = new ModelTemplate<BankrollApplyResult>(applyResult);
        } catch (ServiceException ex) {
            r = new ModelTemplate<BankrollApplyResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<BankrollApplyResult>(ex);
        }
        return r;
    }

    @RequestMapping("/getApplyList")
    public @ResponseBody ModelTemplate<Pages<BankrollApplyResult>> getApplyList(String token, int unit, int status, int fundStat, int index, int size) {
        ModelTemplate<Pages<BankrollApplyResult>> r = null;
        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            EnumCycleUnit eUnit = EnumCycleUnit.parse(unit);
            EnumBankrollApplyStatus eStatus = EnumBankrollApplyStatus.parse(status);
            //  fundStat 0(余额不足，不能进入审核),1（审核中，可以审核）
            PageableList<BankrollApplyDTO> list = bankrollService.getApplyDTOList(userToken.getUser().getId(), eUnit, eStatus, fundStat, index, size);
            Pages<BankrollApplyResult> pages = new Pages<BankrollApplyResult>();

            BeanUtils.copyProperties(list, pages, "list");
            if (list.getList().size() > 0) {
                List<BankrollApplyResult> l = new ArrayList<BankrollApplyResult>();
                for (BankrollApplyDTO item: list.getList()) {
                    BankrollApplyResult result = new BankrollApplyResult();
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
            r = new ModelTemplate<Pages<BankrollApplyResult>>(pages);
        } catch (ServiceException ex) {
            r = new ModelTemplate<Pages<BankrollApplyResult>>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<BankrollApplyResult>>(ex);
        }

        return r;
    }

    @RequestMapping("/getAllApplyList")
    public @ResponseBody ModelTemplate<Pages<BankrollApplyResult>> getAllApplyList(int index, int size) {
        ModelTemplate<Pages<BankrollApplyResult>> r = null;
        try {
            PageableList<BankrollApply> list = bankrollService.getApplyList(index, size);
            Pages<BankrollApplyResult> pages = new Pages<BankrollApplyResult>();

            BeanUtils.copyProperties(list, pages, "list");
            if (list.getList().size() > 0) {
                List<BankrollApplyResult> l = new ArrayList<BankrollApplyResult>();
                for (BankrollApply item: list.getList()) {
                    BankrollApplyResult result = new BankrollApplyResult();
                    BeanUtils.copyProperties(item, result);
                    result.setId(item.getId().longValue());

                    BankrollApplicantResult applicant = new BankrollApplicantResult();
                    BeanUtils.copyProperties(item.getApplicant(), applicant);
                    BeanUtils.copyProperties(item.getApplicant().getUserFund(), applicant);
                    result.setApplicant(applicant);
                    result.setAddTime(CommonUtil.formatDate(item.getAddTime()));
                    result.setCycleUnit(item.getCycleUnit().ordinal());
                    result.setStatus(item.getStatus().ordinal());
                    if (item.getBankrollRecord() != null) {
                        result.setBankrollRecordId(item.getBankrollRecord().getId());
                    }
                    l.add(result);
                }
                pages.setList(l);
                pages.setPageCount(list.getPageCount());
            }
            r = new ModelTemplate<Pages<BankrollApplyResult>>(pages);
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<BankrollApplyResult>>(ex);
        }

        return r;
    }


    @RequestMapping("/getBankrollApplyById")
    public @ResponseBody ModelTemplate<BankrollApplyResult> getBankrollApplyById(String token, long applyId) {
        ModelTemplate<BankrollApplyResult> r = null;
        try {

            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            BankrollApply item = bankrollService.getApplyById(applyId);

            if (item.getApplicant().getId() != userToken.getUser().getId()) {
                throw new ServiceException("您没有权限访问该配资记录");
            }

            BankrollApplyResult result = new BankrollApplyResult();
            BeanUtils.copyProperties(item, result);
            BankrollApplicantResult applicant = new BankrollApplicantResult();
            BeanUtils.copyProperties(item.getApplicant(), applicant);
            applicant.setBalance(item.getApplicant().getUserFund().getBalance());
            result.setApplicant(applicant);
            result.setStatus(item.getStatus().ordinal());
            result.setCycleUnit(item.getCycleUnit().ordinal());
            result.setAddTime(CommonUtil.formatDate(item.getAddTime()));

            if (item.getBankrollRecord() != null) {
                result.setBankrollRecordId(item.getBankrollRecord().getId());
            }

            r = new ModelTemplate<BankrollApplyResult>(result);
        } catch (ServiceException ex) {
            r = new ModelTemplate<BankrollApplyResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<BankrollApplyResult>(ex);
        }
        return r;
    }

    @RequestMapping("/getBankrollRecordList")
    public @ResponseBody ModelTemplate<Pages<BankrollRecordResult>> getBankrollRecordList(String token, int unit, int status, int index, int size) {

        ModelTemplate<Pages<BankrollRecordResult>> r = null;
        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            EnumCycleUnit munit = EnumCycleUnit.parse(unit);
            EnumBankrollRecordStatus mStatus = EnumBankrollRecordStatus.parse(status);
            PageableList<BankrollRecord> p = bankrollService.getBankrollRecordList(munit, mStatus, userToken.getUser().getId(), index, size);

            Pages<BankrollRecordResult> result = new Pages<BankrollRecordResult>();

            BeanUtils.copyProperties(p, result, "list");

            List<BankrollRecordResult> list = new ArrayList<BankrollRecordResult>();

            for (BankrollRecord item: p.getList()) {
                BankrollRecordResult record = new BankrollRecordResult();
                BeanUtils.copyProperties(item, record);
                record.setCycleUnit(item.getCycleUnit().ordinal());
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

            r = new ModelTemplate<Pages<BankrollRecordResult>>(result);
        } catch (ServiceException ex) {
            r = new ModelTemplate<Pages<BankrollRecordResult>>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<BankrollRecordResult>>(ex);
            ex.printStackTrace();
        }

        return r;

    }

    @RequestMapping("/getBankrollRecordById")
    public @ResponseBody ModelTemplate<BankrollRecordResult> getBankrollRecordById(String token, long recordId) {
        ModelTemplate<BankrollRecordResult> r = null;
        try {

            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            BankrollRecord record = bankrollService.getBankrollRecordById(recordId);

            if (record.getApplicant().getId() != userToken.getUser().getId()) {
                throw new ServiceException("您没有权限访问该配资记录");
            }

            BankrollRecordResult result = new BankrollRecordResult();

            BeanUtils.copyProperties(record, result);

            result.setCycleUnit(record.getCycleUnit().ordinal());
            result.setStartDate(CommonUtil.formatDate(record.getStartDate()));
            result.setEndDate(CommonUtil.formatDate(record.getEndDate()));
            result.setUpdateTime(CommonUtil.formatDate(record.getUpdateTime()));
            result.setAddTime(CommonUtil.formatDate(record.getAddTime()));
            BankrollApplicantResult applicant = new BankrollApplicantResult();
            BeanUtils.copyProperties(record.getApplicant(), applicant);
            applicant.setBalance(record.getApplicant().getUserFund().getBalance());
            result.setStatus(record.getStatus().ordinal());
            result.setApplicant(applicant);

            r = new ModelTemplate<BankrollRecordResult>(result);
        } catch (ServiceException ex) {
            r = new ModelTemplate<BankrollRecordResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<BankrollRecordResult>(ex);
        }
        return r;
    }
    
    @RequestMapping("/getBankrollHomsInfo")
    public @ResponseBody ModelTemplate<BankrollHomsInfoResult> getBankrollHomsInfo(String token, long recordId) {
        ModelTemplate<BankrollHomsInfoResult> r = null;
        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            BankrollHomsInfo homsInfo = bankrollService.getBankrollHomsInfo(recordId);
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
    @RequestMapping("/getBankrollApplyStat")
    public @ResponseBody  ModelTemplate<BankrollApplyStatResult> getBankrollApplyStat() {
        ModelTemplate<BankrollApplyStatResult> r = null;
        try {
            BankrollApplyStatDTO applyStat = bankrollService.getBankrollApplyStat();
            BankrollApplyStatResult result = new BankrollApplyStatResult();
            BeanUtils.copyProperties(applyStat, result);
            r = new ModelTemplate<BankrollApplyStatResult>(result);
        } catch (Exception ex) {
            r = new ModelTemplate<BankrollApplyStatResult>(ex);
        }
        return r;
    }


    @Autowired
    public void setBankrollSolutionService(BankrollSolutionService bankrollSolutionService) {
        this.bankrollSolutionService = bankrollSolutionService;
    }

    @Autowired
    public void setBankrollSolutionDayService(BankrollSolutionDayService bankrollSolutionDayService) {
        this.bankrollSolutionDayService = bankrollSolutionDayService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setBankrollService(BankrollService bankrollService) {
        this.bankrollService = bankrollService;
    }
}

package com.goldbao.bankroll.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserFundLog;
import com.goldbao.bankroll.model.user.UserToken;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.ModelTemplate;
import com.goldbao.bankroll.vo.Pages;
import com.goldbao.bankroll.vo.UserFundLogResult;
import com.goldbao.bankroll.vo.UserFundResult;
import com.goldbao.utils.CommonUtil;

// 用户账户
@Controller
@RequestMapping("/userfunds")
public class UserFundsController {

    private UserService userService;

    @RequestMapping("/getUserFund")
    public @ResponseBody ModelTemplate<UserFundResult> getUserFund(String token) {

        ModelTemplate<UserFundResult> r = null;

        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            UserFund userFund = userService.getUserFundByUserId(userToken.getUser().getId());

            UserFundResult result = new UserFundResult();
            BeanUtils.copyProperties(userFund, result);
            result.setUserId(userFund.getUser().getId()+"");
            r = new ModelTemplate<UserFundResult>(result);
        } catch (ServiceException ex) {
            r = new ModelTemplate<UserFundResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<UserFundResult>(ex);
        }

        return r;

    }

    @RequestMapping("/getUserFundLog")
    public @ResponseBody ModelTemplate<Pages<UserFundLogResult>> getUserFundLog(String token, int tradeType, int index, int size) {
        ModelTemplate<Pages<UserFundLogResult>> r = null;

        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            PageableList<UserFundLog> logs = userService.getUserFundLogs(userToken.getUser().getId(), EnumTradeType.parse(tradeType), index, size);
            Pages<UserFundLogResult> pages = new Pages<UserFundLogResult>();
            if (logs.getList() != null && logs.getList().size() > 0) {
                BeanUtils.copyProperties(logs, pages, "list");
                List<UserFundLogResult> rs = new ArrayList<UserFundLogResult>();
                for (UserFundLog log: logs.getList()) {
                    UserFundLogResult l = new UserFundLogResult();
                    BeanUtils.copyProperties(log, l, "payer", "payee");
                    l.setPayeeId(log.getPayee().getId());
                    l.setPayerId(log.getPayer().getId());
                    l.setTradeDirection(log.getTradeDirection().ordinal());
                    l.setTradeType(log.getTradeType().ordinal());
                    l.setAddTime(CommonUtil.formatDate(log.getAddTime()));
                    rs.add(l);
                }
                pages.setList(rs);
                pages.setPageCount(logs.getPageCount());
            }
            r = new ModelTemplate<Pages<UserFundLogResult>>(pages);
        } catch (ServiceException ex) {
            r = new ModelTemplate<Pages<UserFundLogResult>>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<UserFundLogResult>>(ex);
        }
        return r;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

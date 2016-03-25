package com.goldbao.bankroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shuyu.fang
 */
@Controller
public class ApiController {

    @RequestMapping("/")
    public ModelAndView api(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("api/user");
        String baseUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
        String contextPath  = req.getContextPath();
        baseUrl += contextPath;
        mav.addObject("url", baseUrl);
        return mav;
    }
}

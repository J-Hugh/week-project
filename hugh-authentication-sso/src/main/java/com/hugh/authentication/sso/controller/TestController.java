package com.hugh.authentication.sso.controller;

import com.hugh.authentication.core.commons.annotation.EasyValid;
import com.hugh.authentication.sso.biz.User;
import com.hugh.authentication.sso.biz.UserBizService;
import com.hugh.om.menu.annotation.NavLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/sso")
@Slf4j
public class TestController extends BaseController {

    @Autowired
    private UserBizService userBizService;


    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login(@PathParam("returnUrl") String returnUrl, ModelMap modelMap) {
        modelMap.addAttribute("returnUrl", returnUrl);

        return "/login";
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET})
    @NavLocation(selected = "TEST-MENU")
    public String home(ModelMap modelMap) {
        modelMap.put("msg", "test");
        return "/home";
    }

    @RequestMapping(value = "/login", method = { RequestMethod.POST})
    public String loginform(User user, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        String userAccount = request.getParameter("userAccount");
        String password = request.getParameter("password");
        String returnUrl = request.getParameter("returnUrl");
        response.addCookie(new Cookie("123123","123"));
        userBizService.userLogin(user,userAccount, password, "", "");
        modelMap.addAttribute("userAccount", userAccount);
        modelMap.addAttribute("password", password);
        modelMap.addAttribute("returnUrl", returnUrl);
        modelMap.addAttribute("error", "查询用户名密码异常！");

        return "login";
    }

    @RequestMapping(value = "/getMenu", method = { RequestMethod.GET})
    @ResponseBody
    public HttpResponse getMenu() {
        List<String> menus = new ArrayList<>();
        menus.add("00001");
        menus.add("00002");
        return success("success", menus);
    }

    @RequestMapping(value = "rest/login", produces = "application/json")
    @ResponseBody
    public HttpResponse login(User user, HttpServletRequest request, HttpServletResponse response) {
        return success("login ok");
    }
}

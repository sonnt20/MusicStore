package com.btec.store.musicstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private Logger logger = LogManager.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login.page";//Cấu hình trong file tiles.xml (webapp/WEB-INF/tiles.xml)
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        logger.info("/logout");
        try {
//            nếu vẫn còn session thì sẽ xóa đi
            Object session = request.getSession().getAttribute("username");
            if (session != null) {
                request.getSession().removeAttribute("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login.page";//Cấu hình trong file tiles.xml (webapp/WEB-INF/tiles.xml)
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "register.page";//Cấu hình trong file tiles.xml (webapp/WEB-INF/tiles.xml)
    }
}

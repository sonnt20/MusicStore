package com.btec.store.musicstore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {
        return "web.home.page";//Cấu hình trong file tiles.xml (webapp/WEB-INF/tiles.xml)
    }
}

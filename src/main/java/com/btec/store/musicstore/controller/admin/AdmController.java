package com.btec.store.musicstore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class AdmController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {
        return "admin.home.page";//Cấu hình trong file tiles.xml (webapp/WEB-INF/tiles.xml)
    }

    @RequestMapping(value = "/song", method = RequestMethod.GET)
    public String songManage(HttpServletRequest request) {
        return "admin.song.page";//Cấu hình trong file tiles.xml (webapp/WEB-INF/tiles.xml)
    }

    @RequestMapping(value = "/album", method = RequestMethod.GET)
    public String albumManage(HttpServletRequest request) {
        return "admin.album.page";//Cấu hình trong file tiles.xml (webapp/WEB-INF/tiles.xml)
    }
}

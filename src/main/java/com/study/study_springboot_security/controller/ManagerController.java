package com.study.study_springboot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagerController {

    @GetMapping({ "/admin/*" }) /* admin폴더 안의 모든 url. ROLE_ADMIN 만 접근가능 */
    public ModelAndView admin(ModelAndView modelAndView) {
        String viewName = "/WEB-INF/views/admin/read.jsp";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @GetMapping({ "/manager/*" }) /* manager폴더 안의 모든 url. ROLE_MANAGER or ROLE_ADMIN 접근가능 */
    public ModelAndView manager(ModelAndView modelAndView) {
        String viewName = "/WEB-INF/views/manager/read.jsp";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}

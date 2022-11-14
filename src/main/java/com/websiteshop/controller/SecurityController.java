package com.websiteshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.websiteshop.model.AccountDto;

@Controller
public class SecurityController {
    @RequestMapping("/security/login/form")
    public String loginPage(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập!");
        return "security/login";
    }

    @RequestMapping("/security/login/success")
    public ModelAndView loginSuccess(ModelMap model) {
        model.addAttribute("message", "Đã đăng nhập!");
        return new ModelAndView("forward:/", model);
    }
    
   
    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng!");
        return "security/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Tài khoản không có quyền truy xuất!");
        return "security/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoff(Model model) {
        return "security/login";
    }

    @RequestMapping("/security/register")
    public String register(Model model) {
    	AccountDto dto = new AccountDto();
		dto.setIsEdit(false);
		model.addAttribute("account", dto);
        return "/admin/dist/register";
    }

    @RequestMapping("/security/change")
    public String change() {
        return "/security/change";
    }

    @RequestMapping("/security/forgotPassword")
    public String forgot() {
        return "/admin/dist/forgotPassword";
    }

    @RequestMapping("/security/resetPassword")
    public String resetPass() {
        return "/admin/dist/resetPassword";
    }

    @RequestMapping("/security/statitic")
    public String statitic() {
        return "/admin/dist/statitic";
    }

}

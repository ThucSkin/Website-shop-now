package com.websiteshop.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websiteshop.entity.Authority;
import com.websiteshop.service.AuthorityService;

import java.util.List;
import java.util.Optional;

@Controller
public class authorityController {
    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/admin/authority")
    public String index() {
        return "/admin/admin";
    }

    @RequestMapping("/authority")
    public String a(Model model) {
        List<Authority> list = authorityService.findAll();
        model.addAttribute("autho", list);
        return "/admin/authority/index";
    }

    // @RequestMapping("/authority/delete/{id}")
    // public String delete(Model model,
    // @PathVariable("id") Integer id) {
    // Authority au = authorityService.delete(id);
    // return "/admin/authority/index";
    // }

    @RequestMapping("/authority/list")
    public String findAll(@RequestParam("admin") Optional<Boolean> admin,
            Model model) {
        if (admin.orElse(false)) {
            List<Authority> list = authorityService.findAuthoritiesOfAdministrators();
        }
        List<Authority> list = authorityService.findAll();
        model.addAttribute("author", list);
        return "/admin/authority/index";
    }

    @RequestMapping("/admin/authority/unauthorized")
    public String unauthorized() {
        return "/admin/authority/unauthorized";
    }

}

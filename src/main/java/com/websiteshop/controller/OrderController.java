package com.websiteshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.service.OrderService;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/order/checkout")
    public String checkout() {
        return "order/checkout";

    }

    @RequestMapping("/order/list")
    public String list(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderService.findByUsername(username));
        return "order/list";
    }

    @RequestMapping("/order/detail/{orderId}")
    public String detail(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "order/detail";
    }

    // @RequestMapping("/order/delete/{orderId}")
    // public String del(@PathVariable("orderId") Long orderId, Model model) {
    // model.addAttribute("order", orderService.deleteById(orderId));
    // return "order/detail";
    // }

}

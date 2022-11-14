package com.websiteshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websiteshop.dao.CategoryDAO;
import com.websiteshop.entity.Product;
import com.websiteshop.service.ProductService;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryDAO dao;

    @RequestMapping("/product/list")
    public String list(Model model, @RequestParam("cid") Optional<Long> cid) {
        if (cid.isPresent()) {
            List<Product> list = productService.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        } else {
            List<Product> list = productService.findAll();
            model.addAttribute("items", list);
        }
        return "product/list";
    }

    @RequestMapping("/product/detail/{productId}")
    public String detail(Model model, @PathVariable("productId") Long id) {
        Product item = productService.findById(id).get();
        model.addAttribute("item", item);
        model.addAttribute("cates", dao.findAll());
        return "product/detail";
    }
    
    @GetMapping("/product/search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Product> list = null;

		if (StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name);
		} else {
			list = productService.findAll();
		}
		model.addAttribute("items", list);
		return "product/list";
	}


}

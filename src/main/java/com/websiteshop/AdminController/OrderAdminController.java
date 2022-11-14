package com.websiteshop.AdminController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.websiteshop.entity.Order;
import com.websiteshop.model.AccountDto;
import com.websiteshop.model.OrderDto;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.OrderService;

@Controller
@RequestMapping("admin/orders")
public class OrderAdminController {
	@Autowired
	OrderService orderService;

	@Autowired
	AccountService accountService;

	@ModelAttribute("accounts")
	public List<AccountDto> getAccounts() {
		return accountService.findAll().stream().map(item -> {
			AccountDto dto = new AccountDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@RequestMapping("")
	public String list(Model model) {
		List<Order> list = orderService.findAll();
		model.addAttribute("orders", list);
		return "admin/orders/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		OrderDto dto = new OrderDto();
		dto.setIsEdit(false);
		model.addAttribute("order", dto);
		return "admin/orders/addOrEdit";
	}

	@GetMapping("edit/{orderId}")
	public ModelAndView edit(ModelMap model, @PathVariable("orderId") Long orderId) {

		Optional<Order> opt = orderService.findByIdd(orderId);
		OrderDto dto = new OrderDto();

		if (opt.isPresent()) {
			Order entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("order", dto);
			return new ModelAndView("admin/orders/addOrEdit", model);
		}

		model.addAttribute("message", "Order is not existed");

		return new ModelAndView("redirect:/admin/orders", model);
	}

	@GetMapping("reset")
	public String createOrder(Model model) {
		OrderDto p = new OrderDto();
		model.addAttribute("order", p);

		return "admin/orders/addOrEdit";

	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@ModelAttribute("order") OrderDto dto, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("admin/orders/addOrEdit");
		}
		Order entity = new Order();
		BeanUtils.copyProperties(dto, entity);

		orderService.save(entity);
		model.addAttribute("message", "Order is saved!");
		return new ModelAndView("forward:/admin/orders", model);
	}

	@GetMapping("delete/{orderId}")
	public ModelAndView delete(ModelMap model, @PathVariable("orderId") Long orderId) throws IOException {

		Optional<Order> opt = orderService.findByIdd(orderId);

		if (opt.isPresent()) {
			orderService.delete(opt.get());
			model.addAttribute("message", "Order is deleted!");
		} else {
			model.addAttribute("message", "Order is not Found!");
		}

		return new ModelAndView("forward:/admin/orders", model);
	}

}

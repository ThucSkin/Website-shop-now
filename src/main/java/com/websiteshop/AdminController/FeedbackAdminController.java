package com.websiteshop.AdminController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;

import com.websiteshop.entity.Feedback;
import com.websiteshop.model.AccountDto;
import com.websiteshop.model.CategoryDto;
import com.websiteshop.model.FeedbackDto;
import com.websiteshop.model.ProductDto;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.FeedbackService;
import com.websiteshop.service.ProductService;

@Controller
@RequestMapping("admin/feedbacks")
public class FeedbackAdminController {
	@Autowired
	FeedbackService feedbackService;

	@Autowired
	AccountService accountService;

	@Autowired
	ProductService productService;

	@ModelAttribute("accounts")
	public List<AccountDto> getAccounts() {
		return accountService.findAll().stream().map(item -> {
			AccountDto dto = new AccountDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@ModelAttribute("products")
	public List<ProductDto> getCategories() {
		return productService.findAll().stream().map(item -> {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@RequestMapping("")
	public String list(Model model) {
		List<Feedback> list = feedbackService.findAll();
		model.addAttribute("feedbacks", list);
		return "admin/feedbacks/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		FeedbackDto dto = new FeedbackDto();
		dto.setIsEdit(false);
		model.addAttribute("feedback", dto);
		return "admin/feedbacks/addOrEdit";
	}

	@GetMapping("edit/{feedbackId}")
	public ModelAndView edit(ModelMap model, @PathVariable("feedbackId") Long feedbackId) {

		Optional<Feedback> opt = feedbackService.findById(feedbackId);
		FeedbackDto dto = new FeedbackDto();

		if (opt.isPresent()) {
			Feedback entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("feedback", dto);
			return new ModelAndView("admin/feedbacks/addOrEdit", model);
		}

		model.addAttribute("message", "Feedback is not existed");

		return new ModelAndView("redirect:/admin/feedbacks", model);
	}

	@GetMapping("reset")
	public String createFeedback(Model model) {
		FeedbackDto p = new FeedbackDto();
		model.addAttribute("feedback", p);

		return "admin/feedbacks/addOrEdit";

	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@ModelAttribute("feedback") FeedbackDto dto, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("admin/feedbacks/addOrEdit");
		}
		Feedback entity = new Feedback();
		BeanUtils.copyProperties(dto, entity);

		feedbackService.save(entity);
		model.addAttribute("message", "Feedback is saved!");
		return new ModelAndView("forward:/admin/feedbacks", model);
	}

	@GetMapping("delete/{feedbackId}")
	public ModelAndView delete(ModelMap model, @PathVariable("feedbackId") Long feedbackId) throws IOException {

		Optional<Feedback> opt = feedbackService.findById(feedbackId);

		if (opt.isPresent()) {
			feedbackService.delete(opt.get());
			model.addAttribute("message", "Feedback is deleted!");
		} else {
			model.addAttribute("message", "Feedback is not Found!");
		}

		return new ModelAndView("forward:/admin/feedbacks", model);
	}
	// @GetMapping("search")
	// public String search(ModelMap model,
	// @RequestParam(name = "name", required = false) String name) {
	//
	// List<Feedback> list = null;
	// if(StringUtils.hasText(name)) {
	// list = feedbackService.findByUsernameContaining(name);
	// }
	// else {
	// list = feedbackService.findAll();
	// }
	//
	// model.addAttribute("feedbacks", list);
	// return "admin/feedbacks/list";
	// }

}

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

import com.websiteshop.entity.Account;
import com.websiteshop.model.AccountDto;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.StorageService;

@Controller
@RequestMapping("admin/accounts")
public class AccountAdminController {
	@Autowired
	AccountService accountService;

	@Autowired
	StorageService storageService;

	@RequestMapping("")
	public String list(Model model) {
		List<Account> list = accountService.findAll();
		model.addAttribute("accounts", list);
		return "admin/accounts/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		AccountDto dto = new AccountDto();
		dto.setIsEdit(false);
		model.addAttribute("account", dto);
		return "admin/accounts/addOrEdit";
	}

	@GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {

		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();

		if (opt.isPresent()) {
			Account entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("account", dto);
			return new ModelAndView("admin/accounts/addOrEdit", model);
		}

		model.addAttribute("message", "Account is not existed");

		return new ModelAndView("forward:/admin/accounts", model);
	}

	@GetMapping("reset")
	public String createAccount(Model model) {
		AccountDto p = new AccountDto();
		model.addAttribute("account", p);

		return "admin/accounts/addOrEdit";

	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@ModelAttribute("account") AccountDto dto, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);

		if (!dto.getImageFile().isEmpty()) {
			entity.setImage(storageService.getStoredFilename(dto.getImageFile(), null));
			storageService.store(dto.getImageFile(), entity.getImage());
		}

		accountService.save(entity);
		model.addAttribute("message", "Account is saved!");
		return new ModelAndView("forward:/admin/accounts", model);
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("delete/{username}")
	public ModelAndView delete(ModelMap model, @PathVariable("username") String username) throws IOException {
		Optional<Account> opt = accountService.findById(username);
		if (opt.isPresent()) {
			if (!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			accountService.delete(opt.get());
			model.addAttribute("message", "Account is deleted!");
		} else {
			model.addAttribute("message", "Account is not Found!");
		}
		return new ModelAndView("forward:/admin/accounts", model);
	}

	@GetMapping("search")
	public String search(ModelMap model,
			@RequestParam(name = "username", required = false) String username) {

		List<Account> list = null;
		if (StringUtils.hasText(username)) {
			list = accountService.findByUsernameContaining(username);
		} else {
			list = accountService.findAll();
		}

		model.addAttribute("accounts", list);
		return "admin/accounts/list";
	}

}

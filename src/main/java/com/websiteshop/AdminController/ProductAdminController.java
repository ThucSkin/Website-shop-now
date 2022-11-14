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

import com.websiteshop.entity.Category;
import com.websiteshop.entity.Product;
import com.websiteshop.model.CategoryDto;
import com.websiteshop.model.ProductDto;
import com.websiteshop.service.CategoryService;
import com.websiteshop.service.ProductService;
import com.websiteshop.service.StorageService;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StorageService storageService;

    @ModelAttribute("categories")
    public List<CategoryDto> getCategories() {
        return categoryService.findAll().stream().map(item -> {
            CategoryDto dto = new CategoryDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }

    @GetMapping("list")
    public String list(Model model) {
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "admin/product/list";
    }

    @RequestMapping("")
    public String list_2(ModelMap model, @RequestParam("cid") Optional<Long> cid) {
        if (cid.isPresent()) {
            List<Product> list = productService.findByCategoryId(cid.get());
            model.addAttribute("products", list);
        } else {
            List<Product> list = productService.findAll();
            model.addAttribute("products", list);
        }

        return "admin/product/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        ProductDto dto = new ProductDto();
        dto.setIsEdit(false);
        model.addAttribute("product", dto);
        return "admin/product/addOrEdit";
    }

    @GetMapping("edit/{productId}")
    public ModelAndView edit(ModelMap model,
            @PathVariable("productId") Long productId) {
        Optional<Product> opt = productService.findById(productId);
        ProductDto dto = new ProductDto();
        if (opt.isPresent()) {
            Product entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            dto.setCategoryId(entity.getCategory().getCategoryId());
            model.addAttribute("product", dto);
            return new ModelAndView("admin/product/addOrEdit", model);
        } else if (!opt.isPresent()) {
            return new ModelAndView("/admin/dist/404", model);
        }

        return new ModelAndView("redirect:/admin/product/list", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
            @ModelAttribute("product") ProductDto dto,
            BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("/admin/dist/404");
        }
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);

        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        entity.setCategory(category);

        if (!dto.getImage1File().isEmpty()) {
            // UUID uuid = UUID.randomUUID();
            // String uuString = uuid.toString();
            entity.setImage1(storageService.getStoredFilename(dto.getImage1File(),
                    dto.getImage1File().getOriginalFilename()));
            storageService.store(dto.getImage1File(), entity.getImage1());

        }
        productService.save(entity);
        model.addAttribute("message", "Product is saved!");
        return new ModelAndView("forward:/admin/product", model);
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("delete/{productId}")
    public ModelAndView delete(ModelMap model, @PathVariable("productId") Long productId) throws IOException {

        Optional<Product> opt = productService.findById(productId);

        if (opt.isPresent()) {
            if (!StringUtils.isEmpty(opt.get().getImage1())) {
                storageService.delete(opt.get().getImage1());
            }
            productService.delete(opt.get());
            model.addAttribute("message", "Product is deleted!");
        } else if (!opt.isPresent()) {
            return new ModelAndView("/admin/dist/404", model);
        }

        return new ModelAndView("forward:/admin/product/list", model);
    }

}

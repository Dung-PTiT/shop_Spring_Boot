package com.trongdung.website.controller.AdminController;

import com.trongdung.website.model.CategoryDTO;
import com.trongdung.website.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String cate() {
        categoryService.delete(categoryService.getById(1));
        return "user";
    }

    @GetMapping("/category/list")
    public String listCategory(Model model) {
        List<CategoryDTO> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "admin/category/listCategory";
    }

    @GetMapping("/category/add")
    public String addCategory(Model model) {
        model.addAttribute("newCategory", new CategoryDTO());
        return "admin/category/addCategory";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute("newCateogry") CategoryDTO categoryDTO, BindingResult bindingResult) {

        if(categoryDTO.getName().isEmpty()){
            bindingResult.rejectValue("name","error.empty");
        }else if(bindingResult.hasErrors()){
            return "redirect: /admin/category/add";
        }else{
            categoryService.add(categoryDTO);
        }
        return "redirect:/admin/category/list";
    }

    @GetMapping("/category/{id}/edit")
    public String editCategory(@PathVariable("id") int id, Model model) {
        CategoryDTO categoryDTO = categoryService.getById(id);
        model.addAttribute("categoryEdit", categoryDTO);
        return "admin/category/editCategory";
    }

    @PostMapping("/category/{id}/edit")
    public String editCategory(@ModelAttribute("categoryEdit") CategoryDTO categoryDTO) {
        categoryService.edit(categoryDTO);
        return "redirect:/admin/category/list";
    }

    @GetMapping("/category/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id, CategoryDTO categoryDTO) {
        categoryDTO = categoryService.getById(id);
        categoryService.delete(categoryDTO);
        return "redirect:/admin/category/list";
    }

    @GetMapping("/category/searchByName")
    public String searchByName(@RequestParam(name = "name", required = false) String name, Model model) {
        CategoryDTO categoryDTO = categoryService.getByName(name);
        model.addAttribute("categories", categoryDTO);
        return "admin/category/listCategory";
    }
}

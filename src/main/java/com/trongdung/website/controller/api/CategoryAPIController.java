package com.trongdung.website.controller.api;


import com.trongdung.website.model.CategoryDTO;
import com.trongdung.website.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CategoryAPIController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category-list")
    public @ResponseBody
    List<CategoryDTO> categories() {
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public @ResponseBody
    CategoryDTO getCategory(@PathVariable("id") int id) {
        return categoryService.getById(id);
    }

    @PostMapping("/category/add")
    @ResponseBody
    public String addCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.add(categoryDTO);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @PostMapping("/category/edit")
    public @ResponseBody
    String editCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.edit(categoryDTO);
            return "Successfully";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @GetMapping("/category/{id}/delete")
    public @ResponseBody
    String deleteCategory(@PathVariable("id") int id) {
        try {
            CategoryDTO categoryDTO = categoryService.getById(id);
            categoryService.delete(categoryDTO);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }
}

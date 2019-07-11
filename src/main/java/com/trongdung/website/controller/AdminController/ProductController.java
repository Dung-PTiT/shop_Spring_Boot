package com.trongdung.website.controller.AdminController;

import com.trongdung.website.model.CategoryDTO;
import com.trongdung.website.model.ProductDTO;
import com.trongdung.website.service.CategoryService;
import com.trongdung.website.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        List<CategoryDTO> categoryDTOS = categoryService.getAll();
        model.addAttribute("categories", categoryDTOS);
        return "admin/product/addProduct";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO) {
        if (productDTO.getProductFile() != null && !productDTO.getProductFile().isEmpty()) {
            try {
                final String UPLOAD_FOLDER = "D:\\product\\img";
                String image = System.currentTimeMillis() + ".jpg";
                Path pathAvatar = Paths.get(UPLOAD_FOLDER + File.separator + image);
                Files.write(pathAvatar, productDTO.getProductFile().getBytes());
                productDTO.setProductFileName(image);
                int productId = productService.add(productDTO);
            } catch (Exception e) {
                System.out.println("Loi: " + e);
            }
        }
        return "redirect:/admin/product/list";
    }
    @GetMapping("/product/{id}/edit")
    public String editProduct(@PathVariable("id") int id, Model model){
        ProductDTO productDTO = productService.getById(id);
        model.addAttribute("productEdit",productDTO);
        List<CategoryDTO> categoryDTOS = categoryService.getAll();
        model.addAttribute("categories", categoryDTOS);
        return "admin/product/editProduct";
    }
    @PostMapping("/product/{id}/edit")
    public String editProduct(@ModelAttribute("productEdit") ProductDTO productDTO){
        productService.edit(productDTO);
        return "redirect:/admin/product/list";
    }


    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id, ProductDTO productDTO){
        productDTO = productService.getById(id);
        productService.delete(productDTO);
        return "redirect:/admin/product/list";
    }
    @GetMapping("/product/list")
    public String listProduct(Model model) {
        List<ProductDTO> listProducts = productService.getAll();
        model.addAttribute("products", listProducts);
        return "admin/product/productList";
    }

    @GetMapping("/product/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<ProductDTO> productDTOS = productService.getByName(name);
        model.addAttribute("products", productDTOS);
        return "admin/product/productList";
    }

}

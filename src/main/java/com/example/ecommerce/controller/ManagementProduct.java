package com.example.ecommerce.controller;

import com.example.ecommerce.bean.CreatProd;
import com.example.ecommerce.domain.UpSertProduct;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("ManagementProd")
public class ManagementProduct {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showProductList(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 12;

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<Product> productPage = productService.getAllProducts(pageable);

        model.addAttribute("product", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "managementProd";
    }
    @GetMapping("/create")
    public String showCreatePage(Model model){
        CreatProd creatProd = new CreatProd();
        model.addAttribute("creat", creatProd);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "createProduct";
    }
    @PostMapping("/create")
    public String createProduct(@ModelAttribute UpSertProduct product) throws IOException {
        productService.createProd(product);
        return "redirect:/ManagementProd";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductByID(id);
        model.addAttribute("product", product);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "editProd";
    }

    @PostMapping("/update/{id}")
    public String updateProd(@PathVariable("id") Long id, @ModelAttribute UpSertProduct product) throws IOException {
        productService.updateProd(id, product);
        return "redirect:/ManagementProd";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/ManagementProd";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("product", products);
        return "managementProd";
    }
}

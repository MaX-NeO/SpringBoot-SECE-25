package com.store.app.ThymeLeaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.app.Models.Product;
import com.store.app.Services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductView {

    @Autowired
    private ProductService productService;


    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.GetProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form"; 
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product newproduct) {
        productService.AddProduct(newproduct);
        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product existingProduct = productService.GetProducts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("product", existingProduct);
        return "product-form";
    }

    // Handle edit form submission
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute("product") Product updateproduct) {
        productService.EditProduct(id, updateproduct);
        return "redirect:/products";
    }

    // Handle delete
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.DeleteProduct(id);
        return "redirect:/products";
    }
}

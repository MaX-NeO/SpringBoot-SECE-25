package com.max.test3.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max.test3.Models.Product;
import com.max.test3.Services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService; 

    @GetMapping("/all")
    public List<Product> getAllProducts (){
        return productService.GetAllProducts();
    }
    @GetMapping("/product/{id}")
    public Optional<Product> getProductByID(@PathVariable Long id){
        return productService.GetProductByID(id);
    }
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.AddProduct(product);
    }
    @PutMapping("/edit/{id}")
    public Product editProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.EditProduct(id, product);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.DeleteProduct(id);
    }
}


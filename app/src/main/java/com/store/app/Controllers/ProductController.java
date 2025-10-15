package com.store.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.app.Models.Product;
import com.store.app.Services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getProducts(){
        return productService.GetProducts();
    }
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product newproduct){
        return productService.AddProduct(newproduct);
    }
    @PutMapping("/edit/{id}")
    public Product editProduct(@PathVariable Long id, @RequestBody Product updateproduct){
        return productService.EditProduct(id, updateproduct);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.DeleteProduct(id);
    }
}

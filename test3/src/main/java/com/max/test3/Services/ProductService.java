package com.max.test3.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.max.test3.Models.Product;
import com.max.test3.Repos.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> GetAllProducts() {
        return productRepo.findAll();
    }
    public Optional<Product> GetProductByID(Long id){
        Optional<Product> findproduct = productRepo.findById(id);
        return findproduct;
    }
    public Product AddProduct (Product newproduct){
        return productRepo.save(newproduct);
    }
    public Product EditProduct(Long id, Product newproduct){
        Product findproduct = productRepo.findById(id).orElse(null);
        if(findproduct !=null){
            findproduct.setTitle(newproduct.getTitle());
            findproduct.setDescription(newproduct.getDescription());
            findproduct.setPrice(newproduct.getPrice());
            findproduct.setQuantity(newproduct.getQuantity());
            return productRepo.saveAndFlush(findproduct);
        }
        return null;
    }
    public String DeleteProduct(Long id){
         Product findproduct = productRepo.findById(id).orElse(null);
         if(findproduct !=null){
            productRepo.deleteById(id);
            return findproduct.getTitle() + " Deleted !";
         }{
             return "Product Not Fouud";
         }
    }
}

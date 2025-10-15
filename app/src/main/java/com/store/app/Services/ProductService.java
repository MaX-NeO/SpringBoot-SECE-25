package com.store.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.app.Models.Product;
import com.store.app.Repos.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    //list all available products inside my app
    public List<Product> GetProducts (){
        return productRepo.findAll();
    } 

    // addming new product inside table (insert query)
    public Product AddProduct(Product newproduct){
        return productRepo.save(newproduct);
    }

    //find and edit the product
    public Product EditProduct (Long id, Product updateproduct){
        Product oldproduct = productRepo.findById(id).orElse(null);
        if(oldproduct != null){
            oldproduct.setName(updateproduct.getName());
            oldproduct.setDescription(updateproduct.getDescription());
            oldproduct.setPrice(updateproduct.getPrice());
            oldproduct.setQuantity(updateproduct.getQuantity());
            return productRepo.saveAndFlush(oldproduct);
        }
      
        return null;
    }
    //find and delete product
    public String DeleteProduct(Long id){
        Product foundproduct = productRepo.findById(id).orElse(null);
        if(foundproduct !=null){
            productRepo.deleteById(id);
            return foundproduct.getName() +" Delted !";
        }
        else{
            return "Product not found !";
        }
    }
}
   
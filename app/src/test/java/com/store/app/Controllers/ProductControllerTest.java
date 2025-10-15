package com.store.app.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.app.Models.Product;
import com.store.app.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProducts() throws Exception {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Laptop");
        p1.setDescription("Gaming laptop");
        p1.setPrice(1200);
        p1.setQuantity(5);

        List<Product> products = Arrays.asList(p1);
        when(productService.GetProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].price").value(1200))
                .andExpect(jsonPath("$[0].quantity").value(5));

        verify(productService, times(1)).GetProducts();
    }

    @Test
    void testAddProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Phone");
        newProduct.setDescription("Android");
        newProduct.setPrice(499);
        newProduct.setQuantity(10);

        when(productService.AddProduct(any(Product.class))).thenReturn(newProduct);

        mockMvc.perform(post("/api/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"))
                .andExpect(jsonPath("$.price").value(499));

        verify(productService, times(1)).AddProduct(any(Product.class));
    }

    @Test
    void testEditProduct() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Laptop");
        updatedProduct.setDescription("Updated Desc");
        updatedProduct.setPrice(1300);
        updatedProduct.setQuantity(7);

        when(productService.EditProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Laptop"))
                .andExpect(jsonPath("$.price").value(1300));

        verify(productService, times(1)).EditProduct(eq(1L), any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        when(productService.DeleteProduct(1L)).thenReturn("Laptop Deleted !");

        mockMvc.perform(delete("/api/products/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Laptop Deleted !"));

        verify(productService, times(1)).DeleteProduct(1L);
    }
}

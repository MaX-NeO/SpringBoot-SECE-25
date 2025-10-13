package com.example.test2.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Endpoint -> "/hello" return -> "Hello from Spring Server !!" 
    @GetMapping("/hello")
    public String HelloResponse(){
        return "Hello from Spring Server !!";
    }


    // Endpoint -> "/name" return -> "mohanraj" 
    // Endpoint -> "/rollnumber" return -> "1234567" 
}

package com.example.test2.Controllers2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {

    @GetMapping("/hi")
    public String H2(){
        return "Hi";
    }
}

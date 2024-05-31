package id.beni.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class WelcomeController {
    
    @GetMapping
    public String welcome(){
        return "Welcome to Spring Boot REST API";
    }
}

package cz.ok1xoe.crypto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {

    @GetMapping("/")
    public String getHello(){
        return "Hello World";
    }
    @GetMapping("/portfolio")
    public String getPortfolio(){
        return "Portfolio";
    }
}

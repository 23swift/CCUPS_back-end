package com.clg.ccupsbackend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GreetingController
 */
@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class GreetingController {

    @RequestMapping("/greeting")
    public String Greeting(){

        return "Helo world";
    }
    
}
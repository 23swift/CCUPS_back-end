package com.clg.ccupsbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController
 */
@Controller
public class HomeController {
    @GetMapping("/home")
    public String Index(){


        return "Index";
    }

    
}
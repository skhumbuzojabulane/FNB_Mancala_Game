package com.mancala.mancala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mancala.mancala.util.GameConstants;

@Controller
public class ViewController {
    
    @GetMapping("/")
    public String main(Model model) {
        return GameConstants.HOME_TEMPLATE;
    }
    
    @GetMapping("/error")
    public String handleError() {
        // Handle error and return error view
        return "error";
    }
}

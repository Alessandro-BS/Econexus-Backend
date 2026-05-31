package com.econexus.backend.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Hidden
@Controller
public class RootController {

    @GetMapping("/")
    public String redirectRootToSwagger() {
        return "redirect:/swagger-ui.html";
    }
}

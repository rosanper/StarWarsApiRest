package com.letscode.starwarsapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WellcomeController {

    @GetMapping
    public String wellcomeMensage(){
        return "Wellcome to Star Wars API REST";
    }
}

package com.foyhWebAppApi.WebAppService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class controllerWebApp {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/get")
    public String get(){
        return "get";
    }

}

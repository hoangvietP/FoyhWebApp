package com.foyhWebAppApi.WebAppService.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class controllerWebApp {
    @GetMapping("/home")
    public String home(){
        return "index";
    }
    @GetMapping("/sigin")
    public String sigin(){
        return "sigin";
    }

    @GetMapping("/single")
    public String single(){
        return "single";
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

}

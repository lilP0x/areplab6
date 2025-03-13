package com.example.areplab6.controller;


import com.example.areplab6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password) {


        return "Bienvenido señor" + " " + username + " " + "te apareceria jugar una partida de villanos?";
    }

}

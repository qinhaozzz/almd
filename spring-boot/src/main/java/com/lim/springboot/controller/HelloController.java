package com.lim.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinhao
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "username") String username) {
        return "Hello Spring Boot! by " + username + ".";
    }
}

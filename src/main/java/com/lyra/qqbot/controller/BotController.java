package com.lyra.qqbot.controller;

import com.lyra.qqbot.entity.ListenerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class BotController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public void listener(@RequestBody ListenerEntity listenerBody) {

        System.out.println(listenerBody);
    }

}

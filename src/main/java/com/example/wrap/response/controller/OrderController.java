package com.example.wrap.response.controller;

import com.example.wrap.response.model.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            "application/vnd.example.wrapped+json"
    })
    public Order getOrder() {
        return new Order("1");
    }
}

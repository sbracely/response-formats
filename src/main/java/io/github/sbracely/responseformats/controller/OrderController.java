package io.github.sbracely.responseformats.controller;

import io.github.sbracely.responseformats.media.MediaTypes;
import io.github.sbracely.responseformats.model.Order;
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
            MediaTypes.WRAPPED_JSON_VALUE,
            MediaTypes.NON_NULL_JSON_VALUE

    })
    public Order getOrder() {
        Order order = new Order();
        order.setId("1");
        return order;
    }
}

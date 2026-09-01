package com.example.wrap.response.entity;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "OrderValue", namespace = "OrderNamespace")
public class Order {

    private String id;

    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

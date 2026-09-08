package io.github.sbracely.responseformats.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "OrderValue", namespace = "OrderNamespace")
public class Order {

    private String id;
    private String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

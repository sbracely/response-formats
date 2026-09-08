package com.example.wrap.response.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;


@SpringBootTest
@AutoConfigureWebTestClient
class OrderControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getOrderReturnsJson() {
        webTestClient.get()
                .uri("/order")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .consumeWith(responseBody -> {
                    String json = null;
                    if (responseBody.getResponseBody() != null) {
                        json = new String(responseBody.getResponseBody(), StandardCharsets.UTF_8);
                    }
                    IO.println(json);
                })
                .json("""
                        {
                          "id": "1"
                        }
                        """);
    }

    @Test
    void getOrderReturnsXml() {
        webTestClient.get()
                .uri("/order")
                .accept(MediaType.APPLICATION_XML)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_XML)
                .expectBody()
                .xml("""
                        <OrderValue xmlns="OrderNamespace">
                            <customerId xmlns="" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/>
                            <id xmlns="">1</id>
                        </OrderValue>
                        """);
    }

    @Test
    void getOrderReturnsWrappedJson() {
        webTestClient.get()
                .uri("/order")
                .accept(MediaType.valueOf("application/vnd.example.wrapped+json"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.valueOf("application/vnd.example.wrapped+json"))
                .expectBody()
                .json("""
                        {
                          "OrderValue": {
                            "id": "1"
                          }
                        }
                        """);
    }
}

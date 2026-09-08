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
                .consumeWith(entityExchangeResult -> {
                    byte[] responseBody = entityExchangeResult.getResponseBody();
                    String json = null;
                    if (responseBody != null) {
                        json = new String(responseBody, StandardCharsets.UTF_8);
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
                .consumeWith(entityExchangeResult -> {
                    byte[] responseBody = entityExchangeResult.getResponseBody();
                    String xml = null;
                    if (responseBody != null) {
                        xml = new String(responseBody, StandardCharsets.UTF_8);
                    }
                    IO.println(xml);
                })
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
                .consumeWith(entityExchangeResult -> {
                    byte[] responseBody = entityExchangeResult.getResponseBody();
                    String wrappedJson = null;
                    if (responseBody != null) {
                        wrappedJson = new String(responseBody, StandardCharsets.UTF_8);
                    }
                    IO.println(wrappedJson);
                })
                .json("""
                        {
                          "OrderValue": {
                            "id": "1"
                          }
                        }
                        """);
    }
}

package com.example.wrap.response.controller;

import com.example.wrap.response.config.WrappedJsonConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;


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

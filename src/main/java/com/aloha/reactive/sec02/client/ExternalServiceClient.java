package com.aloha.reactive.sec02.client;

import com.aloha.reactive.common.AbstractHttpClient;

import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public ExternalServiceClient() {
        super();
    }

    public Mono<String> getProductName(int productId) {
        return httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent().asString().next();
    }

}

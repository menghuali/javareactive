package com.aloha.reactive.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Demonstrates how to subscribe to a Mono in Reactor.
 * This example shows how to handle the emitted item, errors, and completion signal.
 */
@Slf4j
public class MonoSubscribe {

    public static void main(String[] args) {
        var mono = Mono.just("Hello, Reactive World!")
            .map(item -> {
                log.info("Processing item: {}", item);
                return item.toUpperCase(); // Transform the item to uppercase
            });
        mono.subscribe(
            item -> log.info("Received: {}", item),
            error -> log.error("Error occurred: {}", error.getMessage()),
            () -> log.info("Mono completed successfully")
        );
    }

}

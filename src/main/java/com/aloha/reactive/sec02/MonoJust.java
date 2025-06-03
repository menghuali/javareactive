package com.aloha.reactive.sec02;

import com.aloha.reactive.sec01.subscriber.SubscriberImpl;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Demonstrates the usage of Mono.just to create a Mono that emits a single item.
 * This is a simple example of how to use Reactor's Mono type.
 */
@Slf4j
public class MonoJust {

    public static void main(String[] args) {
        // Example usage of Mono.just
        var mono = Mono.just("Hello, Reactive World!");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();
    }
}

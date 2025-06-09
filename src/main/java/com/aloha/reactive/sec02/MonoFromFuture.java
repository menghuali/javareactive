package com.aloha.reactive.sec02;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import com.aloha.reactive.common.DefaultSubscriber;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoFromFuture {

    private final static Faker faker = Faker.instance();

    public static void main(String[] args) throws InterruptedException {
        // Demonstrating the use of Mono.fromFuture with a CompletableFuture
        // The supplier method getName() is called immediately even if there is no
        // subscriber.
        log.info("== Supplier method is called even if there is no subscriber ==");
        CompletableFuture<String> future = getName(); // This will execute immediately
        Mono<String> mono1 = Mono.fromFuture(future);
        mono1.subscribe(DefaultSubscriber.create());
        Thread.sleep(Duration.ofSeconds(2));

        // Demonstrating the use of Mono.fromFuture with a method reference.
        // The supplier method getName() won't be called until there is a subscriber.
        log.info("\n\n== Supplier won't be called until there is a subscriber ==");
        // This will not execute immediately, it will wait for the subscriber.
        // The method reference MonoFromFuture::getName actually return a Supplier
        // that will be executed when the Mono is subscribed to.
        // This is a lazy evaluation.
        Mono<String> mono2 = Mono.fromFuture(MonoFromFuture::getName);
        mono2.subscribe(DefaultSubscriber.create());
        Thread.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Generating a random name");
            try {
                Thread.sleep(1000); // Simulate a delay
            } catch (InterruptedException e) {
                log.error("Error while generating name", e);
            }
            String name = faker.name().fullName();
            log.info("Generated name: {}", name);
            return name;
        });
    }

}

package com.aloha.reactive.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Demonstrates how to create a Mono using a Runnable.
 * This example shows how to use Mono.fromRunnable to execute a task
 * in a separate thread without returning any value.
 * 
 * Use case: When you want to perform a side effect or run a task
 * without needing to return a value, such as logging, sending a notification,
 * or performing cleanup operations.
 */
@Slf4j
public class MonoFromRunnable {

    public static void main(String[] args) {
        Mono.fromRunnable(() -> {
            log.info("Running a task in a separate thread");
            // Simulate some work
            try {
                Thread.sleep(3000);
                log.info("Task completed");
            } catch (InterruptedException e) {
                log.error("Task interrupted", e);
            }
        }).subscribe(
                item -> log.info("Received: {}", item), // No item to consume
                error -> log.error("Error occurred: {}", error.getMessage()),
                () -> log.info("Subscription completed"));
    }

}

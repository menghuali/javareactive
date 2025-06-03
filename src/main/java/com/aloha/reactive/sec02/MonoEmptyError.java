package com.aloha.reactive.sec02;

import com.aloha.reactive.common.DefaultSubscriber;

import reactor.core.publisher.Mono;

/**
 * Demonstrates how to handle Mono.empty() and Mono.error() in Reactor.
 * This example shows how to subscribe to a Mono that may emit an empty value or an error.
 */
public class MonoEmptyError {

    public static void main(String[] args) {
        getUsername(1).subscribe(DefaultSubscriber.create("User1"));
        getUsername(2).subscribe(DefaultSubscriber.create("User2"));
        getUsername(3).subscribe(DefaultSubscriber.create("User3"));
        getUsername(100).subscribe(DefaultSubscriber.create("User100"));

        // Default onErrorDropped behavior
        getUsername(100).subscribe(
            username -> System.out.println("Username: " + username)
        );
    }

    private static Mono<String> getUsername(int userId) {
        switch (userId) {
            case 1:
                return Mono.just("Alice");
            case 2:
                return Mono.just("Bob");
            case 3:
                return Mono.empty();
            default:
                return Mono.error(new IllegalArgumentException("Invalid user ID: " + userId));
        }
    }

}

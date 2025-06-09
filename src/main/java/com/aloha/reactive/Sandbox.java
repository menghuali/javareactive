package com.aloha.reactive;

import reactor.core.publisher.Mono;

public class Sandbox {

    public static void main(String[] args) {
        get()
            .subscribe(
                result -> System.out.println("Result: " + result),
                error -> System.err.println("Error: " + error.getMessage()),
                () -> System.out.println("Completed successfully")
            );
    }

    static Mono<Integer> get(){
        return Mono.fromRunnable(() -> {
            int a = 1 * 2;
        });
    }

}

package com.aloha.reactive.sec02;

import java.time.Duration;
import java.util.List;

import com.aloha.reactive.common.DefaultSubscriber;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoDefer {

    public static void main(String[] args) throws InterruptedException {        
        // Demonstrating the use of Mono.defer to lazily create a Mono
        // If there is no subscriber, the Mono will not be created.
        Mono.defer(() -> {
            try {
                return getSumMono();
            } catch (InterruptedException e) {
                log.error("Error creating Mono", e);
                return Mono.empty(); // or handle the error appropriately
            }
        }).subscribe(DefaultSubscriber.create());
    }

    private static Mono<Integer> getSumMono() throws InterruptedException {
        int defer = 3;
        log.info("Creating Mono in {} seconds", defer);
        Thread.sleep(Duration.ofSeconds(defer));

        var mono = Mono.fromSupplier(() -> {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5);
            try {
                return sum(numbers);
            } catch (InterruptedException e) {
                log.error("Error calculating sum", e);
                return null; // or handle the error appropriately
            }
        });
        log.info("Mono created");
        return mono;
    }

    /**
     * Calculates the sum of a list of integers.
     *
     * @param numbers the list of integers to sum
     * @return the sum of the integers
     * @throws InterruptedException 
     */
    private static int sum(List<Integer> numbers) throws InterruptedException {
        int defer = 6;
        log.info("Calculating sum of numbers in {} seconds: {}", defer, numbers);
        Thread.sleep(Duration.ofSeconds(defer));
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

}

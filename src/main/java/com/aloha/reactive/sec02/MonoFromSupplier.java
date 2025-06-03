package com.aloha.reactive.sec02;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Demonstrates how to create a Mono using a Supplier.
 * This example shows the difference between immediate and lazy evaluation of a Mono.
 */
@Slf4j
public class MonoFromSupplier {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // Sum is calculated immediately even if there is no subscriber.
        log.info("== Creating Mono with immediate sum calculation ==");
        Mono.just(sum(numbers));

        // Sum is calculated only when there is a subscriber.
        log.info("\n\n== Creating Mono with lazy sum calculation ==");
        Mono<Integer> mono = Mono.fromSupplier(() -> sum(numbers));
        log.info("Mono created, but sum not calculated yet.");
        mono.subscribe(
            result -> log.info("Sum of numbers: {}", result)
        );
        log.info("Mono subscription complete.");
    }

    /**
     * Calculates the sum of a list of integers.
     *
     * @param numbers the list of integers to sum
     * @return the sum of the integers
     */
    private static int sum(List<Integer> numbers) {
        log.info("Calculating sum of numbers: {}", numbers);
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

}

package com.aloha.reactive.sec02;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Demonstrates how to create a Mono using a Callable.
 * This example shows how to use Mono.fromCallable to lazily evaluate a
 * computation.
 */
@Slf4j
public class MonoFromCallable {

    public static void main(String[] args) {
        Mono.fromCallable(() -> {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5);
            return sum(numbers);
        })
                .subscribe(
                        result -> log.info("Sum of numbers: {}", result));
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

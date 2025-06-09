package com.aloha.reactive.sec02;

import com.aloha.reactive.common.DefaultSubscriber;
import com.aloha.reactive.sec02.client.ExternalServiceClient;

import lombok.extern.slf4j.Slf4j;

/**
 * This class demonstrates non-blocking I/O operations using a reactive
 * programming approach. It utilizes an external service client to fetch product
 * names asynchronously. The main method creates multiple subscribers to the
 * product name stream. Each subscriber will log the product name received from
 * the external service. The operations are non-blocking, allowing the main
 * thread to continue executing while waiting for responses. The example uses a
 * sleep to allow time for the asynchronous operations to complete before the
 * program exits.
 */
@Slf4j
public class NonBlockingIO {

    private static final int LOOP_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        demoBlockingIO();
        demoNonBlockingIO();
    }

    private static void demoBlockingIO() {
        log.info("== Demo Blocking I/O ==");
        var client = new ExternalServiceClient();
        for (int i = 0; i < LOOP_COUNT; i++) {
            log.info("Product name for id {}: {}", i, client.getProductName(i).block());
        }
    }

    private static void demoNonBlockingIO() throws InterruptedException {
        log.info("\n\n== Demo Non-Blocking I/O ==");
        var client = new ExternalServiceClient();
        for (int i = 0; i < LOOP_COUNT; i++) {
            client.getProductName(i).subscribe(DefaultSubscriber.create("subscriber-" + i));
        }
        Thread.sleep(3000); // Wait for the async operation to complete
    }

}

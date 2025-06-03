package com.aloha.reactive.sec01;

import com.aloha.reactive.sec01.publisher.PublisherImpl;
import com.aloha.reactive.sec01.subscriber.SubscriberImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Demonstrates the usage of a custom Publisher and Subscriber
 */
@Slf4j
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        demo1();
        demo2();
        demo3();
    }

    /**
     * Demonstrates requesting items in batches of 3.
     * The subscriber will receive items in batches and process them.
     */
    private static void demo1() throws InterruptedException {
        log.info("\n\n== Demo 1: Requesting items in batches of 3 ==");
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000); // Wait for items to be processed

        subscriber.getSubscription().request(3);
        Thread.sleep(2000); // Wait for items to be processed

        subscriber.getSubscription().request(3);
        Thread.sleep(2000); // Wait for items to be processed

        subscriber.getSubscription().request(3);
        Thread.sleep(000); // Wait for items to be processed
    }

    /**
     * Demonstrates canceling the subscription after requesting items.
     * The subscriber will cancel the subscription after processing some items.
     */
    private static void demo2() throws InterruptedException {
        log.info("\n\n== Demo 2: Canceling subscription after requesting items ==");
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000); // Wait for items to be processed

        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(2000); // Wait for items to be processed

        subscriber.getSubscription().request(3);
        Thread.sleep(2000); // Wait for items to be processed

        subscriber.getSubscription().request(3);
        Thread.sleep(000); // Wait for items to be processed
    }

    /**
     * Demonstrates requesting more than the maximum allowed items.
     * The subscriber will request more items than the maximum allowed, triggering an error.
     */
    private static void demo3() throws InterruptedException {
        log.info("\n\n== Demo 3: Requesting more than the maximum allowed items ==");
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(11);
        Thread.sleep(2000); // Wait for items to be processed
        subscriber.getSubscription().request(3);
    }
}

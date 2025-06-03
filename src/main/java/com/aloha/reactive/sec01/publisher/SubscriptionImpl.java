package com.aloha.reactive.sec01.publisher;



import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubscriptionImpl implements Subscription {

    private static final long MAX_REQUESTS = 10; // Maximum number of items to request
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private boolean isCancelled = false;
    private long count;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        faker = Faker.instance();
        count = 0;
    }

    @Override
    public void request(long n) {
        if (isCancelled) {
            log.warn("Subscription is cancelled, ignoring request");
            return;
        }

        log.info("Requesting {} items", n);
        if (n > MAX_REQUESTS) {
            subscriber.onError(new IllegalArgumentException("Cannot request more than " + MAX_REQUESTS + " items"));
            cancel();
            return;
        }

        for (int i = 0; i < n && count < MAX_REQUESTS; i++, count++) {
            // Simulate sending an item to the subscriber
            log.info("Sending item {} to subscriber", count);
            subscriber.onNext(faker.internet().emailAddress());
        }

        if (count == MAX_REQUESTS) {
            log.info("Reached maximum request limit of {}", MAX_REQUESTS);
            subscriber.onComplete();
            cancel();
        } else {
            log.info("Sent {} items to subscriber", count);
        }
    }

    @Override
    public void cancel() {
        log.info("Subscription cancelled");
        isCancelled = true;
    }

}

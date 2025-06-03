package com.aloha.reactive.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of a Subscriber that logs received items, errors, and completion signals.
 * This class can be used as a base for custom subscribers or for testing purposes.
 *
 * @param <T> the type of items this subscriber will handle
 */
@Slf4j
public class DefaultSubscriber<T> implements Subscriber<T> {
    @Getter
    private final String name;

    /**
     * Constructs a DefaultSubscriber with a specified name.
     *
     * @param name the name of the subscriber, used for logging
     */
    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info("{} received: {}", name, item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} error: {}", name, throwable);
    }

    @Override
    public void onComplete() {
        log.info("{} completed", name);
    }

    /**
     * Creates a DefaultSubscriber with a specified name.
     *
     * @param name the name of the subscriber
     * @param <T>  the type of items this subscriber will handle
     * @return a new instance of DefaultSubscriber
     */
    public static <T> DefaultSubscriber<T> create(String name) {
        return new DefaultSubscriber<>(name);
    }

    /**
     * Creates a DefaultSubscriber with a default name "default-subscriber".
     *
     * @param <T> the type of items this subscriber will handle
     * @return a new instance of DefaultSubscriber with the default name
     */
    public static <T> DefaultSubscriber<T> create() {
        return new DefaultSubscriber<>("default-subscriber");
    }

}

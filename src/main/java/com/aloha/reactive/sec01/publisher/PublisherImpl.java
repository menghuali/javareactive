package com.aloha.reactive.sec01.publisher;



import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PublisherImpl implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        var subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }

}

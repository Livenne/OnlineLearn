package com.livenne.onlinelearn.core.network;

@FunctionalInterface
public interface NetworkCallable <T>{

    void onSuccess(T result);

    default void onFailure(String errMessage) {

    }
}
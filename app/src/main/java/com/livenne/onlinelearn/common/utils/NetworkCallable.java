package com.livenne.onlinelearn.common.utils;

@FunctionalInterface
public interface NetworkCallable <T>{

    void onSuccess(T result);

    default void onFailure(String errMessage) {

    }
}
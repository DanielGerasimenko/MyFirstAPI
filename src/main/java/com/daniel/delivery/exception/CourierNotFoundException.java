package com.daniel.delivery.exception;

public class CourierNotFoundException extends RuntimeException {

    public CourierNotFoundException(Long id) {
        super("Could not find courier " + id);
    }
}
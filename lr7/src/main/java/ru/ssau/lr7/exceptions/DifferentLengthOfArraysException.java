package ru.ssau.lr7.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {

    public DifferentLengthOfArraysException() {
        super();
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
package org.nsu.oop.calculator.exception.stream;

public class StreamException extends RuntimeException {
    public StreamException(String msg) {
        super("StreamException: " + msg);
    }
}

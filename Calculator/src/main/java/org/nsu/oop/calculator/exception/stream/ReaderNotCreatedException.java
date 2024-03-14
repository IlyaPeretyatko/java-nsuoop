package org.nsu.oop.calculator.exception.stream;

import org.nsu.oop.calculator.exception.stream.StreamException;

public class ReaderNotCreatedException extends StreamException {
    public ReaderNotCreatedException() {
        super("Reader not created.");
    }
}

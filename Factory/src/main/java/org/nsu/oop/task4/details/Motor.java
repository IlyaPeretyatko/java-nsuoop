package org.nsu.oop.task4.details;

import java.util.UUID;

public class Motor {
    private final UUID id;

    public Motor() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}

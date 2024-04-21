package org.nsu.oop.task4.details;

import java.util.UUID;

public class Accessory {
    private final UUID id;

    public Accessory() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}

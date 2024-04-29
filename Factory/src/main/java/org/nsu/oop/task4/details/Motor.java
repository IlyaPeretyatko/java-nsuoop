package org.nsu.oop.task4.details;

import java.util.UUID;

public class Motor implements Detail {
    private final UUID id;

    public Motor() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getName() { return "Motor"; }

}

package org.nsu.oop.Factory.details;

import java.util.UUID;

public class Body {
    private final UUID id;

    public Body() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}

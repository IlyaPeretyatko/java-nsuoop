package org.nsu.oop.task4.details;

import java.util.UUID;

public class Car {
    private final UUID id;

    private final UUID motorId;
    private final UUID bodyId;
    private final UUID accessoryId;

    public Car(UUID motorId, UUID bodyId, UUID accessoryId) {
        this.id = UUID.randomUUID();
        this.motorId = motorId;
        this.bodyId = bodyId;
        this.accessoryId = accessoryId;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getMotorId() {
        return motorId;
    }

    public UUID getBodyId() {
        return bodyId;
    }

    public UUID getAccessoryId() {
        return accessoryId;
    }
}

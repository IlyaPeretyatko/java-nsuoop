package org.nsu.oop.task4.staff;

import org.nsu.oop.task4.threadpool.ThreadTask;

import java.util.UUID;

public class Worker extends ThreadTask {
    private final UUID id;

    public Worker() {
        this.id = UUID.randomUUID();
    }

    public UUID getUId() {
        return id;
    }
}

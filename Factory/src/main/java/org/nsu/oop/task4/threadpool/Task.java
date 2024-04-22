package org.nsu.oop.task4.threadpool;

import java.util.UUID;

public interface Task {
    void executeTask() throws InterruptedException;

    UUID getIdDetail();
}

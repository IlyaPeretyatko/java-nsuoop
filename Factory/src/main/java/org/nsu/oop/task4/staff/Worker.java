package org.nsu.oop.task4.staff;

import org.nsu.oop.task4.threadpool.Task;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Worker extends Thread {

    private static final Logger log = Logger.getLogger(Worker.class.getName());

    private final UUID id;

    private BlockingQueue<Task> queue;

    public Worker() {
        this.id = UUID.randomUUID();
    }

    public UUID getUId() {
        return id;
    }

    public void setQueue(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isAlive()) {
            Task task;
            try {
                task = queue.take();
                task.executeTask();
                Thread.sleep(1000);
                log.info("Worker ID: " + getUId() + " assembled car ID: " + task.getIdDetail() + " and put in storage of car.");
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

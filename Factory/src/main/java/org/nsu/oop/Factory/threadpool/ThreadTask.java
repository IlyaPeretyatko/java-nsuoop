package org.nsu.oop.Factory.threadpool;

import java.util.concurrent.BlockingQueue;

public class ThreadTask extends Thread {

    private BlockingQueue<Task> queue;

    public void setQueue(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Task task;
        try {
            task = queue.take();
        } catch (InterruptedException e) {
            return;
        }
        task.executeTask();
    }
}

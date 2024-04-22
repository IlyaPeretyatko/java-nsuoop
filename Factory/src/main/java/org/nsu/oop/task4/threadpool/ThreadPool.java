package org.nsu.oop.task4.threadpool;

import org.nsu.oop.task4.staff.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private final List<Worker> threads;
    private final BlockingQueue<Task> queue;

    public ThreadPool() {
        threads = new ArrayList<>();
        queue = new LinkedBlockingQueue<>();
    }

    public int getQueueSize() {
        return queue.size();
    }

    public boolean addTask(Task task) {
        return queue.offer(task);
    }

    public void addThread(Worker threadTask) {
        threads.add(threadTask);
    }

    public void start() {
        for (Worker thread : threads) {
            thread.setQueue(queue);
            thread.start();
        }
    }

    public void stop() {
        for (Worker thread : threads) {
            thread.interrupt();
        }
    }



}

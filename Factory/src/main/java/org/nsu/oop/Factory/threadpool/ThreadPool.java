package org.nsu.oop.Factory.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private final List<ThreadTask> threads;
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

    public void addThread(ThreadTask threadTask) {
        threads.add(threadTask);
    }

    public void start() {
        for (ThreadTask thread : threads) {
            thread.setQueue(queue);
            thread.start();
        }
    }

    public void stop() {
        for (ThreadTask thread : threads) {
            thread.interrupt();
        }
    }



}

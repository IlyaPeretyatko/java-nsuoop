package org.nsu.oop.Factory;

import org.nsu.oop.Factory.staff.Worker;
import org.nsu.oop.Factory.threadpool.Task;
import org.nsu.oop.Factory.threadpool.ThreadPool;

public class AssemblyCar {

    ThreadPool threadPool;

    public AssemblyCar(int countWorkers) {
        threadPool = new ThreadPool();
        for (int i = 0; i < countWorkers; ++i) {
            threadPool.addThread(new Worker());
        }
    }

    public boolean addTask(Task task) {
        return threadPool.addTask(task);
    }

    public void start() {
        threadPool.start();
    }

    public void stop() {
        threadPool.stop();
    }

}

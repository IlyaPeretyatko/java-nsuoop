package org.nsu.oop.Factory.assembling;

import org.nsu.oop.Factory.FactoryInfo;
import org.nsu.oop.Factory.staff.Worker;
import org.nsu.oop.Factory.threadpool.Task;
import org.nsu.oop.Factory.threadpool.ThreadPool;

public class AssemblingCar {

    ThreadPool threadPool;

    public AssemblingCar(int countWorkers, FactoryInfo factoryInfo) {
        threadPool = new ThreadPool();
        for (int i = 0; i < countWorkers; ++i) {
            threadPool.addThread(new Worker());
        }
    }

    public boolean addTask(Task task) {
        return threadPool.addTask(task);
    }

    public int getQueueSize() {
        return threadPool.getQueueSize();
    }

    public void start() {
        threadPool.start();
    }

    public void stop() {
        threadPool.stop();
    }

}

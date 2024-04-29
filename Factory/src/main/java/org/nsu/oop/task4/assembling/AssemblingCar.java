package org.nsu.oop.task4.assembling;

import org.nsu.oop.task4.factory.FactoryInfo;
import org.nsu.oop.task4.threadpool.Task;
import org.nsu.oop.task4.threadpool.ThreadPool;
import org.nsu.oop.task4.staff.Worker;

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

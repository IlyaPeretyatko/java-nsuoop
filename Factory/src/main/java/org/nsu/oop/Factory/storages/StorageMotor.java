package org.nsu.oop.Factory.storages;

import org.nsu.oop.Factory.details.Motor;

import java.util.ArrayList;
import java.util.List;

public class StorageMotor {
    private final int capacity;

    private final List<Motor> details;

    public StorageMotor(int capacity) {
        details = new ArrayList<>();
        this.capacity = capacity;
    }

    public synchronized void put(Motor motor) {
        while (details.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        details.add(motor);
        notifyAll();
    }

    public synchronized Motor get() {
        while (details.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        Motor returnMotor = details.getFirst();
        details.remove(returnMotor);
        notifyAll();
        return returnMotor;
    }
}

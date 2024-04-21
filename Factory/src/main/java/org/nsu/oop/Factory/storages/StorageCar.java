package org.nsu.oop.Factory.storages;

import org.nsu.oop.Factory.details.Car;

import java.util.ArrayList;
import java.util.List;

public class StorageCar {
    private final int capacity;

    private final List<Car> details;

    public StorageCar(int capacity) {
        details = new ArrayList<>();
        this.capacity = capacity;
    }

    public synchronized void put(Car car) {
        while (details.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        details.add(car);
        notifyAll();
    }

    public synchronized Car get() {
        while (details.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        Car returnCar = details.getFirst();
        details.remove(returnCar);
        notifyAll();
        return returnCar;
    }
}

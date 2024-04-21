package org.nsu.oop.task4.storages;

import org.nsu.oop.task4.details.Car;

import java.util.ArrayList;
import java.util.List;

public class StorageCar {

    private final int capacity;

    private ControllerStorageCar controllerStorageCar;

    private final List<Car> cars;

    public StorageCar(int capacity) {
        cars = new ArrayList<>();
        this.capacity = capacity;
    }

    public int getSize() {
        return cars.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setControllerStorageCar(ControllerStorageCar controllerStorageCar) {
        this.controllerStorageCar = controllerStorageCar;
    }

    public synchronized void put(Car car) {
        while (cars.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        cars.add(car);
        notifyAll();
    }

    public synchronized Car get() {
        while (cars.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        Car returnCar = cars.getFirst();
        cars.remove(returnCar);
        controllerStorageCar.estimateStorageCar();
        notifyAll();
        return returnCar;
    }
}

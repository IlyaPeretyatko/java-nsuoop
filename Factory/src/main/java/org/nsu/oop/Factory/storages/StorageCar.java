package org.nsu.oop.Factory.storages;

import org.nsu.oop.Factory.FactoryInfo;
import org.nsu.oop.Factory.assembling.AssemblingCar;
import org.nsu.oop.Factory.details.Car;

import java.util.ArrayList;
import java.util.List;

public class StorageCar {
    private final int capacity;

    private final FactoryInfo factoryInfo;

    private ControllerStorageCar controllerStorageCar;

    private final List<Car> cars;

    public StorageCar(int capacity, FactoryInfo factoryInfo) {
        cars = new ArrayList<>();
        this.capacity = capacity;
        this.factoryInfo = factoryInfo;
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
        factoryInfo.realese();
        notifyAll();
        controllerStorageCar.estimateStorageCar();
        return returnCar;
    }
}

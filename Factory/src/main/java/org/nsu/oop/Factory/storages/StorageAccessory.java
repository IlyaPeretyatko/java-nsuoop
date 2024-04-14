package org.nsu.oop.Factory.storages;

import org.nsu.oop.Factory.details.Accessory;

import java.util.ArrayList;
import java.util.List;

public class StorageAccessory {
    private final int capacity;

    private final List<Accessory> details;

    public StorageAccessory(int capacity) {
        details = new ArrayList<>();
        this.capacity = capacity;
    }

    public synchronized void put(Accessory accessory) {
        while (details.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        details.add(accessory);
        notifyAll();
    }

    public synchronized Accessory get() {
        while (details.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        Accessory returnAccessory = details.getFirst();
        details.remove(returnAccessory);
        notifyAll();
        return returnAccessory;
    }
}

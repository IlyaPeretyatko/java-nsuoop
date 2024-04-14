package org.nsu.oop.Factory.storages;

import org.nsu.oop.Factory.details.Body;

import java.util.ArrayList;
import java.util.List;

public class StorageBody {
    private final int capacity;

    private final List<Body> details;

    public StorageBody(int capacity) {
        details = new ArrayList<>();
        this.capacity = capacity;
    }

    public synchronized void put(Body body) {
        while (details.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        details.add(body);
        notifyAll();
    }

    public synchronized Body get() {
        while (details.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        Body returnBody = details.getFirst();
        details.remove(returnBody);
        notifyAll();
        return returnBody;
    }
}

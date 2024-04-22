package org.nsu.oop.task4.storages;

import org.nsu.oop.task4.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Storage<Detail> {

    private final int capacity;

    private final List<Detail> details;

    public Storage(int capacity) {
        details = new ArrayList<>();
        this.capacity = capacity;
    }

    public int getCurrentSize() {
        return details.size();
    }

    public synchronized void put(Detail detail) throws InterruptedException {
        while (details.size() == capacity) {
            wait();
        }
        details.add(detail);
        notifyAll();
    }

    public synchronized Detail get() throws InterruptedException {
        while (details.isEmpty()) {
            wait();
        }
        Detail returnDetail = details.getFirst();
        details.remove(returnDetail);
        notifyAll();
        return returnDetail;
    }
}

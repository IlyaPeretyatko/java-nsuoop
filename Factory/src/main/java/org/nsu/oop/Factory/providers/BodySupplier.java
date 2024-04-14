package org.nsu.oop.Factory.providers;

import org.nsu.oop.Factory.details.Body;
import org.nsu.oop.Factory.storages.StorageBody;

import java.util.UUID;

public class BodySupplier extends Thread {
    private final UUID id;

    private final StorageBody storage;

    private int freq;

    public BodySupplier(StorageBody storage) {
        this.id = UUID.randomUUID();
        this.storage = storage;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public UUID getUId() {
        return id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(freq);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException");
        }
        storage.put(new Body());
    }
}

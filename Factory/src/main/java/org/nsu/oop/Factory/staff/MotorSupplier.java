package org.nsu.oop.Factory.staff;


import org.nsu.oop.Factory.details.Motor;
import org.nsu.oop.Factory.storages.StorageMotor;

import java.util.UUID;

public class MotorSupplier extends Thread {
    private final UUID id;

    private final StorageMotor storage;

    private int freq;

    public MotorSupplier(StorageMotor storage) {
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
        storage.put(new Motor());
    }
}

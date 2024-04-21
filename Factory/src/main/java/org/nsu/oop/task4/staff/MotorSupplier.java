package org.nsu.oop.task4.staff;


import org.nsu.oop.task4.details.Motor;
import org.nsu.oop.task4.storages.StorageMotor;

import java.util.UUID;

public class MotorSupplier extends Thread {
    private final UUID id;

    private final StorageMotor storage;

    private int freq;

    public MotorSupplier(StorageMotor storage) {
        this.id = UUID.randomUUID();
        this.storage = storage;
        freq = 1000;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }


    public UUID getUId() {
        return id;
    }

    @Override
    public void run() {
        while (isAlive()) {
            try {
                Thread.sleep(freq);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
            storage.put(new Motor());
        }
    }
}

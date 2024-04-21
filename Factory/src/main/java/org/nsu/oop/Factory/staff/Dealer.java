package org.nsu.oop.Factory.staff;

import org.nsu.oop.Factory.FactoryInfo;
import org.nsu.oop.Factory.details.Car;
import org.nsu.oop.Factory.storages.StorageCar;

import java.util.UUID;
import java.util.logging.Logger;

public class Dealer extends Thread {

    private static final Logger log = Logger.getLogger(Dealer.class.getName());

    private final UUID id;

    private final StorageCar storage;
    private final FactoryInfo factoryInfo;
    private final boolean logging;

    private int freq;

    public Dealer(StorageCar storageCar, FactoryInfo factoryInfo, boolean logging) {
        id = UUID.randomUUID();
        this.factoryInfo = factoryInfo;
        this.storage = storageCar;
        this.logging = logging;
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
            Car car = storage.get();
            factoryInfo.realese();
            if (logging) {
                log.info("Dealer: " + getUId() + " Auto: " + car.getId() + " (Body: " + car.getBodyId() + ", Motor: " + car.getMotorId() + ", Accessory: " + car.getAccessoryId() + ")");
            }
        }
    }


}

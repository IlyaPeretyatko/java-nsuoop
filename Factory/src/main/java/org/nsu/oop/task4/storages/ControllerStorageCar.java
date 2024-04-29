package org.nsu.oop.task4.storages;

import org.nsu.oop.task4.assembling.AssemblingCar;
import org.nsu.oop.task4.details.Accessory;
import org.nsu.oop.task4.details.Body;
import org.nsu.oop.task4.details.Motor;
import org.nsu.oop.task4.staff.Worker;
import org.nsu.oop.task4.staff.WorkerTask;

import java.util.logging.Logger;

public class ControllerStorageCar {

    private static final Logger log = Logger.getLogger(ControllerStorageCar.class.getName());

    private final Storage<Body> storageBody;
    private final Storage<Motor> storageMotor;
    private final Storage<Accessory> storageAccessory;
    private final StorageCar storageCar;
    private final AssemblingCar assemblingCar;

    public ControllerStorageCar(Storage<Body> storageBody, Storage<Motor> storageMotor, Storage<Accessory> storageAccessory, StorageCar storageCar, AssemblingCar assemblingCar) {
        this.storageBody = storageBody;
        this.storageMotor = storageMotor;
        this.storageAccessory = storageAccessory;
        this.storageCar = storageCar;
        this.assemblingCar = assemblingCar;
        for (int i = 0; i < storageCar.getCapacity(); ++i) {
            assemblingCar();
        }
    }

    private void assemblingCar() {
        assemblingCar.addTask(new WorkerTask(storageBody, storageMotor, storageAccessory, storageCar));
    }

    public void estimateStorageCar() {
        int capacity = storageCar.getCapacity();
        int inQueue = assemblingCar.getQueueSize();
        int inStorage = storageCar.getSize();
        if (inQueue + inStorage < capacity * 0.5) {
            for (int i = 0; i < capacity - inQueue - inStorage; ++i) {
                assemblingCar();
            }
            log.info(capacity - inQueue - inStorage + " cars have been added to the production queue.");
        }
    }
}

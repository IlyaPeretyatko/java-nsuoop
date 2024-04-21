package org.nsu.oop.task4.storages;

import org.nsu.oop.task4.assembling.AssemblingCar;
import org.nsu.oop.task4.staff.WorkerTask;

public class ControllerStorageCar {

    private final StorageBody storageBody;
    private final StorageMotor storageMotor;
    private final StorageAccessory storageAccessory;
    private final StorageCar storageCar;
    private final AssemblingCar assemblingCar;

    public ControllerStorageCar(StorageBody storageBody, StorageMotor storageMotor, StorageAccessory storageAccessory, StorageCar storageCar, AssemblingCar assemblingCar) {
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
        if (inQueue + inStorage < capacity) {
            for (int i = 0; i < capacity - inQueue - inStorage; ++i) {
                assemblingCar();
            }
        }
    }
}

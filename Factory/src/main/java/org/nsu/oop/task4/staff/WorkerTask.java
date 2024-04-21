package org.nsu.oop.task4.staff;

import org.nsu.oop.task4.details.Accessory;
import org.nsu.oop.task4.details.Body;
import org.nsu.oop.task4.details.Car;
import org.nsu.oop.task4.details.Motor;
import org.nsu.oop.task4.storages.StorageAccessory;
import org.nsu.oop.task4.storages.StorageBody;
import org.nsu.oop.task4.storages.StorageCar;
import org.nsu.oop.task4.storages.StorageMotor;
import org.nsu.oop.task4.threadpool.Task;

public class WorkerTask implements Task {

    private final StorageBody storageBody;
    private final StorageMotor storageMotor;
    private final StorageAccessory storageAccessory;
    private final StorageCar storageCar;

    public WorkerTask(StorageBody storageBody, StorageMotor storageMotor, StorageAccessory storageAccessory, StorageCar storageCar) {
        this.storageBody = storageBody;
        this.storageMotor = storageMotor;
        this.storageAccessory = storageAccessory;
        this.storageCar = storageCar;
    }


    @Override
    public void executeTask() {
        Body body = storageBody.get();
        Motor motor = storageMotor.get();
        Accessory accessory = storageAccessory.get();
        if (body == null || motor == null || accessory == null) {
            if (body != null) {
                storageBody.put(body);
            }
            if (motor != null) {
                storageMotor.put(motor);
            }
            if (accessory != null) {
                storageAccessory.put(accessory);
            }
            return;
        }
        Car car = new Car(motor.getId(), body.getId(), accessory.getId());
        storageCar.put(car);
    }

}

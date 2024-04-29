package org.nsu.oop.task4.staff;

import org.nsu.oop.task4.details.Accessory;
import org.nsu.oop.task4.details.Body;
import org.nsu.oop.task4.details.Car;
import org.nsu.oop.task4.details.Motor;
import org.nsu.oop.task4.storages.Storage;
import org.nsu.oop.task4.storages.StorageCar;
import org.nsu.oop.task4.threadpool.Task;

import java.util.UUID;

public class WorkerTask implements Task {

    private final Storage<Body> storageBody;
    private final Storage<Motor> storageMotor;
    private final Storage<Accessory> storageAccessory;
    private final StorageCar storageCar;

    private UUID idCar;

    public WorkerTask(Storage<Body> storageBody, Storage<Motor> storageMotor, Storage<Accessory> storageAccessory, StorageCar storageCar) {
        this.storageBody = storageBody;
        this.storageMotor = storageMotor;
        this.storageAccessory = storageAccessory;
        this.storageCar = storageCar;
    }


    @Override
    public void executeTask() throws InterruptedException {
        Body body = storageBody.get();
        Motor motor = storageMotor.get();
        Accessory accessory = storageAccessory.get();
        Car car = new Car(motor.getId(), body.getId(), accessory.getId());
        idCar = car.getId();
        storageCar.put(car);
    }

    @Override
    public UUID getIdDetail() {
        return idCar;
    }

}

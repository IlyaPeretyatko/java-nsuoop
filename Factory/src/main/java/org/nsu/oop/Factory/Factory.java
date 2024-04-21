package org.nsu.oop.Factory;

import org.nsu.oop.Factory.assembling.AssemblingCar;
import org.nsu.oop.Factory.staff.*;
import org.nsu.oop.Factory.storages.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    private final BodySupplier bodySupplier;
    private final MotorSupplier motorSupplier;
    private final List<AccessorySupplier> accessorySupplierList;
    private final List<Dealer> dealerList;
    private final AssemblingCar assemblingCar;

    public Factory(FactoryInfo factoryInfo) throws IOException {

        StorageMotor storageMotor = new StorageMotor(factoryInfo.getStorageMotorSize());
        StorageBody storageBody = new StorageBody(factoryInfo.getStorageBodySize());
        StorageAccessory storageAccessory = new StorageAccessory(factoryInfo.getStorageAccessorySize());
        StorageCar storageCar = new StorageCar(factoryInfo.getStorageAutoSize());

        bodySupplier = new BodySupplier(storageBody);
        motorSupplier = new MotorSupplier(storageMotor);
        accessorySupplierList = new ArrayList<>();
        for (int i = 0; i < factoryInfo.getAccessorySuppliers(); ++i) {
            accessorySupplierList.add(new AccessorySupplier(storageAccessory));
        }
        dealerList = new ArrayList<>();
        for (int i = 0; i < factoryInfo.getDealers(); ++i) {
            dealerList.add(new Dealer(storageCar, factoryInfo, factoryInfo.getLogSale()));
        }

        assemblingCar = new AssemblingCar(factoryInfo.getWorkers(), factoryInfo);

        ControllerStorageCar controllerStorageCar = new ControllerStorageCar(storageBody, storageMotor, storageAccessory, storageCar, assemblingCar);

        storageCar.setControllerStorageCar(controllerStorageCar);
    }

    public void setFreqBodySupplier(int freq) {
        bodySupplier.setFreq(freq);
    }

    public void setFreqMotorSupplier(int freq) {
        motorSupplier.setFreq(freq);
    }

    public void setFreqAccessorySupplier(int freq) {
        for (AccessorySupplier accessorySupplier : accessorySupplierList) {
            accessorySupplier.setFreq(freq);
        }
    }

    public void setFreqDealer(int freq) {
        for (Dealer dealer : dealerList) {
            dealer.setFreq(freq);
        }
    }

    public int getInQueue() {
        return assemblingCar.getQueueSize();
    }


    public void start() {
        bodySupplier.start();
        motorSupplier.start();
        for (AccessorySupplier accessorySupplier : accessorySupplierList) {
            accessorySupplier.start();
        }
        assemblingCar.start();
        for (Dealer dealer : dealerList) {
            dealer.start();
        }
    }

    public void stop() {
        bodySupplier.interrupt();
        motorSupplier.interrupt();
        for (AccessorySupplier accessorySupplier : accessorySupplierList) {
            accessorySupplier.interrupt();
        }
        assemblingCar.stop();
        for (Dealer dealer : dealerList) {
            dealer.interrupt();
        }
    }


}

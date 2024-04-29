package org.nsu.oop.task4.factory;

import org.nsu.oop.task4.assembling.AssemblingCar;
import org.nsu.oop.task4.details.Accessory;
import org.nsu.oop.task4.details.Body;
import org.nsu.oop.task4.details.Motor;
import org.nsu.oop.task4.staff.*;
import org.nsu.oop.task4.storages.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Factory {

    private static final Logger log = Logger.getLogger(Factory.class.getName());

    private final Storage<Motor> storageMotor;
    private final Storage<Body> storageBody;
    private final Storage<Accessory> storageAccessory;
    private final StorageCar storageCar;

    private final Supplier<Body> bodySupplier;
    private final Supplier<Motor> motorSupplier;
    private final List<Supplier<Accessory>> accessorySupplierList;
    private final List<Dealer> dealerList;
    private final AssemblingCar assemblingCar;

    public Factory(FactoryInfo factoryInfo) throws IOException {

        storageMotor = new Storage<>(factoryInfo.getStorageMotorSize());
        storageBody = new Storage<>(factoryInfo.getStorageBodySize());
        storageAccessory = new Storage<>(factoryInfo.getStorageAccessorySize());
        storageCar = new StorageCar(factoryInfo.getStorageAutoSize());
        log.info("Created storages.");

        bodySupplier = new Supplier<>(storageBody, Body.class);
        motorSupplier = new Supplier<>(storageMotor, Motor.class);
        accessorySupplierList = new ArrayList<>();
        for (int i = 0; i < factoryInfo.getAccessorySuppliers(); ++i) {
            accessorySupplierList.add(new Supplier<>(storageAccessory, Accessory.class));
        }
        log.info("Created suppliers.");

        dealerList = new ArrayList<>();
        for (int i = 0; i < factoryInfo.getDealers(); ++i) {
            dealerList.add(new Dealer(storageCar, factoryInfo, factoryInfo.getLogSale()));
        }
        log.info("Created dealers.");

        assemblingCar = new AssemblingCar(factoryInfo.getWorkers(), factoryInfo);
        ControllerStorageCar controllerStorageCar = new ControllerStorageCar(storageBody, storageMotor, storageAccessory, storageCar, assemblingCar);
        storageCar.setControllerStorageCar(controllerStorageCar);
        log.info("Created assembling cars and controller.");
    }

    public void setFreqBodySupplier(int freq) {
        bodySupplier.setFreq(freq);
    }

    public void setFreqMotorSupplier(int freq) {
        motorSupplier.setFreq(freq);
    }

    public void setFreqAccessorySupplier(int freq) {
        for (Supplier<Accessory> accessorySupplier : accessorySupplierList) {
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

    public int getInStorageCar() {
        return storageCar.getSize();
    }

    public int getInStorageBody() {
        return storageBody.getCurrentSize();
    }

    public int getInStorageMotor() {
        return storageMotor.getCurrentSize();
    }

    public int getInStorageAccessory() {
        return storageAccessory.getCurrentSize();
    }


    public void start() {
        bodySupplier.start();
        motorSupplier.start();
        for (Supplier<Accessory> accessorySupplier : accessorySupplierList) {
            accessorySupplier.start();
        }
        assemblingCar.start();
        for (Dealer dealer : dealerList) {
            dealer.start();
        }
        log.info("Start factory.");
    }

    public void stop() {
        bodySupplier.interrupt();
        motorSupplier.interrupt();
        for (Supplier<Accessory> accessorySupplier : accessorySupplierList) {
            accessorySupplier.interrupt();
        }
        assemblingCar.stop();
        for (Dealer dealer : dealerList) {
            dealer.interrupt();
        }
        log.info("Stop factory.");
    }


}

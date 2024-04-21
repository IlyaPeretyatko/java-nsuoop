package org.nsu.oop.Factory;

import org.nsu.oop.Factory.staff.*;
import org.nsu.oop.Factory.storages.StorageAccessory;
import org.nsu.oop.Factory.storages.StorageBody;
import org.nsu.oop.Factory.storages.StorageCar;
import org.nsu.oop.Factory.storages.StorageMotor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    FactoryInfo factoryInfo;

    private final StorageMotor storageMotor;
    private final StorageBody storageBody;
    private final StorageAccessory storageAccessory;
    private final StorageCar storageCar;

    private final BodySupplier bodySupplier;
    private final MotorSupplier motorSupplier;
    private final List<AccessorySupplier> accessorySupplierList;
    private final List<Dealer> dealerList;




    public Factory() throws IOException {
        factoryInfo = new FactoryInfo();
        //создаём склады
        storageMotor = new StorageMotor(factoryInfo.getStorageMotorSize());
        storageBody = new StorageBody(factoryInfo.getStorageBodySize());
        storageAccessory = new StorageAccessory(factoryInfo.getStorageAccessorySize());
        storageCar = new StorageCar(factoryInfo.getStorageAutoSize());

        //создаём производителей
        bodySupplier = new BodySupplier(storageBody);
        motorSupplier = new MotorSupplier(storageMotor);
        accessorySupplierList = new ArrayList<>();
        for (int i = 0; i < factoryInfo.getAccessorySuppliers(); ++i) {
            accessorySupplierList.add(new AccessorySupplier(storageAccessory));
        }
        dealerList = new ArrayList<>();
        for (int i = 0; i < factoryInfo.getDealers(); ++i) {
            dealerList.add(new Dealer(storageCar, factoryInfo.getLogSale()));
        }
    }


}

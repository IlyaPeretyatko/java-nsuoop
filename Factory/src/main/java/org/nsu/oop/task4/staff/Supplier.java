package org.nsu.oop.task4.staff;

import org.nsu.oop.task4.details.Detail;
import org.nsu.oop.task4.storages.Storage;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.logging.Logger;

public class Supplier<T extends Detail> extends Thread {

    private static final Logger log = Logger.getLogger(Supplier.class.getName());

    private final UUID id;

    private final Storage<T> storage;

    private final Class<?> detailClass;

    private int freq;

    public Supplier(Storage<T> storage, Class<?> detailClass) {
        this.id = UUID.randomUUID();
        this.detailClass = detailClass;
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
                T detail = (T) detailClass.getDeclaredConstructor().newInstance();
                storage.put(detail);
                log.info("Supplier ID: " + getUId() + " created detail ID: " + detail.getId() + " and put in storage of " + detail.getName() + ".");
            } catch (InterruptedException | InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                return;
            }
        }
    }
}

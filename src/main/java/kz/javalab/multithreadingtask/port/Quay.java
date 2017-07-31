package kz.javalab.multithreadingtask.port;

import kz.javalab.multithreadingtask.entity.ship.Ship;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by PaperPlane on 31.07.2017.
 */
public class Quay extends Thread {
    private Ship shipArrived;
    private boolean wharfHasWaitingShips = true;
    private int quayNumber;
    private int baseUnloadingTime;

    public Quay(int quayNumber, int baseUnloadingTime) {
        this.quayNumber = quayNumber;
        this.baseUnloadingTime = baseUnloadingTime;
    }

    public boolean isWharfHasWaitingShips() {
        return wharfHasWaitingShips;
    }

    public void setWharfHasWaitingShips(boolean wharfHasWaitingShips) {
        this.wharfHasWaitingShips = wharfHasWaitingShips;
    }

    public int getQuayNumber() {
        return quayNumber;
    }

    public void setQuayNumber(int quayNumber) {
        this.quayNumber = quayNumber;
    }

    public int getBaseUnloadingTime() {
        return baseUnloadingTime;
    }

    public void setBaseUnloadingTime(int baseUnloadingTime) {
        this.baseUnloadingTime = baseUnloadingTime;
    }

    public void takeShip(Ship ship) {
        System.out.println(new Date() + " Ship " + ship.toString() + " has arrived to the quay number " + quayNumber + " .");
        shipArrived = ship;
    }

    public boolean canTakeShip() {
        return shipArrived == null;
    }

    public synchronized void unloadShip() {
        while (wharfHasWaitingShips) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (shipArrived != null) {
                System.out.println(new Date() + "Ship " + shipArrived.toString() + " is being unloaded...");
                while (shipArrived.getWeightOfCargo() >= 0) {
                    shipArrived.setWeightOfCargo(shipArrived.getWeightOfCargo() - 100);
                    try {
                        Thread.sleep(baseUnloadingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(new Date() + "Ship " + shipArrived.toString() + " has been unloaded.");
                shipArrived = null;
            }
        }
    }

    @Override
    public void run() {
        unloadShip();
    }
}

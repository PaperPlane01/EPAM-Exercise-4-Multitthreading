package kz.javalab.multithreadingtask.port;

import kz.javalab.multithreadingtask.entity.ship.Ship;
import kz.javalab.multithreadingtask.entity.ship.impl.LowWeightCargoShip;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by PaperPlane on 31.07.2017.
 */
public class Wharf extends Thread {
    private ConcurrentLinkedQueue<Ship> shipsArrived = new ConcurrentLinkedQueue<Ship>();
    private boolean porIsWorking = true;
    private int wharfNumber;
    private Quay firstQuay;
    private Quay secondQuay;

    public Wharf(int wharfNumber, Quay firstQuay, Quay secondQuay) {
        this.wharfNumber = wharfNumber;
        this.firstQuay = firstQuay;
        this.secondQuay = secondQuay;
    }

    public ConcurrentLinkedQueue<Ship> getShipsArrived() {
        return shipsArrived;
    }

    public void setShipsArrived(ConcurrentLinkedQueue<Ship> shipsArrived) {
        this.shipsArrived = shipsArrived;
    }

    public boolean isPortIsWorking() {
        return porIsWorking;
    }

    public void setPorIsWorking(boolean porIsWorking) {
        this.porIsWorking = porIsWorking;
    }

    public int getWharfNumber() {
        return wharfNumber;
    }

    public void setWharfNumber(int wharfNumber) {
        this.wharfNumber = wharfNumber;
    }

    public Quay getFirstQuay() {
        return firstQuay;
    }

    public void setFirstQuay(Quay firstQuay) {
        this.firstQuay = firstQuay;
    }

    public Quay getSecondQuay() {
        return secondQuay;
    }

    public void setSecondQuay(Quay secondQuay) {
        this.secondQuay = secondQuay;
    }

    public void takeShip(Ship ship) {
        System.out.println("Ship " + ship.toString() + " has arrived to the wharf number " + wharfNumber + " .");
    }

    public synchronized void dispatchShips() {

        while (porIsWorking) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Ship ship = shipsArrived.peek();
            if (ship != null) {
                if (firstQuay.canTakeShip()) {
                    System.out.println(new Date() + " Ship " + ship.toString() + " has been dispatched to the quay number " + firstQuay.getQuayNumber() + " .");
                    dispatchShip(shipsArrived.poll(), firstQuay);
                } else if (secondQuay.canTakeShip()) {
                    System.out.println(new Date() + " Ship " + ship.toString() + " has been dispatched to the quay number " + secondQuay.getQuayNumber() + " .");
                    dispatchShip(shipsArrived.poll(), secondQuay);
                } else {
                    System.out.println(new Date() + " Ship " + ship.toString() + " is waiting.");
                }
            }
        }
        firstQuay.setWharfHasWaitingShips(false);
        secondQuay.setWharfHasWaitingShips(false);
    }

    public synchronized void dispatchShip(Ship ship, Quay quay) {
        quay.takeShip(ship);
    }

    @Override
    public void run() {
        firstQuay.start();
        secondQuay.start();
        dispatchShips();
    }
}

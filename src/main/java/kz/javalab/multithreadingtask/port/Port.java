package kz.javalab.multithreadingtask.port;

import kz.javalab.multithreadingtask.entity.ship.Ship;
import kz.javalab.multithreadingtask.entity.ship.impl.HighWeightCargoShip;
import kz.javalab.multithreadingtask.entity.ship.impl.LowWeightCargoShip;
import kz.javalab.multithreadingtask.entity.ship.impl.MidWeightCargoShip;
import kz.javalab.multithreadingtask.shipgenerator.ShipGenerator;

import java.util.Date;

/**
 * Created by PaperPlane on 31.07.2017.
 */
public class Port extends Thread {
    private Wharf wharfForSmallShips;
    private Wharf wharfForMidShips;
    private Wharf wharfForLargeShips;
    private ShipGenerator shipGenerator = new ShipGenerator();

    public Port() {
        wharfForLargeShips = new Wharf(1, new Quay(1, 200),
                new Quay(2, 2));
        wharfForMidShips = new Wharf(2, new Quay(3,200),
                new Quay(4, 2));
        wharfForSmallShips = new Wharf(3, new Quay(5, 250),
                new Quay(6, 200));
    }

    @Override
    public void run() {
        startWharves();
        int requiredNumberOfShips = 10;
        int numberOfShipsProduced = 0;

        while (numberOfShipsProduced != requiredNumberOfShips) {
            Ship ship = shipGenerator.generateShip();
            takeShip(ship);
            numberOfShipsProduced++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWharves();
    }

    public void startWharves() {
        wharfForSmallShips.start();
        wharfForMidShips.start();
        wharfForLargeShips.start();
    }

    public void stopWharves() {
        wharfForSmallShips.setPorIsWorking(false);
        wharfForMidShips.setPorIsWorking(false);
        wharfForLargeShips.setPorIsWorking(false);
    }

    public void takeShip(Ship ship) {
        System.out.println(new Date() + " Ship " + ship.toString() + " has arrived to the port.");
        dispatchShip(ship);
    }

    private void dispatchShip(Ship ship) {
        if (ship instanceof LowWeightCargoShip) {
            System.out.println(new Date() + " Ship " + ship.toString() + " has been dispatched to the wharf number " + wharfForSmallShips.getWharfNumber() + " for small ships.");
            wharfForSmallShips.getShipsArrived().add(ship);
        } else if (ship instanceof MidWeightCargoShip) {
            System.out.println(new Date() + " Ship " + ship.toString() + " has been dispatched to the wharf number " + wharfForMidShips.getWharfNumber() + " for mid ships.");
            wharfForMidShips.getShipsArrived().add(ship);
        } else if (ship instanceof HighWeightCargoShip) {
            System.out.println(new Date() + " Ship " + ship.toString() + " has been dispatched to the wharf number " + wharfForLargeShips.getWharfNumber() + " for large ships.");
            wharfForLargeShips.getShipsArrived().add(ship);
        }
    }

}

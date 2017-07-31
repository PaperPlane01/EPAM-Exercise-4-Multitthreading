package kz.javalab.multithreadingtask.shipgenerator;


import kz.javalab.multithreadingtask.entity.ship.Ship;
import kz.javalab.multithreadingtask.entity.ship.impl.HighWeightCargoShip;
import kz.javalab.multithreadingtask.entity.ship.impl.LowWeightCargoShip;
import kz.javalab.multithreadingtask.entity.ship.impl.MidWeightCargoShip;

import java.util.concurrent.ThreadLocalRandom;

public class ShipGenerator {

    public Ship generateShip() {
        int randomNumber = ThreadLocalRandom.current().nextInt();

        long minID = 100;
        long maxID = 250;

        int minWeightForLowCargoShip = 1000;
        int maxWeightForLowCargoShip = 2000;

        int minWeightForMidCargoShip = 3000;
        int maxWeightForMidCargoShip = 4000;

        int minWeightForLargeCargoShip = 5000;
        int maxWeightForLargeCargoShip = 6000;

        if (randomNumber % 2 == 0) {
            LowWeightCargoShip ship = new LowWeightCargoShip();
            long shipID = generateShipID(minID, maxID);
            int weightOfCargo = generateWeightCapacity(minWeightForLowCargoShip, maxWeightForLowCargoShip);
            ship.setID(shipID);
            ship.setWeightOfCargo(weightOfCargo);
            return ship;
        } else if (randomNumber % 3 == 0) {
            MidWeightCargoShip ship = new MidWeightCargoShip();
            long shipID = generateShipID(minID, maxID);
            int weightCapacity = generateWeightCapacity(minWeightForMidCargoShip, maxWeightForMidCargoShip);
            ship.setID(shipID);
            ship.setWeightOfCargo(weightCapacity);
            return ship;
        } else {
            HighWeightCargoShip ship = new HighWeightCargoShip();
            long shipID = generateShipID(minID, maxID);
            int weightCapacity = generateWeightCapacity(minWeightForLargeCargoShip, maxWeightForLargeCargoShip);
            ship.setID(shipID);
            ship.setWeightOfCargo(weightCapacity);
            return ship;
        }
    }


    private long generateShipID(long minID, long maxID) {
        return ThreadLocalRandom.current().nextLong(minID, maxID);
    }

    private int generateWeightCapacity(int minWeightCapacity, int maxWeightCapacity) {
        return ThreadLocalRandom.current().nextInt(minWeightCapacity, maxWeightCapacity);
    }
}

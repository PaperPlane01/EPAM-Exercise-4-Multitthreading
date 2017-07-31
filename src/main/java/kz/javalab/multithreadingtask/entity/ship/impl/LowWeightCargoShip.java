package kz.javalab.multithreadingtask.entity.ship.impl;

import kz.javalab.multithreadingtask.entity.ship.Ship;

/**
 * Created by PaperPlane on 31.07.2017.
 */
public class LowWeightCargoShip extends Ship {

    public LowWeightCargoShip() {
    }

    public LowWeightCargoShip(long ID, long weightOfCargo) {
        super(ID, weightOfCargo);
    }

    @Override
    public long getID() {
        return super.getID();
    }

    @Override
    public void setID(long ID) {
        super.setID(ID);
    }

    @Override
    public long getWeightOfCargo() {
        return super.getWeightOfCargo();
    }

    @Override
    public void setWeightOfCargo(long weightOfCargo) {
        super.setWeightOfCargo(weightOfCargo);
    }

    @Override
    public String toString() {
        return "LowWeightCargoShip ID" + getID();
    }
}

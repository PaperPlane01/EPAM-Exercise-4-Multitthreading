package kz.javalab.multithreadingtask.entity.ship;

/**
 * Created by PaperPlane on 31.07.2017.
 */
public class Ship {
    private long ID;
    private long weightOfCargo;

    public Ship() {
    }

    public Ship(long ID, long weightOfCargo) {
        this.ID = ID;
        this.weightOfCargo = weightOfCargo;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getWeightOfCargo() {
        return weightOfCargo;
    }

    public void setWeightOfCargo(long weightOfCargo) {
        this.weightOfCargo = weightOfCargo;
    }
}

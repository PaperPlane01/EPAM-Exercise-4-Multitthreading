package kz.javalab.multithreadingtask.runner;

import kz.javalab.multithreadingtask.entity.ship.impl.HighWeightCargoShip;
import kz.javalab.multithreadingtask.port.Port;

/**
 * Created by PaperPlane on 31.07.2017.
 */
public class Runner {
    public static void main(String[] args) {
        Port port = new Port();
        port.start();
    }
}

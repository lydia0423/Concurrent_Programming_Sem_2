package HelperClasses;

import Classes.*;
import java.util.concurrent.TimeUnit;

public class PassengerGenerator {

    Aircraft aircraft;
    Statistics stat;
    String operation;
    int count = 1;
    String id;

    public PassengerGenerator(Aircraft aircraft, String operation, Statistics stat) {
        this.aircraft = aircraft;
        this.stat = stat;
    }

    public void run() {
        // make 50 passengers to either board or exit a plane
        while (count <= 50) {
            String id = String.valueOf(count);
            Passenger passenger = new Passenger(id, operation, aircraft, stat);
            passenger.start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1));
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }
}

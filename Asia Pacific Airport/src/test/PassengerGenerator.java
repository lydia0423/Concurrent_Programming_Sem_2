package test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PassengerGenerator implements Runnable{
    private int count = 1;
    private String flightStatus;
    Plane aircraft;
    
    public PassengerGenerator(Plane aircraft, String flightStatus){
        this.aircraft = aircraft;
        this.flightStatus = flightStatus;
    }
    public void run(){
        while (count != 51) {
            Passenger passenger = new Passenger(aircraft, flightStatus);
            Thread thPassenger = new Thread(passenger);
            passenger.setName("Passenger " + count);
            thPassenger.start();

            try {
                TimeUnit.SECONDS.sleep((long) (Math.random() * 5));
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
            count++;
        }
        
        if (count == 51) {
            try {
                Thread.sleep(500);
                Passenger passenger = new Passenger(aircraft, flightStatus);
                Thread thPassenger = new Thread(passenger);
                thPassenger.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}

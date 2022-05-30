package test;

import java.util.concurrent.TimeUnit;

public class LuggageGenerator implements Runnable{
    private int count = 1;
    private String flightStatus;
    Plane aircraft;
    
    public LuggageGenerator(Plane aircraft, String flightStatus){
        this.aircraft = aircraft;
        this.flightStatus = flightStatus;
    }
    
    public void run(){
        while (count != 51) {
            Luggage luggage = new Luggage(aircraft, flightStatus);
            Thread thLuggage = new Thread(luggage);
            luggage.setName("Luggage " + count);
            thLuggage.start();

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
                Luggage luggage = new Luggage(aircraft, flightStatus);
                Thread thLuggage = new Thread(luggage);
                thLuggage.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}

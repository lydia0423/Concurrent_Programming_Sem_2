package asia.pacific.airport.stimulation;

import Classes.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AsiaPacificAirportStimulation {

    public static void main(String[] args) {
        int totalAircraft = 6;
        Statistics stat = new Statistics();
        Lock lock = new ReentrantLock();
        List<Aircraft> listAircraft = new LinkedList<>();
        Runway runway = new Runway();

        Airport airport = new Airport(totalAircraft, runway, lock, listAircraft, stat);
        airport.openAirport();
    }
    
}

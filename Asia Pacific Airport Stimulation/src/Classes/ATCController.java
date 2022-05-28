package Classes;

import HelperClasses.AircraftGenerator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ATCController implements Runnable{
    Airport airport;
    int totalAircraft;
    Runway runway;
    Lock lock;
    List<Aircraft> listAircraft;
    Gate gate;
    String operation = "Landing";

    
    public ATCController(Airport airport, int totalAircraft, Runway runway, Lock lock, List<Aircraft> listAircraft , Gate gate){
        this.airport = airport;
        this.totalAircraft = totalAircraft;
        this.runway = runway;
        this.lock = lock;
        this.listAircraft = listAircraft ;
        this.gate = gate;
    }

    public void run()
    {
        System.out.println("The Air Traffic Controller (ATC) is ready to give instructions.");
        
        AircraftGenerator aircraftGenerator = new AircraftGenerator (this, airport, totalAircraft, lock, listAircraft, gate);
        Thread threadAirGen = new Thread(aircraftGenerator);
        threadAirGen.start();
        
        while(airport.listAircraft.size() > 0){
            // Checks if any airplanes still queueing up.
            System.out.println("ATC: There are " + airport.listAircraft.size() + 
                    " aircrafts still waiting in-line. The next plane can proceed to the runway!");
            airport.openAirport();
        }
    }
    
    public void checkGatesLanding(Gate gate, Aircraft aircraft){
        try {
            // returns TRUE if the lock is available (meaning it acquires lock)
            if (lock.tryLock(new Random().nextInt(15), TimeUnit.SECONDS)) {
                try {
                    aircraft.requestRunway("Landing");
                    System.out.println("ATC: Gate " + gate.getGateId() + " is available now for the next aircraft");
                } 
                finally {
                    lock.unlock();
                }
            }
            else {
                System.out.println("ATC: Aircrafts waiting in-line will need to wait until the runway is free.");
                checkGatesLanding(gate, aircraft);
            }
        } catch (InterruptedException ex) {}
    }
    
    public void checkGatesTakeOff(Aircraft aircraft){
        try {
            // returns TRUE if the lock is available (meaning it acquires lock)
            if (lock.tryLock(new Random().nextInt(15), TimeUnit.SECONDS)) {
                try {
                    aircraft.requestRunway("Take Off");
                    System.out.println("ATC: Aircraft " + aircraft.getAircraftId() + " ready for take-off can use the runway now.");
                    runway.useRunway("Take Off");
                } 
                finally {
                    lock.unlock();
                }
            }
            else {
                System.out.println("ATC: Aircraft " + aircraft.getAircraftId() + " ready for take-off will need to wait until the runway is free.");
                checkGatesTakeOff(aircraft);
            }
        } catch (InterruptedException ex) {}
    }
}

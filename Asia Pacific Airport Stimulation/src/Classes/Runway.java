package Classes;

import HelperClasses.AircraftGenerator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Runway {

    void useRunway(String operation) {
        // if using runway to make a landing.
        if (operation.equals("Landing")) {
            System.out.println("Aircraft makes its landing on the runway.");
        } // else, use runway for taking off.
        else {
            System.out.println("Aircraft takes off using the runway.");
        }
    }

    public synchronized void arrivalAirport(ATCController ATC, AircraftGenerator aircraftGenerator, Aircraft aircraft, Gate gate, Lock lock) {
        aircraftGenerator.add(aircraft);
        ATC.checkGatesLanding(gate, aircraft);

        try {
            // returns TRUE if the lock is available (meaning it acquires lock)
            if (lock.tryLock(new Random().nextInt(15), TimeUnit.SECONDS)) {
                try {
                    useRunway("Landing");
                    aircraft.runway("Landing");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Aircraft " + aircraft.getAircraftId()
                        + " will continue to wait for its turn to use the runway for landing.");
            }
        } catch (InterruptedException ex) {
        }
    }

    public synchronized void departureAirport(Aircraft aircraft, Lock lock) {
        try {
            // tryLock(timeout, unit) 
            // returns TRUE if the lock is available (meaning it acquires lock)
            if (lock.tryLock(new Random().nextInt(15), TimeUnit.SECONDS)) {
                try {
                    useRunway("Take Off");
                    aircraft.runway("Take Off");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Aircraft " + aircraft.getAircraftId()
                        + " will continue to wait for its turn to use the runway for departure.");
            }
        } catch (InterruptedException ex) {
        }
    }
}

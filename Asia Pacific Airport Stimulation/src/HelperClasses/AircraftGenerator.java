package HelperClasses;

import Classes.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class AircraftGenerator implements Runnable {

    ATCController ATC;
    Airport airport;
    int totalAircraft;
    Lock lock;
    List<Aircraft> listAircraft;
    Gate gate;
    Statistics stat;

    int count = 0;
    int gates = 0;
    String id;
    Runway runway;
    Aircraft aircraft;

    public AircraftGenerator(ATCController ATC, Airport airport, int totalAircraft, Lock lock, List<Aircraft> listAircraft, Gate gate) {
        this.ATC = ATC;
        this.airport = airport;
        this.totalAircraft = totalAircraft;
        this.lock = lock;
        this.listAircraft = listAircraft;
        this.gate = gate;
    }

    public void run() {
        while (count != totalAircraft) {
            count++;
            id = String.valueOf(count);
            // creates an aircarft thread
            Aircraft aircraft = new Aircraft(id, airport, gate, runway, lock, this, stat, ATC);
            Thread thAircraft = new Thread(aircraft);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
            LocalDateTime inTime = LocalDateTime.now(); 
            System.out.println(inTime + "\t" + " Aircraft " + id + " : Appears in the sky and approaches the airport.");
            thAircraft.start();
            
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }

    public void add(Aircraft aircraft) {
        System.out.println("Aircarft " + aircraft.getAircraftId() + " is drawing close to the airport.");

        synchronized (listAircraft) {
            if (listAircraft.size() == totalAircraft) {   // Only 6 airplanes needed for the Airport Demo. This cancels extra airplanes.
                return;
            }
            ((LinkedList<Aircraft>) listAircraft).offer(aircraft);
            System.out.println("Aircraft " + aircraft.getAircraftId() + " was added to the waiting queue.");

            // a single airplane is released from the queue
            if (listAircraft.size() == 1) {
                listAircraft.notify();
            }
        }

//        gates = gate.getNumberOfGates();
        ATC.checkGatesLanding(gate, aircraft);
        //atc.checkGatesTakeOff();

    }
}

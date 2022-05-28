package Classes;

import HelperClasses.AircraftGenerator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Airport {

    int totalAircraft;
    int gates;
    Runway runway;
    Lock lock;
    List<Aircraft> listAircraft;
    Statistics stat;

    Aircraft aircraft;

    public Airport(int totalAircraft, Runway runway, Lock lock, List<Aircraft> listAircraft, Statistics stat) {
        this.totalAircraft = totalAircraft;
        this.runway = runway;
        this.lock = lock;
        this.listAircraft = listAircraft;
        this.stat = stat;
    }

    public void openAirport() {
        Gate gate = new Gate(stat);
        gate.start();

        ATCController ATC = new ATCController(this, totalAircraft, runway, lock, listAircraft, gate);
        Thread threadATC = new Thread(ATC);
        threadATC.start();

//        AircraftGenerator aircraftGenerator = new AircraftGenerator(ATC, this, totalAircraft, lock, listAircraft, gate);
//        Thread threadAirGenerator = new Thread(aircraftGenerator);
//        threadAirGenerator.start();

        synchronized (listAircraft) {
            while (listAircraft.isEmpty()) {
                System.out.println("Airport is open. Now waiting for the arrival of aircraft.");
                try {   // waiting for notify method
                    listAircraft.wait();
                } catch (InterruptedException iex) {
                    iex.printStackTrace();
                }
            }
            System.out.println("The aircrafts have arrived! They are queueing up.");

            //below will remove the first airplane in the queue, and store it in the variable
            aircraft = (Aircraft) ((LinkedList<?>) listAircraft).poll();
        }
    }
}

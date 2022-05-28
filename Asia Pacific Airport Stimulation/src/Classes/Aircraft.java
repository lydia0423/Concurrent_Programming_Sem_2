package Classes;

import HelperClasses.AircraftGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Aircraft implements Runnable{
    LocalDateTime inTime, outTime;
    String aircraftId;
    Airport airport;
    Gate gate;
    Runway runway;
    Statistics stat;
    Lock lock;
    ATCController ATC;
    AircraftGenerator aircraftGenerator;
    Thread threadAircraft; //aircraft thread
    int totalPassengers = 50;
    List<Passenger> listPassengers;
    
    public Aircraft(String aircraftId, Airport airport, Gate gate, Runway runway, Lock lock, AircraftGenerator airvraftGenerator, Statistics stat, ATCController ATC){
        this.aircraftId = aircraftId;
        this.airport = airport;
        this.gate = gate;
        this.runway = runway;
        this.lock = lock;
        this.aircraftGenerator = aircraftGenerator;
        this.stat = stat;
        this.ATC = ATC;
        
        listPassengers = new LinkedList<Passenger>();
        //threadAircraft = new Thread(this, aircraftId);
    }
    
    public String getAircraftId() {
        return aircraftId;
    }

    public void run() {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
//        inTime = LocalDateTime.now(); 
//        System.out.println(inTime + "\t" + " Aircraft " + aircraftId + " : Appears in the sky and approaches the airport.");
        requestRunway("Landing"); 
        //runway("Landing");
    }
    
    public void loadPasenger(Passenger passenger, Gate gate, Statistics stat){
        System.out.println("Aircraft " + aircraftId + " ready to load passengers at Gate " + gate.gateId + " .");
    }
    
    public void unloadPassenger(Passenger passenger, Gate gate, Statistics stat){
        System.out.println("Aircraft " + aircraftId + " ready to unload passengers at Gate " + gate.gateId + " .");
    }
    
    public void addPassenger(Passenger passenger, Statistics stat){
        System.out.println("Passenger "+ passenger.getPassengerId() + " boarded the aircraft.");
 
        synchronized (listPassengers)
        {
            if(listPassengers.size() == totalPassengers)
            {
            	System.out.println("Airplanes: The aircraft is full, with all passengers already onboard.");
                System.out.println("Airplanes: Airplane "+ aircraftId +" is ready to take off.");
                return ;
            }
 
            // Add the new passenger as the tail (last element) of the list
            ((LinkedList<Passenger>)listPassengers).offer(passenger);
            System.out.println("Aircraft " + aircraftId + " : Passenger "+ passenger.getPassengerId()+ 
                    " is seated down with safety belt fastened on securely.");
        }
    }
    
    public void requestRunway(String operation){
        if(operation.equals("Landing")){
            System.out.println("Aircraft " + aircraftId + " : Request to land on the runway.");
            runway(operation);
        }else{
            System.out.println("Aircraft " + aircraftId + " : Request to take off from the runway.");
            runway(operation);
        }
    }
    
    public void runway(String operation){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        
        try{
            runway.useRunway(operation);
        }
        finally{
            if(operation.equals("Landing")){
                System.out.println("Aircraft " + aircraftId + " : Get the permission to land on runway.");
                runway.arrivalAirport(ATC, aircraftGenerator, this, gate, lock);
            }else{
                System.out.println("Aircraft " + aircraftId + " : Get the permission to take of from the runway.");
                runway.departureAirport(this, lock);
            }
        }
    }
}

package test;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Plane implements Runnable {

    private String flightStatus;
    private int aircraftId;
    private long timeWaitingStart, timeWaitingEnd;
    private BlockingQueue<Runway> runways = null; //initialization
    private BlockingQueue<Gate> gates = null;
    private BlockingQueue<RefuelTruck> refuelTruck = null;
    private Runway runway;
    private Gate gate;
    private RefuelTruck truck;
    List<Passenger> listPassenger;
    List<Luggage> listLuggage;

    //Constructor . getting values from main
    public Plane(int aircraftId, String flightStatus, BlockingQueue<Runway> runways, BlockingQueue<Gate> gates, BlockingQueue<RefuelTruck> refuelTruck) {
        this.aircraftId = aircraftId;
        this.runways = runways;
        this.gates = gates;
        this.refuelTruck = refuelTruck;
        this.flightStatus = flightStatus;
        listPassenger = new LinkedList<Passenger>();
        listLuggage = new LinkedList<Luggage>();
        //printing after creation of a plane
        System.out.println(java.time.LocalTime.now() + " Aircraft " + aircraftId + " has reached the airport and is requesting to " + flightStatus + ".");
    }

    public void removePassenger(Passenger passenger) {
        synchronized (listPassenger) {
            if (listPassenger.size() == 50) {
                System.out.println(java.time.LocalTime.now() + " The Passengers of have disembarked from Aircraft " + aircraftId + " at the " + gate.getName());
                System.out.println("Aircraft " + aircraftId + " : The aircraft is empty, with all passengers already exit.");
                System.out.println("Aircraft " + aircraftId + " is ready for cleaning and refuel");

                try {
                    //gates.add(gate); // free up the gate
                    System.out.println("Number of gates available: " + gates.size());
                    System.out.println("Number of runway available: " + runways.size());
                    Thread.sleep(500); // take off time - 3 seconds
                    truck = refuelTruck.take();
                    System.out.println("Start refuelling for Aircraft " + aircraftId + " at the " + gate.getName() + " by " + truck.getName());
                    Thread.sleep(500);
                    System.out.println("Finish refuelling Aircraft " + aircraftId);
                    refuelTruck.add(truck);
                    flightStatus = "take off";
                    PassengerGenerator passengerGenerator = new PassengerGenerator(this, flightStatus);
                    Thread thPassenger = new Thread(passengerGenerator);
                    thPassenger.start();
                    
                    LuggageGenerator luggageGenerator = new LuggageGenerator(this, flightStatus);
                    Thread thLuggage = new Thread(luggageGenerator);
                    thLuggage.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }

            System.out.println("Aircraft " + aircraftId + " : Unload " + passenger.getName());
            ((LinkedList<Passenger>) listPassenger).offer(passenger);

            if (listPassenger.size() == 1) {
                listPassenger.notify();
            }
        }
    }

    public void addPassenger(Passenger passenger) {
        synchronized (listPassenger) {
            if (listPassenger.size() == 0) {
                System.out.println("Aircraft " + aircraftId + " : The aircraft is full, with all passengers already onboard.");
                System.out.println("Aircraft " + aircraftId + " is ready to take off.");
                System.out.println(java.time.LocalTime.now() + " The Passengers of have embarked on Aircraft " + aircraftId + " at the " + gate.getName() + " and requesting take-off.");

                try {
                    gates.add(gate);
                    runways.take();
                    System.out.println("Number of runway available: " + runways.size());
                    Thread.sleep(500);
                    System.out.println(java.time.LocalTime.now() + " Aircraft " + aircraftId + " has taken off at " + runway.getName() + " and freed it.");
                    runways.add(runway);
                    System.out.println("Number of gates available: " + gates.size());
                    if(aircraftId == 6){
                        timeWaitingEnd = System.nanoTime();
                        DecimalFormat df = new DecimalFormat("#.##");
                        double totalTimeWait = ((timeWaitingEnd - timeWaitingStart) / 1_000_000_000.0);
                        double averageTimeWait = ((timeWaitingEnd - timeWaitingStart) / 1_000_000_000.0) / 100;
                        System.out.println("\nAsia Pacific Airport Simulation Has Ended.");
                                System.out.println("\n===========================================");
                                System.out.println("Asia Pacific Airport Summary Report");
                                System.out.println("Number of passengers served : 600");
                                System.out.println("Total Waiting Time for a planne : " + df.format(totalTimeWait) + " seconds.");
                                System.out.println("Average waiting time for a plane : " + df.format(averageTimeWait) + " seconds.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }

            System.out.println("Aircraft " + aircraftId + " : Load " + passenger.getName());
            ((LinkedList<Passenger>) listPassenger).poll();
            
            if (listPassenger.size() == 1) {
                listPassenger.notify();
            }
        }
    }
    
    public void unloadLuggage(Luggage luggage) {
        synchronized (listLuggage) {
            if (listLuggage.size() == 50) {
                System.out.println(java.time.LocalTime.now() + " The luggages have been uploaded from Aircraft " + aircraftId + " at the " + gate.getName());
                return;
            }
            
            ((LinkedList<Luggage>) listLuggage).offer(luggage);
            System.out.println("Aircraft " + aircraftId + " : Unload " + luggage.getName());
            

            if (listLuggage.size() == 1) {
                listLuggage.notify();
            }
        }
    }
    
    public void loadLuggage(Luggage luggage) {
        synchronized (listLuggage) {
            if (listLuggage.size() == 0) {
                System.out.println(java.time.LocalTime.now() + " The luggages have been loaded into Aircraft " + aircraftId + " at the " + gate.getName());
                return;
            }
            
            ((LinkedList<Luggage>) listLuggage).poll();
            System.out.println("Aircraft " + aircraftId + " : Load " + luggage.getName());
            
            if (listLuggage.size() == 1) {
                listLuggage.notify();
            }
        }
    }

    public void run() {
        try {
            System.out.println("Number of runway available: " + runways.size());
            if (flightStatus == "landing") {
                runway = runways.take();
                gate = gates.take();
                System.out.println(java.time.LocalTime.now() + " The Aircraft " + aircraftId + " will be " + flightStatus + " on " + runway.getName() + ".");
                Thread.sleep(5000); // 5 seconds
                System.out.println(java.time.LocalTime.now() + " Aircraft " + aircraftId + " has be landed and " + runway.getName() + " has been freed.");
                System.out.println("Number of gates available: " + gates.size());
                System.out.println("Number of runway available: " + runways.size());
                runways.add(runway); // giving back runway or freeing it for other planes
                System.out.println("Number of runway available: " + runways.size());
                if(aircraftId == 6){
                    timeWaitingStart = System.nanoTime();
                }
                System.out.println(java.time.LocalTime.now() + " The " + gate.getName() + " is being utilized by Aircraft " + aircraftId + "");
                //System.out.println("Passengers prepare to onboard aircraft " + aircraftId);
                System.out.println(java.time.LocalTime.now() + " The Passengers of Aircraft " + aircraftId + " are disembarking " + "at the " + gate.getName() + ".");
                PassengerGenerator passengerGenerator = new PassengerGenerator(this, flightStatus);
                Thread thPassenger = new Thread(passengerGenerator);
                thPassenger.start();
                
                LuggageGenerator luggageGenerator = new LuggageGenerator(this, flightStatus);
                Thread thLuggage = new Thread(luggageGenerator);
                thLuggage.start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

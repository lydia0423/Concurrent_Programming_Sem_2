package test;

public class Luggage implements Runnable{
    private String id, flightStatus;
    Plane aircraft;

    public Luggage(Plane aircraft, String flightStatus) {
        this.aircraft = aircraft;
        this.flightStatus = flightStatus;
    }

    public String getName() {
        return id;
    }

    public void setName(String id) {
        this.id = id;
    }

    private synchronized void unloadFromAircraft(){
        aircraft.unloadLuggage(this);
    }

    private synchronized void loadIntoAircraft(){
        aircraft.loadLuggage(this);
    }

    public void run() {
        if (flightStatus == "landing") {
            unloadFromAircraft();
        } else {
            loadIntoAircraft();
        }

    }
}

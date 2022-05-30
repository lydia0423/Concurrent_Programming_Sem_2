package test;

public class Passenger implements Runnable {

    private String id, flightStatus;
    Plane aircraft;

    public Passenger(Plane aircraft, String flightStatus) {
        this.aircraft = aircraft;
        this.flightStatus = flightStatus;
    }

    public String getName() {
        return id;
    }

    public void setName(String id) {
        this.id = id;
    }

    private synchronized void exitAircraft(){
        aircraft.removePassenger(this);
    }

    private synchronized void onBoardAircraft(){
        aircraft.addPassenger(this);
    }

    public void run() {
        if (flightStatus == "landing") {
            exitAircraft();
        } else {
            onBoardAircraft();
        }

    }

}

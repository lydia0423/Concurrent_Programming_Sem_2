package Classes;

public class Passenger extends Thread{
    String passengerId, operation;    
    Aircraft aircraft;
    Statistics stat;
    
    //passenger constrcutor
    public Passenger(String passengerId, String operation, Aircraft aircraft, Statistics stat){
        this.passengerId = passengerId;
        this.operation = operation;
        this.aircraft = aircraft;
        this.stat = stat;
    }
    
    public String getPassengerId() {
        return passengerId ;
    }

    public String getOperation() {
        return operation;
    }
    
    public void run() {
        if(operation.equals("Landing")){
            System.out.println("Passengers prepare to exit Aircraft " + aircraft.getAircraftId());
            exitAircraft();
        }else{
            System.out.println("Passengers prepare to enter Aircraft " + aircraft.getAircraftId());
            onboardAircraft();
        }
    }
     
    public synchronized void onboardAircraft(){
        System.out.println("Load Passenger " + passengerId + " into " + aircraft.getAircraftId());
        aircraft.addPassenger(this, stat);
    }
    
    public synchronized void exitAircraft(){
        System.out.println("Unload Passenger " + passengerId + " from " + aircraft.getAircraftId());
    }
}

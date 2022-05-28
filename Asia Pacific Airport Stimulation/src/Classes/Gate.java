package Classes;

import java.util.concurrent.Semaphore;

public class Gate extends Thread{
    String gateId;
    Statistics stat;
    int numberOfGates;
    static Semaphore semaphore = new Semaphore(2); //only 2 gates available
    
    Gate(Statistics stat){
        this.stat = stat;
    }
    
    public int getNumberOfGates() {
        return numberOfGates;
    }

    public String getGateId() {
        return gateId = Integer.toString(numberOfGates);
    }

    public void run() {
        numberOfGates = semaphore.availablePermits();
        // airplane is still circling the sky now
        // ATC instructs airplanes to go specific gate after landing
        System.out.println("Gates: Number Of Gates Available Now: " + semaphore.availablePermits());
    }
    
    public void arrival(Aircraft aircraft){
        try{
            // asks the airplane thread to wait if no gates available
            semaphore.acquire(); 
            numberOfGates = semaphore.availablePermits();
            //TODO: Need to put aircraft id
            System.out.println("Aircraft " + aircraft.getAircraftId() + " docked to Gate " + gateId + ".");

            System.out.println("Gate: Available number of gates now: " + semaphore.availablePermits()); 
        }catch (InterruptedException e) {
            e.printStackTrace();
	} 
    }
    
    public void departure(Aircraft aircraft){
        semaphore.release();
        numberOfGates = semaphore.availablePermits();
        System.out.println("Aircraft " + aircraft.getAircraftId() + " take off from Gate " + gateId + ".");
        System.out.println("Gate: Available number of gates now: " + semaphore.availablePermits()); 
    }
    
    public void connectJetBridge() throws InterruptedException{
        System.out.println("Connect jet bridge between aircraft and gate ...");
        Thread.sleep(150);
        System.out.println("Successfully connecting aircraft and gate.");
    }
}

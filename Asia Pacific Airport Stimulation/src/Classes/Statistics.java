package Classes;

public class Statistics {
    int statsPBoarded;
    int statsAServed;
    
    synchronized void statsPBoarded(Passenger passengers){
        statsPBoarded = statsPBoarded + 1;
        //System.out.println("Statistics: Boarded passengers count increased.");
    }
    
    synchronized void statsAServed(Aircraft aricraft){
        statsAServed = statsAServed + 1;
        //System.out.println("Statistics: Number of airplanes served increased.");
    }
    
    synchronized void timeUsed(Aircraft aircraft){
        
    }
    
    public void generateReport(){
        
    }
}

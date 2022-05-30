package test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class AirTrafficController {
	public static void main(String[] args) {
		
		Random random = new Random();
		
		//Functions the same way as arrayblockingqueue but unlike arrayblockingqueue it can be optionally-bounded.
		BlockingQueue<Runway> runways = new LinkedBlockingQueue<Runway>(1);
		BlockingQueue<Gate> gates = new LinkedBlockingQueue<Gate>(2);
                BlockingQueue<RefuelTruck> refuelTruck = new LinkedBlockingQueue<RefuelTruck>(1);
		
		
		//These thread pools will used for runway and gate, respectively.
		//After plane has successfully landed then it give the aircraft to the gate.
		//After Gate does his thing it will give it back the runway for take off.
		ExecutorService runwayThreadPool = Executors.newFixedThreadPool(1);
		ExecutorService gateThreadPool = Executors.newFixedThreadPool(2);
                ExecutorService refuelTruckThreadPool = Executors.newFixedThreadPool(1);
		
		//creating runways and refuel truck objects
		for(int i = 1; i <= 1; i++) {
			runways.add(new Runway(i));
                        refuelTruck.add(new RefuelTruck(i));
		}
                
                //creating gates objects
		for(int i = 1; i <= 2; i++) {
			gates.add(new Gate(i));
		}
		
                System.out.println("The Air Traffic Controller (ATC) is ready to give instructions.");
		//contruct 6 aircrafts according to the requirements and submitting them to the thread pool
		for (int i = 1; i <= 6; i++) {
			try { 
				Thread.sleep(random.nextInt(5001));
				runwayThreadPool.submit(new Plane(i, "landing", runways, gates, refuelTruck));
				
			}catch (InterruptedException e) {
				e.printStackTrace(); //error handling method
				}	
		}
		gateThreadPool.shutdown(); 
		runwayThreadPool.shutdown();
                refuelTruckThreadPool.shutdown();
	}
}

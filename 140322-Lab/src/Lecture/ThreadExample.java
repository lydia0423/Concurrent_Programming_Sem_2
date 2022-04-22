package Lecture;

public class ThreadExample { 
	
    public static void main(String[ ] args){ 	
        System.out.println(Thread.currentThread().getName()); 
        for(int i = 0; i < 10; i++){ 
            new Thread("" + i){ 
                public void run(){ 
                    //this is called inside the threads
                    //not execute in the for loop
                    //there are a list of threads send to the CPU then CPU will decide which one to start first
                    System.out.println("Thread: " + getName() + " running"); 
                } 
            }.start(); 
        }
        
        //why i is increase in sequence but why the result are run randomly?
        //calling start is just saying the threads can be runnable (still not running yet), 
        //but it depends on the OS to decide which thread to run first
    } 
}
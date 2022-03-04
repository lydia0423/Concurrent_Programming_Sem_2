package Lecture;

public class ThreadExtend extends Thread{
    private Thread t;
    private String threadName;

    ThreadExtend(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", Loop: " + i);
                //let the thread sleep for a while (waiting state)
                Thread.sleep(500); //time-slicing -> will run another thread (randomly decided by OS)
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
    
    //actually no needed for ThreadExtend -> why?
    //because it inherit from Thread -> it is a full track object
    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start(); //call run()
        }
    }
}

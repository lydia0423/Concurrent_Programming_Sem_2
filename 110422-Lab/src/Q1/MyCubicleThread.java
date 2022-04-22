package Q1;

import java.util.concurrent.Semaphore;

class MyCubicleThread extends Thread {
    // max 2 people

    static Semaphore semaphore = new Semaphore(2);
    String name = "";

    MyCubicleThread(String name) {
        this.name = name;
    }

    public void run() {
        try {
            System.out.println(name + " : entering restroom...");
//				Acquire semaphore here
            System.out.println(name + " : available semaphore permits now : " + semaphore.availablePermits());   
            Thread.sleep(500);
            semaphore.acquire();
            System.out.println(name + " : I'm in a cubicle!");
            
            try {
                for (int i = 1; i <= 3; i++) {
                    System.out.println(name + " : is performing operation " + i + " available semaphore permits now : " + semaphore.availablePermits());
//								Check for semaphore permits
                    // sleep 1 second
                    Thread.sleep(1000);
                }
            } finally {
                // Release the semaphore
                semaphore.release();
                System.out.println(name + ": I'm Done! Available semaphore permits now : " + semaphore.availablePermits());
            }

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

}

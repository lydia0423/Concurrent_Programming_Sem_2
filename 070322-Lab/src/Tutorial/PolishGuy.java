package Tutorial;

public class PolishGuy extends Thread {

    private Thread t;
    private String threadName;

    PolishGuy(String name) {
        threadName = name;
        System.out.println("** " + threadName + " reporting for work! **");
    }

    public void run() {
        System.out.println(threadName + ": I'll start on this car!");
        try {
            for (int i = 1; i < 4; i++) {
                System.out.println(threadName + " putting on coat " + i);
                //Let thread sleep for a while
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + "interrupted.");
        }
        System.out.println("-- " + threadName + ": 3 coats of polish done! --");
    }

}

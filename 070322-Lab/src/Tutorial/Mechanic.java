package Tutorial;

public class Mechanic extends Thread {

    private Thread t;
    private String threadName;

    Mechanic(String name) {
        threadName = name;
        System.out.println("** " + threadName + " reporting for work! **");
    }

    public void run() {

        try {
            for (int i = 4; i > 0; i--) {
                switch (i) {
                    case 1:
                        System.out.println(threadName + " replacing fan belts");
                        break;
                    case 2:
                        System.out.println(threadName + " checking fan belts");
                        break;
                    case 3:
                        System.out.println(threadName + " replacing oil filter");
                        break;
                    case 4:
                        System.out.println(threadName + " replacing engine oil");
                        break;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + "interrupted.");
        }
        System.out.println("-- " + threadName + " engine repairs done! --");
    }

}

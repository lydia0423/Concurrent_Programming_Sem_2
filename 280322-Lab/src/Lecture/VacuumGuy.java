package Lecture;

public class VacuumGuy extends Thread {

    private Thread t;
    String threadName;
    VacuumCleaner vacuum;

    VacuumGuy(String name, VacuumCleaner theVacuum) {
        this.threadName = name;
        vacuum = theVacuum;
        System.out.println("** " + threadName + " reporting for work! **");
    }

    public void run() {
        try {
            vacuum.useVacuum(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-- " + threadName + " done vacuuming! --");
    }

    public String threadName() {
        return this.threadName;
    }

}

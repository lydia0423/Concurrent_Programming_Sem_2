package Lecture;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Mechanic M1 = new Mechanic("Mechanic");
        M1.start();
        M1.join();

        VacuumCleaner theVacuum = new VacuumCleaner();
        VacuumGuy V1 = new VacuumGuy("Vacuum Guy 1", theVacuum);
        V1.start();
        VacuumGuy V2 = new VacuumGuy("Vacuum Guy 2", theVacuum);
        V2.start();
    }
}

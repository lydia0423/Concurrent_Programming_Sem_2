package Tutorial;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Mechanic M1 = new Mechanic("Mechanic");
        M1.start();
        M1.join(); //wait for the threads completely finish before starting the next threads
        TyreMan T1 = new TyreMan("Tyre Guy");
        T1.start();
        T1.join();
        PolishGuy P1 = new PolishGuy("Polish Guy");
        P1.start();
        P1.join();
        VacuumGuy V1 = new VacuumGuy("Vacuum Guy 1");
        V1.start();
        VacuumGuy V2 = new VacuumGuy("Vacuum Guy 2");
        V2.start();
    }

}

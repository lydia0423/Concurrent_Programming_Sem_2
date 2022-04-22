package DoctorPatient;

public class MainClass {

    public static void main(String ar[]) {
        Doctor s = new Doctor();
        Patients u1 = new Patients(1, s);
        Patients u2 = new Patients(2, s);
        Patients u3 = new Patients(3, s);
        Patients u4 = new Patients(4, s);

        u1.start();
        u2.start();
        u3.start();
        u4.start();

        try {
            u1.join();
            u2.join();
            u3.join();
            u4.join();
        } catch (Exception e) {
        }

        //System.out.println(s.n);
    }
}

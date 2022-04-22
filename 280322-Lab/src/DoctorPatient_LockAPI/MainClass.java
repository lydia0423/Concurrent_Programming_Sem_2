package DoctorPatient_LockAPI;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

    public static void main(String ar[]) {
        Lock z1 = new ReentrantLock();
        //Lock z2 = new ReentrantLock();
        Doctor s = new Doctor();
        Patients u1 = new Patients(1, s, z1);
        Patients u2 = new Patients(2, s, z1);
        Patients u3 = new Patients(3, s, z1);
        Patients u4 = new Patients(4, s, z1);
        Patients u5 = new Patients(5, s, z1);
        Patients u6 = new Patients(6, s, z1);

        u1.start();
        u2.start();
        u3.start();
        u4.start();
        u5.start();
        u6.start();

        try {
            u1.join();
            u2.join();
            u3.join();
            u4.join();
            u5.join();
            u6.join();
        } catch (Exception e) {
        }

        //System.out.println(s.n);
    }
}

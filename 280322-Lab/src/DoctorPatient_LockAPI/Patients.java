package DoctorPatient_LockAPI;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Patients extends Thread {

    Doctor sv;
    int id;
    Lock z;

    public Patients(int id, Doctor s, Lock z) {
        this.z = z;
        this.id = id;
        sv = s;
    }

    public void run() {
        try { 
                //tryLock(long timeout, TimeUnit unit) 
            if (z.tryLock(new Random().nextInt(15), TimeUnit.SECONDS)) {
                sv.visit(this);
                z.unlock();
            } else {
                System.out.println("Patient " + id + " is tired of waiting. Bye!");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Patients.class.getName()).log(Level.SEVERE, null, ex);
        }

        //z.lock();
    }
}

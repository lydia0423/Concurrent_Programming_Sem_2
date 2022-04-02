/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoctorPatient_LockAPI;
import java.util.concurrent.locks.Lock;
/**
 *
 * @author vazeer
 */
public class Patients extends Thread {
    Doctor sv;
    int id;
    Lock z;
    
    public Patients(int id,Doctor s, Lock z)
    {
        this.z = z;
        this.id=id;
        sv=s;
    }
    
    public void run()
    {
     z.lock();  
     sv.visit(this);
     z.unlock();
               
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoctorPatient_LockAPI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author vazeer
 */
public class MainClass {
    public static void main(String ar[])
    {
        Lock z = new ReentrantLock();
        Doctor s=new Doctor();
        Patients u1 =new Patients(1,s, z);
        Patients u2 =new Patients(2,s, z);
        Patients u3 =new Patients(3,s, z);
        Patients u4 =new Patients(4,s, z);
        
        u1.start();u2.start();u3.start();u4.start();
        
        try{
            u1.join();u2.join();u3.join();u4.join();
        }catch(Exception e){}
        
        //System.out.println(s.n);
    }
}

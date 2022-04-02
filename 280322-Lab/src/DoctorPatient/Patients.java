/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoctorPatient;

/**
 *
 * @author vazeer
 */
public class Patients extends Thread {
    Doctor sv;
    int id;
    public Patients(int id,Doctor s)
    {
        this.id=id;
        sv=s;
    }
    
    public void run()
    {
            
     sv.visit(this);
            
               
        
    }
}

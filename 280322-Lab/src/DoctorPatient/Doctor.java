/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoctorPatient;

import java.util.Random;

/**
 *
 * @author vazeer
 */
public class Doctor {
    
    
   synchronized void visit(Patients use)
    {
        
        System.out.println(use.id+ " Visit Doc ");
        try{
                Thread.sleep(new Random().nextInt(5)*1000);
            }catch(Exception e){}
        System.out.println(use.id+ " left ");
        
    }

  
}

package DoctorPatient_LockAPI;

import java.util.Random;

public class Doctor {

    void visit(Patients use) {
//        if(use.id % 2 == 0){
//            System.out.println(use.id+ " Visit Doctor Sally ");
//        }else{
//            System.out.println(use.id+ " Visit Doctor Chong ");
//        }
        System.out.println(use.id + " Visit Doc");

        try {
            Thread.sleep(new Random().nextInt(5) * 1000);
        } catch (Exception e) {
        }
        System.out.println(use.id + " left ");

    }

}

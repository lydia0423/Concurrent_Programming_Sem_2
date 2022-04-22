package DoctorPatient;

public class Patients extends Thread {

    Doctor sv;
    int id;

    public Patients(int id, Doctor s) {
        this.id = id;
        sv = s;
    }

    public void run() {

        sv.visit(this);

    }
}

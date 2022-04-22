package Exercise;

import java.util.concurrent.*;

class SemaCount extends Thread {

    static volatile int n = 0;
    static Semaphore s = new Semaphore(1);

    public void run() {
        int temp;
        
        for (int i = 0; i < 100000; i++) {
            try{
                s.acquire();
            }catch(InterruptedException e){
                
            }
            temp = n;
            n = temp + 1;
            s.release();
        }
    }

    public static void main(String[] args) {
        SemaCount p = new SemaCount();
        SemaCount q = new SemaCount();
        p.start();
        q.start();

        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
        }
        System.out.println("The value of n is " + n);
    }
}

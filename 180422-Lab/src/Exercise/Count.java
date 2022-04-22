package Exercise;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

class Count extends Thread {

    static volatile int n = 0;

    public void run() {
        int temp;

        for (int i = 0; i < 100000; i++) {
            temp = n;
            n = temp + 1;
        }

    }

public static void main(String[] args) {
        Count p = new Count();
        Count q = new Count();
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

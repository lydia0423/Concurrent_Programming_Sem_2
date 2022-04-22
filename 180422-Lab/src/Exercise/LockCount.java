package Exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockCount extends Thread {

    static volatile int n = 0;
    static Lock lock = new ReentrantLock();

    public void run() {
        int temp;
        for (int i = 0; i < 100000; i++) {
            lock.lock();
            temp = n;
            n = temp + 1;
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockCount p = new LockCount();
        LockCount q = new LockCount();

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

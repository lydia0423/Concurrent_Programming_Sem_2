package Q2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    protected BlockingQueue<?> queue = null;

    public Consumer(BlockingQueue<?> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Thread.sleep(7000);
            System.out.println("Take Queue size: " + queue.size());
            System.out.println("Take " + queue.take());
            Thread.sleep(4000);
            System.out.println("Take Queue size: " + queue.size());
            System.out.println("Take " + queue.take());
            Thread.sleep(4000);
            System.out.println("Take Queue size: " + queue.size());
            System.out.println("Take " + queue.take());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

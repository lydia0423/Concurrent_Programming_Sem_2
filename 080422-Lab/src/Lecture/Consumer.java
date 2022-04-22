package Lecture;

/**
 * ******************CONSUMER******************************
 */
class Consumer implements Runnable {

    Buffer<Character> buf;

    Consumer(Buffer<Character> b) {
        buf = b;
    }

    public void run() {
        try {
            while (true) {
                ThreadPanel.rotate(180);
                Character c = buf.get();
                ThreadPanel.rotate(180);
            }
        } catch (InterruptedException e) {
        }
    }
}

package Lecture;

public class Main {
    public static void main(String[] args) {
        //two ways of creating thread:  
        //1. Runnable (used when the thread need to extends from other class)
                //RunnableThread R1 = new RunnableThread("Thread-1");
                //Thread R1Thread = new Thread(R1);
                //R1Thread.start();
                //RunnableThread R2 = new RunnableThread("Thread-2");
                //Thread R2Thread = new Thread(R2);
                //R2Thread.start();
        //2. Thread (used when no need to extends from other class)
        
//        RunnableThread R1 = new RunnableThread("Thread-1");
//        R1.start();
//        
//        RunnableThread R2 = new RunnableThread("Thread-2");
//        R2.start();
//       
        
        ThreadExtend T1 = new ThreadExtend("Thread-1");
        T1.start();
        
        ThreadExtend T2 = new ThreadExtend("Thread-2");
        T2.start();
    }
}

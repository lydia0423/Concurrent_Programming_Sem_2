package Lecture;
import Lecture.Fork;

import java.awt.*;

class Philosopher extends Thread {
  private int identity;
  private PhilCanvas view;
  private Diners controller;
  private Fork left;
  private Fork right;

  Philosopher(Diners ctr, int id, Fork l, Fork r) {
    controller = ctr; view = ctr.display;
    identity = id; left = l; right = r;
  }

  public void run() {
    try {
      while (true) {
        //thinking
        view.setPhil(identity,view.THINKING);
        sleep(controller.sleepTime());
        //hungry
        view.setPhil(identity,view.HUNGRY);
        
        right.get();
        view.setPhil(identity,view.GOTRIGHT);
        sleep(1500);
        
//        if(left.taken){ //No Pre-emption
//            right.put();
//        }else{
//            left.get();
//            view.setPhil(identity,view.GOTLEFT);
//            sleep(500);
//            
//            //eating
//            view.setPhil(identity,view.EATING);
//            sleep(controller.eatTime());
//            right.put();
//            left.put();
//        }
        
          // introducing asymmetry
        if(identity % 2 == 0){
            right.get();
            view.setPhil(identity,view.GOTRIGHT);
            sleep(500);
            left.get();
        }else{
            left.get();
            view.setPhil(identity,view.GOTLEFT);
            sleep(500);
            right.get();
            //gotright chopstick
        }
        
        //another version - will also cause problem when it happen at the same time
//        if(!right.taken && !left.taken){
//            right.get();
//            view.setPhil(identity,view.GOTRIGHT);
//            sleep(500);
//            left.get();
//            view.setPhil(identity,view.GOTLEFT);
//            sleep(500);
//        }
        //eating
        view.setPhil(identity,view.EATING);
        sleep(controller.eatTime());
        right.put();
        left.put();
      }
    } catch (java.lang.InterruptedException e) {}
  }
}

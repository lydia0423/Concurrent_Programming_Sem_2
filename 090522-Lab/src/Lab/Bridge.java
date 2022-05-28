package Lab;

class Bridge {
    
//    private int nred = 0;
//    private int nblue = 0;
//
//    synchronized void redEnter() throws InterruptedException {
//        while(nblue > 0){
//            wait();
//        }
//        ++nred;
//    }
//
//    synchronized void redExit(){
//        --nred;
//        if(nred == 0){
//            notifyAll();
//        }
//    }
//
//    synchronized void blueEnter() throws InterruptedException {
//        while (nred > 0){
//            wait();  
//        }
//        ++nblue;
//    }
//
//    synchronized void blueExit(){
//        --nblue;
//        if(nblue == 0){
//            notifyAll();
//        }
//    }
    
    private int nred = 0;
    private int nblue = 0;
    private int waitblue = 0;
    private int waitred = 0;
    private boolean blueturn = true;
    
    synchronized void redEnter() throws InterruptedException{
        ++waitred;
        while(nblue > 0 || (waitblue >0 && blueturn)){
            wait();
        }
            --waitred;
            ++nred;
        
    }
    
    synchronized void redExit(){
        --nred;
        blueturn = true;
        if(nred == 0){
            notifyAll();
        }
    }
    
    synchronized void blueEnter() throws InterruptedException{
        ++waitblue;
        while(nred >0 || (waitred > 0 && !blueturn)){
            wait();
        }
            --waitblue;
            ++nblue;
        
    }
        
    synchronized void blueExit(){
        --nblue;
        blueturn = false;
        if(nblue == 0){
            notifyAll();
        }
    }
}

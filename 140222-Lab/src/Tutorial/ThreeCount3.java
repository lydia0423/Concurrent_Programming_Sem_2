package Tutorial;

public class ThreeCount3 implements Runnable{
    final static int N = 3;
    int i;
    Thread counter;

    public static void main(String [] args){
	ThreeCount3 tc = new ThreeCount3();
	tc.start();
    }

    public void start(){
	counter = new Thread(this);
	i = 0;
	counter.start(); 
    }

    public void stop(){
        //will keep the thread without knowing what are the action of thread, will not know the program is stable or not
	counter.stop(); //deprecated method -- should avoid using it
    }

    public void run(){
	while(true){
            while (i < N){
		try{
                    counter.sleep(1000);
		}catch(Exception ex){
		
                }
		inc();
		i++;
            }
			
            while (i > 0){
                try{
                    counter.sleep(1000);
		}catch(Exception ex){}
		dec();
		i--;
                
                if(i == 0){
                   System.out.println("Bye");
//                    counter.stop();
                   return; //break out the loopb 
                }
            }
	}
    }

    public void inc(){
        System.out.println("Incrementing.....\ni = " + i);
    }

    public void dec(){
        System.out.println("Decrementing.....\ni = " + i);
    }

}
package Tutorial;

public class TyreMan extends Thread {

	private Thread t;
	private String threadName;
	
	TyreMan(String name){
		threadName=name;
		System.out.println("** "+ threadName+ " reporting for work! **");
	}
	
	public void run() {
		System.out.println(threadName + ": I'll start on this car!");
		try {
			for (int i=4; i>0; i--) {
				System.out.println(threadName + " removing tyre " +i);
				//Let thread sleep for a while
				Thread.sleep(2000);
				System.out.println(threadName + " replacing tyre " +i);
				Thread.sleep(2000);
				System.out.println(threadName + " reinstalling tyre " +i);
			}
		} catch(InterruptedException e) {
			System.out.println("Thread "+threadName + "interrupted.");
		}
		System.out.println("-- "+threadName+ ": All tyres replaced! --");
	}


}

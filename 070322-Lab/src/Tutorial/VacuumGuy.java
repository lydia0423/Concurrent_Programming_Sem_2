package Tutorial;

public class VacuumGuy extends Thread {

	private Thread t;
	private String threadName;
	
	VacuumGuy(String name){
		threadName=name;
		System.out.println("** "+ threadName+ " reporting for work! **");
	}
	
	public void run() {

		try {
			for (int i=2; i>0; i--) {
				switch (i) {
				  case 1:
				    System.out.println(threadName+ " vacuuming back seats");
				    break;
				  case 2:
				    System.out.println(threadName+ " vacuuming front seats");
				    break;
				    }
				Thread.sleep(2500);
			}
		} catch(InterruptedException e) {
			System.out.println("Thread "+threadName + "interrupted.");
		}
		System.out.println("-- "+threadName+ " done vacuuming! --");
	}


	
}

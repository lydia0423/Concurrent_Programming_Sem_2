package Q1;

// The restroom contains 2 cubicles. Visitors need to acquire a cubicle and perform
// 3 operations in it, and then release the cubicles for others to use it. 
// Use Semaphores to manage the limited cubicles resource.

public class Restroom {

	public static void main(String[] args) {
		System.out.println("Looks like there are only 2 cubicles!");
		MyCubicleThread t1 = new MyCubicleThread("A");
		t1.start();

		MyCubicleThread t2 = new MyCubicleThread("B");
		t2.start();

		MyCubicleThread t3 = new MyCubicleThread("C");
		t3.start();

		MyCubicleThread t4 = new MyCubicleThread("D");
		t4.start();

		MyCubicleThread t5 = new MyCubicleThread("E");
		t5.start();

		MyCubicleThread t6 = new MyCubicleThread("F");
		t6.start();

	}

}

package Barber;

public class Main {

	public static void main(String[] args) {
		
                Bshop shop = new Bshop();
		 
	        Barber barber = new Barber(shop);
	        CustomerGenerator cg = new CustomerGenerator(shop);
	        
	        Thread thbarber = new Thread(barber);
	        Thread thcg = new Thread(cg);
	        thcg.start();
	        thbarber.start();
	        Clock clock = new Clock(cg, barber);
                clock.start();
	}

}

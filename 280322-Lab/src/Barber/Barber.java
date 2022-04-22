package Barber;

class Barber implements Runnable {

    Bshop shop;
    public boolean closingTime = false;

    public Barber(Bshop shop) {
        this.shop = shop;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("Barber started..");
        while (!closingTime) {
            shop.cutHair();
        }
        if (closingTime) {
            while(shop.listCustomer.size() > 0){
                System.out.println("Looks like there's " + shop.listCustomer.size() + " customers left. Next!");
                shop.cutHair();
            }
            
            if(shop.listCustomer.size() == 0){
                System.out.println("Looks like there's " + shop.listCustomer.size() + " customers left. Going home now.");
                return;
            }
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }
    }

    public synchronized void setclosingTime() {
        closingTime = true;
        System.out.println("Barber : We're closing now!");
    }

}

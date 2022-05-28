package Lab;



import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class SingleLaneBridge extends Applet {

    BridgeCanvas display;
    Button restart;
    Button freeze;
    Button onecar;
    Button twocar;
    Button threecar;
    Checkbox fair;
    Checkbox safe;
    boolean fixed = false;
    int maxCar = 1;

    Thread red[];
    Thread blue[];

    public void init() {
        setLayout(new BorderLayout());
        display = new BridgeCanvas(this);
        add("Center",display);
        restart = new Button("Restart");
        restart.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             display.thaw();
           }
        });
		
        freeze = new Button("Freeze");
        freeze.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             display.freeze();
           }
        });
		
        onecar = new Button("One Car");
        onecar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             stop();
             maxCar = 1;
             start();        
           }
        });
		
        twocar = new Button("Two Cars");
        twocar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              stop();
              maxCar = 2;
              start();        
            }
         });
		
        threecar = new Button("Three Cars");
        threecar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              stop();
              maxCar = 3;
              start();        
            }
         });
		

		
        Panel p1 = new Panel();
        p1.setLayout(new FlowLayout());
        p1.add(freeze);
        p1.add(restart);
        p1.add(onecar);
        p1.add(twocar);
        p1.add(threecar);

        add("South",p1);
        setBackground(Color.lightGray);
   }


    public void start() {
        red = new Thread[maxCar];
        blue = new Thread[maxCar];
        display.init(maxCar);
        Bridge b= new Bridge();
        for (int i = 0; i<maxCar; i++) {
            red[i] = new Thread(new RedCar(b,display,i));
            blue[i] = new Thread(new BlueCar(b,display,i));
        }
        for (int i = 0; i<maxCar; i++) {
            red[i].start();
            blue[i].start();
        }
    }

    public void stop() {
        for (int i = 0; i<maxCar; i++) {
            red[i].interrupt();
            blue[i].interrupt();
        }
    }

 }

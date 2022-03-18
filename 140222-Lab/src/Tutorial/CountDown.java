package Tutorial;

import java.awt.*;
import java.applet.*;

public class CountDown extends Applet implements Runnable {

    Thread counter;
    int i;
    final static int N = 10;
    AudioClip beepSound, tickSound;
    NumberCanvas display;

    public void init() {
        add(display = new NumberCanvas("CountDown"));
        display.setSize(150, 100);
        tickSound
                = getAudioClip(getDocumentBase(), "classes/Sound/tick.au");
        beepSound
                = getAudioClip(getDocumentBase(), "classes/Sound/beep.au");
    }

    public void start() {
        counter = new Thread(this);
        i = N;
        counter.start();
    }

    public void stop() {
        counter = null;
    }

    public void run() {
        while (true) {
            if (counter == null) {
                return;
            }
            if (i > 0) {
                tick();
                --i;
            }
            if (i == 0) {
                beep();
                return;
            }
        }
    }

    private void tick() {
        display.setvalue(i);
        tickSound.play();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    private void beep() {
        display.setvalue(i);
        beepSound.play();
    }
}

class NumberCanvas extends Canvas {

    int value = 0;
    String title;

    public NumberCanvas(String title) {
        this.title = title;
        setBackground(Color.orange);
        repaint();
    }

    public void setvalue(int x) {
        value = x;
        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);

        // Display the title		
        g.setFont(new Font("Helvetica", Font.BOLD, 20));

        g.drawString(title, 10, 30);
        g.drawRect(40, 40, 30, 30);
        g.drawString(String.valueOf(value), 45, 60);
    }
}

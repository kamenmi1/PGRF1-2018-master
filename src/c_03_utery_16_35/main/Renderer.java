package c_03_utery_16_35.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer {

    private BufferedImage img;
    private Canvas canvas;
    private static final int FPS = 1000/30;

    public Renderer(BufferedImage img, Canvas canvas) {
        this.canvas = canvas;
        this.img = img;
        setTimer();
    }

    private void setTimer() {
        //časovač, který 30 krát za vteřinu obnoví obsah plátna aktuálním img
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // říct plátnu, aby zobrazil aktuální img
                canvas.getGraphics().drawImage(img, 0, 0, null);
                // co dělá observer - https://stackoverflow.com/a/1684476
            }
        },  0, FPS);
    }

    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,600);
    }

    public void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
    }
    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        float k = (y2-y1)/(float)(x2-x1); //abychom dostali desetinne cislo (float)
        float q = y1 - k*x1;

        for(int x = x1; x <= x2; x++){
            int y = Math.round (k*x+q);
            drawPixel(x, y, color);
        }
    }
}

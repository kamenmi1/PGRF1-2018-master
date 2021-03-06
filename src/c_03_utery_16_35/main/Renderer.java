package c_03_utery_16_35.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer {

    private BufferedImage img;
    private Canvas canvas;
    private static final int FPS = 1000 / 30;

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
        }, 0, FPS);
    }

    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
    }

    public void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        float k = (y2 - y1) / (float) (x2 - x1); //abychom dostali desetinne cislo (float)
        float q = y1 - k * x1;

        // řídící osa X
        if (Math.abs(k) < 1) {

            if (x1 > x2) {
                int a = x1;
                x1 = x2;
                x2 = a;

                //     a = y1;
                //     y1 = y2;
                //     y2 = a;
            }

            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                drawPixel(x, y, color);
            }
        } else {
            //končit doma // řídící osa Y
            if (y1 > y2) {
                //     int a = x1;
                //     x1 = x2;
                //     x2 = a;

                int a = y1;
                y1 = y2;
                y2 = a;
            }

            for (int y = y1; y <= y2; y++) {
                int x = Math.round((y - q) / k);
                drawPixel(x, y, color);

            }
        }
    }

    public void drawlineDDA2(int x1, int y1, int x2, int y2, int color) {
        int dx, dy;
        float k, h, g;

        dx = x2 - x1;
        dy = y2 - y1;

        k = (float) dy / dx;
        g = 1;
        h = k;

        float x = x1;
        float y = x2;


        //  if (dx>dy) {
        //      g =1; h = k;
        //  }

        for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
            drawPixel(Math.round(x), Math.round(y), color);
            x = x + g;
            y = y + h;
//DOKONČIT DOMA TODO
        }

        public void drawNuhelnik (List < Integer > points) {

            claer();
            drawLine(points.get(0), points.get(1), points.get(2), points.get(3));
            i += 2;

            drawLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3));
        }
    }
}

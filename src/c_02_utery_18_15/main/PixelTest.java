package c_02_utery_18_15.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PixelTest {

    private JFrame window;
    private BufferedImage img; // objekt pro zápis pixelů
    private Canvas canvas; // plátno pro vykreslení BufferedImage

    public PixelTest() {
        window = new JFrame();
        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(800, 600); // velikost okna
        window.setLocationRelativeTo(null);// vycentrovat okno
        window.setTitle("PGRF1 cvičení"); // titulek okna

        // inicializace image, nastavení rozměrů (nastavení typu - pro nás nedůležité)
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

        // inicializace plátna, do kterého budeme kreslit img
        canvas = new Canvas() {
            // https://stackoverflow.com/a/44491919
            @Override
            public void paint(Graphics g) {
                // při překreslení panelu zajistí vykreslení img
                g.drawImage(img, 0, 0, null);
            }
        };

        window.add(canvas); // vložit plátno do okna
        window.setVisible(true); // zorabzit okno

        drawPixel(100, 50, Color.GREEN.getRGB());
        // 0x00ff00 == Color.GREEN.getRGB()

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawPixel(e.getX(), e.getY(), 0xffffff);
            }
        });
    }

    private void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
        // říct plátnu, aby zobrazil aktuální img
        canvas.getGraphics().drawImage(img, 0, 0, null);
        // co dělá observer - https://stackoverflow.com/a/1684476
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PixelTest::new);
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
        // https://www.google.com/search?q=java+double+colon
    }
}

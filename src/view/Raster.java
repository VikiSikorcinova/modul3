package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Raster extends JPanel {

    private final BufferedImage img;
    private final Graphics g;
    private static final int FPS = 1000 / 30;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Raster() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = img.getGraphics();
        setLoop();
        clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    private void setLoop() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, FPS);
    }

    public void drawPixel(int x, int y, int color) {
        img.setRGB(x, y, color);
    }

    public void clear() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }
}

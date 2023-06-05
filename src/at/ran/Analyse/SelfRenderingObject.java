package at.ran.Analyse;
import javax.swing.*;
import java.awt.*;

public class SelfRenderingObject extends JPanel {
    private int x = 0;
    private int y = 100;

    public SelfRenderingObject() {
        JFrame frame = new JFrame("Self Rendering Object");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(this);
        frame.setVisible(true);
    }

    public void move() {
        x += 5; // Ändere die Geschwindigkeit, indem du den Wert anpasst
        if (x > getWidth()) {
            x = -50; // Starte das Objekt erneut von der linken Seite
        }
        repaint(); // Neuzeichnen des Objekts
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50); // Ändere die Größe und das Aussehen des Objekts hier
    }

    public static void main(String[] args) {
        SelfRenderingObject object = new SelfRenderingObject();
        while (true) {
            object.move();
            try {
                Thread.sleep(50); // Ändere die Bewegungsgeschwindigkeit, indem du den Wert anpasst
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

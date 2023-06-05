package at.ran.Aufgabe1;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectsFirst extends JPanel {
    private List<Rectangle> rectangles;
    private List<Circle> circles;
    private List<Ellipse> ellipses;

    public ObjectsFirst() {
        rectangles = new ArrayList<>();
        circles = new ArrayList<>();
        ellipses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            rectangles.add(new Rectangle(i % 2 == 0));
            circles.add(new Circle());
            ellipses.add(new Ellipse());
        }

        JFrame frame = new JFrame("Self Rendering Objects");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(this);
        frame.setVisible(true);
    }

    public void move() {
        for (Rectangle rectangle : rectangles) {
            rectangle.move();
        }

        for (Circle circle : circles) {
            circle.move();
        }

        for (Ellipse ellipse : ellipses) {
            ellipse.move();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Rectangle rectangle : rectangles) {
            rectangle.render(g);
        }

        for (Circle circle : circles) {
            circle.render(g);
        }

        for (Ellipse ellipse : ellipses) {
            ellipse.render(g);
        }
    }

    public static void main(String[] args) {
        ObjectsFirst objects = new ObjectsFirst();
        while (true) {
            objects.move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Rectangle {
        private int x;
        private int y = 100;
        private int width = 50;
        private int height = 50;
        private int speed = 5;
        private boolean moveRight;

        public Rectangle(boolean moveRight) {
            this.moveRight = moveRight;
            if (moveRight) {
                x = -width;
            } else {
                x = getWidth();
            }
        }

        public void move() {
            if (moveRight) {
                x += speed;
                if (x > getWidth()) {
                    x = -width;
                }
            } else {
                x -= speed;
                if (x + width < 0) {
                    x = getWidth();
                }
            }
        }

        public void render(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    private class Circle {
        private int x = 100;
        private int y = 250;
        private int diameter = 50;
        private int speed = 2;

        public void move() {
            x += speed;
            if (x > getWidth()) {
                x = -diameter;
            }
            diameter += 2;
        }

        public void render(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillOval(x, y, diameter, diameter);
        }
    }

    private class Ellipse {
        private int x = getWidth() / 2;
        private int y = 400;
        private int width = 50;
        private int height = 100;
        private int speed = 5;

        public void move() {
            if (x < getWidth()) {
                x += speed;
            } else {
                x = -width;
            }
        }

        public void render(Graphics g) {
            g.setColor(Color.GREEN);
            g.fillOval(x, y, width, height);
        }
    }
}

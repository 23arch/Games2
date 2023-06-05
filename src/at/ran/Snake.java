package at.ran;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends JPanel implements KeyListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int DOT_SIZE = 20;
    private static final int DELAY = 150;

    private List<Point> snake;
    private Point food;
    private int direction;
    private boolean gameOver;

    public Snake() {
        snake = new ArrayList<>();
        snake.add(new Point(0, 0)); // Startpunkt der Schlange
        generateFood();
        direction = KeyEvent.VK_RIGHT; // Anfangsrichtung
        gameOver = false;

        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);

        Timer timer = new Timer(DELAY, e -> {
            if (!gameOver) {
                move();
                checkCollision();
                repaint();
            }
        });
        timer.start();
    }

    private void generateFood() {
        Random random = new Random();
        int x = random.nextInt(WIDTH / DOT_SIZE) * DOT_SIZE;
        int y = random.nextInt(HEIGHT / DOT_SIZE) * DOT_SIZE;
        food = new Point(x, y);
    }

    private void move() {
        Point head = new Point(snake.get(0));
        if (direction == KeyEvent.VK_RIGHT) {
            head.x += DOT_SIZE;
        } else if (direction == KeyEvent.VK_LEFT) {
            head.x -= DOT_SIZE;
        } else if (direction == KeyEvent.VK_DOWN) {
            head.y += DOT_SIZE;
        } else if (direction == KeyEvent.VK_UP) {
            head.y -= DOT_SIZE;
        }
        snake.add(0, head);
        if (!head.equals(food)) {
            snake.remove(snake.size() - 1);
        } else {
            generateFood();
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            gameOver = true; // Kollision mit den Wänden
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true; // Kollision mit dem Körper
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.GREEN);
        for (Point point : snake) {
            g.fillRect(point.x, point.y, DOT_SIZE, DOT_SIZE);
        }

        g.setColor(Color.RED);
        g.fillRect(food.x, food.y, DOT_SIZE, DOT_SIZE);

        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Game Over", WIDTH / 2 - 60, HEIGHT / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT) {
            direction = keyCode;
        } else if (keyCode == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) {
            direction = keyCode;
        } else if (keyCode == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) {
            direction = keyCode;
        } else if (keyCode == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) {
            direction = keyCode;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Snake::new);
    }
}

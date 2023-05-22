package at.ran.games.firstgame;

import org.newdawn.slick.*;
import org.newdawn.slick.tests.AnimationTest;

public class Rectangles extends BasicGame {
    private float x;
    private float y;
    private float speed;

    private float o1;

    private float o2;

    private float widthrec;
    private float heightrec;

    // statusDirectionRect 1 = rechts, 2= up, 3=links, 4=down
    private int statusDirectionRect = 1;

    private int statusDirectionKr = 1;
    private float ml;

    public Rectangles(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.x = 100;
        this.speed = 5.0f;
        this.o1 = 0;
        this.o2 = 200;
        this.y = 400;
        this.ml = 500;
        this.heightrec = 100;
        this.widthrec = 100;
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        if (this.o2 < 400) {
            this.o2 += (float) delta / this.speed;
        } else if (this.o2 > 400) {
            this.o2 -= (float) delta / this.speed;
        }



        if (this.ml<200 && this.statusDirectionKr == 1) this.statusDirectionKr = 2;
        if (this.ml>500 && this.statusDirectionKr == 2) this.statusDirectionKr = 1;


        if (this.ml > 200 && this.statusDirectionKr==1) {
            this.ml -= (float) delta / this.speed;
        }
        if (this.ml<201 && this.statusDirectionKr==2){
            this.ml += (float) delta / this.speed;
        }

        // Bewegung Rectangle

        // Bereich Richtungswechsel feststellen
        if (this.x>599 && this.statusDirectionRect == 1) this.statusDirectionRect = 2;
        if (this.y<100 && this.statusDirectionRect == 2) this.statusDirectionRect = 3;
        if (this.x<100 && this.y < 100 && this.statusDirectionRect == 3) this.statusDirectionRect = 4;
        if (this.y>399 && this.statusDirectionRect == 4) this.statusDirectionRect = 1;
        // Bewege Objekt
        if (this.x < 600 && this.statusDirectionRect == 1) {
                this.x += (float) delta / this.speed;
        }

        if (this.y > 100 && this.statusDirectionRect == 2) {
                this.y -= (float) delta / this.speed;
        }

        if (this.x > 100 && this.statusDirectionRect == 3) {
                this.x -= (float) delta / this.speed;
        }
        if (this.y < 399 && this.statusDirectionRect == 4) {
            this.y += (float) delta / this.speed;
        }
        System.out.println(this.ml + " " +this.statusDirectionKr);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawRect(this.x, this.y, this.widthrec, this.heightrec);
        graphics.drawString("Hello you!", 50, 50);

        graphics.drawOval(this.ml, this.o1, 100, 50);
        graphics.drawOval(0, this.o2, 50, 50);
    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new Rectangles("Rectangles"));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

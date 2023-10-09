public class Enemy extends DefaultCritter {  //Coded by Jacques

    public Enemy() {
    }

    public void draw() {
        StdDraw.picture(x, y, "Enemy4.png", 11, 6);
    }

    void moveDown() {
        y -= 3;
    }

    void moveLeft() {
        x -= 1.5;
    }

    void moveRight() {
        x += 1.5;
    }
}

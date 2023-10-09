public class Missile extends DefaultCritter {  //Coded by Jacques

    double angle;

    public Missile(double angle) {
        this.angle = angle;
        initMissile();
    }

    private void initMissile() {
        move();
    }

    void move() {
        y += 0.5 * Math.cos(Math.toRadians(Math.abs(angle)));
        x += 0.5 * Math.sin(Math.toRadians(-angle));
    }

    public void draw() {
        StdDraw.picture(x, y, "Missile.png", 2, 4, angle);
    }
}

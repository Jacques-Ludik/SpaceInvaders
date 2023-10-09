public class Shooter extends DefaultCritter {  //Coded by Jacques
    double angle;

    public Shooter() {
        initPlayer();
    }

    private void initPlayer() {
        x = 30;
        y = 7;
        angle = 0;
    }

    public void draw() {
        StdDraw.picture(x, y + 2, "Turret.png", 5, 15, angle);
        StdDraw.picture(x, y, "Shooter6.png", 11, 6);
    }

    double getAngle() {
        return angle;
    }

    void setAngle(double angle) {
        this.angle = angle;
    }

}

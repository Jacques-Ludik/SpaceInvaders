public class Shooter2 extends DefaultCritter {   //Coded by George
    double angle2;

    public Shooter2() {
        initPlayer2();
    }

    private void initPlayer2() {
        x = 70;
        y = 7;
        angle2 = 0;
    }

    public void draw() {
        StdDraw.picture(x, y + 2, "Player2_turret.png", 5, 15, angle2);
        StdDraw.picture(x, y, "Player2_base.png", 11, 6);
    }

    double getAngle() {
        return angle2;
    }

    void setAngle(double angle) {
        this.angle2 = angle;
    }

}

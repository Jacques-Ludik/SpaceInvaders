public class EnemyMissile extends DefaultCritter {  //Jacques

    public EnemyMissile() {
        initMissile();
    }

    private void initMissile() {
        move();
    }

    public void move() {
        y -= 0.5;
    }

    public void draw() {
        StdDraw.picture(x, y, "Missile.png", 2, 4, 180);
    }

}

public class DefaultCritter implements Invaders.Critter {   //Coded by Jacques
    double x, y;

    // implementing default methods of the Critter API
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void draw() {
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

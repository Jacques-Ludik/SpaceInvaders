public class Invaders {  //Coded by Jacques

    public interface Critter {
        // specifying minimum API for in-game objects
        double getX();

        double getY();

        void draw();

        void setX(double x);

        void setY(double y);
    }

}

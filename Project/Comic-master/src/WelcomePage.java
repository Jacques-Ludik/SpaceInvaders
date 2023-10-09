import java.awt.*;

public class WelcomePage {  //Coded by Jacques
    public WelcomePage() {
    }

    void DrawPage() {
        Font fontHeading = new Font("Arial", Font.BOLD, 40);
        Font fontButton = new Font("Arial", Font.BOLD, 20);
        StdDraw.disableDoubleBuffering();
        StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.setFont(fontHeading);
        StdDraw.text(50, 85, "Welcome Page");
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(50, 55, 16, 10);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(50, 55, 16, 10);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(50, 30, 16, 10);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(50, 30, 16, 10);
        StdDraw.setFont(fontButton);
        StdDraw.text(50, 55, "Play");
        StdDraw.text(50, 30, "Instructions");

    }
}

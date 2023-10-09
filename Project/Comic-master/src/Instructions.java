import java.awt.*;

public class Instructions {   //Coded by Jacques


    public Instructions() {
    }

    void DrawPage() {
        Font fontHeading = new Font("Arial", Font.BOLD, 40);
        Font fontScores = new Font("Arial", Font.BOLD, 20);
        StdDraw.disableDoubleBuffering();
        StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.setFont(fontHeading);
        StdDraw.text(50, 85, "Instructions");
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(fontScores);
        StdDraw.text(50, 40 + 20, "Player 1:    Keys 'S' and 'F' for movement");   //George
        StdDraw.text(50, 35 + 20, "Player 1:    Keys 'W' and 'R' for  turret rotation");   //George
        StdDraw.text(38, 30 + 20, "Player 1:    Press 'D' to shoot");  //George

        StdDraw.text(50, 25 + 14, "Player 2:     Keys 'H' and 'K' for movement");   //George
        StdDraw.text(50, 20 + 14, "Player 2:     Keys 'Y' and 'I' for  turret rotation");  //George
        StdDraw.text(38, 15 + 14, "Player 2:     Press 'J' to shoot");   //George
        StdDraw.text(50, 7, "Press 'Q' to quit");    //Jacques

    }
}

import java.awt.*;

public class GameOver {

    public GameOver() {
    }

    void DrawPage(boolean won) {
        Font fontGameOver = new Font("Arial", Font.BOLD, 40);
        Font fontSubtitle = new Font("Arial", Font.BOLD, 30);
        Font fontScore = new Font("Arial", Font.BOLD, 15);
        Font fontSmallerSubtitle = new Font("Arial", Font.BOLD, 24);
        StdDraw.disableDoubleBuffering();
        StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.setFont(fontGameOver);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(50, 90, "Game Over");

        if (won) {
            StdDraw.setFont(fontSubtitle);
            StdDraw.text(50, 73, "Level completed!");
            StdDraw.setPenRadius(0.01);
            StdDraw.rectangle(50, 41, 30, 20);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(50, 41, 30, 20);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setPenRadius(0.005);
            StdDraw.text(50, 55, "LeaderBoard");
            StdDraw.setFont(fontScore);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(80, 12, "Press enter for next level");
            StdDraw.text(20, 12, "Press 'Q' to Welcome Page");
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(50, 47, "Player 1's winnings: " + InvaderGameState.winnings);
            StdDraw.text(50, 42, "Player 1's score: " + InvaderGameState.Score);
            StdDraw.text(50, 37, "Player 2's winnings: " + InvaderGameState.winnings2);
            StdDraw.text(50, 32, "Player 2's score: " + InvaderGameState.Score2);
            StdDraw.setPenColor(StdDraw.BLACK);
        } else {
            StdDraw.setFont(fontSubtitle);
            StdDraw.text(50, 78, "Level failed!");
            StdDraw.setPenRadius(0.01);
            StdDraw.rectangle(50, 41, 30, 20);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(50, 41, 30, 20);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setPenRadius(0.005);
            StdDraw.text(50, 53, "LeaderBoard");
            StdDraw.setPenColor(StdDraw.BLACK);

            StdDraw.setFont(fontSmallerSubtitle);
            if (InvaderGameState.Lives == 0) {
                StdDraw.text(50, 69, "Player 1 died!");
            } else if (InvaderGameState.Lives2 == 0) {
                StdDraw.text(50, 69, "Player 2 died!");
            }
            StdDraw.setFont(fontScore);
            StdDraw.text(50, 12, "Press 'Q' to Welcome Page");
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(50, 45, "Player 1's winnings: " + InvaderGameState.winnings);
            StdDraw.text(50, 40, "Player 1's score: " + InvaderGameState.Score);
            StdDraw.text(50, 35, "Player 2's winnings: " + InvaderGameState.winnings2);
            StdDraw.text(50, 30, "Player 2's score: " + InvaderGameState.Score2);
            StdDraw.setPenColor(StdDraw.BLACK);
        }
    }
}

import java.awt.*;
import java.util.ArrayList;

public class InvaderGameState {
    static int Lives;  //Coded by Jacques
    static int Lives2;  //Coded by George
    static int Score;  //Coded by Jacques
    static int Score2;   //Coded by George
    static int winnings;  //Group effort
    static int winnings2;
    static boolean start; //Coded by Jacques
    static int speed = 1;  //Coded by Joshua
    static boolean won = false; //Coded by Jacques

    public static void main(String[] args) {   //Coded by George

        // Setting up the screen
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);

        StdAudio.loopInBackground("CosmicbackgroundMusic2.wav");  //George
        WelcomePage();
    }

    static void WelcomePage() {  //Method created by Jacques
        start = false;
        // speed = 1;
        WelcomePage menu = new WelcomePage();
        if (!start) {
            menu.DrawPage();
        }
        while (!start) {
            if (StdDraw.isMousePressed() && Math.abs(StdDraw.mouseX() - 50) <= 16 && Math.abs(StdDraw.mouseY() - 55) <= 10) {
                GameState();
                start = true;
            }
            if (StdDraw.isMousePressed() && Math.abs(StdDraw.mouseX() - 50) <= 16 && Math.abs(StdDraw.mouseY() - 30) <= 10) {
                Instructions();
                start = true;
            }
        }
    }


    static void Instructions() {      //Method created by Jacques
        Instructions instruct = new Instructions();
        boolean bflag = false;
        instruct.DrawPage();
        while (!bflag) {
            if (StdDraw.isKeyPressed(81)) {  // press Q for quit
                bflag = true;
            }
        }
        if (bflag) {
            WelcomePage();
        }
    }


    static void GameState() {       //Method created by Jacques
        //////////INITIALIZE GAME
        ArrayList<Missile> missiles = new ArrayList<Missile>();     //https://www.geeksforgeeks.org/remove-element-arraylist-java/
        ArrayList<Missile> missiles2 = new ArrayList<Missile>();
        ArrayList<Enemy> enemy = new ArrayList<Enemy>();
        ArrayList<Barriers> barrier = new ArrayList<Barriers>();
        ArrayList<EnemyMissile> enemyMissiles = new ArrayList<EnemyMissile>();
        ArrayList<HealthPack> health = new ArrayList<HealthPack>();

        boolean running = true;
        won = false;
        int counter = 0;  //Counter for the reload-time of a missile and aliens periodically moving down
        boolean Right = true; //If true enemies move right and if false enemies move right.
        boolean enable = false; //If true it enables the player to shoot if false it stops the player from shooting
        Score = 0;
        Score2 = 0;
        Lives = 3;
        Lives2 = 3;

        // Font fontGameOver = new Font("Arial",Font.BOLD, 40);
        Font fontScore = new Font("Arial", Font.BOLD, 15);
        Font fontLevel = new Font("Arial", Font.BOLD, 21);

        //Initialize shooter
        Shooter player = new Shooter();
        Shooter2 player2 = new Shooter2();

        //Initialize Aliens(Enemy)
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                enemy.add(new Enemy());      //https://www.geeksforgeeks.org/remove-element-arraylist-java/
                enemy.get(k).setX(j * 14 + 22);
                enemy.get(k).setY(i * 10 + 66);
                k++;
            }
        }

        //Initialize Barriers
        int l = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                barrier.add(new Barriers());       //    https://www.geeksforgeeks.org/remove-element-arraylist-java/
                barrier.get(l).setX(j * 14 + 15);
                barrier.get(l).setY(i * 10 + 25);
                l++;
            }
        }


        ////////////GAME LOOP
        // Start animation
        StdDraw.enableDoubleBuffering();
        while (running) {

            if (StdDraw.isKeyPressed(83) && player.getX() - 6 > 0)
                player.setX(player.getX() - 0.4);   // Move player 1 left
            if (StdDraw.isKeyPressed(70) && player.getX() + 6 < 100)
                player.setX(player.getX() + 0.4);   // Move player 1 right

            if (StdDraw.isKeyPressed(72) && player2.getX() - 6 > 0)
                player2.setX(player2.getX() - 0.4);   // Move player 2 left
            if (StdDraw.isKeyPressed(75) && player2.getX() + 6 < 100)
                player2.setX(player2.getX() + 0.4);   // Move player 2 right

            // PLAYER AIM AND SHOOT
            if (counter % 80 == 0) {
                if (!enable) {
                    enable = true;
                }
            }

            //rotate player 1 turret
            if (StdDraw.isKeyPressed(87) && player.getAngle() < 51)
                player.setAngle(player.getAngle() + 0.4);  // rotate player 1 clockwise
            if (StdDraw.isKeyPressed(82) && player.getAngle() > -51)
                player.setAngle(player.getAngle() - 0.4);  // rotate player 1 counterclockwise

            //rotate player 2 turret      Coded by George
            if (StdDraw.isKeyPressed(89) && player2.getAngle() < 51)
                player2.setAngle(player2.getAngle() + 0.4);  // rotate player 1 clockwise
            if (StdDraw.isKeyPressed(73) && player2.getAngle() > -51)
                player2.setAngle(player2.getAngle() - 0.4);  // rotate player 1 counterclockwise

            // PLAYER 1 SHOOT
            if (StdDraw.isKeyPressed(68) && enable) {
                missiles.add(new Missile(player.getAngle()));     //https://www.geeksforgeeks.org/remove-element-arraylist-java/
                missiles.get(missiles.size() - 1).setX(player.getX());
                missiles.get(missiles.size() - 1).setY(8);
                StdAudio.playInBackground("playershoot.wav");
                enable = false;
            }

            // PLAYER 2 SHOOT       George
            if (StdDraw.isKeyPressed(74) && enable) {
                missiles2.add(new Missile(player2.getAngle()));
                missiles2.get(missiles2.size() - 1).setX(player2.getX());
                missiles2.get(missiles2.size() - 1).setY(8);
                StdAudio.playInBackground("playershoot.wav");
                enable = false;
            }

            // ENEMY SHOOT
            if (counter % 100 == 0) {
                int RandomEnemy = (int) Math.round(Math.random() * enemy.size());
                if (RandomEnemy < enemy.size()) {
                    enemyMissiles.add(new EnemyMissile());
                    enemyMissiles.get(enemyMissiles.size() - 1).setX(enemy.get(RandomEnemy).x);
                    enemyMissiles.get(enemyMissiles.size() - 1).setY(enemy.get(RandomEnemy).y);
                    StdAudio.playInBackground("enemyshoot.wav");
                }
            }

            //SPAWN HEALTHPACK
            if ((counter + 1) % 1000 == 0) {
                int RandomHealthx = (int) Math.round(Math.random() * 92);
                Integer[] RandomHealthy = {5, 52, 94};
                health.add(new HealthPack());
                health.get(health.size() - 1).setX(RandomHealthx + 4);
                health.get(health.size() - 1).setY(RandomHealthy[(int) Math.round(Math.random() * 2)]);
            }


            //DRAWS OBJECTS
            StdDraw.clear();  // Clears the screen

            for (int i = 0; i < missiles.size(); i++) {  //Draws players missiles
                if (missiles.get(i) != null) {           //https://www.geeksforgeeks.org/remove-element-arraylist-java/
                    missiles.get(i).move();
                    missiles.get(i).draw();
                }
            }
            for (int i = 0; i < missiles2.size(); i++) {  //Draws players missiles
                if (missiles2.get(i) != null) {
                    missiles2.get(i).move();
                    missiles2.get(i).draw();
                }
            }
            for (int i = 0; i < enemyMissiles.size(); i++) {  //Draw enemies missiles
                if (enemyMissiles.get(i) != null) {
                    enemyMissiles.get(i).move();
                    enemyMissiles.get(i).draw();
                }
            }
            player2.draw(); // Draw player 2
            player.draw();  // Draw player 1
            for (int i = 0; i < enemy.size(); i++) {      //Draw enemy
                if (enemy.get(i) != null) {
                    enemy.get(i).draw();
                }
            }
            for (int i = 0; i < barrier.size(); i++) {      //Draw barriers
                if (barrier.get(i) != null) {
                    barrier.get(i).draw();
                }
            }
            for (int i = 0; i < health.size(); i++) {      //Draw healthpacks
                if (health.get(i) != null) {
                    health.get(i).draw();
                }
            }
            StdDraw.setFont(fontScore);
            StdDraw.text(18, 97, "Score: " + Score);
            StdDraw.text(85, 97, "Score: " + Score2);
            StdDraw.text(18, 92, "Lives: " + Lives);
            StdDraw.text(85, 92, "Lives: " + Lives2);
            StdDraw.setFont(fontLevel);
            StdDraw.text(50, 95, "Level " + speed);

            StdDraw.show();
            StdDraw.pause(2);
            counter += speed;  //Timer for the reload time of a missile and the downward interval movement of the aliens


            //COLLISIONS
            //Missiles out of room
            for (int i = 0; i < missiles.size(); i++) {
                if (missiles.get(i).getY() >= 100) {     //https://www.geeksforgeeks.org/remove-element-arraylist-java/
                    missiles.remove(i);
                }
            }
            for (int i = 0; i < enemyMissiles.size(); i++) {
                if (enemyMissiles.get(i).getY() <= 0) {
                    enemyMissiles.remove(i);
                }
            }
            //Collision of enemy missiles with player 1
            for (int i = 0; i < enemyMissiles.size(); i++) {
                if ((Math.abs(enemyMissiles.get(i).getX() - player.getX()) <= 6) && (java.lang.Math.abs(enemyMissiles.get(i).getY() - player.getY()) <= 6)) {
                    enemyMissiles.remove(i);
                    StdAudio.playInBackground("player_hit.wav");   //Sound effects coded by George
                    Lives -= 1;
                }
            }
            //Collision of enemy missiles with player 2       //Coded by George
            for (int i = 0; i < enemyMissiles.size(); i++) {
                if ((Math.abs(enemyMissiles.get(i).getX() - player2.getX()) <= 6) && (java.lang.Math.abs(enemyMissiles.get(i).getY() - player2.getY()) <= 6)) {
                    enemyMissiles.remove(i);
                    StdAudio.playInBackground("player_hit.wav");
                    Lives2 -= 1;
                }
            }

            //Collision of healthpack with player
            for (int i = 0; i < health.size(); i++) {
                if ((Math.abs(health.get(i).getX() - player.getX()) <= 9) && (java.lang.Math.abs(health.get(i).getY() - player.getY()) <= 6)) {
                    health.remove(i);
                    Lives++;
                }
            }
            //Collision of healthpack with player2
            for (int i = 0; i < health.size(); i++) {
                if ((Math.abs(health.get(i).getX() - player2.getX()) <= 9) && (java.lang.Math.abs(health.get(i).getY() - player2.getY()) <= 6)) {
                    health.remove(i);
                    Lives2++;
                }
            }

            //Collision of enemy missiles with player's missiles
            for (int i = 0; i < enemyMissiles.size(); i++) {
                for (int j = 0; j < missiles.size(); j++) {
                    if (i < enemyMissiles.size() && j < missiles.size()) {
                        if ((java.lang.Math.abs(missiles.get(j).getX() - enemyMissiles.get(i).getX()) <= 3) && (java.lang.Math.abs(missiles.get(j).getY() - enemyMissiles.get(i).getY()) <= 4)) {
                            missiles.remove(j);
                            enemyMissiles.remove(i);
                        }
                    }
                }
            }
            //Collision of enemy missiles with player2's missiles       Group effort
            for (int i = 0; i < enemyMissiles.size(); i++) {
                for (int j = 0; j < missiles2.size(); j++) {
                    if (i < enemyMissiles.size() && j < missiles2.size()) {
                        if ((java.lang.Math.abs(missiles2.get(j).getX() - enemyMissiles.get(i).getX()) <= 3) && (java.lang.Math.abs(missiles2.get(j).getY() - enemyMissiles.get(i).getY()) <= 4)) {
                            missiles2.remove(j);
                            enemyMissiles.remove(i);
                        }
                    }
                }
            }
            //Collision of enemies with missiles
            for (int i = 0; i < enemy.size(); i++) {
                for (int j = 0; j < missiles.size(); j++) {
                    if (i < enemy.size() && j < missiles.size()) {
                        if ((java.lang.Math.abs(missiles.get(j).getX() - enemy.get(i).getX()) <= 6) && (java.lang.Math.abs(missiles.get(j).getY() - enemy.get(i).getY()) <= 6)) {
                            missiles.remove(j);
                            enemy.remove(i);
                            StdAudio.playInBackground("enemy_hit.wav");      //Sound effects coded by George
                            Score++;

                        }
                    }
                }
            }
            //Collision of enemies with missiles2            Group effort
            for (int i = 0; i < enemy.size(); i++) {
                for (int j = 0; j < missiles2.size(); j++) {
                    if (i < enemy.size() && j < missiles2.size()) {
                        if ((java.lang.Math.abs(missiles2.get(j).getX() - enemy.get(i).getX()) <= 6) && (java.lang.Math.abs(missiles2.get(j).getY() - enemy.get(i).getY()) <= 6)) {
                            missiles2.remove(j);
                            enemy.remove(i);
                            StdAudio.playInBackground("enemy_hit.wav");     //Sound effects coded by George
                            Score2++;

                        }
                    }
                }
            }
            //Collision of missiles with barriers
            for (int i = 0; i < barrier.size(); i++) {
                for (int j = 0; j < missiles.size(); j++) {
                    if (i < barrier.size() && j < missiles.size()) {
                        if ((java.lang.Math.abs(missiles.get(j).getX() - barrier.get(i).getX()) <= 5) && (java.lang.Math.abs(missiles.get(j).getY() - barrier.get(i).getY()) <= 5)) {
                            missiles.remove(j);
                            barrier.remove(i);
                        }
                    }
                }
            }
            //Collision of missiles2 with barriers
            for (int i = 0; i < barrier.size(); i++) {
                for (int j = 0; j < missiles2.size(); j++) {
                    if (i < barrier.size() && j < missiles2.size()) {
                        if ((java.lang.Math.abs(missiles2.get(j).getX() - barrier.get(i).getX()) <= 5) && (java.lang.Math.abs(missiles2.get(j).getY() - barrier.get(i).getY()) <= 5)) {
                            missiles2.remove(j);
                            barrier.remove(i);
                        }
                    }
                }
            }

            //Collision of healthpacks with missiles
            for (int i = 0; i < health.size(); i++) {
                for (int j = 0; j < missiles.size(); j++) {
                    if (i < health.size() && j < missiles.size()) {
                        if ((java.lang.Math.abs(missiles.get(j).getX() - health.get(i).getX()) <= 5) && (java.lang.Math.abs(missiles.get(j).getY() - health.get(i).getY()) <= 5)) {
                            missiles.remove(j);
                            health.remove(i);
                            Lives++;
                        }
                    }
                }
            }
            //Collision of healthpacks with missiles2
            for (int i = 0; i < health.size(); i++) {
                for (int j = 0; j < missiles2.size(); j++) {
                    if (i < health.size() && j < missiles2.size()) {
                        if ((java.lang.Math.abs(missiles2.get(j).getX() - health.get(i).getX()) <= 5) && (java.lang.Math.abs(missiles2.get(j).getY() - health.get(i).getY()) <= 5)) {
                            missiles2.remove(j);
                            health.remove(i);
                            Lives2++;
                        }
                    }
                }
            }
            //Collision of healthpacks with enemy missiles
            for (int i = 0; i < health.size(); i++) {
                for (int j = 0; j < enemyMissiles.size(); j++) {
                    if (i < health.size() && j < enemyMissiles.size()) {
                        if ((java.lang.Math.abs(enemyMissiles.get(j).getX() - health.get(i).getX()) <= 5) && (java.lang.Math.abs(enemyMissiles.get(j).getY() - health.get(i).getY()) <= 5)) {
                            enemyMissiles.remove(j);
                            health.remove(i);
                        }
                    }
                }
            }
            //Collision of healthpacks with enemies
            for (int i = 0; i < health.size(); i++) {
                for (int j = 0; j < enemy.size(); j++) {
                    if (i < health.size() && j < enemy.size()) {
                        if ((java.lang.Math.abs(enemy.get(j).getX() - health.get(i).getX()) <= 5) && (java.lang.Math.abs(enemy.get(j).getY() - health.get(i).getY()) <= 5)) {
                            health.remove(i);
                        }
                    }
                }
            }
            //Collision of enemy missiles with barriers
            for (int i = 0; i < barrier.size(); i++) {
                for (int j = 0; j < enemyMissiles.size(); j++) {
                    if (i < barrier.size() && j < enemyMissiles.size()) {
                        if ((java.lang.Math.abs(enemyMissiles.get(j).getX() - barrier.get(i).getX()) <= 5) && (java.lang.Math.abs(enemyMissiles.get(j).getY() - barrier.get(i).getY()) <= 5)) {
                            enemyMissiles.remove(j);
                            barrier.remove(i);
                        }
                    }
                }
            }

            //ALIENS(ENEMIES) MOVEMENT
            //Aliens moving down
            if (counter % 800 == 0) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (enemy.size() > i * 5 + j) {
                            if (enemy.get(i * 5 + j) != null) {
                                enemy.get(i * 5 + j).moveDown();
                            }
                        }
                    }
                }

            }
            //Aliens moving sideways
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    if (enemy.size() > i * 5 + j) {
                        if (enemy.get(i * 5 + j) != null) {
                            if (enemy.get(i * 5 + j).x > 90) {
                                Right = false;
                            } else if (enemy.get(i * 5 + j).x < 10) {
                                Right = true;
                            }
                        }
                    }
                }
            }
            if (counter % 20 == 0) {  //For periodical movement
                if (Right) {  //Going to the right
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (enemy.size() > i * 5 + j) {
                                if (enemy.get(i * 5 + j) != null) {
                                    enemy.get(i * 5 + j).moveRight();
                                }
                            }
                        }
                    }
                } else {   //Going to the left
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (enemy.size() > i * 5 + j) {
                                if (enemy.get(i * 5 + j) != null) {
                                    enemy.get(i * 5 + j).moveLeft();
                                }
                            }
                        }
                    }
                }

            }

            //     LEVEL UP      Group effort
            if (enemy.size() == 0) {
                if (Score > Score2) {
                    winnings++;
                }
                if (Score < Score2) {
                    winnings2++;
                }
                speed++;
                won = true;
                gameOver();
                running = false;

            }

            //GAME LOST         Group effort
            if (Lives == 0 || Lives2 == 0) {
                if (Lives2 == 0) {
                    winnings++;
                }
                if (Lives == 0) {
                    winnings2++;
                }
                won = false;
                gameOver();
                running = false;
            }
            for (int i = 0; i < enemy.size(); i++) {

                if (enemy.get(i).getY() <= 5 || (Math.abs(enemy.get(i).getX() - player.getX()) <= 7 && Math.abs(enemy.get(i).getY() - player.getY()) <= 9) || (Math.abs(enemy.get(i).getX() - player2.getX()) <= 7 && Math.abs(enemy.get(i).getY() - player2.getY()) <= 9)) {
                    gameOver();
                    running = false;
                }
            }

            //QUIT    Coded by Jacques
            if (StdDraw.isKeyPressed(81)) {
                running = false;
                WelcomePage();
            }

        }
    }

    private static void gameOver() {    //Coded by Joshua

        GameOver gameOver = new GameOver();
        gameOver.DrawPage(won);
        if (!won) {
            boolean ans = false;
            while (!ans) {
                if (StdDraw.isKeyPressed(81)) {
                    ans = true;
                    WelcomePage();
                }
            }
        } else {
            boolean ans = false;
            while (!ans) {
                if (StdDraw.isKeyPressed(81)) {
                    ans = true;
                    WelcomePage();
                } else if (StdDraw.isKeyPressed(10)) {
                    GameState();
                    ans = true;
                }
            }
        }


    }
}

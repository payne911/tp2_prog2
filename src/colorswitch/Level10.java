package colorswitch;

/**
 * Niveau 10.
 */
public class Level10 extends Level {

    public Level10(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        // Squares
        for (int i = 0; i < 45; i++){
            obstacles.add( new Square(
                            Math.random()*screenWidth,
                            300 + Math.random() * screenHeight * 1.8,
                            15 + Math.random() * 25)
            );
        }


        // circles
        for (int i = 0; i < 20; i++){
            obstacles.add( new GrowingCircle(
                    Math.random()*screenWidth,
                    screenHeight * 2.8 + Math.random() * screenHeight * 1.5,
                    5 + Math.random() * 25,
                    45 + Math.random() * 10
                    )
            );
        }

        for (int i = 0; i < 15; i++){
            obstacles.add( new RotatingCircle(
                            Math.random() * screenWidth,
                            screenHeight * 2.9 + Math.random() * screenHeight * 1.5,
                            5 + Math.random() * 25,
                            50 + Math.random() * 100,
                            0.01 + Math.random() * 0.3
                    )
            );
        }

        // horizbar
        for (int i = 0; i < 15; i++){
            obstacles.add( new VerticalBar(
                            Math.random() * screenWidth,
                            screenHeight * 5.25 + Math.random() * screenHeight * 1.5,
                            5 + Math.random() * 10,
                            30 + Math.random() * 40,
                            1 + Math.random() * 8,
                            false
                    )
            );
        }


        // Création des items
        Shield s1 = new Shield(x, screenHeight/2);
        Shield s2 = new Shield(x, screenHeight * 2.6);
        Shield s3 = new Shield(x, screenHeight * 5);

        items.add(s1);
        items.add(s2);
        items.add(s3);


        victoryMushroom = new Mushroom(x, 7.5 * screenHeight);
    }
}

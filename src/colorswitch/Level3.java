package colorswitch;

/**
 * Niveau 3.
 */
public class Level3 extends Level {

    public Level3(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle0 = new Square(x, 0.6 * screenHeight, 30);
        RotatingCircle obstacle1 = new RotatingCircle(x,
                1.05 * screenHeight, 16,70,.05);
        Square obstacle2 = new Square(x, 1.1 * screenHeight, 20);
        Square obstacle3 = new Square(x, 2.0 * screenHeight, 150);
        Square obstacle4 = new Square(x, 3 * screenHeight, 200);

        obstacles.add(obstacle0);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);

        // Création des items
        Potion potion1 = new Potion(x, 1.23 * screenHeight);
        Potion potion2 = new Potion(x, 2.6 * screenHeight);

        items.add(potion1);
        items.add(potion2);

        victoryMushroom = new Mushroom(x, 3.5 * screenHeight);
    }
}

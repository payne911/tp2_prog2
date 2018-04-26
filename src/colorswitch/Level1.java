package colorswitch;

public class Level1 extends Level {

    public Level1(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        RotatingCircle obstacle1 = new RotatingCircle(x, .7 * screenHeight,
                12, 75, .05);
        GrowingCircle obstacle2 = new GrowingCircle(x, 1.4 * screenHeight,
                10, 50);
        VerticalBar obstacle3 = new VerticalBar(x, 1.9 * screenHeight,
                30, 100);
        Square obstacle4 = new Square(x, 2.6 * screenHeight, 5);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);

        // Création des items
        Shield shield1 = new Shield(x, 1.05 * screenHeight);
        Potion potion1 = new Potion(x, 2.3 * screenHeight);

        items.add(shield1);
        items.add(potion1);

        victoryMushroom = new Mushroom(screenWidth / 2, 3.0 * screenHeight);
    }
}

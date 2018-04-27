package colorswitch;

public class Level1 extends Level {

    public Level1(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles

        Square obstacle1 = new Square(x, 0.55 * screenHeight, 30);

        obstacles.add(obstacle1);

        victoryMushroom = new Mushroom(x, 0.9 * screenHeight);
    }
}

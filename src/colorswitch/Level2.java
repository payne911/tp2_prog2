package colorswitch;
// TODO: Remplacer par notre propre Level2
public class Level2 extends Level {

    public Level2(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle1 = new Square(screenWidth / 2,
                screenHeight * 2, screenHeight * 1.9);

        obstacles.add(obstacle1);

        victoryMushroom = new Mushroom(screenWidth / 2, 100 * screenHeight);
    }
}

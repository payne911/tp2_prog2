package colorswitch;
// TODO: Remplacer par notre propre Level7
public class Level7 extends Level {

    public Level7(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        victoryMushroom = new Mushroom(x, 3.5 * screenHeight);
    }
}

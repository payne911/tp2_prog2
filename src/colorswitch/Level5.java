package colorswitch;
// TODO: Remplacer par notre propre Level5
public class Level5 extends Level {

    public Level5(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle1 = new Square(x, 0.75 * screenHeight, 40);

        obstacles.add(obstacle1);

        // Création des items
        Potion potion1 = new Potion(x, 1.15 * screenHeight);
        Potion potion2 = new Potion(x, 2.6 * screenHeight);

        items.add(potion1);
        items.add(potion2);

        victoryMushroom = new Mushroom(x, 3.5 * screenHeight);
    }
}

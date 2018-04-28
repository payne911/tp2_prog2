package colorswitch;

public class Level1 extends Level {

    public Level1(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Scroller obstacle1 = new Scroller(x, 0.75 * screenHeight, 25,
                120, 50);
        Square obstacle2 = new Square(x, 1.5 * screenHeight, 60);
        VerticalBar obstacle3 = new VerticalBar(x, 1.6 * screenHeight,
                50,70,2, false);
        Square obstacle4 = new Square(x, 2.2 * screenHeight, 300);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);

        // Création des items
        Potion potion1 = new Potion(x, 1.15 * screenHeight);

        items.add(potion1);

        victoryMushroom = new Mushroom(x, 3 * screenHeight);
    }
}

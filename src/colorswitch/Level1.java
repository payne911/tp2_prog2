package colorswitch;

public class Level1 extends Level {

    public Level1(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Scroller obstacle1 = new Scroller(x,.7 * screenHeight, 10,
                70, 50);
        Canon obstacle2 = new Canon(x*.3, 1.4 * screenHeight,
                24, 200);

        VerticalBar obstacle3 = new VerticalBar(x, 1.9 * screenHeight,
                30, 100);
        Square obstacle4 = new Square(x, 2.6 * screenHeight, 5);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);

        // Création des items

        Dizzy dizzy = new Dizzy(x, 1.05 * screenHeight);
        Shield shield = new Shield(x, 2.3 * screenHeight);

        items.add(shield);
        items.add(dizzy);

        victoryMushroom = new Mushroom(x, 3.0 * screenHeight);
    }
}

package colorswitch;

/**
 * Niveau 4.
 */
public class Level4 extends Level {

    public Level4(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        RotatingCircle obstacle0 = new RotatingCircle(x * .5, 1.05
                * screenHeight, 10, 70,.15);
        RotatingCircle obstacle1 = new RotatingCircle(x * 1.5, 1.05
                * screenHeight, 10,70,-.15);
        RotatingCircle obstacle2 = new RotatingCircle(x, .8
                * screenHeight, 15,60,-.08);

        Square obstacle3 = new Square(x, 2.1 * screenHeight, 20);
        Square obstacle4 = new Square(x, 2.5 * screenHeight, 100);

        VerticalBar obstacle5 = new VerticalBar(x, 1.7 * screenHeight,
                20,70,3, true);
        VerticalBar obstacle6 = new VerticalBar(x, 1.75 * screenHeight,
                20,90,1.5, true);
        VerticalBar obstacle7 = new VerticalBar(x, 1.8 * screenHeight,
                20,20,3.5, true);

        // Beyond the mushroom: to prevent stalls
        VerticalBar ender = new VerticalBar(x, 3.5 * screenHeight,
                200,30,15, true);

        obstacles.add(obstacle0);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);
        obstacles.add(ender);

        // Création des items
        Potion potion = new Potion(x, 1.32 * screenHeight);
        Dizzy dizzy = new Dizzy(x, 1.5 * screenHeight);

        items.add(potion);
        items.add(dizzy);

        victoryMushroom = new Mushroom(x, 2.8 * screenHeight);
    }
}

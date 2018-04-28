package colorswitch;

public class Level5 extends Level {

    public Level5(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Canon obstacle0 = new Canon(.15 * x, .8 * screenHeight, 30,
                250, 170, 1.3);
        RotatingCircle obstacle1 = new RotatingCircle(x, 1.05 * screenHeight,
                16,70,.05);
        Square obstacle2 = new Square(x, 1.1 * screenHeight, 20);

        Canon obstacle3 = new Canon(.25 * x, 2 * screenHeight, 30,
                200, 300, 1);
        Canon obstacle4 = new Canon(1.75 * x, 2 * screenHeight, 30,
                200, 170, 1);

        Scroller obstacle5 = new Scroller(x, 2.25 * screenHeight, 25,
                120, 40);
        VerticalBar obstacle6 = new VerticalBar(x, 2.55 * screenHeight,
                20,20,3, true);

        VerticalBar obstacle7 = new VerticalBar(x, 3.25 * screenHeight,
                20,90,1.5, true);
        VerticalBar obstacle8 = new VerticalBar(x, 3.23 * screenHeight,
                20,20,3.5, true);
        Canon obstacle9 = new Canon(1.8 * x, 3.4 * screenHeight,
                50, 200, 250, .7);

        obstacles.add(obstacle0);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);
        obstacles.add(obstacle8);
        obstacles.add(obstacle9);

        // Création des items
        Potion potion = new Potion(x, 1.23 * screenHeight);
        Shrinker shrinker = new Shrinker(x, 2.6 * screenHeight);

        items.add(potion);
        items.add(shrinker);

        victoryMushroom = new Mushroom(x, 3.5 * screenHeight);
    }
}

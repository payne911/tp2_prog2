package colorswitch;

public class Level7 extends Level {

    public Level7(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle0 = new Square(x, 0.6 * screenHeight, 30);
        RotatingCircle obstacle1 = new RotatingCircle(x, 1.05 * screenHeight,
                16,70,.05);
        Square obstacle2 = new Square(x, 1.1 * screenHeight, 20);
        Square obstacle3 = new Square(x, 2.0 * screenHeight, 150);
        Square obstacle4 = new Square(x, 3 * screenHeight, 200);

        obstacles.add(obstacle0);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);

        // Création des items
        Potion[] potions = new Potion[20];
        double posY = .95;

        for (int i = 0; i < potions.length; i++) {
            potions[i] = new Potion(x, posY * screenHeight);
            posY += 0.1;
        }

        for (Potion p : potions) {
            items.add(p);
        }

        victoryMushroom = new Mushroom(x, 3.5 * screenHeight);
    }
}

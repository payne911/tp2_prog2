package colorswitch;

/**
 * Niveau 7.
 */
public class Level7 extends Level {

    public Level7(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        VerticalBar obstacle0 = new VerticalBar(x, 1.77 * screenHeight,
                10,10,4.5, false);
        VerticalBar obstacle1 = new VerticalBar(x, 1.94 * screenHeight,
                10,10,4.5, false);
        VerticalBar obstacle2 = new VerticalBar(x, 2.11 * screenHeight,
                10,10,4.5, true);
        RotatingCircle obstacle3 = new RotatingCircle(x * .5, 1.05
                * screenHeight, 15, 90,.14);
        RotatingCircle obstacle4 = new RotatingCircle(x * 1.5, 1.15
                * screenHeight, 15, 90,-.14);

        RotatingCircle obstacle5 = new RotatingCircle(x*1.2, 3
                * screenHeight, 10, 140,.16);
        RotatingCircle obstacle6 = new RotatingCircle(x, 3
                * screenHeight, 10, 140,.16);
        RotatingCircle obstacle7 = new RotatingCircle(x*.8, 3
                * screenHeight, 10, 140,.16);
        Square obstacle8 = new Square(x, 3 * screenHeight, 35);

        obstacles.add(obstacle0);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);
        obstacles.add(obstacle8);

        // Création des items
        Potion[] potions = new Potion[8];
        double posY = .95;

        for (int i = 0; i < potions.length; i++) {
            potions[i] = new Potion(x, posY * screenHeight);
            posY += 0.18;
        }

        Potion potion = new Potion(x, 2.85 * screenHeight);

        for (Potion potionGroup : potions) {
            items.add(potionGroup);
        }
        items.add(potion);

        victoryMushroom = new Mushroom(x, 3.5 * screenHeight);
    }
}

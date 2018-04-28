package colorswitch;

public class Level6 extends Level {

    public Level6(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        double posX = 0;
        double posY = .95;

        VerticalBar[] verticalBars1 = new VerticalBar[13];
        VerticalBar[] verticalBars2 = new VerticalBar[11];

        // Création des obstacles
        Canon obstacle0 = new Canon(.15 * x, .8 * screenHeight, 30,
                250, 170, 1.3);
        Canon obstacle1 = new Canon(1.85 * x, 1.5 * screenHeight, 30,
                150, 150, 2);

        for (int i = 0; i < verticalBars1.length; i++) {
            verticalBars1[i] = new VerticalBar(posX, posY * screenHeight,
                    15, 50, 1.65, false);

            posX += 10;
            posY += 0.28;
        }

        posX = 0;
        posY = 4.8;

        for (int i = 0; i < verticalBars2.length; i++) {
            verticalBars2[i] = new VerticalBar(posX, posY * screenHeight,
                    15, 55, 2, true);

            posX += 8;
            posY += 0.28;
        }

        // Ajouter les groupes d'obstacles
        for (VerticalBar v : verticalBars1) {
            obstacles.add(v);
        }
        for (VerticalBar v : verticalBars2) {
            obstacles.add(v);
        }

        obstacles.add(obstacle0);
        obstacles.add(obstacle1);

        // Création des items
        Shrinker shrinker = new Shrinker(x, 4.5 * screenHeight);
        Dizzy dizzy = new Dizzy(x, .72 * screenHeight);

        items.add(shrinker);
        items.add(dizzy);

        victoryMushroom = new Mushroom(x, 8 * screenHeight);
    }
}

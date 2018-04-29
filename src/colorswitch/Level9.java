package colorswitch;

/**
 * Niveau 9.
 */
public class Level9 extends Level {

    public Level9(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        // solar system 1
        RotatingCircle rtc1 = new RotatingCircle(x, screenHeight * 0.75,
                10,10,0.2);
        RotatingCircle rtc2 = new RotatingCircle(x, screenHeight * 0.75,
                15,50,0.09);
        RotatingCircle rtc3 = new RotatingCircle(x, screenHeight * 0.75,
                19,95,0.03);

        // Asteroid field
        GrowingCircle gc1 = new GrowingCircle(x - 65, screenHeight * 1.3
                + 50, 10, 80);
        GrowingCircle gc2 = new GrowingCircle(x + 35, screenHeight * 1.3
                + 200, 8, 43);
        GrowingCircle gc3 = new GrowingCircle(x - 45, screenHeight * 1.3
                + 400, 12, 56);
        GrowingCircle gc4 = new GrowingCircle(x + 70, screenHeight * 1.3
                + 490, 8, 95);
        GrowingCircle gc5 = new GrowingCircle(x - 60, screenHeight * 1.3
                + 660, 30, 100);

        // Boss
        Canon c1 = new Canon(30, screenHeight * 3.4,60,
                200,200,0.5);
        Canon c2 = new Canon(screenWidth - 30, screenHeight * 3.4,
                60,200,150,0.5);

        Canon c3 = new Canon(80, screenHeight * 3.4,30,
                100,300,0.28);
        Canon c4 = new Canon(screenWidth - 80, screenHeight * 3.4,
                30,100,300,0.28);

        // solar system 2
        RotatingCircle rtc4 = new RotatingCircle(x, screenHeight * 4.3,
                12,20,0.2);
        RotatingCircle rtc5 = new RotatingCircle(x, screenHeight * 4.3,
                18,60,0.09);
        RotatingCircle rtc6 = new RotatingCircle(x, screenHeight * 4.3,
                11,115,0.03);
        RotatingCircle rtc7 = new RotatingCircle(x, screenHeight * 4.3,
                7,140,0.01);

        obstacles.add(rtc1);
        obstacles.add(rtc2);
        obstacles.add(rtc3);
        obstacles.add(rtc4);
        obstacles.add(rtc5);
        obstacles.add(rtc6);
        obstacles.add(rtc7);
        obstacles.add(gc1);
        obstacles.add(gc2);
        obstacles.add(gc3);
        obstacles.add(gc4);
        obstacles.add(gc5);
        obstacles.add(c1);
        obstacles.add(c2);
        obstacles.add(c3);
        obstacles.add(c4);


        // Création des items
        Shrinker s1 = new Shrinker(x, screenHeight * 1.3 - 50);

        items.add(s1);

        victoryMushroom = new Mushroom(x, 5 * screenHeight);
    }
}

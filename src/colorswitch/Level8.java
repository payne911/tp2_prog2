package colorswitch;
// TODO: Remplacer par notre propre Level8
public class Level8 extends Level {

    public Level8(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;
        double spacing = 200;
        double range = 200;
        double size = 30;
        double baseSpeed = 100;
        double rate = 2;

        Canon[] canonsRight = new Canon[10];
        Canon[] canonsLeft = new Canon[10];

        // Création des obstacles
        for (int i = 0; i < canonsRight.length; i++){
            canonsRight[i] = new Canon(screenWidth - (size + 5), spacing + spacing * i, size,range,
                    Math.random() * baseSpeed + baseSpeed, rate + Math.random());
        }

        for (int i = 0; i < canonsLeft.length; i++){
            canonsLeft[i] = new Canon(size + 5, spacing + spacing * i, size,range,
                    (Math.random() * baseSpeed + baseSpeed), rate + Math.random());
        }

        VerticalBar vb1 = new VerticalBar(ColorsWitch.WIDTH * Math.random(), 0.65 * screenHeight, 10, 60,1, false);
        VerticalBar vb2 = new VerticalBar(ColorsWitch.WIDTH * Math.random(), 1.05 * screenHeight, 10, 60,1, false);
        VerticalBar vb3 = new VerticalBar(ColorsWitch.WIDTH * Math.random(), 1.5 * screenHeight, 10, 60,2, false);

        Scroller scroller1 = new Scroller(x,2 * screenHeight,15,90,60);

        VerticalBar vb4 = new VerticalBar(ColorsWitch.WIDTH * Math.random(), 2.25 * screenHeight, 10, 60,1, false);
        VerticalBar vb5 = new VerticalBar(ColorsWitch.WIDTH * Math.random(), 2.7 * screenHeight, 10, 60,2, false);
        VerticalBar vb6 = new VerticalBar(ColorsWitch.WIDTH * Math.random(), 3.1 * screenHeight, 10, 60,1, false);

        Scroller scroller2 = new Scroller(x,3.5 * screenHeight,15,100,80);
        Scroller scroller3 = new Scroller(x,3.5 * screenHeight + 15,15,100,-80);


        for (Canon c : canonsLeft){
            obstacles.add(c);
        }
        for (Canon c : canonsRight){
            obstacles.add(c);
        }

        obstacles.add(scroller1);
        obstacles.add(scroller2);
        obstacles.add(scroller3);
        obstacles.add(vb1);
        obstacles.add(vb2);
        obstacles.add(vb3);
        obstacles.add(vb4);
        obstacles.add(vb5);
        obstacles.add(vb6);

        Dizzy dizzy = new Dizzy(ColorsWitch.WIDTH / 2,2 * screenHeight + 50);



        items.add(dizzy);

        victoryMushroom = new Mushroom(x, 6 * screenHeight);
    }
}

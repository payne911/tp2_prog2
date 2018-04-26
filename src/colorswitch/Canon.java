package colorswitch;

import javafx.application.Platform;


/**
 * Obstacle simple : un canon rouge qui lance des balles vers le joueur.
 */
public class Canon extends Obstacle {

    private double width;
    private double timeSinceLastShot = 0;
    private Level level;
    private Player player;
    private double posX;
    private double posY;
    private boolean initialized = false;
    private double range;

    public Canon(double x, double y, double longueur, double range) {
        super(x, y);

        this.posX = x;
        this.posY = y;
        this.width = longueur;
        this.range = range;
        this.player = Game.getPlayer();
        this.renderer = new CanonRenderer(this);

        this.color = 4;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public double getRange() {
        return range;
    }

    @Override
    public double getHeight() {
        return width;
    }

    @Override
    public void tick(double dt) {

        timeSinceLastShot += dt;

        if (timeSinceLastShot > 2) {

            // To prevent NullPointer error, this attribute is assigned here
            if (!initialized) {
                initialized = !initialized;
                this.level = Game.getLevel();
            }

            double x = player.getX();
            double y = player.getY();
            double r = player.getRadius();

            // If the player is within range
            if (x - r < posX + range
                    && x + r > posX - range
                    && y - r < posY + range
                    && y + r > posY - range) {

                // Le canon tire une balle!
                Platform.runLater(() ->
                        level.obstacles.add(new Ball(posX, posY, 3, 70))
                );

                timeSinceLastShot = 0;
            }


        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {
        return false;
    }
}

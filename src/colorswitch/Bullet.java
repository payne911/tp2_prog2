package colorswitch;

import javafx.application.Platform;

/**
 * Obstacle :
 * Une balle est générée par un Canon et se dirige vers une position fixe
 * qui est celle où le joueur était placé lors de la création de la balle.
 * Elle change de couleur avec le temps.
 */
public class Bullet extends Obstacle {

    private double radius;
    private double speed;
    private double posX;
    private double posY;
    private double targetY;
    private double timeSinceColorChange = 0;
    private Object self;

    /**
     * Constructeur.
     *
     * @param x             Position en X du point de rotation.
     * @param y             Position en Y du point de rotation.
     * @param radius        Rayon du cercle qui bouge.
     * @param speed         Vitesse de la balle.
     */
    public Bullet(double x, double y, double radius, double speed) {
        super(x, y);

        this.self = this;

        this.radius = radius;
        this.speed = speed;
        this.posX = x;
        this.posY = y;

        this.targetY = Game.getPlayer().getY() - posY;
        this.renderer = new BulletRenderer(this);

        this.color = (int) (Math.random() * 4);
    }

    public double getRadius(){
        return radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    @Override
    public void tick(double dt) {

        // TODO: Tweak these values! It should aim at the player directly, and not adjust (afterward).
        posX += speed * dt;
        posY += targetY * dt;

        if (posX > 500) {
            Platform.runLater(() ->
                    Game.getLevel().getObstacles().remove(self)
            );
        }

        // Pour le changement de couleur
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 1.5) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {

        double x = player.getX();
        double y = player.getY();
        double r = player.getRadius();

        return this.color != player.getColor()
                && x - r < posX + radius
                && x + r > posX - radius
                && y - r < posY + radius
                && y + r > posY - radius;
    }
}

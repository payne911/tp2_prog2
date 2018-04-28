package colorswitch;

/**
 * Cercle qui apparaît lors de la mort de la sorcière.
 */
public class DeathCircle extends Obstacle {

    private int color;
    private double radius;
    private double vy;
    private double ay;
    private double vx;
    private double speed;

    /**
     * Un des 100 cercles qui remplace le joueur lorsqu'il meurt.
     *
     * @param x Sa position en X.
     * @param y Sa position en Y.
     */
    public DeathCircle(double x, double y) {
        super(x, y);

        this.speed = Math.random() * 600;

        double angle = (Math.random() * Math.PI * 2);

        this.vy = Math.sin(angle) * speed * 0.8 + Game.getPlayer().getYSpeed();
        this.vx = Math.cos(angle) * speed;

        this.ay = -400;

        this.renderer = new DeathCircleRenderer(this);

        this.radius = Math.random() * 3 + 2;
        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return this.getRadius() * 2;
    }

    @Override
    public double getHeight() {
        return this.getRadius() * 2;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void tick(double dt) {

        // Mise à jour de la vitesse
        vy += dt * ay;

        // Mise à jour de la position
        y += dt * vy;
        x += dt * vx;

        // Touche au côté gauche de l'écran
        if (x - radius <= 0) {
            x = radius + 1;
            vx = -vx * 0.9;
        }

        // Touche au côté droit de l'écran
        if (x + radius >= ColorsWitch.WIDTH) {
            x = ColorsWitch.WIDTH - radius - 1;
            vx = -vx * 0.9;
        }

        // Vitesse miniale: -300
        vy = Math.max(vy, -300);

        vx *= 0.99999;
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {
        return false;
    }
}

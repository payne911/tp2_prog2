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
    private double ax;

    public DeathCircle(double x, double y) {
        super(x, y);

        this.vy = randomSign() * Math.random() * 400;
        this.vx = randomSign() * Math.random() * 150;

        this.ay = -400;
        this.ax = 400 * vx / Math.abs(vx) ;

        this.renderer = new DeathCircleRenderer(this);

        this.radius = Math.random() * 3 + 3;
        this.color = (int) (Math.random() * 4);
    }

    /**
     * Pour obtenir un signe négatif ou positif de manière aléatoire
     *
     * @return 1.0 ou -1.0
     */
    public double randomSign() {
        double x = Math.random();

        if(x < 0.5)
            return 1.0;
        else
            return -1.0;
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
        vx += dt * ax;

        // Mise à jour de la position
        y += dt * vy;
        x += dt * vx;

        // Touche au côté gauche de l'écran
        if (x - radius <= 0) {
            x = radius + 1;
            vx = -vx/30;
            ax = -ax/1.5;
        }

        // Touche au côté droit de l'écran
        if (x + radius >= ColorsWitch.WIDTH) {
            x = ColorsWitch.WIDTH - radius - 1;
            vx = -vx/30;
            ax = -ax/1.5;
        }

        // Vitesse miniale: -300
        vy = Math.max(vy, -300);
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {
        return false;
    }
}

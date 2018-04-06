package colorswitch;

/**
 * Obstacle simple :
 * Il s’agit d’un cercle qui grossit et rétrécit avec le temps
 * et dont la couleur change avec le temps (à la même
 * vitesse que le Square donné en exemple)
 */
public class GrowingCircle extends Obstacle {

    private double radius;
    private double minRadius;
    private double maxRadius;
    private boolean growing = true;
    private double timeSinceColorChange = 0;

    public GrowingCircle(double x, double y,
                         double minRadius, double maxRadius) {
        super(x, y);

        this.radius = minRadius;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.renderer = new GrowCRenderer(this);

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

    @Override
    public void tick(double dt) {

        // Pour le changement de rayon
        if(growing) {
            radius += .5;
            if(radius >= maxRadius)
                growing = false;
        } else {
            radius -= .5;
            if(radius <= minRadius)
                growing = true;
        }

        // Pour le changement de couleur
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 2) {
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
                && x - r < this.getX() + radius
                && x + r > this.getX() - radius
                && y - r < this.getY() + radius
                && y + r > this.getY() - radius;
    }
}

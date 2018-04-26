package colorswitch;

/**
 * Obstacle simple :
 * Il s’agit d’un cercle de taille fixe qui tourne autour d’un point
 * central et dont la couleur change avec le temps (à la même vitesse
 * que le Square donné en exemple)
 */
public class RotatingCircle extends Obstacle {

    private double circRadius;
    private double speed;
    private double posX;
    private double posY;
    private double timeSinceColorChange = 0;

    /**
     * Constructeur.
     *
     * @param x             Position en X du point de rotation.
     * @param y             Position en Y du point de rotation.
     * @param circRadius    Rayon du cercle qui bouge.
     * @param rotRadius     Distance entre le cercle et son point de rotation.
     * @param speed         Vitesse à laquelle le cercle tourne (1 = normal).
     */
    public RotatingCircle(double x, double y, double circRadius,
                          double rotRadius, double speed) {
        super(x, y);

        this.circRadius = circRadius;
        this.speed = speed;
        this.posX = x;
        this.posY = y - rotRadius; // starts at the bottom-most part
        this.renderer = new RotatingCircleRenderer(this);

        this.color = (int) (Math.random() * 4);
    }

    public double getCircRadius(){
        return circRadius;
    }

    @Override
    public double getWidth() {
        return circRadius * 2;
    }

    @Override
    public double getHeight() {
        return circRadius * 2;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    @Override
    public void tick(double dt) {

        // Pour la rotation autour du centre
        double angle = speed;
        double x1 = posX - this.getX();
        double y1 = posY - this.getY();

        double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
        double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);

        posX = x2 + this.getX();
        posY = y2 + this.getY();


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
                && x - r < posX + circRadius
                && x + r > posX - circRadius
                && y - r < posY + circRadius
                && y + r > posY - circRadius;
    }
}

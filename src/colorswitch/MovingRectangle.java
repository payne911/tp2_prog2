package colorswitch;

/**
 * Obstacle simple :
 * Un rectangle qui bouge horizontalement, aligné avec d'autres rectangles.
 */
public class MovingRectangle extends Obstacle {

    private double width;
    private double height;

    /**
     * Constructeur.
     *
     * @param x         Position en X.
     * @param y         Position en Y.
     * @param longueur  Largeur du rectangle.
     * @param hauteur   Hauteur du rectangle.
     * @param color     Couleur du rectangle (entier entre 0 et 3).
     */
    public MovingRectangle(double x, double y, double longueur,
                           double hauteur, int color) {
        super(x, y);

        this.width = longueur;
        this.height = hauteur;
        this.renderer = new MovingRectangleRenderer(this);

        this.color = color;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void tick(double dt) {
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
                && x - r < this.getX() + this.getWidth() / 2
                && x + r > this.getX() - this.getWidth() / 2
                && y - r < this.getY() + this.getHeight() / 2
                && y + r > this.getY() - this.getHeight() / 2;
    }
}

package colorswitch;

/**
 * Obstacle simple :
 * Il s’agit simplement d’une barre verticale dont la couleur ne
 * change pas et qui se déplace de gauche à droite en rebondissant
 * sur les murs du niveau
 */
public class VerticalBar extends Obstacle {

    private double width;
    private double height;
    private boolean movingRight = true;

    public VerticalBar(double x, double y, double largeur, double hauteur) {
        super(x, y);

        this.width = largeur;
        this.height = hauteur;
        this.renderer = new VerticalBarRenderer(this);

        this.color = (int) (Math.random() * 4);
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

        double x = this.getX();

        if(movingRight) {
            x += 1.8;
            if(x >= ColorsWitch.WIDTH - width/2)
                movingRight = false;
        } else {
            x -= 1.8;
            if(x <= width/2)
                movingRight = true;
        }

        this.setX(x);
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
                && x - r < this.getX() + width / 2
                && x + r > this.getX() - width / 2
                && y - r < this.getY() + height / 2
                && y + r > this.getY() - height / 2;
    }
}

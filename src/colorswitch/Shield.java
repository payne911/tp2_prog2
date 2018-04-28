package colorswitch;

/**
 * Item : Bouclier magique.
 *
 * Rend la sorcière invincible pendant 3 secondes.
 */
public class Shield extends Item {

    /**
     * Constructeur.
     *
     * @param x     Position en X.
     * @param y     Position en Y.
     */
    public Shield(double x, double y) {
        super(x, y);

        this.renderer = new ImageRenderer("shield", this);
    }

    @Override
    public void tick(double dt) {
    }

    @Override
    public double getWidth() {
        return 32;
    }

    @Override
    public double getHeight() {
        return 32;
    }

    @Override
    public void handleCollision(Player player, Game game) {

        // push outside of the screen
        this.setX(this.getWidth() + game.getScreenWidth());

        player.makeInvincible();
    }

    @Override
    public boolean intersects(Player player) {

        double x = player.getX();
        double y = player.getY();
        double r = player.getRadius();

        return x - r < this.getX() + this.getWidth() / 2
                && x + r > this.getX() - this.getWidth() / 2
                && y - r < this.getY() + this.getHeight() / 2
                && y + r > this.getY() - this.getHeight() / 2;
    }
}

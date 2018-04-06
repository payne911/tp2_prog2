package colorswitch;

/**
 * Item : Bouclier magique.
 *
 * Rend la sorcière invincible pendant 3 secondes.
 */
public class Shield extends Item {

    private boolean used = false;

    public Shield(double x, double y) {
        super(x, y);

        this.renderer = new ImageRenderer("shield", this);
    }

    @Override
    public void tick(double dt) {
        // Rien à faire
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
        used = true;
        this.setX(this.getWidth() + game.getScreenWidth());
        /* TODO: Possibly change image instead?
        * this.renderer = new ImageRenderer("blank", this);
        * */
        player.makeInvincible();
    }

    @Override
    public boolean intersects(Player player) {

        double x = player.getX();
        double y = player.getY();
        double r = player.getRadius();

        return !used
                && x - r < this.getX() + this.getWidth() / 2
                && x + r > this.getX() - this.getWidth() / 2
                && y - r < this.getY() + this.getHeight() / 2
                && y + r > this.getY() - this.getHeight() / 2;
    }
}

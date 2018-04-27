package colorswitch;

/**
 * Item : Étourdissement.
 *
 * La sorcière a un peu trop bu de potion... elle déroute horizontalement!
 */
public class Dizzy extends Item {

    public Dizzy(double x, double y) {
        super(x, y);

        this.renderer = new ImageRenderer("dizzy", this);
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

        // push outside of the screen
        this.setX(this.getWidth() + game.getScreenWidth());

        player.makeDizzy();
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

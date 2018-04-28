package colorswitch;

/**
 * Item : Shrinker.
 * 
 * Rends le joueur plus petit et plus rapide.
 */
public class Shrinker extends Item {

    private int number = 13;
    private int frameRate = 20;

    /**
     * Constructeur.
     *
     * @param x     Position en X.
     * @param y     Position en Y.
     */
    public Shrinker(double x, double y) {
        super(x, y);

        this.renderer = new AnimationRenderer("shrinker_anim",
                this.number, this.frameRate, this);
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

        player.shrink();

        // push outside of the screen
        this.setX(this.getWidth() + game.getScreenWidth());
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

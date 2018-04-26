package colorswitch;

/**
 * Item : champignon.
 * 
 * Ramasser un champignon permet de gagner le niveau actuel
 */
public class Mushroom extends Item {

    private int number = 26;
    private int frameRate = 30;

    public Mushroom(double x, double y) {
        super(x, y);

        this.renderer = new AnimationRenderer("mushroom_animation",
                this.number, this.frameRate, this);
    }

    @Override
    public void tick(double dt) {
    }

    @Override
    public double getWidth() {
        return 64;
    }

    @Override
    public double getHeight() {
        return 64;
    }

    @Override
    public void handleCollision(Player player, Game game) {
        game.win();
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

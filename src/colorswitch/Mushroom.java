package colorswitch;

/**
 * Item : champignon.
 * 
 * Ramasser un champignon permet de gagner le niveau actuel
 */
public class Mushroom extends Item {

    private int number = 26;
    private int frameRate = 30;
    private int currImg = 0;
    private int currFrame = 0;
    private AnimationRenderer animRenderer;

    public Mushroom(double x, double y) {
        super(x, y);

        animRenderer = new AnimationRenderer("mushroom_animation",
                this.number, this.frameRate, this);

        this.renderer = animRenderer.getImgRenderer(0);
    }

    @Override
    public void tick(double dt) {

        currFrame++;

        if(currFrame >= 60/frameRate) { // "frameRate" fois par seconde

            currFrame = 0;
            currImg++;

            if (currImg > 25)
                currImg = 0;

            // TODO: Respecte le M-V-C ? ("Potion" fait déjà cette erreur)
            this.renderer = animRenderer.getImgRenderer(currImg);
        }
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

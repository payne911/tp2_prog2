package colorswitch;

/**
 * Éléments qui composent un niveau.
 *
 * Le joueur peut interagir avec tous les objets de ce type en entrant en
 * collision avec.
 */
public abstract class LevelElement extends Entity {

    public LevelElement(double x, double y) {
        super(x, y);
    }

    public abstract boolean intersects(Player player);

    public abstract void handleCollision(Player player, Game game);
}

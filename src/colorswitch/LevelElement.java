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

    /**
     * Vérifie si l'entité intersecte la sorcière.
     *
     * @param player    La sorcière.
     * @return  'true' si oui, 'false' sinon.
     */
    public abstract boolean intersects(Player player);

    /**
     * S'occupe de gérer la collision.
     *
     * @param player    La sorcière.
     * @param game      Le jeu.
     */
    public abstract void handleCollision(Player player, Game game);
}

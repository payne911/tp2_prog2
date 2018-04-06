package colorswitch;

import java.util.List;


public class Controller {

    private Game game;
    private int level = 1;

    /**
     * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
     */
    public Controller() {
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
    }

    public List<Entity> getEntities() {
        return this.game.getEntities();
    }

    public Level getCurrentLevel() {
        return this.game.getLevel();
    }

    /**
     * Fonction appelée à chaque frame du jeu.
     * @param dt Delta-temps exprimé en secondes
     */
    public void tick(double dt) {
        if (this.game.isGameOver()) {
            if (this.game.hasWon()) {
                level++;
            }

            this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
        } else {
            this.game.tick(dt);
        }
    }

    /**
     * Fonction appelée lorsque la barre espace est enfoncée.
     */
    public void spaceTyped() {
        this.game.jump();
    }
}

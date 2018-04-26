package colorswitch;

import java.util.List;

/**
 * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
 */
public class Controller {

    private Game game;
    private int level = 1;

    public Controller() {
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
    }

    public List<Entity> getEntities() {
        return this.game.getEntities();
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

    public Level getCurrentLevel() {
        return this.game.getLevel();
    }

    /**
     * Fonction appelée lorsque la barre espace est enfoncée.
     */
    public void spaceTyped() {
        this.game.jump();
    }

    /**
     * Fonction appelée lorsque le TAB est enfoncé.
     */
    public void tabTyped() {
        System.out.println("TAB pressed!");
        // TODO: toggle Invincibility
    }

    /**
     * Pour retourner au jeu après l'affichage temporaire de "Win"/"Loss".
     */
    public void setLevel(){
        System.out.println("Should be setting new level");
        ColorsWitch.setMainScene();
    }
}

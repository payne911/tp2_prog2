package colorswitch;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
 */
public class Controller {

    private Game game;
    private int level = 1;
    private Scene mainScene;

    static private Stage primaryStage;
    static private Text outputTxt;
    static private Scene intermScene;


    public Controller(Stage primaryStage, Text outputTxt,
                      Scene intermScene, Scene mainScene) {
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
        this.primaryStage = primaryStage;
        this.intermScene = intermScene;
        this.mainScene = mainScene;
        this.outputTxt = outputTxt;
    }

    public List<Entity> getEntities() {
        return this.game.getEntities();
    }

    /**
     * Fonction appelée à chaque frame du jeu.
     *
     * @param dt Delta-temps exprimé en secondes
     */
    public void tick(double dt) {

        if (this.game.isGameOver()) {

            if (this.game.hasWon()) {
                level++;
            }
            // TODO: Attendre la nouvelle game? (Menu de confirmation)
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
        Player.toggleInvincible();
    }

    /**
     * Pour retourner au jeu après l'affichage temporaire de "Win"/"Loss".
     */
    public void setLevel(){
        primaryStage.setScene(mainScene);
    }

    /**
     * Changes the current level.
     * Triggered from the "Level SeLection" Menu.
     *
     * @param level the input level.
     */
    public void changeLvl(int level) {

        this.level = level;
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
    }

    /**
     * Affichage intermédiaire entre deux niveaux.
     *
     * @param hasWon indique la cause de l'affichage (win = true/loss = false).
     */
    static public void setIntermScene(boolean hasWon) {

        primaryStage.setScene(intermScene);

        if (hasWon) {
            outputTxt.setText("You have succeeded! :D");
            outputTxt.setFill(Color.CHARTREUSE);
        } else {
            outputTxt.setText("You have died. :(");
            outputTxt.setFill(Color.RED);
        }
    }
}

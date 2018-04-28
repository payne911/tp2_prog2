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
    static private Scene lvlScene;
    static private Text infoTxt;


    /**
     * Constructeur.
     *
     * @param primaryStage  La fenêtre du jeu.
     * @param mainScene     L'affichage principal du jeu.
     * @param lvlScene      Le menu de sélection de niveau.
     * @param infoTxt       Le texte informatif (Victory/Loss).
     */
    public Controller(Stage primaryStage, Scene mainScene,
                      Scene lvlScene, Text infoTxt) {
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
        this.lvlScene = lvlScene;
        this.infoTxt = infoTxt;
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

            // TODO: Rajouter le text ici?
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
     * Fonction appelée lorsque 'TAB' est enfoncé.
     */
    public void tabTyped() {
        Player.toggleInvincible();
    }

    /**
     * Fonction appelée lorsque 'ESCAPE' est enfoncé.
     */
    public void escapeTyped() {

        for (int i = 0; i < Game.getLevel().getEntities().size(); i++) {
            Game.getLevel().getEntities().remove(0);
        }

        primaryStage.setScene(lvlScene);
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
        setLevel();
    }


    /**
     * Pour afficher un message de Victoire ou Défaite au joueur pour 1 seconde.
     *
     * @param text  Le message à montrer.
     */
    static public void displayTxt(String text) {

        infoTxt.setText(text);

        if(text.contains("réussi"))
            infoTxt.setFill(Color.CHARTREUSE);
        else
            infoTxt.setFill(Color.RED);

        // Après une seconde et demie, le texte n'est plus!
        Thread displayCounter = new Thread(() -> {
            try {
                Thread.sleep(1500);
                infoTxt.setText("");
            } catch (Exception e) {
            }
        });
        displayCounter.start();
    }

    /**
     * Pour retourner au jeu.
     */
    public void setLevel(){
        infoTxt.setText("");
        primaryStage.setScene(mainScene);
    }
}

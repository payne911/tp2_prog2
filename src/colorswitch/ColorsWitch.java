package colorswitch;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/*
    BONUS

    - TODO: Créer plus de variété (5%)
        • 6 niveaux, pour un total de 10 niveaux
 */

/*
    TODO:
    Une seule personne par équipe doit remettre le travail sur StudiUM, l’autre doit remettre un
    fichier nommé equipe.txt contenant uniquement le code d’identification de l’autre personne
    (p1234..., soit ce que vous utilisez pour vous connecter sur StudiUM)
 */

// TODO: JavaDoc!
// TODO: Allonger le nom des variables? (plus explicite...)    -.-'
// TODO: Possiblement ajouter des indicateurs visuels pour l'état du joueur (invincible ou non, etc.)
// TODO: Possiblement rajouter au canon un rectangle qui suit le joueur lorsqu'il rentre dans son range
// TODO: TAB à désactiver à chaque nouveau niveau ? À vérifier...


/**
 * Classe principale. Définit la vue.
 *
 * Travail remis par:
 *      - Jérémi Grenier-Berthiaume
 *      - Olivier Lepage-Applin
 */
public class ColorsWitch extends Application {

    public static final double WIDTH = 320, HEIGHT = 480;

    private Controller controller;
    private GraphicsContext context;


    /**
     * Lance l'application.
     *
     * @param args Laisser vide.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialise les composantes graphiques de la fenêtre de jeu.
     *
     * @param primaryStage La fenêtre de l'application.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        /* Initialisation de la scène de choix de niveau */
        VBox lvlVBox = new VBox();
        Scene lvlScene = new Scene(lvlVBox, WIDTH, HEIGHT);

        Button level1 = new Button("Level 1");
        Button level2 = new Button("Level 2");
        Button level3 = new Button("Level 3");
        Button level4 = new Button("Level 4");
        Button level5 = new Button("Level 5");
        Button level6 = new Button("Level 6");
        Button level7 = new Button("Level 7");
        Button level8 = new Button("Level 8");
        Button level9 = new Button("Level 9");
        Button level10 = new Button("Level 10");

        Text lvlTxt = new Text("Choisissez un niveau!");

        lvlVBox.getChildren().addAll(lvlTxt, level1, level2, level3,
                level4, level5, level6, level7, level8, level9, level10);

        // Setting up styles
        for (int i = 1; i < lvlVBox.getChildren().size(); i++) {
            lvlVBox.getChildren().get(i).setOpacity(.76);
        }

        lvlTxt.setX(WIDTH/2);
        lvlTxt.setY(HEIGHT/2);
        lvlTxt.setFill(Color.CHARTREUSE);
        lvlTxt.setFont(Font.font("Courier New", 20));

        lvlVBox.setAlignment(Pos.CENTER);
        lvlVBox.setSpacing(8);
        lvlVBox.setStyle("-fx-background-color: black;");


        /* Initialisation du reste du jeu */
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Pane root = new Pane(canvas);

        VBox infoBox = new VBox();
        Text infoTxt = new Text("");

        infoTxt.setFont(Font.font("Courier New", 20));

        infoBox.getChildren().add(infoTxt);
        root.getChildren().add(infoBox);

        context = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                controller.tick((now - lastTime) * 1e-9);

                context.setFill(Color.BLACK);
                context.fillRect(0, 0, WIDTH, HEIGHT);

                List<Entity> entities = controller.getEntities();

                for (Entity e : entities) {
                    e.getRepresentation().draw(controller.getCurrentLevel(),
                            context);
                }

                lastTime = now;
            }
        };
        timer.start();

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        controller = new Controller(primaryStage, scene, lvlScene, infoTxt);


        /* Events */
        scene.setOnKeyPressed((event) -> {

            if (event.getCode() == KeyCode.SPACE) {
                controller.spaceTyped();
            }

            if (event.getCode() == KeyCode.TAB) {
                controller.tabTyped();
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                controller.escapeTyped();
            }
        });

        level1.setOnAction((event) -> controller.changeLvl(1));
        level2.setOnAction((event) -> controller.changeLvl(2));
        level3.setOnAction((event) -> controller.changeLvl(3));
        level4.setOnAction((event) -> controller.changeLvl(4));
        level5.setOnAction((event) -> controller.changeLvl(5));
        level6.setOnAction((event) -> controller.changeLvl(6));
        level7.setOnAction((event) -> controller.changeLvl(7));
        level8.setOnAction((event) -> controller.changeLvl(8));
        level9.setOnAction((event) -> controller.changeLvl(9));
        level10.setOnAction((event) -> controller.changeLvl(10));


        // Finalisation
        primaryStage.setTitle("Colors Witch");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

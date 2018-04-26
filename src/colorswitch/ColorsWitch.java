package colorswitch;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/*
    BONUS

    - TODO: Créer plus de variété (5%)
        • 1 obstacle animé, dont l'animation est différente de celles des autres obstacles
            > ??
        • 1 item qui NUIT au joueur
            > ??
        • 6 niveaux, pour un total de 10 niveaux

    - TODO: Ajouter un menu qui permet de choisir le niveau dans lequel on joue (5%)
        Dans le code fourni, lorsqu’on démarre le jeu, on commence directement dans le niveau 1. On devrait
        plutôt pouvoir choisir le niveau qui nous intéresse.
        Ajoutez un menu qui permet de choisir le niveau dans lequel on joue.
        De plus, pendant un niveau, on devrait pouvoir appuyer sur Escape pour quitter le niveau et revenir
        au menu.
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

        // Initialisation de la scène intermédiaire (Loss/Win)
        VBox rootVBox = new VBox();
        Scene intermScene = new Scene(rootVBox, WIDTH, HEIGHT);

        Text outputTxt = new Text("");
        outputTxt.setX(WIDTH/2);
        outputTxt.setY(HEIGHT/2);
        outputTxt.setFill(Color.WHITE);
        outputTxt.setFont(Font.font("Courier New", 20));

        Text genericTxt = new Text("Press 'N' to proceed.");
        genericTxt.setX(WIDTH/2);
        genericTxt.setY(HEIGHT/2);
        genericTxt.setFill(Color.WHITE);
        genericTxt.setFont(Font.font("Courier New", 13));

        rootVBox.setAlignment(Pos.CENTER);
        rootVBox.getChildren().addAll(outputTxt, genericTxt);
        rootVBox.setSpacing(50);
        rootVBox.setStyle("-fx-background-color: black;");


        // Initialisation du reste du jeu
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Pane root = new Pane(canvas);

        // Create a Level Selection Menu button
        MenuButton levelBtn = new MenuButton("Select a level");
        levelBtn.popupSideProperty().set(Side.TOP);
        levelBtn.setLayoutY(HEIGHT-25);
        levelBtn.backgroundProperty().set(Background.EMPTY);
        levelBtn.setTextFill(Color.CHARTREUSE);

        MenuItem lvl1 = new MenuItem("Level 1");
        MenuItem lvl2 = new MenuItem("Level 2");
        MenuItem lvl3 = new MenuItem("Level 3");
        MenuItem lvl4 = new MenuItem("Level 4");
        MenuItem lvl5 = new MenuItem("Level 5");
        MenuItem lvl6 = new MenuItem("Level 6");
        MenuItem lvl7 = new MenuItem("Level 7");
        MenuItem lvl8 = new MenuItem("Level 8");
        MenuItem lvl9 = new MenuItem("Level 9");
        MenuItem lvl10 = new MenuItem("Level 10");

        levelBtn.getItems().addAll(lvl1, lvl2, lvl3, lvl4, lvl5, lvl6, lvl7,
                lvl8, lvl9, lvl10);

        // Setting up the interaction with each Item
        lvl1.setOnAction((event) -> controller.changeLvl(1));
        lvl2.setOnAction((event) -> controller.changeLvl(2));
        lvl3.setOnAction((event) -> controller.changeLvl(3));
        lvl4.setOnAction((event) -> controller.changeLvl(4));
        lvl5.setOnAction((event) -> controller.changeLvl(5));
        lvl6.setOnAction((event) -> controller.changeLvl(6));
        lvl7.setOnAction((event) -> controller.changeLvl(7));
        lvl8.setOnAction((event) -> controller.changeLvl(8));
        lvl9.setOnAction((event) -> controller.changeLvl(9));
        lvl10.setOnAction((event) -> controller.changeLvl(10));

        root.getChildren().add(levelBtn);

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

        controller = new Controller(primaryStage, outputTxt,
                intermScene, scene);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            // Pour que l'Espace ne puisse pas interagir avec le bouton
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
                controller.spaceTyped();
            }

            if (event.getCode() == KeyCode.TAB) {
                controller.tabTyped();
            }
        });

        intermScene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.N) {
                controller.setLevel();
            }
        });

        primaryStage.setTitle("Colors Witch");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

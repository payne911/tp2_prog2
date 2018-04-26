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
    TODO: BONUS

    - Animer la mort de la soricère (5%)
        Lorsque la sorcière touche un obstacle de la mauvaise couleur, on voudrait qu’elle “explose” en 100
        petites balles qui partent dans tous les sens et qui rebondissent sur les murs, un peu comme dans le
        jeu original

    - Créer plus de variété (5%)
        • 2 autres types d’obstacles animés, dont les animations sont différentes de celles des autres
        obstacles
            > ??
            > ??
        • 2 autres types d’items, un qui aide la sorcière dans sa quête, l’autre qui lui nuit (par exemple,
        en modifiant sa vitesse, en ajoutant des obstacles au niveau, ou quoi que ce soit d’autre. . . )
            > TÉLÉPORTEUR
            > ??
        • 6 niveaux, pour un total de 10 niveaux

    - Ajouter un menu qui permet de choisir le niveau dans lequel on joue (5%)
        Dans le code fourni, lorsqu’on démarre le jeu, on commence directement dans le niveau 1. On devrait
        plutôt pouvoir choisir le niveau qui nous intéresse.
        Ajoutez un menu qui permet de choisir le niveau dans lequel on joue.
        De plus, pendant un niveau, on devrait pouvoir appuyer sur Escape pour quitter le niveau et revenir
        au menu.
 */

// TODO: Ajouter l'option "TAB" pour Toggle Invincibility

/*
    TODO:
    Une seule personne par équipe doit remettre le travail sur StudiUM, l’autre doit remettre un
    fichier nommé equipe.txt contenant uniquement le code d’identification de l’autre personne
    (p1234..., soit ce que vous utilisez pour vous connecter sur StudiUM)
 */

// TODO: JavaDoc!

/**
 * Classe principale. Définit la vue.
 *
 * Travail remis par:
 *      - Jérémi Grenier-Berthiaume
 *      - Olivier Lepage-Applin
 */
public class ColorsWitch extends Application {

    public static final double WIDTH = 320, HEIGHT = 480;

    static private Text outputTxt;
    static private Stage primaryStage;
    static private Scene scene;
    static private Scene intermScene;

    private Controller controller;
    private GraphicsContext context;



    static public void setMainScene(){
        primaryStage.setScene(scene);
    }

    static public void setIntermScene() {
        primaryStage.setScene(intermScene);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        controller = new Controller();

        // Initialisation de la scène intermédiaire (Loss/Win)
        VBox rootVBox = new VBox();
        this.intermScene = new Scene(rootVBox, WIDTH, HEIGHT);

        outputTxt = new Text("You have "); // TODO: Fix properly

        outputTxt.setX(WIDTH/2);
        outputTxt.setY(HEIGHT/2);
        outputTxt.setFill(Color.RED);
        outputTxt.setFont(Font.font("Courier New", 17));

        rootVBox.setAlignment(Pos.CENTER);
        rootVBox.getChildren().add(outputTxt);
        rootVBox.setStyle("-fx-background-color: black;");


        // Initialisation du reste du jeu
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Pane root = new Pane(canvas);

        //Button levelBtn = new Button("Current level: "); // TODO: add currLvl

        //root.getChildren().add(levelBtn);

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

        this.scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.SPACE) {
                controller.spaceTyped();
            }

            if (event.getCode() == KeyCode.TAB) {
                controller.tabTyped();
            }
        });

        intermScene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.SPACE) {
                controller.setLevel();
            }
        });

        primaryStage.setTitle("Colors Witch");
        primaryStage.setScene(scene);
        //primaryStage.setScene(intermScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

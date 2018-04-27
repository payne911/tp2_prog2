package colorswitch;

import java.util.ArrayList;
import java.util.List;

public class Game {

    static private Level level;
    static private Player player;

    /**
     * Dimensions de l'écran
     */
    private double screenWidth, screenHeight;

    /**
     * Indique si la partie est terminée/gagnée
     */
    private boolean gameOver = false;
    private boolean hasWon = false;
    private boolean tempGameOver = false;
    private double gameOverCounter = 0;

    /**
     * Crée une partie dans le niveau levelNumber.
     *
     * @param screenWidth largeur de l'écran
     * @param screenHeight hauteur de l'écran
     * @param levelNumber numéro du niveau
     */
    public Game(double screenWidth, double screenHeight, int levelNumber) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        player = new Player(screenWidth / 2, 200, 15);

        switch (levelNumber) {
            case 1:
                level = new Level1(screenWidth, screenHeight);
                break;
            case 2:
                level = new Level2(screenWidth, screenHeight);
                break;
            case 3:
                level = new Level3(screenWidth, screenHeight);
                break;
            case 4:
                level = new Level4(screenWidth, screenHeight);
                break;
            case 5:
                level = new Level5(screenWidth, screenHeight);
                break;
            case 6:
                level = new Level6(screenWidth, screenHeight);
                break;
            case 7:
                level = new Level7(screenWidth, screenHeight);
                break;
            case 8:
                level = new Level8(screenWidth, screenHeight);
                break;
            case 9:
                level = new Level9(screenWidth, screenHeight);
                break;
            case 10:
                level = new Level10(screenWidth, screenHeight);
                break;
            default:
                throw new IllegalArgumentException("Niveau inconnu");
        }
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Fonction appelée à chaque frame
     *
     * @param dt Delta-Temps (en secondes)
     */
    public void tick(double dt) {
        level.tick(dt);
        player.tick(dt);

        if (tempGameOver)
            gameOverCounter += dt;

        // Petit délai avant de faire recommencer le niveau
        if (gameOverCounter > 1.5)
            gameOver = true;

        if (player.getY() - player.getRadius() < level.getScroll()) {
            // Empêche la balle de sortir de l'écran
            player.setY(level.getScroll() + player.getRadius());
        } else if (player.getY() - level.getScroll() > screenHeight / 2) {
            // Scroll le level verticalement si nécessaire
            level.incrementScroll(
                    player.getY() - level.getScroll() - screenHeight / 2);
        }

        // Gestion des collisions avec les éléments (items/obstacles/...)
        // du niveau
        if(!tempGameOver) {
            for (LevelElement element : level.getEntities()) {
                if (element.intersects(player)) {
                    element.handleCollision(player, this);
                }
            }
        }
    }

    /**
     * @return les entités à afficher à l'écran
     */
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();

        entities.addAll(level.getEntities());

        if (!tempGameOver)
            entities.add(player);

        return entities;
    }

    public static Level getLevel() {
        return level;
    }

    public static Player getPlayer() {
        return player;
    }

    /**
     * Fait en sorte que le joueur bouge vers le haut.
     */
    public void jump() {
        if(!tempGameOver)
            player.jump();
    }

    /**
     * Gère la fin d'un niveau avec échec. Crée 100 petites balles.
     */
    public void loose() {

        Controller.displayTxt("\n\n\n\n\n      Tu as échoué :(");

        this.tempGameOver = true;

        for (int i = 0; i < 100; i++) {
            level.obstacles.add(new DeathCircle(player.getX(), player.getY()));
        }
    }

    /**
     * Gère la fin d'un niveau avec succès.
     */
    public void win() {

        Controller.displayTxt("\n\n\n\n\n      Tu as réussi :D");

        this.hasWon = true;
        this.gameOver = true;
    }

    /**
     * Indique si la partie est gagnée
     *
     * @return booléen qui l'indique
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * Indique si la partie est terminée
     *
     * @return booléen qui l'indique
     */
    public boolean isGameOver() {
        return gameOver;
    }
}

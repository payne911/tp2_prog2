package colorswitch;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Level level;
    private Player player;

    /**
     * Dimensions de l'écran
     */
    private double screenWidth, screenHeight;

    /**
     * Indique si la partie est terminée/gagnée
     */
    private boolean gameOver = false;
    private boolean hasWon = false;

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
        for (LevelElement element : level.getEntities()) {
            if (element.intersects(player)) {
                element.handleCollision(player, this);
            }
        }
    }

    /**
     * @return les entités à afficher à l'écran
     */
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();

        entities.addAll(level.getEntities());
        entities.add(player);

        return entities;
    }

    public Level getLevel() {
        return level;
    }

    public void jump() {
        player.jump();
    }

    public void loose() {
        /*
        TODO! Possiblement dans classe "Controller", ligne 28 et/ou 30.
        Présentement, lorsqu’un niveau est complété, on passe directement au
        niveau suivant, sans félicitations, sans indications de quoi que ce soit.
        De la même façon, si on perd dans un niveau, on devrait avoir une indication textuelle.
        Trouvez une façon d’afficher un message lorsqu’on gagne ou on perd, soit en ajoutant un élément
        Text dans la scène, soit en dessinant du texte sur le canvas déjà présent avec la méthode drawText
        quelque part.
         */
        System.out.println("You loose... Too bad !");
        this.gameOver = true;
    }

    public void win() {
        /*
        TODO! Possiblement dans classe "Controller", ligne 28 et/ou 30.
        Présentement, lorsqu’un niveau est complété, on passe directement au
        niveau suivant, sans félicitations, sans indications de quoi que ce soit.
        De la même façon, si on perd dans un niveau, on devrait avoir une indication textuelle.
        Trouvez une façon d’afficher un message lorsqu’on gagne ou on perd, soit en ajoutant un élément
        Text dans la scène, soit en dessinant du texte sur le canvas déjà présent avec la méthode drawText
        quelque part.
         */
        System.out.println("You win !");
        this.hasWon = true;
        this.gameOver = true;
    }

    /**
     * Indique si la partie est gagnée
     *
     * @return
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * Indique si la partie est terminée
     *
     * @return
     */
    public boolean isGameOver() {
        return gameOver;
    }
}

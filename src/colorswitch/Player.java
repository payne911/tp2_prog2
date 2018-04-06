package colorswitch;

import javafx.application.Platform;

/**
 * Classe représentant l'entité de la personne qui joue (aka, la sorcière).
 *
 * La sorcière est représentée par un cercle.
 */
public class Player extends Entity {

    private double radius;
    private double vy;
    private double ay;
    private int color = 1;
    private boolean invincible = false;

    public Player(double x, double y, double r) {
        super(x, y);

        this.radius = r;

        this.vy = 0;
        this.ay = -400;

        this.renderer = new PlayerRenderer(this);
    }

    public double getRadius() {
        return radius;
    }

    public boolean getInvincible() {
        return invincible;
    }

    /**
     * Makes the player invincible for 3 seconds.
     */
    public void makeInvincible() {

        this.invincible = true;

        Thread wait = new Thread(() -> {
            try{
                Thread.sleep(3000);
            } catch (Exception e) {
            } finally {
                // TODO: Where is the appropriate place for "runLater" ?
                Platform.runLater(() -> this.invincible = false);
            }
        });

        wait.setDaemon(true);
        wait.start();

        /*
        TODO: Sachant que un nouveau joueur est instancié à chaque niveau
        ou mort, est-il nécessaire d'arrêter ce Thread?
        A priori, non!
         */
    }

    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        // Mise à jour de la vitesse
        vy += dt * ay;

        // Mise à jour de la position
        y += dt * vy;

        // Clip la vitesse pour rester entre -300 et 300
        vy = Math.min(vy, 300);
        vy = Math.max(vy, -300);
    }

    public int getColor() {
        return color;
    }

    /**
     * Remplace la couleur actuelle par une nouvelle couleur aléatoire
     */
    public void randomizeColor() {
        int newColor;

        do {
            newColor = (int) (Math.random() * 4);
        } while (newColor == this.color);

        this.color = newColor;
    }

    /**
     * Fait sauter la sorcière
     */
    public void jump() {
        vy = Math.max(vy, 0);
        vy += 200;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getWidth() {
        return this.getRadius() * 2;
    }

    @Override
    public double getHeight() {
        return this.getRadius() * 2;
    }
}

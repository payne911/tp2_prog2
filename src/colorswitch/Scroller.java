package colorswitch;

import javafx.application.Platform;

import java.util.ArrayList;

/**
 * Obstacle "conteneur" :
 * Une bande de rectangles de différentes couleurs défilant horizontalement.
 * Instancie les les rectangles pour la bande.
 */
public class Scroller extends Obstacle {

    private double height;
    private double width;
    private double speed;
    private boolean initialized = false;
    private ArrayList<MovingRectangle> subEntities;


    /**
     * Conctructeur.
     *
     * @param x             Position en X.
     * @param y             Position en Y.
     * @param hauteur       Hauteur de l'obstacle.
     * @param largeur       Largeur des blocs formant l'obstacle.
     * @param vitesse       Négatif => vitesse vers la gauche,
     *                      Positif => vitesse vers la droite.
     */
    public Scroller(double x, double y, double hauteur, double largeur,
                    double vitesse) {
        super(x, y);

        this.height = hauteur;
        this.width = largeur;
        this.speed = vitesse;
        this.subEntities = new ArrayList<>();
        this.renderer = new ScrollerRenderer(this);

        this.color = 5;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void tick(double dt) {

        // To prevent NullPointer error, initialization is done here
        if (!initialized) {
            initialized = !initialized;

            double spawnPos = -1.5*width;

            for (int i = 0; i < 7; i++) {

                MovingRectangle temp = new MovingRectangle(
                        spawnPos, y, width, height, i%4);
                spawnPos += width;

                // Creating the band
                Platform.runLater(() ->
                        Game.getLevel().getObstacles().add(temp)
                );
                subEntities.add(temp);
            }
        }

        for (MovingRectangle mR : subEntities) {

            // Faire bouger dans le bon sens tous les rectangles
            mR.setX(mR.getX() + speed * dt);

            // Si ça bouge vers la droite
            if (speed > 0) {
                // Ramener vers la gauche
                if (mR.getX() > 6 * width) {
                    mR.setX(-width);
                }
            } else { // Vers la gauche...
                // Ramener vers la droite
                if (mR.getX() < -width) {
                    mR.setX(6 * width);
                }
            }
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {
        return false;
    }
}

package colorswitch;

/**
 * Classe représentant l'entité de la personne qui joue (aka, la sorcière).
 *
 * La sorcière est représentée par un cercle.
 */
public class Player extends Entity {

    private static boolean permaInvinc = false;

    private double radius;
    private double vy;
    private double ay;
    private int color = 1;

    private boolean invincible = false;
    private double invCounter = 0;

    private boolean dizzy = false;
    private double dizzyCounter = 0;

    private double realRadius;
    private double targetRadius;
    private boolean shrinking = false;
    private double shrinkCounter = 0;

    /**
     * Constructeur.
     *
     * @param x Position en X du joueur.
     * @param y Pôsition en Y du joueur.
     * @param r Grosseur (rayon) du joueur.
     */
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

    public boolean getPermaInvinc() {
        return permaInvinc;
    }


    /**
     * Shrinks the player (item effect)
     */
    public void shrink() {
        this.shrinking = true;
        this.realRadius = radius;
        this.targetRadius = radius/1.5;
    }

    /**
     * Makes the player invincible for 3 seconds.
     */
    public void makeInvincible() {
        invincible = true;
    }

    /**
     * La sorcière commence à dévier horizontalement.
     */
    public void makeDizzy() {
        dizzy = true;
    }

    /**
     * Toggles the permanent invincibility of the player.
     */
    public static void toggleInvincible() {
        permaInvinc = !permaInvinc;
    }

    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {

        if (dizzy) {
            dizzyCounter += dt;

            // Pour l'effet du mouvement aléatoire
            double temp = Math.random();
            if (temp < .5)
                temp = -1;
            else
                temp = 1;

            setX(getX() + (temp * 220 * Math.random() * dt));
        }

        if (dizzyCounter > 10) {
            dizzy = false;
            dizzyCounter = 0;
        }

        if (!dizzy) {
            // Bring player back to middle of the screen
            if (getX() < ColorsWitch.WIDTH / 2)
                setX(getX() + 10 * dt);
            if (getX() > ColorsWitch.WIDTH / 2)
                setX(getX() - 10 * dt);
        }

        // Réduction graduelle du rayon
        if (shrinking) {
            if (radius >= targetRadius)
                radius -= 7*dt;
            else
                radius = targetRadius;
        }

        // Une fois le rayon assez réduit, on commence l'attente
        if (shrinking && targetRadius == radius)
            shrinkCounter += dt;

        // Après 3 secondes avec un rayon réduit, on regrossit
        if (shrinkCounter > 3) {
            shrinking = false;
            radius += 9*dt;
        }

        // On requalibre les paramètres correctement
        if (!shrinking && radius >= realRadius && shrinkCounter > 3) {
            radius = realRadius;
            shrinkCounter = 0;
        }

        // Pour gérer l'invincibilité
        if (invincible)
            invCounter += dt;

        // Après 3 secondes d'invincibilité, le joueur peut mourir
        if (invCounter > 3) {
            invCounter = 0;
            invincible = false;
        }

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

        if (shrinking) {
            vy = Math.max(vy, 0);
            vy += 300;
        } else {
            vy = Math.max(vy, 0);
            vy += 200;
        }
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

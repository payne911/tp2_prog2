package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * (...) dessine une balle qui bouge vers le joueuer à l'écran.
 */
public class BulletRenderer extends Renderer {

    private Bullet bullet;

    public BulletRenderer(Bullet c) {
        this.bullet = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, bullet.getPosY());

        context.setFill(Renderer.convertColor(bullet.getColor()));

        context.fillOval(bullet.getPosX() - bullet.getRadius(),
                canvasY - bullet.getRadius(),
                2 * bullet.getRadius(),
                2 * bullet.getRadius());
    }
}

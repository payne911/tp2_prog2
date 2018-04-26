package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * (...) dessine un cercle coloré.
 */
public class DeathCircleRenderer extends Renderer {

    private DeathCircle deathCircle;

    public DeathCircleRenderer(DeathCircle c) {
        this.deathCircle = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, deathCircle.getY());

        context.setFill(Renderer.convertColor(deathCircle.getColor()));

        context.fillOval(deathCircle.getX() - deathCircle.getRadius(),
                canvasY - deathCircle.getRadius(),
                2 * deathCircle.getRadius(),
                2 * deathCircle.getRadius());
    }
}

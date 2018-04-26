package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * (...) dessine un cercle coloré qui grossit sur l'écran.
 */
public class GrowingCircleRenderer extends Renderer {

    private GrowingCircle growC;

    public GrowingCircleRenderer(GrowingCircle c) {
        this.growC = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, growC.getY());

        context.setFill(Renderer.convertColor(growC.getColor()));

        context.fillOval(growC.getX() - growC.getRadius(),
                canvasY - growC.getRadius(),
                2 * growC.getRadius(),
                2 * growC.getRadius());
    }
}

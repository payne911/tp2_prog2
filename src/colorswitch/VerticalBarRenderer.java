package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * (...) dessine une barre verticale colorée sur l'écran.
 */
public class VerticalBarRenderer extends Renderer {

    private VerticalBar vertB;

    public VerticalBarRenderer(VerticalBar c) {
        this.vertB = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, vertB.getY());

        context.setFill(Renderer.convertColor(vertB.getColor()));

        context.fillRect(
                vertB.getX() - vertB.getWidth() / 2,
                canvasY - vertB.getHeight() / 2,
                vertB.getWidth(),
                vertB.getHeight());
    }
}

package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * (...) dessine une barre verticale colorée sur l'écran.
 */
public class VerticalBarRenderer extends Renderer {

    private VerticalBar verticalBar;

    public VerticalBarRenderer(VerticalBar c) {
        this.verticalBar = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, verticalBar.getY());

        context.setFill(Renderer.convertColor(verticalBar.getColor()));

        context.fillRect(
                verticalBar.getX() - verticalBar.getWidth() / 2,
                canvasY - verticalBar.getHeight() / 2,
                verticalBar.getWidth(),
                verticalBar.getHeight());
    }
}

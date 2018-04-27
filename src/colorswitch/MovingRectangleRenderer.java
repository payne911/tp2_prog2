package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un Rectangle en le dessinant sur l'écran.
 */
public class MovingRectangleRenderer extends Renderer {

    private MovingRectangle movingRectangle;

    public MovingRectangleRenderer(MovingRectangle c) {
        this.movingRectangle = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, movingRectangle.getY());

        context.setFill(Renderer.convertColor(movingRectangle.getColor()));

        context.fillRect(
                movingRectangle.getX() - movingRectangle.getWidth() / 2,
                canvasY - movingRectangle.getHeight() / 2,
                movingRectangle.getWidth(),
                movingRectangle.getHeight());
    }
}

package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Fait le rendu d'un Canon en dessinant sur l'écran.
 */
public class CanonRenderer extends Renderer {

    private Canon canon;

    public CanonRenderer(Canon c) {
        this.canon = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double posX = canon.getX();
        double posY = canon.getY();
        double width = canon.getWidth();
        double range = canon.getRange();
        double canvasY = Renderer.computeScreenY(level, posY);

        context.setFill(Renderer.convertColor(canon.getColor()));

        // corp du cannon
        context.fillRect(posX - width / 2, canvasY - width / 2,
                width, width);

        context.strokeOval(posX - width / 2, canvasY - width / 2,
                width, width);

        context.setFill(new Color(1, 0.2, 0.2, 0.15));
        context.fillOval(posX - range, canvasY - range, range*2, range*2);

        // Reset Stroke to usual color
        context.setStroke(Color.BLACK);
    }
}

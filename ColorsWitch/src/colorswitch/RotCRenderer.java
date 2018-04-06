package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * (...) dessine un cercle coloré qui tourne sur l'écran.
 */
public class RotCRenderer extends Renderer {

    private RotatingCircle rotC;

    public RotCRenderer(RotatingCircle c) {
        this.rotC = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, rotC.getPosY());

        context.setFill(Renderer.convertColor(rotC.getColor()));

        context.fillOval(rotC.getPosX() - rotC.getCircRadius(),
                canvasY - rotC.getCircRadius(),
                2 * rotC.getCircRadius(),
                2 * rotC.getCircRadius());
    }
}

package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un Scroller en faisant défiler des rectangles horizontalement
 */
public class ScrollerRenderer extends Renderer {

    private Scroller scroller;

    public ScrollerRenderer(Scroller c) {
        this.scroller = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {
        // Dummy
    }
}

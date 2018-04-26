package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**
 * (...) dessine une balle qui bouge vers le joueuer à l'écran.
 */
public class BallRenderer extends Renderer {

    private Ball ball;

    public BallRenderer(Ball c) {
        this.ball = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, ball.getPosY());

        context.setFill(Renderer.convertColor(ball.getColor()));

        context.fillOval(ball.getPosX() - ball.getRadius(),
                canvasY - ball.getRadius(),
                2 * ball.getRadius(),
                2 * ball.getRadius());
    }
}

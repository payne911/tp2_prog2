package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Fait le rendu d'un Player sur l'écran en dessinant un cercle coloré
 */
public class PlayerRenderer extends Renderer {

    private Player player;

    public PlayerRenderer(Player player) {
        this.player = player;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, player.getY());

        // Visual effect for Invisibility
        context.setFill(Color.GRAY);

        if (player.getInvincible() || player.getPermaInvinc()) {
            context.fillOval(
                    player.getX() - (player.getRadius() + 5),
                    canvasY - (player.getRadius() + 5),
                    2 * (player.getRadius() + 5),
                    2 * (player.getRadius() + 5));
        }

        // Drawing the actual player
        context.setFill(Renderer.convertColor(player.getColor()));

        context.fillOval(
                player.getX() - player.getRadius(),
                canvasY - player.getRadius(),
                2 * player.getRadius(),
                2 * player.getRadius());


    }
}

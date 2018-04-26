package colorswitch;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Fait le rendu d'une Entity sur l'écran en affichant une animation.
 */
public class AnimationRenderer extends Renderer {

    private ArrayList<ImageRenderer> imgRenderer = new ArrayList<>();
    private Entity entity;
    private double frameRate;
    private int nbFrame;

    /**
     * Constructeur.
     *
     * @param prefix    le chemin de base de l’image (sans le numéro de frame)
     * @param nbFrame    le nombre de frames totales dans la série d’images
     * @param frameRate le nombre de fois que l’image doit être mise à jour
     *                  à chaque seconde
     * @param entity    l’entité de jeu associée au rendu
     */
    public AnimationRenderer(String prefix, int nbFrame,
                             double frameRate, Entity entity) {

        for(int i = 1; i <= nbFrame; i++){
            this.imgRenderer.add(new ImageRenderer(prefix + i, entity));
        }

        this.frameRate = frameRate;
        this.nbFrame = nbFrame;
        this.entity = entity;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        int frame = (int)(System.nanoTime() * 1e-9 * frameRate) % nbFrame;

        context.drawImage(imgRenderer.get(frame).getImg(),
                x - entity.getWidth() / 2,
                y - entity.getHeight() / 2,
                entity.getWidth(), entity.getHeight());
    }
}

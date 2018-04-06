package colorswitch;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Fait le rendu d'une Entity sur l'écran en affichant une animation.
 */
public class AnimationRenderer extends Renderer {

    private ArrayList<ImageRenderer> imgRenderer = new ArrayList<>();
    private Entity entity;

    /**
     * Constructeur.
     *
     * TODO: S'assurer que l'approche respecte les notes de cours ?
     * TODO: Intégrer frameRate correctement...?
     *
     * @param prefix    le chemin de base de l’image (sans le numéro de frame)
     * @param number    le nombre de frames totales dans la série d’images
     * @param frameRate le nombre de fois que l’image doit être mise à jour
     *                  à chaque seconde
     * @param entity    l’entité de jeu associée au rendu
     */
    public AnimationRenderer(String prefix, int number,
                             double frameRate, Entity entity) {

        for(int i = 1; i <= number; i++){
            this.imgRenderer.add(new ImageRenderer(prefix + i, entity));
        }

        this.entity = entity;
    }

    /**
     * Pour pouvoir changer l'image affichée en fonction de l'index choisi.
     *
     * @param index L'index de l'ImageRenderer enregistré.
     * @return      Retourne l'ImageRenderer voulu.
     */
    public ImageRenderer getImgRenderer(int index) {
        return imgRenderer.get(index);
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        context.drawImage(imgRenderer.get(0).getImg(),
                x - entity.getWidth() / 2,
                y - entity.getHeight() / 2,
                entity.getWidth(), entity.getHeight());
    }
}

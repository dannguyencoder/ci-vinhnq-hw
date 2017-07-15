package game.players;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 7/11/2017.
 */
public class PLayerSpell {
    public int x;
    public int y;
    public BufferedImage image;

    public void move()
    {
        y-=10;
    }

    public void render(Graphics2D g2d)
    {
        double rotationRequired = Math.toRadians(45);
        double locationX = image.getWidth();
        double locationY = image.getHeight();
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX,locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        g2d.drawImage(op.filter(image, null), x - 8, y - 8, null);
    }

}

package game.enemies;

import game.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 7/12/2017.
 */
public class Enemy {
    public int x;
    public int y;
    public BufferedImage image;

//    GameWindow gameWindow = new GameWindow();

    public void move (int dx, int dy)
    {

    }

    public void render (Graphics2D g2d)
    {
        g2d.drawImage(image, x, y, null);
    }
}

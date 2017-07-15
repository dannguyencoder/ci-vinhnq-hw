package game;

import game.enemies.Enemy;
import game.players.PLayerSpell;
import game.players.Player;

import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by NHEM on 09/07/2017.
 */
public class GameWindow extends JFrame {

    public static BufferedImage background;
    private BufferedImage spell;
    private int backgroundY ;
    boolean rightPressed;
    boolean leftPressed;
    boolean upPressed;
    boolean downPressed;
    boolean xPressed;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;
    private int spellY;
    private int spellX;

    Player player = new Player();
    Enemy enemy = new Enemy();

//    PLayerSpell pLayerSpell = new PLayerSpell();
    ArrayList<PLayerSpell> pLayerSpells = new ArrayList<>();
//    ArrayList<Enemy> enemies = new ArrayList<>();

    Timer timer;
    private long lastDelay = 0;

    public GameWindow() {
        setupWindow();
        loadImage();

        player.x = background.getWidth() / 2;
        player.y = this.getHeight() - player.image.getHeight();

        enemy.x = background.getWidth()/2;
        enemy.y = -100;

        backgroundY = this.getHeight() - background.getHeight();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInput();
        this.setVisible(true);
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = true;
                        break;
                    case KeyEvent.VK_X:
                        xPressed = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = false;
                        break;
                    case KeyEvent.VK_X:
                        xPressed = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void loop() {
        while(true) {
            try {
                Thread.sleep(17);
                run();
                render();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        if (backgroundY < 0) {
            backgroundY+=6;

            //move
            enemy.y += 6;
            if (enemy.y ==  this.getHeight() / 3)
            {
                enemy.y-=6;
                enemy.x -= 1;
            }

            if (enemy.x == (GameWindow.background.getWidth()/5))
            {
                enemy.y -= 8;
            }
        }


        int dx = 0;
        int dy = 0;
        int dySpell = 0;

        if (rightPressed == true)
        {
            dx += 5;
        }

        if (leftPressed == true)
        {
            dx -= 5;
        }

        if (upPressed == true)
        {
            dy -= 5;
        }

        if (downPressed == true)
        {
            dy += 5;
        }

        if (xPressed)
        {
            //Create new
            PLayerSpell pLayerSpell = new PLayerSpell();

            //Config
            pLayerSpell.x = player.x;
            pLayerSpell.y = player.y;
            pLayerSpell.image = Utils.loadAssetImage("player-spells/a/0.png");

            //Add to arrayList
            if (System.currentTimeMillis() - lastDelay > 200)
            {
                pLayerSpells.add(pLayerSpell);
                lastDelay = System.currentTimeMillis();
            }
        }

        player.move(dx, dy);
        for (PLayerSpell pLayerSpell : pLayerSpells)
        {
            pLayerSpell.move();
        }

    }

    public void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0 ,this.getWidth(), this.getHeight());

        backBufferGraphics2D.drawImage(background, 0, backgroundY, null);

        player.render(backBufferGraphics2D);
        enemy.render(backBufferGraphics2D);

        for (PLayerSpell pLayerSpell : pLayerSpells)
        {
            pLayerSpell.render(backBufferGraphics2D);
        }


        Graphics2D g2d = (Graphics2D)this.getGraphics();

        g2d.drawImage(backBufferImage, 0, 0, null);
    }

    private void loadImage() {
        background = Utils.loadAssetImage("background/0.png");
        player.image = Utils.loadAssetImage("players/straight/0.png");
        enemy.image = Utils.loadAssetImage("enemies/level0/black/0.png");
    }

    private void setupWindow() {
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Tohou - Remade by Nhem");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //System.out.println("Closing");
                System.exit(0);
                //super.windowClosing(e);
            }
        });
    }

}
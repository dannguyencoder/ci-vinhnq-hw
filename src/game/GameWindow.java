package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;

/**
 * Created by Admin on 09-07-17.
 */
public class GameWindow extends JFrame {

    BufferedImage background;
    BufferedImage player;
    private int playerX;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    public GameWindow()
    {
        setupWindow();
        loadImages();

        playerX = background.getWidth()/2;
        //tai sao dat gia tri bang khong

        setupInputs();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        this.setVisible(true);
    }

    private void setupInputs() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_RIGHT:
                        playerX += 5;
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("left");
                        break;
                    case KeyEvent.VK_UP:
                        System.out.println("up");
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("down");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void run()
    {
        while (true)
        {
            try {
                Thread.sleep(17);
                backBufferGraphics2D.setColor(Color.red);
                backBufferGraphics2D.fillRect(0,0,this.getWidth(),this.getHeight());

                int backgroundHeight = background.getHeight();

                int windowHeight = this.getHeight();

                backBufferGraphics2D.drawImage(background, 0,this.getHeight() - background.getHeight(),null);
                backBufferGraphics2D.drawImage(player, playerX, this.getHeight()/2, null);

                Graphics2D g2d = (Graphics2D) this.getGraphics();

                g2d.drawImage(backBufferImage,0,0,null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadImages() {
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));
            player = ImageIO.read(new File("assets/images/players/straight/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupWindow() {
        //giai thich tu this
        this.setSize(600, 600);

        this.setResizable(false);
        this.setTitle("Touhou - Remade by Vinh");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //thich viet gi thi viet
                System.exit(0);
//                super.windowClosing(e);
                //bo super duoc khong
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //ep kieu, cast, convert
        //parse la gi ?


//        super.paint(g);
    }
}

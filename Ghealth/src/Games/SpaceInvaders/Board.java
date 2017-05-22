package Games.SpaceInvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable, Commons { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension d;
    private ArrayList<Alien> aliens;
    private Player player;
    private Shot shot;

 // private int alienX = 150;
 // private int alienY = 5;
    private int direction = -1;
    private int deaths = 0;

    private boolean ingame = true;
    private final String expl = "explosion.png";
 // private final String alienpix = "alien.png";
    private String message = "Game Over";

    private Thread animator;

    public Board() 
    {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
        setBackground(Color.black);

        gameInit();
        setDoubleBuffered(true);
    }

    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {

    	int alienX = 150;
    	int alienY = 5;
    	String alienpix = "alien.png";
        aliens = new ArrayList<Alien>();

        ImageIcon ii = new ImageIcon(this.getClass().getResource(alienpix));

        for (int i=0; i < 4; i++) {
            for (int j=0; j < 6; j++) {
                Alien alien = new Alien(alienX + 18*j, alienY + 18*i);
                alien.setImage(ii.getImage());
                aliens.add(alien);
            }
        }

        player = new Player();
        shot = new Shot();

        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawAliens(Graphics g) 
    {
        Iterator<Alien> it = aliens.iterator();

        while (it.hasNext()) {
            Alien alien = (Alien) it.next();

            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            ingame = false;
        }
    }

    public void drawShot(Graphics g) {
        if (shot.isVisible())
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
    }

    public void drawBombing(Graphics g) {

        Iterator<Alien> i3 = aliens.iterator();

        while (i3.hasNext()) {
            Alien a = (Alien) i3.next();

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this); 
            }
        }
    }

    public void paint(Graphics g)
    {
      super.paint(g);

      g.setColor(Color.black);
      g.fillRect(0, 0, d.width, d.height);
      g.setColor(Color.green);   

      if (ingame) {

        g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
        drawAliens(g);
        drawPlayer(g);
        drawShot(g);
        drawBombing(g);
      }

      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    }

    public void gameOver()
    {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message))/2, 
            BOARD_WIDTH/2);
    }

    public void animationCycle()  {

        spaceB();

        // player

        player.act();

        // shot
        spaceN();

        // aliens

         Iterator<Alien> it1 = aliens.iterator();

         spaceO(it1);


        Iterator<Alien> it = aliens.iterator();

        spaceG(it);

        // bombs

        Iterator<Alien> i3 = aliens.iterator();
        Random generator = new Random();

        spaceM(i3, generator);
    }

	private void spaceO(Iterator<Alien> it1) {
		while (it1.hasNext()) {
             Alien a1 = (Alien) it1.next();
             int x = a1.getX();

             spaceC(x);

            spaceD(x);
        }
	}

	private void spaceN() {
		if (shot.isVisible()) {
            Iterator<Alien> it = aliens.iterator();
            int shotX = shot.getX();
            int shotY = shot.getY();

            spaceF(it, shotX, shotY);

            int y = shot.getY();
            y -= 4;
            if (y < 0)
                shot.die();
            else shot.setY(y);
        }
	}

	private void spaceM(Iterator<Alien> i3, Random generator) {
		while (i3.hasNext()) {
            int shot = generator.nextInt(15);
            Alien a = (Alien) i3.next();
            Alien.Bomb b = a.getBomb();
            spaceL(shot, a, b);

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.isDestroyed()) {
                spaceH(b, bombX, bombY, playerX, playerY);
            }

            spaceI(b);
        }
	}

	private void spaceL(int shot, Alien a, Alien.Bomb b) {
		if (shot == CHANCE && a.isVisible() && b.isDestroyed()) {

		    b.setDestroyed(false);
		    b.setX(a.getX());
		    b.setY(a.getY());   
		}
	}

	private void spaceI(Alien.Bomb b) {
		if (!b.isDestroyed()) {
		    b.setY(b.getY() + 1);   
		    if (b.getY() >= GROUND - BOMB_HEIGHT) {
		        b.setDestroyed(true);
		    }
		}
	}

	private void spaceH(Alien.Bomb b, int bombX, int bombY, int playerX, int playerY) {
		if ( bombX >= (playerX) && 
		    bombX <= (playerX+PLAYER_WIDTH) &&
		    bombY >= (playerY) && 
		    bombY <= (playerY+PLAYER_HEIGHT) ) {
		        ImageIcon ii = 
		            new ImageIcon(this.getClass().getResource(expl));
		        player.setImage(ii.getImage());
		        player.setDying(true);
		        b.setDestroyed(true);;
		    }
	}

	private void spaceG(Iterator<Alien> it) {
		while (it.hasNext()) {
            Alien alien = (Alien) it.next();
            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > GROUND - ALIEN_HEIGHT) {
                    ingame = false;
                    message = "Invasion!";
                }

                alien.act(direction);
            }
        }
	}

	private void spaceF(Iterator<Alien> it, int shotX, int shotY) {
		while (it.hasNext()) {
		    Alien alien = (Alien) it.next();
		    int alienX = alien.getX();
		    int alienY = alien.getY();

		    if (alien.isVisible() && shot.isVisible()) {
		        spaceA(shotX, shotY, alien, alienX, alienY);
		    }
		}
	}

	private void spaceD(int x) {
		if (x <= BORDER_LEFT && direction != 1) {
		    direction = 1;

		    Iterator<Alien> i2 = aliens.iterator();
		    while (i2.hasNext()) {
		        Alien a = (Alien)i2.next();
		        a.setY(a.getY() + GO_DOWN);
		    }
		}
	}

	private void spaceC(int x) {
		if (x  >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
		     direction = -1;
		     Iterator<Alien> i1 = aliens.iterator();
		     while (i1.hasNext()) {
		         Alien a2 = (Alien) i1.next();
		         a2.setY(a2.getY() + GO_DOWN);
		     }
		 }
	}

	private void spaceB() {
		if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
            ingame = false;
            message = "Game won!";
        }
	}

	private void spaceA(int shotX, int shotY, Alien alien, int alienX, int alienY) {
		if (shotX >= (alienX) && 
		    shotX <= (alienX + ALIEN_WIDTH) &&
		    shotY >= (alienY) &&
		    shotY <= (alienY+ALIEN_HEIGHT) ) {
		        ImageIcon ii = 
		            new ImageIcon(getClass().getResource(expl));
		        alien.setImage(ii.getImage());
		        alien.setDying(true);
		        deaths++;
		        shot.die();
		    }
	}

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {
            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
            	//exception
            }
            beforeTime = System.currentTimeMillis();
        }
        gameOver();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {

          player.keyPressed(e);

          int x = player.getX();
          int y = player.getY();

          if (ingame)
          {
            if (e.isControlDown()) {
                if (!shot.isVisible())
                    shot = new Shot(x, y);
            }
          }
        }
    }
}
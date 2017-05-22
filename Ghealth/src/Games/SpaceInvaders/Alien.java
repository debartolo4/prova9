package Games.SpaceInvaders;

import javax.swing.ImageIcon;


public class Alien extends Sprite {

    private Bomb bomb;
//  private final String shot = "shot.png";

    public Alien(int x, int y) {
    	
    	String shot = "shot.png";
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());

    }

    public void act(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public class Bomb extends Sprite {

 //     private final String bomb = "bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y) {
        	
        	String bomb = "bomb.png";
            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
            setImage(ii.getImage());
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}
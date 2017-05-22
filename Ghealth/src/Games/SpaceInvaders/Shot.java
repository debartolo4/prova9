package Games.SpaceInvaders;


import javax.swing.ImageIcon;


public class Shot extends Sprite {

//    private String shot = "shot.png";
//    private final int H_SPACE = 6;
//    private final int V_SPACE = 1;

    public Shot() {
    }

    public Shot(int x, int y) {
    	
        String shot = "shot.png";
        int H_SPACE = 6;
        int V_SPACE = 1;

        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}
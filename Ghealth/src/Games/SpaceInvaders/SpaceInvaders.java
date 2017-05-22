package Games.SpaceInvaders;


import javax.swing.JFrame;

public class SpaceInvaders extends JFrame implements Commons {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8019017818228599332L;

	public SpaceInvaders()
    {
        add(new Board());
        setTitle("Space Invaders");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
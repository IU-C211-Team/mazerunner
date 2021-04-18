import java.awt.Color;

import javax.swing.JPanel;


public class Player extends JPanel{
	int x, y;
	
    public Player() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(MazeGenerator.panelSize, MazeGenerator.panelSize);
    }

    //methods created for moving the player 
    
    public void moveLeft() {

    }

    public void moveRight() {

    }

    public void moveUp() {

    }

    public void moveDown() {

    }
}

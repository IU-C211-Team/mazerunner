import java.awt.Color;

import javax.swing.JPanel;


public class Player extends JPanel{
	int x, y;
	
    public Player() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(MazeGenerator.panelSize, MazeGenerator.panelSize);
        this.
    }

    //methods created for moving the player 
    
    public void moveLeft() {
        this.x -= 1;
    }

    public void moveRight() {
        this.x += 1;
    }

    public void moveUp() {
        this.y += 1;
    }

    public void moveDown() {
        this.y -= 1;
    }
}

//@author Chloe Uphaus

import javafx.scene.layout.Pane;

public class Space extends Pane{
    int xCoord, yCoord;
    boolean isWall = true;
    
    public Space(int x, int y){
        this.xCoord = x;
        this.yCoord = y;
    }
    
    public void setWall(boolean wall){
        this.isWall = wall;
    }
    
    public boolean getWall() {
    	return this.isWall;
    }
    
    public int getX() {
    	return this.xCoord;
    }
    
    public int getY() {
    	return this.yCoord;
    }
    
}
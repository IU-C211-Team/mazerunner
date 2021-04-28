//@author Cecily Dronebarger
//Contributors: Chloe Uphaus and Shaun Snyder


import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Player extends Node{
	int currentX, currentY;
    int pastX, pastY;
    int row, col;
	private ArrayList<Space> spaces;
	private GridPane gridpane;
    Node prevNode;
    Node targetNode;
    private AlertBox congrats;
	
    public Player(ArrayList<Space> spaces, GridPane gridpane, int currentX, int currentY, int row, int col) {
    	this.spaces = spaces;
		this.gridpane = gridpane;
        this.currentX = currentX;
        this.currentY = currentY;
        this.row = row;
        this.col = col;

    }

    Node moveUp(){
            pastY = currentY;
            currentY--;
            
            if (!getWall(currentX, currentY)) {
                prevNode = setNode(gridpane, currentX, pastY);
                targetNode = getNode(gridpane, currentX, currentY);
            } else {
                currentY++;
                // pastY++;
            }
            return targetNode;
    }

    Node moveDown(){
        pastY = currentY;
		currentY++;
			if (!getWall(currentX, currentY)) {
				prevNode = setNode(gridpane, currentX, pastY);
				targetNode = getNode(gridpane, currentX, currentY);
			} else {
				currentY--;
				// 	pastY--;
				}
        return targetNode;
    }

    Node moveLeft(){
        pastX = currentX;
		currentX--;			
			if (!getWall(currentX, currentY) && currentX >= 0) {
				prevNode = setNode(gridpane, pastX, currentY);
				targetNode = getNode(gridpane, currentX, currentY);
			} else {
				currentX++;
				// 	pastX++;
			}
        return targetNode;
    }

    Node moveRight(){
        pastX = currentX;
		currentX++;
			if (!getWall(currentX, currentY) && currentX != 20) {
				prevNode = setNode(gridpane, pastX, currentY);
				targetNode = getNode(gridpane, currentX, currentY);
				} else if (currentX == 20) {
					congrats = new AlertBox("Congrats", "Congratulations you beat the maze!");
					congrats.getBox();
                } else {
					currentX--;
				// 	pastX--;
				}
        return targetNode;
    }



    Node getInitNode(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) { 
	    	if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	node.setStyle("-fx-background-color: #ffff00;");
	        	this.currentX = col;
	        	this.pastX = col;
	        	this.currentY = row;
	        	this.pastY = row;
                targetNode = node;
	        	return node;
	        }
	    }
	    return null;
	}

    boolean getWall(int col, int row) {
		for (int i = 0; i < spaces.size(); i++) {
    		if (this.spaces.get(i).getX() == col && this.spaces.get(i).getY() == row) {
    			return this.spaces.get(i).isWall;
    		}
    	}
		return false;
	}

    Node getNode(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) { 
	    	if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	node.setStyle("-fx-background-color: #ffff00;");
	        	return node;
	        }
	    }
	    return null;
	}

    Node setNode(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) { 
	    	if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	node.setStyle("-fx-background-color: #ffffff;");
	        	return node;
	        }
	    }
	    return null;
	}
}
//@author Chloe Uphaus 
//contributors: Cecily Dronebarger and Shaun Snyder

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MazeCreator {

	private Stage window = new Stage();
	
	private BorderPane border = new BorderPane();
	private GridPane mapGrid = new GridPane();
	
	private Node targetNode;
	private Node prevNode;
	
	private Scene scene = new Scene(border, ScreenCustomizer.defaultWidth, ScreenCustomizer.defaultHeight);
	
	private Button close = new Button("X");
	
	private Font font = new Font("Helvetica", 12);
	
	private Region region1 = new Region();
	private Region region2 = new Region();
	
	private ArrayList<Space> spaces = new ArrayList<Space>();
	
	private static int rows = 20;
	private static int columns = 20;
	private int currentX;
	private int currentY;
	private int pastX;
	private int pastY;
	
	private ScreenCustomizer sCustom = new ScreenCustomizer();
	
	private Player player = new Player();
	
	
	public MazeCreator(String level) {
		ScreenCustomizer sCustom = new ScreenCustomizer();
		scene.getStylesheets().add(getClass().getResource("designstyles.css").toExternalForm());
		scene.setFill(Color.TRANSPARENT);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if(e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
				pastY = currentY;
				currentY--;
				System.out.println("UP\nPrev Y: " + pastY + "\nCurrent Y: " + currentY);
			} else if(e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
				pastX = currentX;
				currentX++;
				if (!getWall(currentX, currentY)) {
					prevNode = setNode(mapGrid, pastX, pastY);
					targetNode = getNode(mapGrid, currentX, currentY);
				} else {
					currentX--;
					pastX--;
				}
				
				System.out.println("RIGHT\nPrev X: " + pastX + "\nCurrent X: " + currentX);
			} else if(e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
				pastY = currentY;
				currentY++;
				System.out.println("DOWN\nPrev Y: " + pastY + "\nCurrent Y: " + currentY);
			} else if(e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
				pastX = currentX;
				currentX--;
				System.out.println("LEFT\nPrev X: " + pastX + "\nCurrent X: " + currentX);
			} 
		});
		
		border.setTop(setTop());
		border.setCenter(loadMap(level));
		border.setBackground(new Background(sCustom.setBackground("#F4EFE9", "#DECFBE", "#C8AF93", 1)));
		border.setBorder(sCustom.loadBorder());
		
		if (ScreenCustomizer.fullscreen) {
			window.setWidth(ScreenCustomizer.screenWidth);
			window.setHeight(ScreenCustomizer.screenHeight);
			window.setX(ScreenCustomizer.screenBounds.getMinX());
			window.setY(ScreenCustomizer.screenBounds.getMinY());
		} else {
			window.setX((ScreenCustomizer.screenWidth - ScreenCustomizer.defaultWidth) / 2);
			window.setY((ScreenCustomizer.screenHeight - ScreenCustomizer.defaultHeight) / 2);
		}
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.UNDECORATED);
		window.initStyle(StageStyle.TRANSPARENT);
		window.setScene(scene);
		window.show();
	}
	
	private HBox setTop() {
		HBox top = new HBox();
		
		Tooltip tp = new Tooltip("Close Window");
		tp.setFont(font);
		
		close.setTooltip(tp);
		close.getStyleClass().add("button-style-closebutton");
		close.setOnAction(e -> {
			Node  source = (Node)  e.getSource(); 
			Stage stage  = (Stage) source.getScene().getWindow();
			stage.close();
		});
		
		top.setAlignment(Pos.TOP_RIGHT);
		top.getChildren().add(close);
		top.setPadding(new Insets(ScreenCustomizer.screenHeight * .01, ScreenCustomizer.screenWidth * .01, 
				ScreenCustomizer.screenHeight * .01, ScreenCustomizer.screenWidth * .01));
		
		return top;
	}
	
	private GridPane loadMap(String mapLvl) {
		mapGrid.setAlignment(Pos.CENTER);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapLvl));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			boolean start = false;
			
			while(line != null) {
				sb.append(line);
				line = br.readLine();
			}
			char[] mapChar = sb.toString().toCharArray();
			int count = 0, row = 0;
			
			for (int i = 0; i < mapChar.length; i++) {
				if (count == 20) {
					count = 0;
					row++;
				}
				
				spaces.add(new Space(count, row));
				
				if (ScreenCustomizer.fullscreen) {
					spaces.get(i).setMinHeight(40);
					spaces.get(i).setMinWidth(40);
				} else {
					spaces.get(i).setMinHeight(30);
					spaces.get(i).setMinWidth(30);
				}
				if (mapChar[i] == '0') {
					spaces.get(i).setStyle("-fx-background-color: #905E26;");
					
				} else if (mapChar[i] == '1') {
					spaces.get(i).setStyle("-fx-background-color: #ffffff;");
					spaces.get(i).setWall(false);
					if (spaces.get(i).getX() == 0) {
						start = true;
						mapGrid.add(spaces.get(i), i % 20, row);
						targetNode = getInitNode(mapGrid, count, row);
					} else if (spaces.get(i).getX() == 19) {
						//System.out.println("Exit is located at: " + row);
					}
				}
				if (!start) {
					mapGrid.add(spaces.get(i), i % 20, row);
				}
				count++;
				start = false;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return mapGrid;
	}
	
	private Node getInitNode(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) { 
	    	if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	node.setStyle("-fx-background-color: #ffff00;");
	        	currentX = col;
	        	pastX = col;
	        	currentY = row;
	        	pastY = row;
	        	System.out.println("Original X and Y: " + currentX + ", " + currentY);
	        	return node;
	        }
	    }
	    return null;
	}
	private Node setNode(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) { 
	    	if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	node.setStyle("-fx-background-color: #ffffff;");
	        	return node;
	        }
	    }
	    return null;
	}
	private Node getNode(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) { 
	    	if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	node.setStyle("-fx-background-color: #ffff00;");
	        	return node;
	        }
	    }
	    return null;
	}
	
	private boolean getWall(int col, int row) {
		for (int i = 0; i < spaces.size(); i++) {
    		if (spaces.get(i).getX() == col && spaces.get(i).getY() == row) {
    			return spaces.get(i).isWall;
    		}
    	}
		return false;
	}
	
}

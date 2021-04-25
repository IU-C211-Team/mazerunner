import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
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
	
	private Scene scene = new Scene(border, ScreenCustomizer.defaultWidth, ScreenCustomizer.defaultHeight);
	
	private Button close = new Button("X");
	
	private Font font = new Font("Helvetica", 12);
	
	private Region region1 = new Region();
	private Region region2 = new Region();
	
	private Pane[] boxes = new Pane[400];
	//private Space[] test = new Space[400];
	private ArrayList<Space> test = new ArrayList<Space>();
	
	private static int rows = 20;
	private static int columns = 20;
	
	private ScreenCustomizer sCustom = new ScreenCustomizer();
	
	
	public MazeCreator(String level) {
		ScreenCustomizer sCustom = new ScreenCustomizer();
		scene.getStylesheets().add(getClass().getResource("designstyles.css").toExternalForm());
		scene.setFill(Color.TRANSPARENT);
		
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
		GridPane mapGrid = new GridPane();
		mapGrid.setAlignment(Pos.CENTER);
		
	
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapLvl));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
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
				
				boxes[i] = new Pane();
				test.add(new Space(count, row));
				
				if (ScreenCustomizer.fullscreen) {
					boxes[i].setMinHeight(40);
					boxes[i].setMinWidth(40);
				} else {
					boxes[i].setMinHeight(30);
					boxes[i].setMinWidth(30);
				}
				if (mapChar[i] == '0') {
					boxes[i].setStyle("-fx-background-color: #905E26;");
					
				} else if (mapChar[i] == '1') {
					boxes[i].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					test.get(i).setWall(false);
					if (test.get(i).getX() == 0) {
						System.out.println("Entrance is located at: " + row);
					} else if (test.get(i).getX() == 19) {
						System.out.println("Exit is located at: " + row);
					}
				}
				mapGrid.add(boxes[i], i % 20, row);
				count++;
				
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return mapGrid;
	}
}

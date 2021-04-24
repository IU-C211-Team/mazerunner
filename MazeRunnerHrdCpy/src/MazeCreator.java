import java.io.BufferedReader;
import java.io.FileReader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MazeCreator {

	private Stage window = new Stage();
	
	private BorderPane border = new BorderPane();
	
	private Scene scene = new Scene(border, ScreenCustomizer.defaultWidth, ScreenCustomizer.defaultHeight);
	
	private Region region1 = new Region();
	private Region region2 = new Region();
	
	private Pane[] boxes = new Pane[400];
	
	private static int rows = 20;
	private static int columns = 20;
	
	
	public MazeCreator(String level) {
		ScreenCustomizer sCustom = new ScreenCustomizer();
		
		
		border.setCenter(loadMap(level));
		border.setBackground(new Background(sCustom.setBackground("#F4EFE9", "#DECFBE", "#C8AF93", 1)));
		
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
	
	public GridPane loadMap(String mapLvl) {
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
				boxes[i] = new Pane();
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
				}
				
				if (count == 20) {
					count = 0;
					row++;
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

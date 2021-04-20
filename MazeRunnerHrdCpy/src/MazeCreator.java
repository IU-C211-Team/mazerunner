import java.io.BufferedReader;
import java.io.FileReader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MazeCreator {

	private Stage window = new Stage();
	
	private Pane[] boxes = new Pane[400];
	
	private static int rows = 20;
	private static int columns = 20;
	
	
	public MazeCreator(String level) {
		
		Scene scene = new Scene(loadMap(level));
		
		
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
			System.out.println(mapLvl);
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
				boxes[i].setMinHeight(30);
				boxes[i].setMinWidth(30);
				if (mapChar[i] == '0') {
					boxes[i].setStyle("-fx-background-color: #c0c0c0;");
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

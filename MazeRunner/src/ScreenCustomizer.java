import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScreenCustomizer {
	private static Rectangle2D screenBounds = Screen.getPrimary().getBounds();

	public static double screenHeight = screenBounds.getHeight();
	public static double screenWidth = screenBounds.getWidth();
	public BorderPane border = new BorderPane();
	
	private Scene scene = new Scene(border, screenWidth * .65, screenHeight * .75);
	private Button close = new Button("X");
	
	BackgroundFill backgroundScheme = new BackgroundFill(Color.web("#D3BFA8"), CornerRadii.EMPTY, Insets.EMPTY);
	
	public Scene constructScene() {
		scene.getStylesheets().add(getClass().getResource("designstyles.css").toExternalForm());
		
		close.setTooltip(new Tooltip("Closes App"));
		close.getStyleClass().add("button-style-closebutton");
		close.setOnAction(e -> {
			Node  source = (Node)  e.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		});
		
		border.setTop(setTitleBar());
		border.setBackground(new Background(backgroundScheme));
		
		return scene;
	}
	
	private HBox setTitleBar() {
		HBox title = new HBox();
		
		
		
		return title;
	}
}

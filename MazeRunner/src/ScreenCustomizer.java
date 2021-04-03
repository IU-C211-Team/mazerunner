import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
	
	BackgroundFill backgroundScheme = new BackgroundFill(Color.web("#E9DFD4"), CornerRadii.EMPTY, Insets.EMPTY);
	BackgroundFill titleBackground = new BackgroundFill(Color.web("#B18E67"), CornerRadii.EMPTY, Insets.EMPTY);
	
	Image logo = new Image("logo.png");
	ImageView logoIV = new ImageView(logo);
	
	Region r1 = new Region();
	Region r2 = new Region();
	
	public Scene constructScene() {
		scene.getStylesheets().add(getClass().getResource("designstyles.css").toExternalForm());
		
		border.setTop(setTitleBar());
		border.setBackground(new Background(backgroundScheme));
		
		return scene;
	}
	
	private HBox setTitleBar() {
		HBox title = new HBox();
		logoIV.setFitHeight(screenHeight * .1);
		logoIV.setPreserveRatio(true);
		HBox.setHgrow(r1, Priority.ALWAYS);
		HBox.setHgrow(r2, Priority.ALWAYS);
		
		close.setTooltip(new Tooltip("Closes App"));
		close.getStyleClass().add("button-style-closebutton");
		close.setOnAction(e -> {
			Node  source = (Node)  e.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		});
		
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(screenHeight * .01, screenWidth * .01, screenHeight * .01, screenWidth * .01));
		title.setBackground(new Background(titleBackground));
		title.getChildren().addAll(r1, logoIV, r2, close);
		
		return title;
	}
}

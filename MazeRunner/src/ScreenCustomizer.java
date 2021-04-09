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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScreenCustomizer {
	private static Rectangle2D screenBounds = Screen.getPrimary().getBounds();

	public static double screenHeight = screenBounds.getHeight();
	public static double screenWidth = screenBounds.getWidth();
	public BorderPane border = new BorderPane();

	private Scene scene = new Scene(border, screenWidth * .65, screenHeight * .75);
	private Button close = new Button("X");
	private Button minimize = new Button("\uD83D\uDDD5");
	private Button maximize = new Button("\uD83D\uDDD6");
	
	private Font font = new Font("Helvetica", 12);
	
	private Boolean fullscreen = false;

	private Image logo = new Image("logo.png");
	private ImageView logoIV = new ImageView(logo);

	private Region r1 = new Region();
	private Region r2 = new Region();

	public Scene constructScene() {
		scene.getStylesheets().add(getClass().getResource("designstyles.css").toExternalForm());

		border.setTop(setTitleBar());
		border.setBackground(new Background(setBackground("#F4EFE9", "#DECFBE", "#C8AF93")));

		return scene;
	}

	private HBox setTitleBar() {
		HBox title = new HBox();
		logoIV.setFitHeight(screenHeight * .1);
		logoIV.setPreserveRatio(true);
		HBox.setHgrow(r1, Priority.ALWAYS);
		HBox.setHgrow(r2, Priority.ALWAYS);
		
		minimize.setTooltip(setToolTips(1));
		minimize.getStyleClass().add("button-style-minimizebutton");
		minimize.setOnAction(e -> {
			Stage stage = (Stage) minimize.getScene().getWindow();
			stage.setIconified(true);
		});

		maximize.setTooltip(setToolTips(2));
		maximize.getStyleClass().add("button-style-maximizebutton");
		maximize.setOnAction(e -> {
			Node  source = (Node)  e.getSource();
			Stage stage  = (Stage) source.getScene().getWindow();
			if (fullscreen) {
				stage.setWidth(screenWidth * .65);
				stage.setHeight(screenHeight * .75);
				stage.setX((screenWidth - stage.getWidth()) / 2);
				stage.setY((screenHeight - stage.getHeight()) / 2);
				logoIV.setFitHeight(screenHeight * .1);
				maximize.setText("\uD83D\uDDD6");
				maximize.setTooltip(setToolTips(2));
				fullscreen = false;
			} else {
				stage.setWidth(screenWidth);
				stage.setHeight(screenHeight);
				stage.setX(screenBounds.getMinX());
				stage.setY(screenBounds.getMinY());
				logoIV.setFitHeight(screenHeight * .125);
				maximize.setText("\uD83D\uDDD7");
				maximize.setTooltip(setToolTips(3));
				fullscreen = true;
			}

		});

		close.setTooltip(setToolTips(4));
		close.getStyleClass().add("button-style-closebutton");
		close.setOnAction(e -> {
			Node  source = (Node)  e.getSource(); 
			Stage stage  = (Stage) source.getScene().getWindow();
			stage.close();
		});

		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(screenHeight * .01, screenWidth * .01, screenHeight * .01, screenWidth * .01));
		title.setSpacing(screenWidth * .002);
		title.setBackground(new Background(setBackground("#B18E67")));
		title.getChildren().addAll(r1, logoIV, r2, minimize, maximize, close);

		return title;
	}

	private void setCenterArea() {
		// Here is where the main code for the maze will be displayed
	}

	private BackgroundFill setBackground(String color) {
		BackgroundFill bf = new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY);

		return bf;
	}
	
	private BackgroundFill setBackground(String color1, String color2, String color3) {
		Stop[] stop = {new Stop(0, Color.web(color1)), new Stop(.5, Color.web(color2)), new Stop(1, Color.web(color3))};
		LinearGradient lg;
		
		if (fullscreen) {
			lg = new LinearGradient(0, 0, (screenWidth) / 2, (screenHeight) / 2, false, CycleMethod.REFLECT, stop);
		} else {
			lg = new LinearGradient(0, 0, (screenWidth * .65) / 2, (screenHeight * .75) / 2, false, CycleMethod.REFLECT, stop);
		}
		
		BackgroundFill bf = new BackgroundFill(lg, CornerRadii.EMPTY, Insets.EMPTY);
		
		return bf;
	}
	
	private Tooltip setToolTips(int selection) {
		Tooltip tp = new Tooltip();
		
		switch (selection) {
		case 1:
			tp = new Tooltip("Minimize");
			break;
		case 2:
			tp = new Tooltip("Maximize");
			break;
		case 3:
			tp = new Tooltip("Restore");
			break;
		case 4:
			tp = new Tooltip("Close");
			break;
		}
		
		tp.setFont(font);
		return tp;
	}
}

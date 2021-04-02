import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class ScreenCustomizer {
	private static Rectangle2D screenBounds = Screen.getPrimary().getBounds();

	public static double screenHeight = screenBounds.getHeight();
	public static double screenWidth = screenBounds.getWidth();
	public BorderPane border = new BorderPane();
	
	public Scene scene = new Scene(border, screenWidth * .65, screenHeight * .75);
}

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenCustomizer {
	private static Rectangle2D screenBounds = Screen.getPrimary().getBounds();

	public static double screenHeight = screenBounds.getHeight();
	public static double screenWidth = screenBounds.getWidth();
	
	
}

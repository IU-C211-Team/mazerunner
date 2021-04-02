import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MazeRunner extends Application {
	ScreenCustomizer sCustom = new ScreenCustomizer();
	
	double scrHeight = sCustom.screenHeight;
	double scrWidth = sCustom.screenWidth;
	
	BorderPane border = new BorderPane();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage mainScreen) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(border, scrWidth * .65, scrHeight * .75);
		
		mainScreen.initStyle(StageStyle.UNDECORATED);
		mainScreen.initStyle(StageStyle.TRANSPARENT);
		mainScreen.setScene(scene);
		mainScreen.show();
	}

}

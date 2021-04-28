import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox {

	private String title, message;
	
	private Button okay = new Button("OKAY");
	
	private Font font = new Font("Helvetica", 22);
	
	private Stage window = new Stage();
	
	private Scene scene;
	
	private VBox textArea = new VBox();
	
	private HBox buttonArea = new HBox();
	
	private Text boxMessage = new Text();
	
	private Region vSpacer = new Region();
	
	private ScreenCustomizer sCustom = new ScreenCustomizer();
	
	public AlertBox() {
		
	}
	
	public AlertBox(String title2Set, String message2Set) {
		this.title = title2Set;
		this.message = message2Set;
	}
	
	public void getBox() {
		setWindowSettings();
		
		boxMessage.setText(this.message);
		boxMessage.setFill(Color.BLACK);
		boxMessage.setFont(font);
		
		okay.setFont(font);
		
		okay.setOnAction(e -> {
			window.close();
		});
		
		buttonArea.setAlignment(Pos.CENTER);
		buttonArea.setPadding(new Insets(2));
		buttonArea.getChildren().add(okay);
		
		textArea.setAlignment(Pos.CENTER);
		textArea.setPadding(new Insets(20));
		textArea.setSpacing(15);
		
		VBox.setVgrow(vSpacer, Priority.ALWAYS);
		textArea.setBackground(new Background(sCustom.setBackground("#F4EFE9", "#DECFBE", "#C8AF93", 1)));
		textArea.getChildren().addAll(boxMessage, vSpacer, buttonArea);
		textArea.setBorder(sCustom.loadBorder());
		
		scene = new Scene(textArea);
		scene.setFill(Color.TRANSPARENT);
		window.setScene(scene);
		window.showAndWait();
		window.setX((ScreenCustomizer.screenWidth - window.getWidth()) / 2);
		window.setY((ScreenCustomizer.screenHeight - window.getHeight()) / 2);
	}
	
	private void setWindowSettings() {
		window.initStyle(StageStyle.UNDECORATED);
		window.initStyle(StageStyle.TRANSPARENT);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(this.title);	
	}
}

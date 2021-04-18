import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	private String title, message;
	
	private Button okay = new Button("OKAY");
	
	private Font font = new Font("Helvetica", 20);
	
	private double height = ScreenCustomizer.screenHeight * .18;
	private double width = ScreenCustomizer.screenWidth *.18;
	
	private Stage window = new Stage();
	
	private Scene scene;
	
	private VBox textArea = new VBox();
	
	private HBox buttonArea = new HBox();
	
	private Text boxMessage = new Text();
	
	private Region vSpacer = new Region();
	
	ScreenCustomizer sCustom = new ScreenCustomizer();
	
	public AlertBox() {
		
	}
	
	public AlertBox(String title2Set, String message2Set) {
		this.title = title2Set;
		this.message = message2Set;
	}
	
	public void getCongratsBox() {
		setWindowSettings();
		
		boxMessage.setText(this.message);
		boxMessage.setFill(Color.WHITE);
		boxMessage.setFont(font);
		
		okay.setFont(font);
		okay.setMinWidth(ScreenCustomizer.screenWidth * .11);
		
		okay.setOnAction(e -> {
			window.close();
		});
		
		buttonArea.setAlignment(Pos.CENTER);
		buttonArea.setPadding(new Insets(2));
		buttonArea.getChildren().add(okay);
		
		textArea.setAlignment(Pos.CENTER);
		textArea.setPadding(new Insets(20, 10, 15, 10));
		VBox.setVgrow(vSpacer, Priority.ALWAYS);
		textArea.setBackground(new Background(sCustom.setBackground("#F4EFE9", "#DECFBE", "#C8AF93", 1)));
		textArea.getChildren().addAll(boxMessage, vSpacer, buttonArea);
		
		scene = new Scene(textArea);
		
		window.setScene(scene);
		window.showAndWait();
	}
	
	private void setWindowSettings() {
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(this.title);
		window.setMinHeight(height);
		window.setMinWidth(width);
	}
}

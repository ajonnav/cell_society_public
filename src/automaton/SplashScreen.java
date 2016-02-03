package automaton;


import java.io.File;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class SplashScreen {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	
	private static Group root;
	private static String chosenFile;
	
	public Scene initScreen() {
		root = new Group();
		Scene s = new Scene(root, WIDTH, HEIGHT);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("CellSocietyBackground.jpg"));
		ImageView background = new ImageView(image);
		root.getChildren().add(background);
		setupFileChoose();
		return s;
	}
	
	public void setupFileChoose() {
		FileChooser f = new FileChooser();
		f.setTitle("Open XML file");
		setUploadButton(f);
	}
	
	private void setUploadButton(FileChooser f) {
		Button b = new Button("Open XML");
		b.setLayoutX(WIDTH/2.25);
		b.setLayoutY(HEIGHT*2/3);
		root.getChildren().add(b);
		b.setOnAction(e -> selectFiletoParse(f));
	}
	
	private void selectFiletoParse(FileChooser f) {
		File file = f.showOpenDialog(root.getScene().getWindow());
		if (file != null) {
			chosenFile = file.getName();
			//return object from parsed
			openAutomationWindow();
		}
	}
	
	private void openAutomationWindow() {
		AutomatonDisplay myAutomation = new AutomatonDisplay();
		myAutomation.loadAutomaton(myAutomation);
	}
	
	public String getChosenFile() {
		return chosenFile;
	}
}

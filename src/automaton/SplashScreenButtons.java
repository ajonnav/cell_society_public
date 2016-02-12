package automaton;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;

public class SplashScreenButtons extends AutomatonButtons {
	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/";
	private static final int HBOX_SPACING = 10;
	private HBox hbox;
	private Group root;
	private Collection<Control> myControllers;
	private AutomatonControlFactory a;
	
	public SplashScreenButtons(Group root, String language) throws IOException {
		super(root, language);
		
		this.root = root;
		XMLArgs x = new XMLArgs();
		hbox = new HBox(HBOX_SPACING);
		HashMap<String, String> myDefaults = x.readXML("testdefaultSplash.xml");
	}

}

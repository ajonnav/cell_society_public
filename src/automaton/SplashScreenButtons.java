package automaton;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import simulations.CA;
import utilities.xmlStringtoCollection;

public class SplashScreenButtons extends AutomatonButtons {
	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/";
	private static final int HBOX_SPACING = 20;
	private HBox hbox;
	private Group root;
	private Collection<Control> myControllers;
	private AutomatonControlFactory a;
	
	public SplashScreenButtons(Group root, String language) throws IOException, ParserConfigurationException, SAXException {
		super(root, language);
		
		this.root = root;
		XMLArgs x = new XMLArgs();
		hbox = new HBox(HBOX_SPACING);
		x.readXML("testdefaultSplash.xml");
		a = new AutomatonControlFactory(x.getAsListOfString("controlNames"),
				xmlStringtoCollection.xmlStringtoControlsCollection(x.getAsString("controlTypes")), "English");
	}

	@Override
	public void setDisplayControllers() {
		myControllers = a.returnControls();
		setControlActions(myControllers);
		addtohbox(myControllers);
		setHBox();
	}
	
	private void setHBox() {
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.setLayoutX(200);
		hbox.setLayoutY(300);
		root.getChildren().add(hbox);
	}
	
	private void addtohbox(Collection<Control> controllers) {
		for (Control c : controllers) {
			hbox.getChildren().add(c);
		}
	}

	@Override
	public void setControlActions(Collection<Control> controllers) {
	}

	@Override
	public void setDisplayControllers(CA ca) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setControlActions(Collection<Control> controllers, CA ca) {
		// TODO Auto-generated method stub
	}

}

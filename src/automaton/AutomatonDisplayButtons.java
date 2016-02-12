package automaton;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import simulations.CA;
import utilities.xmlStringtoCollection;

public class AutomatonDisplayButtons extends AutomatonButtons {
	private static final int HBOX_SPACING = 5;
	private HBox hbox;
	private Group root;
	private Collection<Control> myControllers;
	private AutomatonControlFactory a;
	
	public AutomatonDisplayButtons(Group root, String language) throws IOException, ParserConfigurationException, SAXException {
		super(root, language);
		// TODO Auto-generated constructor stub
		this.root = root;
		XMLArgs x = new XMLArgs();
		hbox = new HBox(HBOX_SPACING);
		x.readXML("testdefaultsim.xml");
		a = new AutomatonControlFactory(x.getAsListOfString("controlNames"),
				xmlStringtoCollection.xmlStringtoControlsCollection(x.getAsString("controlTypes")), "English");
	}
	
	public void setDisplayControllers(CA ca) {
		myControllers = a.returnControls();
		setControlActions(myControllers, ca);
		addtohbox(myControllers);
		setHBox(ca);
	}
	
	private void setHBox(CA ca) {
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.setLayoutX(ca.getSimWidth()/7);
		hbox.setLayoutY(ca.getSimWidth() + 10);
		root.getChildren().add(hbox);
	}
	
	private void addtohbox(Collection<Control> controllers) {
		for (Control c : controllers) {
			hbox.getChildren().add(c);
		}
	}
	
	public void setControlActions(Collection<Control> controllers, CA ca) {
		for (Control c : controllers) {
			switch (ButtonNames.valueOf(c.getId())) {
				case StartButton:
					((Button) c).setOnAction(e -> ca.getTimeline().play());
					break;
				case PauseButton:
					((Button) c).setOnAction(e -> ca.getTimeline().pause());
					break;
				case StepButton:
					((Button) c).setOnAction(e -> {
						ca.updateCells();
						ca.drawCells();
					});
					break;
				case ResetButton:
					((Button) c).setOnAction(e -> {
						ca.getTimeline().stop();
						ca.initializeScreen();
					});
					break;
				case SpeedUpButton:
					((Button) c).setOnAction(e -> {
				    	if(ca.getTimeline().getRate()<=8)
			    		ca.getTimeline().setRate(ca.getTimeline().getRate()*2);
					});
					break;
				case SlowDownButton:
					((Button) c).setOnAction(e -> {
				    	ca.getTimeline().setRate(ca.getTimeline().getRate()/2);
				    	});
				default:
					break;
			}
		}

	}

}

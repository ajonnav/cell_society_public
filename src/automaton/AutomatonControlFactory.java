package automaton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JSlider;

import javafx.scene.control.Button;
import javafx.scene.control.Control;

public class AutomatonControlFactory {
	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/";
	private List<Control> controllers;
	private ResourceBundle myResources;

	public AutomatonControlFactory() {
		
	}
	
	public AutomatonControlFactory(List<String> controlNames, List<Controls> controlType, String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		createControls(controlNames, controlType);
	}

	private void createControls(List<String> controlNames, List<Controls> controlType) {
		controllers = new ArrayList<Control>();
		Iterator<String> names = controlNames.iterator();
		for (Controls c : controlType) {
			String n = names.next();
			switch (c) {
				case Button: 
					//make it work with the resource file
					Button b = createButton(myResources.getString(n.toString()), n.toString());
					controllers.add(b);
					break;
				case Slider:
					//make a slider
					break;
				case ComboBox:
					//make a combobox
					break;
				default:
			}
		}
	}
	
	private Button createButton(String text, String id) {
		Button b = new Button(text);
		b.setId(id);
		return b;
	}
	
	private JSlider createSlider() {
		JSlider j =  new JSlider();
		return j;
	}
	
	public List<Control> returnControls() {
		return controllers;
	}
	
	

}

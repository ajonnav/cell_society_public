package factory;
import grid.*;
import java.util.ResourceBundle;
import simexception.ConfigFileException;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;

public class GridFactory {

	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/Errors"; 
	private static ResourceBundle myResources;
	
	private GridFactory(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	}

	public static AnyGrid create(String id, XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		if(id.equals("Finite")) {
			return new FiniteGrid(xmlArgs, autoDisp);
		}
		else if(id.equals("Toroid")) {
			return new ToroidalGrid(xmlArgs, autoDisp);
		}
		else if(id.equals("Infinite")) {
			return null;
		}
		else {
			throw new ConfigFileException(myResources.getString("NotValid"), "name");
		}
	}
}

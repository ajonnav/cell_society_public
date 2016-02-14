package factory;
import java.util.ResourceBundle;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;
import simexception.ConfigFileException;
import simulations.*;

public class SimulationFactory {

	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/Errors"; 
	private static ResourceBundle myResources;
	
	private SimulationFactory(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	}

	public static CA create(String id, XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		if(id.equals("GOL")) {
			return new GameOfLife(xmlArgs, autoDisp);
		}
		else if(id.equals("Fire")) {
			return new Fire(xmlArgs, autoDisp);
		}
		else if(id.equals("Segregation")) {
			return new SegregationGrid(xmlArgs, autoDisp);
		}
		else if(id.equals("Wator")) {
			return new Wator(xmlArgs, autoDisp);
		}
		else {
			throw new ConfigFileException(myResources.getString("NotValid"), "name");
		}
	}
}

package factory;
import java.util.ResourceBundle;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;
import simexception.ConfigFileException;
import simulations.*;

/**
 * Class that returns the right simulation
 * @author aj168
 *
 */
public class SimulationFactory {

	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/Errors"; 
	private static ResourceBundle myResources;
	
	/**
	 * Constructor
	 */
	private SimulationFactory(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	}

	/**
	 * Method that creates the right simulation
	 * @param id simulation name
	 * @param xmlArgs XML arguments that were parsed in
	 * @param autoDisp AutonmatonDisplay to display the simulation
	 * @return
	 */
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
		else if(id.equals("Forging Ants")) {
			return new ForgingAnts(xmlArgs, autoDisp);
		}
		else {
			throw new ConfigFileException(myResources.getString("NotValid"), "name");
		}
	}
}

package factory;
import grid.*;
import java.util.ResourceBundle;
import simexception.ConfigFileException;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;

/**
 * Class that chooses the appropriate grid type
 * @author aj168
 *
 */
public class GridFactory {

	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/Errors"; 
	private static ResourceBundle myResources;
	
	/**
	 * Constructor
	 */
	private GridFactory(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	}

	/**
	 * Method that returns the right grid
	 * @param xmlArgs XML arguments that have been parsed
	 * @param autoDisp AutonmatonDisplay to display the simulation
	 * @return Grid
	 */
	public static AnyGrid create(XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		Direction[] d = DirectionFactory.create(xmlArgs.getAsString("direction"), xmlArgs.getAsString("gridShape"));
		if(xmlArgs.getAsString("edgeType").equals("Finite")) {
			return new FiniteGrid(xmlArgs, autoDisp, d);
		}
		else if(xmlArgs.getAsString("edgeType").equals("Toroid")) {
			return new ToroidalGrid(xmlArgs, autoDisp, d);
		}
		else if(xmlArgs.getAsString("edgeType").equals("Infinite")) {
			return null;
		}
		else {
			throw new ConfigFileException(myResources.getString("NotValid"), "name");
		}
	}
	
	static class DirectionFactory{
		private DirectionFactory(){
			myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		}
		
		private static Direction[] create(String type, String shape){
			if(type.equals("Cardinal")){
				return Direction.CARDINAL_DIRECTIONS;
			}else if(type.equals("All") && shape.equals("Square")){
				return Direction.ALL_DIRECTIONS_SQUARE;
			}else if(type.equals("All") && shape.equals("Hexagon")) {
				return Direction.ALL_DIRECTIONS_HEXAGON;
			}return Direction.CARDINAL_DIRECTIONS;
			
		}
	}
}

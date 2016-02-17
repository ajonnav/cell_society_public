//This entire file is my masterpiece
// COLIN DUFFY
package factory;

import grid.*;
import java.util.ResourceBundle;
import simexception.ConfigFileException;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;

/**
 * Class that chooses the appropriate grid type
 * 
 * @author aj168
 * @author cpd20
 */
public class GridFactory {

	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/Errors";
	private static ResourceBundle myResources;

	/**
	 * Constructor
	 */
	private GridFactory() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	}

	/**
	 * Method that returns the right grid
	 * 
	 * @param xmlArgs
	 *            XML arguments that have been parsed
	 * @param autoDisp
	 *            AutonmatonDisplay to display the simulation
	 * @return Grid
	 */
	public static AnyGrid create(XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		Direction[] d = DirectionFactory.create(
				xmlArgs.getAsString("direction"),
				xmlArgs.getAsString("gridShape"));

		switch (xmlArgs.getAsString("edgeType")) {
		case "Finite":
			return new FiniteGrid(xmlArgs, autoDisp, d);

		case "Toroid":
			return new ToroidalGrid(xmlArgs, autoDisp, d);

		case "Infinite":
			throw new ConfigFileException("Feature not Implemented: ", "name");

		default:
			throw new ConfigFileException(myResources.getString("NotValid"),
					"name");
		}
	}

	static class DirectionFactory {
		private DirectionFactory() {
			myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		}

		private static Direction[] create(String type, String shape) {
			switch (type) {
			case "Cardinal":
				return Direction.CARDINAL_DIRECTIONS;
			
			case "All":
				if (shape.equals("Square")) {
					return Direction.ALL_DIRECTIONS_SQUARE;
				} else
					return Direction.ALL_DIRECTIONS_HEXAGON;
			
			default:
				return Direction.CARDINAL_DIRECTIONS;
			}
		}
	}
}

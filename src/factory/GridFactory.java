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

	public static AnyGrid create(XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		Direction[] d = DirectionFactory.create(xmlArgs.getAsString("direction"));
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
			
			private static Direction[] create(String type){
				if(type.equals("Cardinal")){
					return Direction.CARDINAL_DIRECTIONS;
				}else if(type.equals("All")){
					return Direction.ALL_DIRECTIONS;
				}else return Direction.ALL_DIRECTIONS;
			}
	}
}

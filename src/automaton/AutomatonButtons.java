package automaton;

import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.control.Control;
import simulations.CA;

public abstract class AutomatonButtons {
	private Group root;
	private Collection<Control> myControllers;
	private AutomatonControlFactory a;
	
	public AutomatonButtons(Group root, String language) {	
	}
	
	public abstract void setDisplayControllers(CA ca);
	
	public abstract void setControlActions(Collection<Control> controllers, CA ca);

	public void setControlActions(Collection<Control> controllers) {
		// TODO Auto-generated method stub
		
	}

	public void setDisplayControllers() {
		// TODO Auto-generated method stub
		
	}

}

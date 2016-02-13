package simulations;

import java.util.Map;

import automaton.AutomatonDisplay;
import automaton.XMLArgs;

public class ForgingAnts extends CA {

	public ForgingAnts(XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		//give slot number to the ant
		//parse from an xml the food spots, home spots;
	}

	@Override
	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCells() {
		//loop through slots
			//get occupants in slot
				//loop through slot
					//if its ant check if its at home or at food source
						//if so, get max hormone of opposite variety, set food 
						//else 
							//get forward slots
							//make list for ground cells and sort
							//check # of ants
							//ant deposits pheromone in spot (may do this within update)
							//add to new slot, remove from current slot
					//if ground
						// mark ground for slot
					//before moving to next slot, get the ground cell and deposit the pheromones 
	}

	@Override
	public void drawCells() {
		// TODO Auto-generated method stub

	}

}

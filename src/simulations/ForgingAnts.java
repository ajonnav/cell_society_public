package simulations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ForgingAntCells.AntCell;
import ForgingAntCells.CardinalDirection;
import ForgingAntCells.GroundCell;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;
import cells.Cell;
import slot.Slot;

public class ForgingAnts extends CA {
	List<Slot> allSlots;
	List<Integer> homeSpot;
	List<Integer> foodSpot;
	int tcol;
	double addPheromone;

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
		List<Slot> updatedSlots = new ArrayList<Slot>(allSlots);
		List<GroundCell> groundCells = new ArrayList<GroundCell>();
		double[] antDropFoodPheromone = new double[allSlots.size()];
		double[] antDropHomePheromone = new double[allSlots.size()];
		double[] diffusionFoodPheromone = new double[allSlots.size()];
		double[] diffusionHomePheromone = new double[allSlots.size()];
		for (Slot s : updatedSlots) {
			Collection<Cell> occupants = s.getOccupants();
			if (homeSpot.contains(s.index())) {
				//write for ant cell
			} else if (foodSpot.contains(s.index())) {
				//write for ground cell
			} else {
				for (Cell c : occupants) {
					if (c instanceof AntCell) {
						if (((AntCell) c).getFoodStatus() == true) {
							antDropHomePheromone[s.index()] += addPheromone;
						} else {
							antDropFoodPheromone[s.index()] += addPheromone;
						}
						Slot nextSlot = ((AntCell) c).findBestNeighbor(allSlots.get(s.index()));
						if (nextSlot == null) {
							nextSlot = s;
						}
						((AntCell) c).setDirection(getDirectionbetweenSlots(s, nextSlot));
						updatedSlots.get(nextSlot.index()).getOccupants().add(c);
						updatedSlots.get(s.index()).getOccupants().remove(c);
						
					} else {
						groundCells.add((GroundCell) c);
						double [] diffusion = ((GroundCell) c).getDiffusionAmount();
						diffusionFoodPheromone[s.index()] += diffusion[0];
						diffusionHomePheromone[s.index()] += diffusion[1];
					}
				}
			}
		}
		for (GroundCell g : groundCells) {
			g.setfoodHormone(antDropFoodPheromone[g.getSlot().index()] + diffusionFoodPheromone[g.getSlot().index()]);
			g.sethomeHormone(antDropHomePheromone[g.getSlot().index()] + diffusionHomePheromone[g.getSlot().index()]);
			g.update();
		}
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
	
	private CardinalDirection getDirectionbetweenSlots(Slot origin, Slot next) {
		if (origin.index() == next.index()) {
			return null;
		}
		int oldx = origin.index() / tcol;
		int oldy = origin.index() % tcol;
		int newx = next.index() / tcol;
		int newy = next.index() % tcol;
		return CardinalDirection.valueOf(newx - oldx, newy - oldy);
	}

}

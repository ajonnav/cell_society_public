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

public class ForgingAnts extends AniCA {
	private List<Slot> allSlots;
	private List<Integer> homeSpot;
	private List<Integer> foodSpot;
	private int tcol;
	private double addPheromone;
	private double diffRate;
	private double evapRate;
	private int life_span;
	private double pheromonelimit;
	private List<List<Cell>> copiesOfCells;
	
	/*
	 * Pseudo for Forging Ants
	 * //get occupants in slot
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
	 */
	public ForgingAnts(XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		// TODO Auto-generated constructor stub
		copiesOfCells = new ArrayList<List<Cell>>();
		allSlots = new ArrayList<Slot>();
		addPheromone = xmlArgs.getAsDouble("addPheromone");
		diffRate = xmlArgs.getAsDouble("diffRate");
		evapRate = xmlArgs.getAsDouble("evapRate");
		life_span = xmlArgs.getAsInt("life_span");
		pheromonelimit = xmlArgs.getAsDouble("pheromonelimit");
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		//give slot number to the ant
		//parse from an xml the food spots, home spots;
		int numCell = 0;
		allSlots = getAllSlots();
	}

	@Override
	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		// TODO Auto-generated method stub

	}
	
	private void makeCopyofCellLists() {
		for (Slot s : allSlots) {
			copiesOfCells.add(new ArrayList<Cell>(s.getOccupants()));
		}
	}

	@Override
	public void updateCells() {
		makeCopyofCellLists();
		//loop through slots
		List<GroundCell> groundCells = new ArrayList<GroundCell>();
		double[] antDropFoodPheromone = new double[allSlots.size()];
		double[] antDropHomePheromone = new double[allSlots.size()];
		double[] diffusionFoodPheromone = new double[allSlots.size()];
		double[] diffusionHomePheromone = new double[allSlots.size()];
		for (Slot s : allSlots) {
			List<Cell> nextOccupants = copiesOfCells.get(s.index());
			if (homeSpot.contains(s.index())) {
				antAtFoodOrHome(antDropFoodPheromone, antDropHomePheromone, s, nextOccupants, false);
			} else if (foodSpot.contains(s.index())) {
				antAtFoodOrHome(antDropFoodPheromone, antDropHomePheromone, s, nextOccupants, true);
			} else {
				for (Cell c : nextOccupants) {
					if (c instanceof AntCell) {
						antCellUpdate(antDropFoodPheromone, antDropHomePheromone, s, nextOccupants, c);
						
					} else {
						groundCells.add((GroundCell) c);
						addUpGroundDiffusion(diffusionFoodPheromone, diffusionHomePheromone, s, c);
					}
				}
			}
		}
		setNewGroundPheromoneLevels(groundCells, antDropFoodPheromone, antDropHomePheromone, diffusionFoodPheromone,
				diffusionHomePheromone);
	}

	private void antAtFoodOrHome(double[] antDropFoodPheromone, double[] antDropHomePheromone, Slot s,
			List<Cell> nextOccupants, boolean atFood) {
		for (Cell c : nextOccupants) {
			if (c instanceof AntCell) {
				((AntCell) c).setFoodStatus(atFood);
				((AntCell) c).setDirection(null);
				antCellUpdate(antDropFoodPheromone, antDropHomePheromone, s, nextOccupants, c);
			}
		}
	}

	private void setNewGroundPheromoneLevels(List<GroundCell> groundCells, double[] antDropFoodPheromone,
			double[] antDropHomePheromone, double[] diffusionFoodPheromone, double[] diffusionHomePheromone) {
		for (GroundCell g : groundCells) {
			g.setfoodHormone(antDropFoodPheromone[g.getSlot().index()] + diffusionFoodPheromone[g.getSlot().index()]);
			g.sethomeHormone(antDropHomePheromone[g.getSlot().index()] + diffusionHomePheromone[g.getSlot().index()]);
			g.update();
		}
	}

	private void addUpGroundDiffusion(double[] diffusionFoodPheromone, double[] diffusionHomePheromone, Slot s,
			Cell c) {
		double [] diffusion = ((GroundCell) c).getDiffusionAmount();
		diffusionFoodPheromone[s.index()] += diffusion[0];
		diffusionHomePheromone[s.index()] += diffusion[1];
	}

	private void antCellUpdate(double[] antDropFoodPheromone, double[] antDropHomePheromone, Slot s,
			List<Cell> nextOccupants, Cell c) {
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
		if (((AntCell) c).getLifeLeft() > 0) {
			copiesOfCells.get(nextSlot.index()).add(c);			
		}
		nextOccupants.remove(c);
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

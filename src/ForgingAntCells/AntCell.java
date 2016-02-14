package ForgingAntCells;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cells.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import slot.Slot;

public abstract class AntCell extends Cell {
	private int lifeCycle;
	private boolean hasFood;
	private double addHormone;
	private GroundCell currentground;
	private CardinalDirection direction;
	
	public AntCell(double x, double y, Color color, double w, double h) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Will find the next location for the cell and drop hormones 
	 */
	public void update(AntCell[] ants, GroundCell[] groundcells) {
		AntCell moveAnt = nextOrientationAnt(groundcells);
		dropHormone(currentground);
		//remove from neighbors list, get another neighbor list...etc
	}
	
	public Slot findBestNeighbor(Slot s) {
		AntOrientationFactory myFactory = new AntOrientationFactory();
		//Collection<Slot> myF_Neighbors = myFactory.getForwardNeighbors();
		Collection<GroundCell> possibleGround = getGroundCellsFromSlot(myFactory.getForwardNeighbors());
		sortCollectionbyFoodStatus(possibleGround);
		Slot newSlot = checkMaxOccupantsandReturnSlot(possibleGround);
		if (newSlot == null) {
			Collection<GroundCell> otherPossible = getGroundCellsFromSlot(myFactory.getBackwardNeighbors());
			return checkMaxOccupantsandReturnSlot(otherPossible);
		}
		return newSlot;
		
	}

	private Slot checkMaxOccupantsandReturnSlot(Collection<GroundCell> possibleGround) {
		for (GroundCell g : possibleGround) {
			if (!(g.getSlot().getOccupants().size() > 10)) {
				return g.getSlot();
			}
		}
		return null;
	}

	private void sortCollectionbyFoodStatus(List<GroundCell> possibleGround) {
		if (hasFood) {
			Collections.sort(possibleGround, GroundCell.homeComparator());
		} else {
			Collections.sort(possibleGround, GroundCell.foodComparator());
		}
	}
	
	private Collection<GroundCell> getGroundCellsFromSlot(Collection<Slot> slots) {
		Collection<GroundCell> my_groundcells = new ArrayList<GroundCell>();
		for (Slot s : slots) {
			Collection<Cell> occupants = s.getOccupants();
			for (Cell c : occupants) {
				if(c instanceof GroundCell) {
					my_groundcells.add((GroundCell) c);
				}
			}			
		}
		return my_groundcells;
	}
	/*
	 * Finds the next location for the ant and creates an ant in that location
	 */
	private AntCell nextOrientationAnt(GroundCell[] cells) {
		return moveToNextLocation(findBestNeighbor(getForwardNeighbors()));
	}
	 
	/*
	 * Gets the forward neighbors for the ant based on its orientation
	 */
	public abstract Collection<GroundCell> getForwardNeighbors();
	
	/*
	 * Gets all the neighbors (ground) for the ant except its forward neighbors
	 */
	public abstract Collection<GroundCell> getNonForwardNeighbors(); 
	
	/*
	 * Gets passed the forward neighbors for the ant and finds which to move to
	 * If not forward neighbors are available, gets rest of neighbors and finds the best to move to
	 */
	public GroundCell findBestNeighbor(Collection<GroundCell> cells) {
		if (getFoodStatus()) {
			//sorts list based on home hormones
		} else {
			//sorts list based on food hormones
		}
		return currentground;
		
	}
	
	/*
	 * Creates a new ant cell with the new orientation of the ant and copies over the life cycle
	 * and modifies the food status of the ant if appropriate
	 */
	public abstract AntCell moveToNextLocation(GroundCell cell);
	
	private void dropHormone(GroundCell currentground) {
		if (hasFood) {
			currentground.sethomeHormone(addHormone);
		} else {
			currentground.setfoodHormone(addHormone);
		}
	}
	
	/*
	 * Returns true if the ant has food, returns false otherwise
	 */
	public boolean getFoodStatus() {
		return hasFood;
	}
	
	public void setFoodStatus(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	public CardinalDirection getCurrentDirection() {
		return direction;
	}
	
	public void setDirection(CardinalDirection d) {
		this.direction = d;
	}
	
}

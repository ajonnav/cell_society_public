package ForgingAntCells;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cells.Cell;
import grid.ForgingAntCell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import slot.Slot;

public abstract class AntCell extends ForgingAntCell {
	private int lifeCycle;
	private boolean hasFood;
	private CardinalDirection direction;
	
	public AntCell(double x, double y, Color color, double w, double h, int lifeSpan) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
		lifeCycle = lifeSpan;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}
	

	public void update() {
		lifeCycle--;
	}
	
	public int getLifeLeft() {
		return lifeCycle;
	}
	
	/*
	 * Returns the best neighbor to move to 
	 */
	public Slot findBestNeighbor(Slot s) {
		AntOrientationFactory myFactory = new AntOrientationFactory(direction, s);
		//Collection<Slot> myF_Neighbors = myFactory.getForwardNeighbors();
		List<GroundCell> possibleGround = getGroundCellsFromSlot(myFactory.getForwardNeighbors());
		sortCollectionbyFoodStatus(possibleGround);
		Slot newSlot = checkMaxOccupantsandReturnSlot(possibleGround);
		if (newSlot == null) {
			Collection<GroundCell> otherPossible = getGroundCellsFromSlot(myFactory.getBackwardNeighbors());
			return checkMaxOccupantsandReturnSlot(otherPossible);
		}
		return newSlot;
		
	}

	/*
	 * Returns a slot if the slot does not have the maximum number of occupants possible
	 */
	private Slot checkMaxOccupantsandReturnSlot(Collection<GroundCell> possibleGround) {
		for (GroundCell g : possibleGround) {
			if (!(g.getSlot().getOccupants().size() > 10)) {
				return g.getSlot();
			}
		}
		return null;
	}
	
	/*
	 * Sorts the ants based on food pheromones and home pheromones 
	 */
	private void sortCollectionbyFoodStatus(List<GroundCell> possibleGround) {
		if (hasFood) {
			Collections.sort(possibleGround, GroundCell.homeComparator());
		} else {
			Collections.sort(possibleGround, GroundCell.foodComparator());
		}
	}
	
	/*
	 * Returns true if the ant has food, returns false otherwise
	 */
	public boolean getFoodStatus() {
		return hasFood;
	}
	
	/*
	 * Sets the food status of the ant
	 */
	public void setFoodStatus(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	
	/*
	 * Returns the cardinal direction of the ant cell
	 */
	public CardinalDirection getCurrentDirection() {
		return direction;
	}
	
	/*
	 * Sets the direction of the ant
	 */
	public void setDirection(CardinalDirection d) {
		this.direction = d;
	}
	
}

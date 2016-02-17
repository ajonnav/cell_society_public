package ForgingAntCells;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ForgingAntCells.ForgingAntCell;
import javafx.scene.paint.Color;
import slot.Slot;

/*
 * Author: Christine Zhou
 */

//This entire file is part of my masterpiece.
//Christine Zhou
/*
 * My ant cell class is created by the CA which passes its life span. It has methods for calculating its best neighbors
 * and setting its current direction and next direction. It will return to the CA through neighbors method the slot it wants
 * to move to or null if there is no spot that it can move to.
 * I chose my AntCell class as part of my masterpiece because I think it a good example of giving more functions to a cell class
 * instead of letting the simulation controller for ant simulation (ForgingAnts) do all the work. I moved some of the 
 * integral parts for updating an ant cell, such as finding its next slot to move to, into the ant cell class itself so
 * that the ant doesn't need to pass a bunch of parameters to the simulation and then get a bunch of parameters back 
 * from the simulation. If the ant cell can calculate its own neighbors, find the next slot to move to, and find its next
 * orientation (which all is specific to the ant cell), then all the simulation has to do is pass the slot that the ant cell is 
 * in and retrieve the best neighbor to move the cell, call update, and check that the ant is still alive. Moving many
 * of the functions to the cell class itself helps with encapsulation and restricted the getters and setters to only a setter
 * for setting the food status of the ant which the simulation checks by seeing if the ant is in the same slot as a food
 * spot and a getter for food status which the simulation uses to deposit pheromones in the ground cells.
 */

public class AntCell extends ForgingAntCell {
	private int lifeCycle;
	private boolean hasFood;
	private CardinalDirection direction;
	private CardinalDirection nextDirection;
	private int maxOccupants;
	
	public AntCell(int lifeSpan) {
		super(Color.RED);
		// TODO Auto-generated constructor stub
		this.lifeCycle = lifeSpan;
	}	

	public void update() {
		lifeCycle--;
		direction = nextDirection;
		nextDirection = null;
	}
	
	public int getLifeLeft() {
		return lifeCycle;
	}
	
	/*
	 * Returns the best neighbor to move to 
	 */
	public Slot findBestNeighbor(Slot currentSlot) {
		AntOrientationFactory myFactory = new AntOrientationFactory();
		myFactory.findNeighbors(direction, currentSlot);
		List<GroundCell> possibleGround = getGroundCellsFromSlot(myFactory.getForwardNeighbors());
		sortCollectionbyFoodStatus(possibleGround);
		Slot newSlot = checkMaxOccupantsandReturnSlot(possibleGround);
		if (newSlot == null) {
			Collection<GroundCell> otherPossible = getGroundCellsFromSlot(myFactory.getBackwardNeighbors());
			return checkMaxOccupantsandReturnSlot(otherPossible);
		}
		setDirectionBetweenSlots(currentSlot, newSlot);
		return newSlot;
		
	}

	/*
	 * Returns a slot if the slot does not have the maximum number of occupants possible
	 */
	private Slot checkMaxOccupantsandReturnSlot(Collection<GroundCell> possibleGround) {
		for (GroundCell g : possibleGround) {
			if (!(g.getSlot().getOccupants().size() > maxOccupants)) {
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
	 * Sets the value for next direction of the ant 
	 */
	private void setDirectionBetweenSlots(Slot currentSlot, Slot newSlot) {
		nextDirection = (newSlot != null) ? currentSlot.getDirectionOf(newSlot) : null;
	}
	
	/*
	 * Sets the food status of the ant
	 */
	public void setFoodStatus(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	/*
	 * Returns the food status of the ant
	 */
	public boolean getFoodStatus() {
		return hasFood;
	}
	
}

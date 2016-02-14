package cells;

import java.util.List;

import slot.Slot;
import javafx.scene.paint.Color;

public class TreeFireCell extends FireCell {

	private static final boolean isBurning = false;
	private static final boolean isEmpty = false;
	private double probCatch;
	
	/*
	 * Sets the that a FireTREE cell is empty and is burning
	 */
	public TreeFireCell(double probCatch) {
		super(Color.GREEN, isEmpty, isBurning);
		// TODO Auto-generated constructor stub
		this.probCatch = probCatch;
	}
	
	/*
	 * Checks whether the neighbors of the FireTREE cell include a burning tree, if so, it gets a random number
	 * and checks if its less than the probability of catching fire. If it is smaller than the probability of catching fire,
	 * then it sets the cell to a new burning cell, otherwise it just returns itself.
	 */
	@Override
	public FireCell update(List<Slot> neighbors) {
		// TODO Auto-generated method stub
		BurningFireCell newFireCell = new BurningFireCell();
		return hasBurningNeighbor(neighbors) && (probCatch >= Math.random()) 
				? newFireCell : this;
	}
	
	/*
	 * Checks for if any of a cell's neighbors are burning.
	 */
	private boolean hasBurningNeighbor(List<Slot> neighbors) {
		for (Slot slot : neighbors) {
			if (((FireCell)slot.getOccupants().get(0)).isBurning()) {
				return true;
			}
		}
		return false;
	}
}

package FireCells;

import javafx.scene.paint.Color;

public class FireTREE extends FireCell {

	private static final boolean isBurning = false;
	private static final boolean isEmpty = false;
	private static double probCatch;
	
	/*
	 * Sets the that a FireTREE cell is empty and is burning
	 */
	public FireTREE(double x, double y, double w, double h, double probCatch) {
		super(x, y, Color.GREEN, w, h, probCatch);
		// TODO Auto-generated constructor stub
		setEmpty(isEmpty);
		setBurning(isBurning);
		this.probCatch = probCatch;
	}
	
	/*
	 * Checks whether the neighbors of the FireTREE cell include a burning tree, if so, it gets a random number
	 * and checks if its less than the probability of catching fire. If it is smaller than the probability of catching fire,
	 * then it sets the cell to a new burning cell, otherwise it just returns itself.
	 */
	@Override
	public FireCell update(FireCell[] cells) {
		// TODO Auto-generated method stub
		FireBURNING newFireCell = new FireBURNING(getX(), getY(), getWidth(), getHeight());
		newFireCell.setNeighbor(getNeighbor());
		return hasBurningNeighbor(cells) && (probCatch >= Math.random()) 
				? newFireCell : this;
	}
	
	/*
	 * Checks for if any of a cell's neighbors are burning.
	 */
	private boolean hasBurningNeighbor(FireCell[] cells) {
		for (int i : getNeighbor()) {
			if (cells[i].isBurning()) {
				return true;
			}
		}
		return false;
	}
}

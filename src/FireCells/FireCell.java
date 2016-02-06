package FireCells;

import cells.SquareCell;
import javafx.scene.paint.Color;

public abstract class FireCell extends SquareCell {
	private boolean isEmpty;
	private boolean isBurning;
	
	/*
	 * Constructor for a FireCell
	 */
	public FireCell(double x, double y, Color color, double w, double h, double probCatch) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Returns whether or not a cell is empty
	 */
	public boolean isEmpty() {
		return isEmpty;
	}
	
	/*
	 * Sets a cell to empty
	 */
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	/*
	 * Returns whether or not a cell is burning
	 */
	public boolean isBurning() {
		return isBurning;
	}
	
	/*
	 * Sets a cell to burning
	 */
	public void setBurning(boolean isBurning) {
		this.isBurning = isBurning;
	}
	
	/*
	 * Updates a cell based on rules applied to each cell
	 */
	public abstract FireCell update(FireCell[] cells);
}

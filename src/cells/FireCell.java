package cells;
import java.util.List;

import slot.Slot;
import javafx.scene.paint.Color;

/*
* Author: Christine Zhou, updated by Ani
*/
public abstract class FireCell extends Cell {
	private boolean isEmpty;
	private boolean isBurning;
	
	/*
	 * Constructor for a FireCell
	 */
	public FireCell(Color color, boolean empty, boolean burning) {
		super(color);
		isEmpty = empty;
		isBurning = burning;
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
	public abstract FireCell update(List<Slot> neighbors);
}

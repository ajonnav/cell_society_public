package cells;

import java.util.List;

import slot.Slot;
import javafx.scene.paint.Color;

/*
* Author: Christine Zhou, updated by Ani
*/
public class BurningFireCell extends FireCell {
	private static final boolean isEmpty = false;
	private static final boolean isBurning = true;
	
	/*
	 * Sets whether the cell is empty and whether the cell is burning
	 */
	public BurningFireCell() {
		super(Color.DARKRED, isEmpty, isBurning);
	}
	
	/*
	 * Changes this cell to an empty cell 
	 */
	@Override
	public FireCell update(List<Slot> neighbors) {
		// TODO Auto-generated method stub
		return new EmptyFireCell();
	}

}

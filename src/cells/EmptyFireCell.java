package cells;

import java.util.List;

import slot.Slot;
import javafx.scene.paint.Color;

/*
* Author: Christine Zhou, updated by Ani
*/
public class EmptyFireCell extends FireCell {
	private static final boolean isEmpty = true;
	private static final boolean isBurning = false;
	
	/*
	 * Sets the status of a FireEMPTY cell to empty
	 */
	public EmptyFireCell() {
		super(Color.YELLOW, isEmpty, isBurning);
	}
	
	/*
	 * Empty cells stay empty
	 */
	@Override
	public FireCell update(List<Slot> neighbors) {
		// TODO Auto-generated method stub
		return this;
	}

}

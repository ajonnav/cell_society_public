package cells;

import java.util.List;
import slot.Slot;
import javafx.scene.paint.Color;

/**
 * Class that represents a Dead Game of Life Cell
 * @author ajonnav
 *
 */
public class DeadGOLCell extends GOLCell{
	public final static boolean ALIVE_STATUS = false;
	private static final Color CELL_COLOR = Color.GRAY;
	
	/**
	 * Constructor
	 */
	public DeadGOLCell() {
		super(CELL_COLOR, ALIVE_STATUS);
	}
	
	/**
	 * Update method -- updates the cell
	 */
	@Override
	public Cell update(List<Slot> neighborSlot) {
		int numAlive = getNumberAlive(neighborSlot);
		if(numAlive == 3) {
			return new LiveGOLCell();
		}
		else{
			return this;
		}
	}

}

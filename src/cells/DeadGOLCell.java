package cells;

import java.util.List;
import slot.Slot;
import javafx.scene.paint.Color;

public class DeadGOLCell extends GOLCell{
	public final static boolean ALIVE_STATUS = false;
	private static final Color CELL_COLOR = Color.GRAY;
	
	public DeadGOLCell() {
		super(CELL_COLOR, ALIVE_STATUS);
	}

	@Override
	public Cell update(List<Slot> neighborSlot) {
		int numAlive = getNumberAlive(neighborSlot);
		if(numAlive == 3) {
			return new LiveGOLCell();
		}
		return this;
	}

}

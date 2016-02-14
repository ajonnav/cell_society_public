package cells;

import java.util.List;

import javafx.scene.paint.Color;
import slot.Slot;

public class LiveGOLCell extends GOLCell {

	public final static boolean ALIVE_STATUS = true;
	private static final Color CELL_COLOR = Color.BLUE;
	
	public LiveGOLCell() {
		super(CELL_COLOR, ALIVE_STATUS);
	}

	@Override
	public Cell update(List<Slot> neighborSlot) {
		int numAlive = getNumberAlive(neighborSlot);
		if(numAlive > 3 || numAlive < 2) {
			return new LiveGOLCell();
		}
		return this;
	}
}

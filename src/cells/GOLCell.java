package cells;
import java.util.List;
import slot.Slot;
import javafx.scene.paint.Color;

/**
 * Class for a general Game of Life Cell
 * @author aj168
 *
 */
public class GOLCell extends Cell {
	private boolean aliveStatus;
	/**
	 * Constructor
	 * @param c Color of cell
	 * @param alive Whether cell is alive or not
	 */
	public GOLCell(Color c, boolean alive) {
		super(c);
		aliveStatus = alive;
	}
	
	/**
	 * 
	 * @return returns whether cell is live or not
	 */
	public boolean isAlive() {
		return aliveStatus;
	}
	
	/**
	 * Finds the number of live cells around this cell
	 * @param neighborSlot Slot of neighbors
	 * @return number of live cells around
	 */
	public int getNumberAlive(List<Slot> neighborSlot) {
		int numAlive = 0;
		for(Slot s: neighborSlot) {
			if(((GOLCell)s.getOccupants().get(0)).isAlive()) {
				numAlive++;
			}
		}
		return numAlive;
	}
}

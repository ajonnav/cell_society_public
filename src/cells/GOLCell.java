package cells;
import java.util.List;
import slot.Slot;
import javafx.scene.paint.Color;

public class GOLCell extends Cell {
	private boolean aliveStatus;
	public GOLCell(Color c, boolean alive) {
		super(c);
		aliveStatus = alive;
	}
	
	public boolean isAlive() {
		return aliveStatus;
	}
	
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

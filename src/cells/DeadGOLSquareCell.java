package cells;
import javafx.scene.paint.Color;

public class DeadGOLSquareCell extends GOLSquareCell{

	public final boolean ALIVE_STATUS = false;
	private static final Color CELL_COLOR = Color.GRAY;
	
	public DeadGOLSquareCell(double x, double y, double w, double h) {
		super(x, y, CELL_COLOR, w, h);
		setAlive(ALIVE_STATUS);
	}
	
	public GOLSquareCell update(GOLSquareCell[] cells) {
		int numAlive = 0;
		for(int i: getNeighbor()) {
			if(cells[i].isAlive())
				numAlive++;
		}
		if(numAlive==3) {
			LiveGOLSquareCell returnCell = new LiveGOLSquareCell(getX(), getY(), getWidth(), getHeight());
			returnCell.setNeighbor(getNeighbor());
			return returnCell;
		}
		return this;
	}
}

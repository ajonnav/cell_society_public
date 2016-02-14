package cells;
import javafx.scene.paint.Color;

/**
 * Class for live cells in Game of Life
 * @author aj168 - Anirudh Jonnavithula
 *
 */

public class LiveGOLSquareCell extends GOLSquareCell{

	private final boolean ALIVE_STATUS = true;
	private static final Color CELL_COLOR = Color.BLUE;
	
	/**
	 * Constructor
	 * @param x X cooridnate
	 * @param y Y coordinate
	 * @param w Width of cell
	 * @param h Height of cell
	 */
	public LiveGOLSquareCell(double x, double y, double w, double h) {
		super(x, y, CELL_COLOR , w, h);
		setAlive(ALIVE_STATUS);
	}

	/**
	 * Updates the cell
	 * @param cells List of the current state cells
	 * @return New cell that replaces the current one
	 */
	@Override
	public GOLSquareCell update(GOLSquareCell[] cells) {
		int numAlive = getNumNeighborsAlive(cells);
		if(numAlive < 2 || numAlive > 3) {
			DeadGOLSquareCell returnCell = new DeadGOLSquareCell(getX(), getY(), getWidth(), getHeight());
			returnCell.setNeighbor(getNeighbor());
			return returnCell;
		}
		return this;
	}
}

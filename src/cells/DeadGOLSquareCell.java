package cells;
import javafx.scene.paint.Color;

/**
 * Class for dead square cell in game of life
 * @author aj168 - Anirudh Jonnavithula
 *
 */
public class DeadGOLSquareCell extends GOLSquareCell{

	public final boolean ALIVE_STATUS = false;
	private static final Color CELL_COLOR = Color.GRAY;
	
	/**
	 * Constructor
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param w Width of cell
	 * @param h Height of cell
	 */
	public DeadGOLSquareCell(double x, double y, double w, double h) {
		super(x, y, CELL_COLOR, w, h);
		setAlive(ALIVE_STATUS);
	}
	
	/**
	 * @param cells List of all the cells to help update current cell
	 * @return New GOLSquareCell to replace current one
	 */
	public GOLSquareCell update(GOLSquareCell[] cells) {
		int numAlive = getNumNeighborsAlive(cells);
		if(numAlive==3) {
			LiveGOLSquareCell returnCell = new LiveGOLSquareCell(getX(), getY(), getWidth(), getHeight());
			returnCell.setNeighbor(getNeighbor());
			return returnCell;
		}
		return this;
	}
}

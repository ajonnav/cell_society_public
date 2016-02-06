package cells;
import javafx.scene.paint.Color;

/**
 * Class for every square cell in Game of Life
 * @author ajonnav
 *
 */

public abstract class GOLSquareCell extends SquareCell{

	private boolean isAlive;
	
	/**
	 * Constructor
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param c Color of cell
	 * @param w Width of cell
	 * @param h Height of Cell
	 */
	public GOLSquareCell(double x, double y, Color c, double w, double h) {
		super(x, y, c, w, h);
	}

	/**
	 * Checks whether cell is alive
	 * @return True if cell is alive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Sets whether cell is alive or not
	 * @param isAlive Boolean indicating state
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	/**
	 * Updates the cell
	 * @param cells List of all the cells
	 * @return New cell to replace the current one with
	 */
	public abstract GOLSquareCell update(GOLSquareCell[] cells);
}

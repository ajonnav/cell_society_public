package cells;
import java.util.HashMap;
import javafx.scene.paint.Color;

/**
 * Class for Square Wator cells
 * @author aj168 - Anirudh Jonanvithula
 *
 */
public abstract class WatorSquareCell extends SquareCell{

	private boolean isEmpty;
	private boolean isShark;
	
	/**
	 * Constructor
	 * @param x X coordinate of cell
	 * @param y Y coordinate of cell
	 * @param c Color of cell
	 * @param w Width of cell
	 * @param h Height of cell
	 */
	public WatorSquareCell(double x, double y, Color c, double w, double h) {
		super(x, y, c, w, h);
	}

	public abstract WatorSquareCell update(WatorSquareCell[] cells);
	
	/**
	 * Updates cell
	 * @param cells List of all cells in current state
	 * @param map Maps new positions to old positions
	 * @param position Position of current cell
	 * @return
	 */
	public abstract WatorSquareCell updateWator(WatorSquareCell[] cells, HashMap<Integer, Integer> map, int position);

	/**
	 * Makes copy of the cell
	 * @return Copy of the cell
	 */
	public abstract WatorSquareCell copy();
	
	/**
	 * Check whether cell is empty cell or not
	 * @return true if cell is empty
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * Sets whether cell is empty or not
	 * @param isEmpty True if cell is empty
	 */
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	/**
	 * Checks whether cell is shark or not
	 * @return True if cell is a shark
	 */
	public boolean isShark() {
		return isShark;
	}

	/**
	 * Sets whether cell is a shark or not
	 * @param isShark True if cell is a shark
	 */
	public void setShark(boolean isShark) {
		this.isShark = isShark;
	}
}

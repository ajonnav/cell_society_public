package cells;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * Class for empty cells in Wator
 * This is a file that followed our old design.
 * It does not work with our final design
 * @author aj168
 *
 */

public class EmptyWatorSquareCell extends WatorSquareCell{

	private static final Color CELL_COLOR = Color.BLUE;
	private static final boolean IS_EMPTY= true;
	private static final boolean IS_SHARK= false;
	
	/**
	 * Constructor
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param w Width of cell
	 * @param h Height of cell
	 */
	public EmptyWatorSquareCell(double x, double y, double w, double h) {
		super(x, y, CELL_COLOR, w, h, IS_EMPTY, IS_SHARK);
	}

	@Override
	public WatorSquareCell update(WatorSquareCell[] cells) {
		return null;
	}

	/**
	 * Update method
	 * @param cells List of cells of current state
	 * @param map Maps new cell positions to old ones
	 * @param position Position of current cell
	 */
	@Override
	public WatorSquareCell updateWator(WatorSquareCell[] cells, Map<Integer, Integer> map, int position) {
		if(map.containsKey(position)) {
			WatorSquareCell returnCell = cells[map.get(position)].copy();
			returnCell.setNeighbor(this.getNeighbor());
			returnCell.setX(getX());
			returnCell.setY(getY());
			return returnCell;
		}
		return this;
	}

	/**
	 * Returns a copy of this cell
	 */
	@Override
	public WatorSquareCell copy() {
		EmptyWatorSquareCell returnCell = new EmptyWatorSquareCell(getX(), getY(), getWidth(), getHeight());
		returnCell.setNeighbor(getNeighbor());
		return returnCell;
	}

}

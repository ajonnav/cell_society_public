package cells;

import java.util.List;

import slot.Slot;
import javafx.scene.paint.Color;

public abstract class Cell {
	private Color cellColor;

	public Cell(Color color) {
		setCellColor(color);
	}

	public Cell update(List<Slot> neighborSlots) {
		return null;
	}
	
	public Color getCellColor() {
		return cellColor;
	}

	public void setCellColor(Color cellColor) {
		this.cellColor = cellColor;
	}
}

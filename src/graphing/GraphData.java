package graphing;

import java.util.HashMap;
import java.util.Map;

import cells.Cell;

public class GraphData {
	private Cell[] allCells;
	private Map<String, Integer> cellData;
	
	public GraphData() {
	
	}
	
	public GraphData(Cell[] allCells)  {
		this.allCells = allCells;
		cellData = new HashMap<String, Integer>();
	}
	
	
	
	private void seperateData() {
		for (Cell c : allCells) {
			if (cellData.get(c.getClass().getName()) == null) {
				cellData.put(c.getClass().getName(), 1);
			} else {
				String key = c.getClass().getName();
				cellData.put(key, cellData.get(key) + 1);
			}
		}
	}

}

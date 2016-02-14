package graphing;

import java.util.HashMap;
import java.util.Map;

import cells.Cell;

public class GraphData {
	private Cell[] allCells;
	private Map<String, Integer> cellData;
	private int cycle;
	
	public GraphData() {
		setCycle(0);
	}
	
	public void setGraphData(Cell[] allCells)  {
		setCycle(getCycle() + 1);
		this.allCells = allCells;
		cellData = new HashMap<String, Integer>();
		seperateData();
	}
	
	public Map<String, Integer> getMap() {
		return cellData;
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

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

}

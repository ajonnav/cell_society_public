package graphing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cells.Cell;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class cellLineGraph {
	private static final int CYCLES_TO_SHOW = 500;
	private LineChart<Number, Number> myGraph;
	private Set<String> cellTypes;
	List<XYChart.Series<Number, Number>> seriesList;
	
	public cellLineGraph() {
		seriesList = new ArrayList<XYChart.Series<Number, Number>>();
		cellTypes = new HashSet<String>();
		createGraph();
	}
	
	private void createGraph() {
		final NumberAxis xAxis = new NumberAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Cycles");
	    yAxis.setLabel("Cells");
		myGraph = new LineChart<Number, Number>(xAxis, yAxis);
	}
	
	public void setCellSeries(Map<String, Integer> cellData, int cycle) {
		Set<String> cells = cellData.keySet();
		Iterator<String> cellIterate = cells.iterator();
		for (int k = 0; k < cellData.size(); k++) {
			String cellName = cellIterate.next().toString();
			if(!cellTypes.contains(cellName)) {
				seriesList.add(new XYChart.Series<Number, Number>());
				seriesList.get(seriesList.size()-1).setName(cellName);
				seriesList.get(seriesList.size()-1).getData().add(new XYChart.Data<Number, Number>(cycle, cellData.get(cellName)));
				cellTypes.add(cellName);
			} else {
				for (Series<Number, Number> s : seriesList) {
					if (s.getName().equals(cellName)) {
						s.getData().add(new XYChart.Data<Number, Number>(cycle, cellData.get(cellName)));
						break;
					}
				}
			}
		}
	}
	
	public void updateChart() {
		for (Series<Number, Number> s : seriesList) {
			myGraph.getData().add(s);
		}
	}
	
}

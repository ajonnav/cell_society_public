package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cells.SharkWatorSquareCell;
import cells.FishWatorSquareCell;
import cells.EmptyWatorSquareCell;
import cells.WatorSquareCell;
import automaton.AutomatonDisplay;

public class Wator extends CA{

	private int fishBreed;
	private int sharkStarve;
	private int sharkBreed;
	private double perFish;
	private double perShark;
	private WatorSquareCell[] allCells;
	
	public Wator(Map<String, String> argsMap, AutomatonDisplay a) {
		super(argsMap, a);
		fishBreed = Integer.parseInt(argsMap.get("fishBreed"));
		sharkStarve = Integer.parseInt(argsMap.get("sharkStarve"));
		sharkBreed = Integer.parseInt(argsMap.get("sharkBreed"));
		perFish = Double.parseDouble(argsMap.get("perFish"));
		perShark = Double.parseDouble(argsMap.get("perShark"));
		setAllCells(new WatorSquareCell[getNumCell()]);
	}

	@Override
	public void initializeScreen() {
		int numCell = 0;
		for(int i = 0; i < getNumCol(); i++) {
			for(int j = 0; j < getNumRow(); j++) {
				double random = Math.random();
				if(random<perFish) {
					getAllCells()[numCell] = new FishWatorSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight(), fishBreed);
				}
				else if(random>=perFish && random<perFish+perShark) {
					getAllCells()[numCell] = new SharkWatorSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight(), sharkBreed, sharkStarve);
				}
				else {
					getAllCells()[numCell] = new EmptyWatorSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight());
				}
				numCell++;
			}
		}
		initializeSimulationLoop();
		calculateAdjacencyMatrixAndSetNeighbor();
		drawCells();
	}

	@Override
	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		for(int i = 0; i < getNumCell(); i++) {
			WatorSquareCell cell = getAllCells()[i];
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j < getNumCell(); j++) {
				WatorSquareCell temp = getAllCells()[j];
				if(((Math.abs(cell.getX() - temp.getX()) == getCellWidth() && Math.abs(cell.getY() - temp.getY()) == 0) 
					|| (Math.abs(cell.getX() - temp.getX()) == 0 && Math.abs(cell.getY() - temp.getY()) == getCellHeight())
					||(Math.abs(cell.getX() - temp.getX()) == getSimWidth() - getCellWidth() && Math.abs(cell.getY() - temp.getY()) == 0))
						&& i != j) {
					getAdjacency()[i][j] =1;
					list.add(j);
				}
			}
			cell.setNeighbor(list);
		}
	}

	@Override
	public void updateCells() {
		WatorSquareCell[] list = new WatorSquareCell[getNumCell()];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < getNumCell(); i ++) {
			list[i] = new EmptyWatorSquareCell(getAllCells()[i].getX(), getAllCells()[i].getY(), getCellWidth(), getCellHeight());
			list[i].setNeighbor(getAllCells()[i].getNeighbor());
		}
		updateSharks(list, map);
		updateFish(list, map);
		updateEmptyCells(list, map);
		setAllCells(list);
	}

	private void updateEmptyCells(WatorSquareCell[] list,
			HashMap<Integer, Integer> map) {
		for(int i = 0; i < getNumCell(); i ++) {
			if(getAllCells()[i].isEmpty()) {
				list[i] = getAllCells()[i].updateWator(getAllCells(), map, i);
			}
		}
	}

	private void updateFish(WatorSquareCell[] list,
			HashMap<Integer, Integer> map) {
		for(int i = 0; i < getNumCell(); i ++) {
			if(!getAllCells()[i].isShark() && !getAllCells()[i].isEmpty()) {
				list[i] = getAllCells()[i].updateWator(getAllCells(), map, i);
			}
		}
	}

	private void updateSharks(WatorSquareCell[] list,
			HashMap<Integer, Integer> map) {
		for(int i = 0; i < getNumCell(); i ++) {
			if(getAllCells()[i].isShark()) {
				list[i] = getAllCells()[i].updateWator(getAllCells(), map, i);
			}
		}
	}

	@Override
	public void drawCells() {
		getGraphicsContext().clearRect(0, 0, getSimWidth(), getSimHeight());
		for(WatorSquareCell cell: getAllCells()) {
			cell.draw(getGraphicsContext());
		}
	}
	
	public WatorSquareCell[] getAllCells() {
		return allCells;
	}
	
	public void setAllCells(WatorSquareCell[] list) {
		allCells = Arrays.copyOf(list, list.length);
	}
}
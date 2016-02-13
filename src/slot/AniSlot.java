package slot;
import java.util.ArrayList;
import java.util.List;

import cells.*;

public class AniSlot {
	
	private double x;
	private double y;
	private double width;
	private double height;
	private int index;
	private List<Cell> cellList;
	private List<AniSlot> neighbors;
	
	public AniSlot(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public AniSlot(double x, double y, double width, double height, int index) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setIndex(index);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<AniSlot> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<AniSlot> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void calculateNeighbors(int numRow, int numCol, int depth, boolean toroidal, String hoodType, List<AniSlot> slotList) {
		return;
	}
	
	public void calculateNeighbors(int numRow, int numCol, int depth, boolean torroidal, List<AniSlot> slotList, int[] dX, int[] dY) {
		List<AniSlot> neighbor = new ArrayList<AniSlot>();
		int row = getIndex()/numCol;
		int col = getIndex()%numCol;
		for(int i=0;i<dX.length; i++) {
			int curRow=row;
			int curCol=col;
			for(int d=0;d<depth; d++) {
				curRow+=dX[i];
				curCol+=dY[i];
				if(torroidal) {
					if(curRow>=numRow||curRow<0) {
						curRow = curRow%numRow;
					}
					if(curCol>=numCol||curCol<0) {
						curCol=curCol%numCol;
					}
				}
				else {
					break;
				}
				neighbor.add(slotList.get(curRow*numCol+curCol));
			}
		}
		setNeighbors(neighbor);
	}

}

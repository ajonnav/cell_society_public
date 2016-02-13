package slot;

import java.util.ArrayList;
import java.util.List;

public class AniSquareSlot extends AniSlot {

	public AniSquareSlot(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public AniSquareSlot(double x, double y, double height, double width, int index) {
		super(x, y, height, width, index);
	}
	
	public void setToroidNeighbors(int numRow, int numCol, int depth, String hoodType, List<AniSlot> slotList) {
		if(hoodType.equals("all")) {
			int[] dX = {-1, 0, 1, 1, 1, 0, -1, -1};
			int[] dY = {-1, -1, -1, 0, 1, 1, 1, 0};
			calculateNeighbors(numRow, numCol, depth, true, slotList, dX, dY);
		}
		if(hoodType.equals("corner")) {
			int[] dX = {-1, 1, 1, -1};
			int[] dY = {-1, -1, 1, 1};
			calculateNeighbors(numRow, numCol, depth, true, slotList, dX, dY);
		}
		if(hoodType.equals("cardinal")) {
			int[] dX = {0, 1, 0, -1};
			int[] dY = {-1, 0, 1, 0};
			calculateNeighbors(numRow, numCol, depth, true, slotList, dX, dY);
		}
	}
}

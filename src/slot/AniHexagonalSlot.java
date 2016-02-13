package slot;
import java.util.List;

public class AniHexagonalSlot extends AniSlot{

	public AniHexagonalSlot(double x, double y, double height, double width) {
		super(x, y, height, width);
	}

	public AniHexagonalSlot(double x, double y, double height, double width, int index) {
		super(x, y, height, width, index);
	}
	
	public void setToroidNeighbors(int numRow, int numCol, int depth, String hoodType, List<AniSlot> slotList) {
		if(hoodType.equals("all")) {
			int[] dX = {-1, -1, 0, 1, 1, 0};
			int[] dY = {-1, 0, 1, 0, -1, -1};
			setNeighbors(numRow, numCol, depth, true, slotList, dX, dY);
		}
	}
}
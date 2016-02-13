package slot;
import java.util.List;

public class AniHexagonalSlot extends AniSlot{

	public AniHexagonalSlot(double x, double y, double height, double width) {
		super(x, y, height, width);
	}

	public AniHexagonalSlot(double x, double y, double height, double width, int index) {
		super(x, y, height, width, index);
	}
	
	@Override
	public void calculateNeighbors(int numRow, int numCol, int depth, boolean toroidal, String hoodType, List<AniSlot> slotList) {
		if(hoodType.equals("all")) {
			int[] dX = {-1, -1, 0, 1, 1, 0};
			int[] dY = {-1, 0, 1, 0, -1, -1};
			super.calculateNeighbors(numRow, numCol, depth, toroidal, slotList, dX, dY);
		}
	}
}
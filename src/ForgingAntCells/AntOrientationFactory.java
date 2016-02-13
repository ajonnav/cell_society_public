package ForgingAntCells;

import slot.Slot;

public class AntOrientationFactory {
	
	public AntOrientationFactory() {	
	}
	
	public AntOrientationFactory(CardinalDirection d, Slot s) {
		
	}
	
	
	private void findForwardNeighbors(CardinalDirection d) {
		int[] dx; 
		int[] dy;
		switch (d) {
			case N:
				dx = new int[]{-1, -1, -1};
				dy = new int[]{-1, 0, 1};
				break;
			case NE: 
				dx = new int[]{-1, -1, 0};
				dy = new int[]{0, 1, 1};
				break;
			case E:
				dx = new int[]{-1, 0, 1};
				dy = new int[]{-1, 1, 1};
				break;
			case SE: 
				dx = new int[]{0, 1, 1};
				dy = new int[]{1, 1, 0};
				break;
			case S:
				dx = new int[]{1, 1, 1};
				dy = new int[]{1, 0, 1};
				break;
			case SW:
				dx = new int[]{1, 1, 0};
				dy = new int[]{0, -1, -1};
				break;
			case W:
				dx = new int[]{1, 0, -1};
				dy = new int[]{-1, -1, -1};
				break;
			case NW:
				dx = new int[]{0, -1, -1};
				dy = new int[]{-1, -1, 0};
				break;
			default:
				break;
		}
		// give these to some other method to calculate slot numbers and return a list of slots
	}

}

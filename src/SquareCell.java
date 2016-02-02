
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class SquareCell extends Cell {
	private int width, height;
	private Color cellColor;
	/**
	 * Constructor for SquareCell that will create a square cell of a certain color
	 */
	public SquareCell(int currentState, double x, double y, Color c, int w, int h){
		super(currentState, x, y);
		cellColor = c;
		
	}
	/**
	 * Returns the Cell's color.   
	 * @return
	 */
	public Color getColor(){
		return cellColor;
	}
	/**
	 * Update Color for given state
	 * @param newColor
	 */
	public void setColor(Color newColor){
		cellColor = newColor;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(cellColor);
		gc.fillRect(getX(), getY(), width, height);
	}

}

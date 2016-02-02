
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class SquareCell extends Cell {
	private int width, height;
	private Color cellColor;
	/**
	 * Constructor for SquareCell without Image
	 */
	public SquareCell(int currentState, double x, double y, Color c, int w, int h){
		super(currentState, x, y);
		cellColor = c;
		
	}
	
	public Color getColor(){
		return cellColor;
	}
	
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

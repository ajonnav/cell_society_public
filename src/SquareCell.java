import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class SquareCell extends Cell {
	int width, height;
	Color cellColor;
	/**
	 * Constructor for SquareCell with Image
	 */
	public SquareCell(int currentState, double x, double y, Image i, int w, int h){
		super(currentState, x, y, i);
		width = w;
		height = h;
	}
	/**
	 * Constructor for SquareCell without Image
	 */
	public SquareCell(int currentState, double x, double y, Color c, int w, int h){
		super(currentState, x, y, null);
		cellColor = c;
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(img.equals(null)){
			drawSquare(gc);
		}
		
		gc.drawImage(img, getX(), getY());

	}
	
	public void drawSquare(GraphicsContext gc){
		gc.setFill(cellColor);
		gc.fillRect(getX(), getY(), width, height);
	}

}

package simulations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import cells.*;
import automaton.AutomatonDisplay;

public class GameOfLife extends CA{
	
	private double perAlive;
	
	public GameOfLife(Map<String, String> mapArgs, AutomatonDisplay a) {
		super(mapArgs, a);
		perAlive = Double.parseDouble(mapArgs.get("perAlive"));
		
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		for(int i =0; i<getNumRow(); i++) {
			for(int j =0; j<getNumCol(); j++) {
				if(i%2==0) {
					getGraphicsContext().setFill(Color.RED);
					getGraphicsContext().fillRect(i*getCellWidth(),j*getCellHeight(),getCellWidth(),getCellHeight());
				}
				else {
					getGraphicsContext().setFill(Color.BLUE);
					getGraphicsContext().fillRect(i*getCellWidth(),j*getCellHeight(),getCellWidth(),getCellHeight());
				}
			}
		}
	}

	@Override
	protected void updateCells() {
		// TODO Auto-generated method stub
		for(int i=0; i<getAllCells().size(); i++) {
			
		}
	}

	@Override
	protected void calculateAdjacencyMatrix() {
		for(int i=0; i<getNumCell(); i++) {
			Cell cell = getAllCells().get(i);
			for(int j=i; j<getNumCell(); j++) {
				Cell temp = getAllCells().get(j);
				if((cell.getX() == temp.getX() && Math.abs(cell.getY()-temp.getY())==getCellWidth()) ||
						(Math.abs(cell.getX()-temp.getX())==getCellWidth() && cell.getY()==temp.getY())) {
					getNeighbors()[i][j] = 1;
					getNeighbors()[j][i] = 1;
				}
				else {
					getNeighbors()[i][j] = 0;
					getNeighbors()[j][i] = 0;
				}
			}
		}
	}
	
}

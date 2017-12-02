package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;

import logic.Coordinate;
import logic.Event;
import logic.GridMap;
import logic.Ticket;
import logic.Tile;

public class ButtonPressed implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")){
			boardDisplay();
		
		} else if (e.getActionCommand().equals("Reset")){
			DisplayWithGUI.xcoordinateF.setText("  X Coordinate  ");;
			DisplayWithGUI.ycoordinateF.setText("  Y Coordinate  ");
			DisplayWithGUI.rePaint();
		} else{
			int x;
			int y;
			String name = ((JComponent) e.getSource()).getName();
			String[] split = name.split(",");
			try{
				x = Integer.valueOf(split[0]);
				y = Integer.valueOf(split[1]);
				x = x-10;
				y = y-10;
			} catch (Exception error) {
				errorMessage("Error: bad button press");
				return;
			}
			
			DisplayWithGUI.xcoordinateF.setText(Integer.toString(x));
			DisplayWithGUI.ycoordinateF.setText(Integer.toString(y));
			boardDisplay();
		}
	}
	
	private static void errorMessage(String errorToReport){
		ArrayList<String> errorMessage = new ArrayList<String>(Arrays.asList(errorToReport));
		DisplayWithGUI.displayText(errorMessage);
	}
	
	private static void boardDisplay(){
		int valx;
		int valy;
		
		String textx = DisplayWithGUI.xcoordinateF.getText();
		String texty = DisplayWithGUI.ycoordinateF.getText();
		try{
			valx = Integer.valueOf(textx);
			valy = Integer.valueOf(texty);
		} catch (Exception error){
			errorMessage("Error: The value you input must be an integer");
			return;
		}
		ArrayList<Tile> tiles = DisplayWithGUI.gridmap.closestEvents(valx, valy);
		
		if (tiles.size() == 0){
			errorMessage("You have inputted values that are out of bounds");
			return;
		}
		
		ArrayList<String> displayArray = new ArrayList<String>();
		
		for (Tile tile : tiles){
			Event event = tile.getEvent().get();
			displayArray.add("Event" + event.getIdentifier() + " - " + event.lowestCost() + ", " + "Distance " + GridMap.distance(event.coord(), new Coordinate(valx, valy)));
		}
		
		DisplayWithGUI.displayText(displayArray);
		DisplayWithGUI.paint(tiles);
		DisplayWithGUI.paint(valx+10,valy+10);
	}
}

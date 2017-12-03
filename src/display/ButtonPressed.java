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

public class ButtonPressed implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			boardDisplay();

		} else if (e.getActionCommand().equals("Reset")) {
			DisplayWithGUI.xcoordinateF.setText("  X Coordinate  ");
			;
			DisplayWithGUI.ycoordinateF.setText("  Y Coordinate  ");
			DisplayWithGUI.rePaint();
		} else {
			int x;
			int y;
			String name = ((JComponent) e.getSource()).getName();
			String[] split = name.split(",");
			try {
				x = Integer.valueOf(split[0]);
				y = Integer.valueOf(split[1]);
				x = x + GridMap.getStart("x");
				y = y + GridMap.getStart("y");
			} catch (Exception error) {
				errorMessage("Error: bad button press");
				return;
			}

			DisplayWithGUI.xcoordinateF.setText(Integer.toString(x));
			DisplayWithGUI.ycoordinateF.setText(Integer.toString(y));
			boardDisplay();
		}
	}

	private static void errorMessage(String errorToReport) {
		ArrayList<String> errorMessage = new ArrayList<String>(Arrays.asList(errorToReport));
		DisplayWithGUI.displayText(errorMessage);
	}

	// displays the data taken from the inputs and displays this on the map and
	// the output data text labels
	private static void boardDisplay() {
		
		int valx;
		int valy;

		//gets x and y coordinates from the input boxes
		String textx = DisplayWithGUI.xcoordinateF.getText();
		String texty = DisplayWithGUI.ycoordinateF.getText();
		
		try {
			valx = Integer.valueOf(textx);
			valy = Integer.valueOf(texty);
		} catch (Exception error) {
			errorMessage("Error: The value you input must be an integer");
			return;
		}
		
		ArrayList<Tile> tiles = DisplayWithGUI.gridmap.closestEvents(valx, valy);

		//the closest events method will return an empty ArrayList in an error
		if (tiles.size() == 0) {
			errorMessage("You have inputted values that are out of bounds");
			return;
		}

		ArrayList<String> displayArray = new ArrayList<String>();

		for (Tile tile : tiles) {
			Event event = tile.getEvent().get();
			displayArray.add("Event" + event + " - " + event.lowestCost() + ", " + "Distance "
					+ Ticket.round(GridMap.distance(event.coord(), new Coordinate(valx, valy))));
		}

		DisplayWithGUI.displayText(displayArray);
		DisplayWithGUI.paint(tiles);
		
		//paints the tiles taking into account the offset of the coordinates
		DisplayWithGUI.paint(valx - GridMap.getStart("x"), valy - GridMap.getStart("x"));
	}
}

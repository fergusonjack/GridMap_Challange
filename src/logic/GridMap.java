package logic;

import java.util.ArrayList;
import java.util.Random;

public class GridMap {

	protected static int realStartx = -10;
	protected static int realStarty = -10;
	protected static int sizex = 21;
	protected static int sizey = 21;
	private static int probablity = 70;
	private int eventIdentif = 0;
	public Tile[][] grid = new Tile[sizex][sizey];

	public void initialise() {
		// generation of the seed
		Random rand = new Random();

		for (int y = 0; y < sizey; y++) {
			for (int x = 0; x < sizex; x++) {
				// randomises the choosing of events can be changed easily
				if (rand.nextInt(100) > probablity) {

					// increments identifier for each new tile
					grid[x][y] = new Tile(x, y, eventIdentif++, true);
				} else {
					grid[x][y] = new Tile(x, y, eventIdentif, false);
				}
			}

			// loading indicator
			System.out.println(y + "/" + (sizey - 1));
		}

	}

	// implements a sort greedy search choosing the next closest tile to go to
	// however it is uniformed
	public ArrayList<Tile> closestEvents(int x, int y) {
		x -= getStart("x");
		y -= getStart("y");

		// checks the array is in the correct size boundries
		if (x >= (sizex) || x < 0 || y >= (sizey) || y < 0) {
			return new ArrayList<Tile>();
		}

		Coordinate originalCoords = new Coordinate(x, y);

		ArrayList<Tile> visited = new ArrayList<Tile>();
		ArrayList<Tile> fringe = new ArrayList<Tile>();
		ArrayList<Tile> found = new ArrayList<Tile>();

		visited.add(grid[x][y]);
		ArrayList<Tile> toAdd = getNextTo(grid[x][y].getCoords(), visited);
		fringe.addAll(toAdd);
		visited.addAll(toAdd);

		while (found.size() < 5) {
			Tile best = bestFinder(fringe, originalCoords);
			if (best.getEvent().isPresent()) {
				found.add(best);
			}

			toAdd = getNextTo(best.getCoords(), visited);

			fringe.addAll(toAdd);
			visited.addAll(toAdd);
			fringe.remove(best);
		}

		return found;
	}

	// finds the next closest tile from the given arraylist
	public Tile bestFinder(ArrayList<Tile> fringe, Coordinate original) {
		Tile best = fringe.get(0);
		for (int i = 1; i < fringe.size(); i++) {
			if (distance(fringe.get(i).getCoords(), original) < distance(best.getCoords(), original)) {
				best = fringe.get(i);
			}
		}
		return best;
	}

	// returns the 4 coords around the current one
	public ArrayList<Tile> getNextTo(Coordinate coord, ArrayList<Tile> visited) {

		ArrayList<Tile> tiles = new ArrayList<Tile>();

		// Java's smart evaluation of the and operator makes sure this doesn't
		// cause errors
		if ((coord.getx() + 1) <= (sizex - 1) && (!(visited.contains(grid[coord.getx() + 1][coord.gety()])))) {
			tiles.add(grid[coord.getx() + 1][coord.gety()]);
		}
		if ((coord.gety() + 1) <= (sizey - 1) && (!(visited.contains(grid[coord.getx()][coord.gety() + 1])))) {
			tiles.add(grid[coord.getx()][coord.gety() + 1]);
		}
		if (((coord.getx() - 1) >= 0) && (!(visited.contains(grid[coord.getx() - 1][coord.gety()])))) {
			tiles.add(grid[coord.getx() - 1][coord.gety()]);
		}
		if ((coord.gety() - 1) >= 0 && (!(visited.contains(grid[coord.getx()][coord.gety() - 1])))) {
			tiles.add(grid[coord.getx()][coord.gety() - 1]);
		}
		return tiles;
	}

	// Manhattan distance
	public static double distance(Coordinate coord1, Coordinate coord2) {
		return Math.sqrt(Math.pow((coord1.getx() - coord2.getx()), 2) + Math.pow((coord1.gety() - coord2.gety()), 2));
	}

	// A toString for debugging this one shows the coordinates
	public String toString() {
		String returnString = "";
		for (int y = 0; y < sizey; y++) {
			for (int x = 0; x < sizex; x++) {
				returnString += grid[x][y].toString() + " ";
			}
			returnString += "\n";
		}
		return returnString;
	}

	// Another toString for debugging this shows with x's and 0's the location
	// of Events
	public String toStringx() {
		String returnString = "";
		for (int y = 0; y < sizey; y++) {
			for (int x = 0; x < sizex; x++) {
				returnString += grid[x][y].toStringx() + " ";
			}
			returnString += "\n";
		}
		return returnString;
	}

	public static int getsizes(String xory) {
		if (xory.equals("x")) {
			return sizex;
		} else {
			return sizey;
		}
	}

	public static int getStart(String xory) {
		if (xory.equals("x")) {
			return realStartx;
		} else {
			return realStarty;
		}
	}
}

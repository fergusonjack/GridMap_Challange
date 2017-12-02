package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GridMap {
	
	protected static int sizex = 21;
	protected static int sizey = 21;
	protected static int offsetx = 10;
	protected static int offsety = 10;
	private static int probablity = 70;
	private int eventIdentif = 0;
	public Tile[][] grid = new Tile[sizex][sizey];
	
	public void initialise(){
		//generation of the seed
		Random rand = new Random();
		for (int y = 0 ; y <sizey ; y++){
			for (int x = 0 ; x <sizex ; x++){
				if (rand.nextInt(100)>probablity){
					grid[x][y] = new Tile(x, y, eventIdentif++, true);
				} else {
					grid[x][y] = new Tile(x, y, eventIdentif, false);
				}
			}
			System.out.println(y+"/"+(sizey-1));
		}
		
	}
	
	public static int offset(int x){
		return (x-10);
	}
	
	public ArrayList<Tile> closestEvents(int x, int y){
		x += 10;
		y += 10;
		
		if (x>=(sizex) || x<0 || y>=(sizey) || y<0){
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
		
		while(found.size() < 5 ){
			Tile best = bestFinder(fringe, originalCoords);			
			if (best.getEvent().isPresent()){
				found.add(best);
			}
			
			toAdd = getNextTo(best.getCoords(), visited);
			
			fringe.addAll(toAdd);
			visited.addAll(toAdd);
			fringe.remove(best);			
		}
		
		return found;
	}
	
	public Tile bestFinder(ArrayList<Tile> fringe, Coordinate original){
		Tile best = fringe.get(0);
		for (int i = 1 ; i < fringe.size() ; i++){
			if (distance(fringe.get(i).getCoords(), original) < distance(best.getCoords(), original)){
				best = fringe.get(i);
			}
		}
		return best;
	}
	
	//returns the 4 coords around the current one
	public ArrayList<Tile> getNextTo(Coordinate coord, ArrayList<Tile> visited){
		
		ArrayList<Tile> tiles = new ArrayList<Tile>();		
		
		//smart evaluation of ands makes sure this doesn't cause errors
		if ((coord.getx()+1)<=20 && (!(visited.contains(grid[coord.getx()+1][coord.gety()])))){
			tiles.add(grid[coord.getx()+1][coord.gety()]);
		}
		if ((coord.gety()+1)<=20 && (!(visited.contains(grid[coord.getx()][coord.gety()+1])))){
			tiles.add(grid[coord.getx()][coord.gety()+1]);
		}
		if (((coord.getx()-1)>=0) && (!(visited.contains(grid[coord.getx()-1][coord.gety()])))){
			tiles.add(grid[coord.getx()-1][coord.gety()]);
		}
		if ((coord.gety()-1)>=0 && (!(visited.contains(grid[coord.getx()][coord.gety()-1])))){
			tiles.add(grid[coord.getx()][coord.gety()-1]);
		}
		return tiles;
	}
	
	
	public static double distance (Coordinate coord1, Coordinate coord2){
		return Math.sqrt(Math.pow((coord1.getx()-coord2.getx()), 2) + Math.pow((coord1.gety()-coord2.gety()), 2));
	}
		
	public String toString(){
		String returnString = "";
		for (int y = 0 ; y < sizey ; y++){
			for (int x = 0 ; x < sizex ; x++){
				returnString += grid[x][y].toString() + " ";
			}
			returnString += "\n";
		}
		return returnString;
	}
	public String toStringx(){
		String returnString = "";
		for (int y = 0 ; y < sizey ; y++){
			for (int x = 0 ; x < sizex ; x++){
				returnString += grid[x][y].toStringx() + " ";
			}
			returnString += "\n";
		}
		return returnString;
	}
}

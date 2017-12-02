package logic;

import java.util.ArrayList;

public class Displayer {

	public static void main(String[] args){
		GridMap gridmap = new GridMap();
		gridmap.initialise();
		//System.out.println(gridmap.toStringx());
		System.out.println(gridmap.toStringx());
		
		ArrayList<Tile> tt =  (gridmap.closestEvents(10, 10));
		for (Tile aa : tt){
			System.out.println(aa.getEvent().get().coords());
		}
	}
}



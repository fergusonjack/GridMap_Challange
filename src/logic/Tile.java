package logic;

import java.util.Optional;

public class Tile {
	
	private Optional<Event> event;
	private Coordinate coord;
	
	public Tile(int valx, int valy, int identif, Boolean bool){
		coord = new Coordinate(valx, valy);
		if (bool){
			event = Optional.of(new Event(identif, coord));
		} else {
			event = Optional.empty();
		}
	}
	
	public Optional<Event> getEvent(){
		return event;
	}
	
	public boolean exists(){
		return event.isPresent();
	}
	
	public Coordinate getCoords(){
		return coord;
	}
	
	@Override
	public String toString(){
		if (event.isPresent()){
			return coord.toString();
		} else {
			return coord.toString();
		}
	}
	
	public String toStringx(){
		if (event.isPresent()){
			return "x";
		} else {
			return "0";
		}
	}
}

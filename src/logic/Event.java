package logic;

import java.util.ArrayList;
import java.util.Random;

public class Event {
	private int identifier;
	private Coordinate coord;
	private ArrayList<Ticket> ticket = new ArrayList<Ticket>();

	public Event(int indentif, Coordinate coordinate) {
		this.identifier = indentif;
		this.coord = new Coordinate(coordinate.getx() + GridMap.realStartx, coordinate.gety() + GridMap.realStarty);

		Random rand = new Random();

		// limit of 1000 tickets per event
		// in reality it may be higher but this gives a better range of ticket
		// prices when displayed
		int numTickets = rand.nextInt(999) + 1;
		while (numTickets >= 0) {
			ticket.add(new Ticket());
			numTickets--;
		}
	}

	//gives lowest price out of all tickets
	public String lowestCost() {
		Ticket lowestCostTicket = ticket.get(0);
		for (Ticket individual : ticket) {
			if (individual.getPrice() < lowestCostTicket.getPrice()) {
				lowestCostTicket = individual;
			}
		}
		return lowestCostTicket.prettyPrice();
	}

	public String coords() {
		return coord.toString();
	}

	public Coordinate coord() {
		return coord;
	}

	@Override
	public String toString() {

		return Integer.toString(identifier);
	}
}

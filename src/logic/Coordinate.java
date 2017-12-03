package logic;

public class Coordinate {
	private int x;
	private int y;
	
	//only used for pretty printing in the -10 to 10 space and debugging
	
	public Coordinate(int valx, int valy){
		x=valx;
		y=valy;
	}
	
	public void setx (int val){
		x = val;
	}
	
	public void sety (int val){
		y = val;
	}
	
	public int getx (){
		return x;
	}
	
	public int gety(){
		return y;
	}
	
	@Override
	public String toString(){
		return "(" + x + "," + y + ")" ;
	}
}

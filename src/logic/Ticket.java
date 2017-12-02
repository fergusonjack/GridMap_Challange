package logic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class Ticket {
	private float price;
	private static float min = 10;
	private static float max = 1500;
	
	
	public Ticket(){
		Random rand = new Random();
		price = ((rand.nextFloat() * (max-min)) + min);
	}
	
	public static Float round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    DecimalFormat df = new DecimalFormat("#.##");
	    return Float.valueOf(df.format(bd));
	}
	
	public float getPrice(){
		return price;
	}
	
	public String prettyPrice(){
		return "$" + round(price, 2);
	}

}

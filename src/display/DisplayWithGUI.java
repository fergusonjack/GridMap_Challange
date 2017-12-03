package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.*;

import javafx.stage.WindowEvent;
import logic.GridMap;
import logic.Tile;

//mainFrame is the JFrame ie the window
//controlpanel is the main panel for the whole window
//jlabels are the labels for the output of data

public class DisplayWithGUI {
	
	private static JFrame mainFrame;
	private static JPanel controlPanel;
	private static JButton[][] buttons;
	protected static GridMap gridmap;
	protected static JTextField xcoordinateF;
	protected static JTextField ycoordinateF;
	private static JLabel[] jlabels = new JLabel[5];

	
	public static void main(String[] args) {
		gridmap = new GridMap();
		gridmap.initialise();
		
		DisplayWithGUI.prepareGUI();
		DisplayWithGUI.addMap();
		DisplayWithGUI.addSidePanel();
	}

	private static void prepareGUI() {
		mainFrame = new JFrame("Viagogo Challenge");
		mainFrame.setSize(1400, 750);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	//panel containing input boxes and output text labels
	private static void addSidePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JPanel input = new JPanel(new GridLayout(2, 2));
		JPanel labelHolder = new JPanel(new GridLayout(5, 1));
		
		xcoordinateF = new JTextField("  X Coordinate  ");
		ycoordinateF = new JTextField("  Y Coordinate  ");
		
		JButton search = new JButton("Search");
		JButton reset = new JButton("Reset");
		
		//add event listeners
		search.addActionListener(new ButtonPressed());
		reset.addActionListener(new ButtonPressed());
		
		input.add(xcoordinateF);
		input.add(ycoordinateF);
		input.add(search);
		input.add(reset);
		
		//add labels to display data
		for (int i = 0; i<5 ; i++){
			jlabels[i] = new JLabel("                          ");
			labelHolder.add(jlabels[i]);
		}
		
		panel.add(input);
		panel.add(labelHolder);
		controlPanel.add(panel);
		mainFrame.setVisible(true);
	}

	//adds in the map using buttons in a grid
	private static void addMap() {
		int xsize = GridMap.getsizes("x");
		int ysize = GridMap.getsizes("y");
		JPanel panel = new JPanel();
		panel.setSize(1000, 1000);
		GridLayout layout = new GridLayout(xsize, ysize);
		
		//adds some nice spacing between the buttons
		layout.setHgap(2);
		layout.setVgap(2);
		panel.setLayout(layout);

		buttons = new JButton[xsize][ysize];
		
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				buttons[x][y] = (new JButton(""));
				buttons[x][y].setName(x + "," + y);
				buttons[x][y].addActionListener(new ButtonPressed());
				panel.add(buttons[x][y]);
				if (gridmap.grid[x][y].exists()) {
					buttons[x][y].setBackground(Color.BLUE);
				} else {
					buttons[x][y].setBackground(Color.LIGHT_GRAY);
				}

				buttons[x][y].setPreferredSize(new Dimension(30, 30));
			}
		}

		controlPanel.add(panel);
	}

	//re-paints back to the original gridmap
	protected static void rePaint() {
		for (int y = 0; y < GridMap.getsizes("y"); y++) {
			for (int x = 0; x < GridMap.getsizes("x"); x++) {
				if (gridmap.grid[x][y].exists()) {
					buttons[x][y].setBackground(Color.BLUE);
				} else {
					buttons[x][y].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	//paints a specific set of tiles
	protected static void paint(ArrayList<Tile> tiles) {
		for (int y = 0; y < GridMap.getsizes("y"); y++) {
			for (int x = 0; x < GridMap.getsizes("x"); x++) {
				if (matcher(tiles, x, y)) {
					buttons[x][y].setBackground(Color.BLUE);
				} else {
					buttons[x][y].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}
	
	//paints a single tile eg the one you selected
	protected static void paint(int x , int y) {
		buttons[x][y].setBackground(Color.ORANGE);
	}

	//checks if a tile is an equal to some coordinates 
	private static boolean matcher(ArrayList<Tile> tiles, int valx, int valy) {
		for (Tile tile : tiles) {
			if (tile.getCoords().getx() == valx && tile.getCoords().gety() == valy) {
				return true;
			}
		}
		return false;
	}

	//displays an array list of up to 5 Strings used for the data output
	protected static void displayText(ArrayList<String> toDisplay) {
		if (toDisplay.size() > 5) {
			System.out.println("Error trying to display too much");
			return;
		}
		
		for (int i = 0 ; i < toDisplay.size() ; i++){
			jlabels[i].setText(toDisplay.get(i));
		}
	}
}
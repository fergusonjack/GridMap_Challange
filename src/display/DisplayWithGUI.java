package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.*;

import javafx.stage.WindowEvent;
import logic.GridMap;
import logic.Tile;

public class DisplayWithGUI {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JLabel msglabel;
	private static JButton[][] buttons;
	protected static GridMap gridmap;
	protected static JTextField xcoordinateF;
	protected static JTextField ycoordinateF;
	private static JLabel[] jlabels = new JLabel[5];

	public DisplayWithGUI() {
		prepareGUI();
	}

	public static void main(String[] args) {
		gridmap = new GridMap();
		gridmap.initialise();
		DisplayWithGUI swingLayoutDemo = new DisplayWithGUI();
		swingLayoutDemo.addButtons(gridmap);
		swingLayoutDemo.addSidePanel();
	}

	private void prepareGUI() {
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

	private void addSidePanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		xcoordinateF = new JTextField("  X Coordinate  ");
		ycoordinateF = new JTextField("  Y Coordinate  ");
		JButton search = new JButton("Search");
		JButton reset = new JButton("Reset");
		
		//add event listeners
		search.addActionListener(new ButtonPressed());
		reset.addActionListener(new ButtonPressed());
		
		xcoordinateF.setSize(150, 150);
		ycoordinateF.setSize(150, 150);
		panel.add(xcoordinateF);
		panel.add(ycoordinateF);
		panel.add(search);
		panel.add(reset);
		
		JPanel labelHolder = new JPanel(new GridLayout(5, 1));
		
		//add labels to display data
		for (int i = 0; i<5 ; i++){
			jlabels[i] = new JLabel("                          ");
			labelHolder.add(jlabels[i]);
		}
		
		panel.add(labelHolder);
		controlPanel.add(panel);
		mainFrame.setVisible(true);
	}

	private void addButtons(GridMap gridmap) {
		JPanel panel = new JPanel();
		panel.setSize(1000, 1000);
		GridLayout layout = new GridLayout(21, 22);
		layout.setHgap(2);
		layout.setVgap(2);

		panel.setLayout(layout);

		int i = 0;
		buttons = new JButton[21][21];
		for (int y = 0; y < 21; y++) {
			for (int x = 0; x < 21; x++) {
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
				i++;
			}
		}

		controlPanel.add(panel);
	}

	protected static void rePaint() {
		for (int y = 0; y < 21; y++) {
			for (int x = 0; x < 21; x++) {
				if (gridmap.grid[x][y].exists()) {
					buttons[x][y].setBackground(Color.BLUE);
				} else {
					buttons[x][y].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	protected static void paint(ArrayList<Tile> tiles) {
		for (int y = 0; y < 21; y++) {
			for (int x = 0; x < 21; x++) {
				if (matcher(tiles, x, y)) {
					buttons[x][y].setBackground(Color.BLUE);
				} else {
					buttons[x][y].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}
	
	protected static void paint(int x , int y) {
		buttons[x][y].setBackground(Color.ORANGE);
	}

	private static boolean matcher(ArrayList<Tile> tiles, int valx, int valy) {
		for (Tile tile : tiles) {
			if (tile.getCoords().getx() == valx && tile.getCoords().gety() == valy) {
				return true;
			}
		}
		return false;
	}

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
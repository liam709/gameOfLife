//Developed by: Liam Kelly
//Dependencies: Picture.java

import java.awt.Color;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLife {
	private int x, y; // x-by-y grid of cells
	private int N; // Number of iterations
	private char choice;
	private int magnification; // pixel-width of each cell
	public int[][] grid;
	private Picture pic; // picture to be drawn on screen

	public GameOfLife(int N, char choice) {
		// Number of iterations
		this.N = N;
		// Pattern Type: Can be R for Random 50% chance cell starting alive or dead. J
		// for Jam oscillator pattern, D for dart glider pattern.
		this.choice = choice;
		// Set demision of grid
		int x = 80;
		int y = 80;
		this.grid = new int[80][80];
		int magnification = 10;
		// display frame
		pic = new Picture(x * magnification, y * magnification);

	}

	// Sets up initial state of board/cell values.
	// And draws the grid based on value stored. Black = 0 = Dead, White = 1 =
	// Alive.
	public void setupRandom() {

		show();

		// Display the grid
		for (int a = 0; a < 80; a++) {
			for (int b = 0; b < 80; b++) {
				drawGrid(a, b);
			}
		}

		// Iterate over empty 2d array and fill with random value, 1 or 0
		// FILLING 2D ARRAY WITH RANDOM VALUES
		for (int i = 0; i < 80; i++) {
			// for each cell in col
			for (int j = 0; j < 80; j++) {
				double rand = Math.random();

				// Randomly generating an intial state
				if (rand < 0.50) {
					grid[i][j] = 0;
				} else {
					grid[i][j] = 1;
				}
			}
		}

		// Iterate over grid and color each cell depending on value.
		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < 80; j++) {
				if (grid[i][j] == 1) {
					drawAliveCell(i, j);
				}
			}
		}
	}

	public void setupJammer() {

		show();

		// Draw grid
		for (int a = 0; a < 80; a++) {
			for (int b = 0; b < 80; b++) {
				drawGrid(a, b);
			}
		}

		// Fill Array with inital values for glider.
		// Defining JAM ossilator
		grid[2][4] = 1;
		grid[2][5] = 1;
		grid[2][6] = 1;
		grid[3][8] = 1;
		grid[4][3] = 1;
		grid[4][8] = 1;
		grid[5][2] = 1;
		grid[5][4] = 1;
		grid[5][7] = 1;
		grid[6][2] = 1;
		grid[6][5] = 1;
		grid[7][3] = 1;
		grid[7][4] = 1;

		// Iterate over the grid and color cell depending on value
		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < 80; j++) {

				if (grid[i][j] == 1) {
					drawAliveCell(i, j);
				}
			}
		}
	}

	public void setupDartGlider() {

		show();

		// Draw grid
		for (int a = 0; a < 80; a++) {
			for (int b = 0; b < 80; b++) {
				drawGrid(a, b);
			}
		}

		// Fill Array with inital values for glider.
		// Defining JAM ossilator
		grid[10][60] = 1;
		grid[9][59] = 1;
		grid[8][60] = 1;
		grid[7][61] = 1;
		grid[11][61] = 1;
		grid[8][62] = 1;
		grid[9][62] = 1;
		grid[10][62] = 1;
		grid[6][64] = 1;
		grid[7][64] = 1;
		grid[11][64] = 1;
		grid[12][64] = 1;
		grid[4][65] = 1;
		grid[8][65] = 1;
		grid[10][65] = 1;
		grid[14][65] = 1;
		grid[4][66] = 1;
		grid[3][66] = 1;
		grid[8][66] = 1;
		grid[10][66] = 1;
		grid[14][66] = 1;
		grid[15][66] = 1;
		grid[2][67] = 1;
		grid[8][67] = 1;
		grid[10][67] = 1;
		grid[16][67] = 1;
		grid[3][68] = 1;
		grid[5][68] = 1;
		grid[6][68] = 1;
		grid[8][68] = 1;
		grid[10][68] = 1;
		grid[12][68] = 1;
		grid[13][68] = 1;
		grid[15][68] = 1;

		// Iterate over the grid and color cell depending on value
		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < 80; j++) {

				if (grid[i][j] == 1) {
					drawAliveCell(i, j);
				}
			}
		}
	}

	// Updates the display with new values stored in next generation
	public void update() {

		// Draw grid
		for (int a = 0; a < 80; a++) {
			for (int b = 0; b < 80; b++) {
				drawGrid(a, b);
			}
		}
		// Draw updated alive cells
		for (int i = 0; i < 80; i++) {

			for (int j = 0; j < 80; j++) {

				if (grid[i][j] == 1) {

					drawAliveCell(i, j);
				}
			}
		}
		show();
	}

	// display (or update) the picture on screen
	public void show() {
		pic.show(); // without calling this the pic will not show
	}

	// Draws each individual alive cell in grid.
	public void drawAliveCell(int i, int j) {
		Color col = new Color(0, 255, 127);
		int magnification = 10;

		// Offset to see lines between individual white cells
		// Offset to see lines between individual white cells
		for (int offsetX = 1; offsetX < magnification; offsetX++) {
			for (int offsetY = 1; offsetY < magnification; offsetY++) {
				// set() colours an individual pixel
				pic.set((i * magnification) + offsetX,
						(j * magnification) + offsetY, col);
			}
		}
	}

	public void drawGrid(int i, int j) {
		// White grid
		Color col = new Color(0, 0, 0);
		int magnification = 10;

		// Offset to see lines grid lines.
		for (int offsetX = 1; offsetX < magnification; offsetX++) {
			for (int offsetY = 1; offsetY < magnification; offsetY++) {
				// set() colours an individual pixel
				pic.set((i * magnification) + offsetX,
						(j * magnification) + offsetY, col);
			}
		}
	}

	// Get number of alive neighbors of cell
	// Following Nature of Code method.
	public int totalLiveN(int x, int y) {

		int aliveN = 0;

		aliveN += getCellState(x, y - 1);
		aliveN += getCellState(x, y + 1);
		aliveN += getCellState(x + 1, y);
		aliveN += getCellState(x + 1, y - 1);
		aliveN += getCellState(x + 1, y + 1);
		aliveN += getCellState(x - 1, y - 1);
		aliveN += getCellState(x - 1, y);
		aliveN += getCellState(x - 1, y + 1);

		return aliveN;
	}

	// Cheese way to do this atm.
	// Need to use modulo in order to wrap around
	public int getCellState(int x, int y) {
		// If col is oob, or row is oob, set dead.
		if ((x < 0 || x >= 80) || (y < 0 || y >= 80)) {
			return 0;
		}

		return grid[x][y];
	}

	public void step() {

		int[][] nextGeneration = new int[80][80];

		for (int x = 0; x < 80; x++) {
			for (int y = 0; y < 80; y++) {
				// Get number of live neighbors
				int livingN = totalLiveN(x, y);

				if ((grid[x][y] == 1) && (livingN < 2)) {
					nextGeneration[x][y] = 0;
				} else if ((grid[x][y] == 1) && (livingN > 3)) {
					nextGeneration[x][y] = 0;
				} else if ((grid[x][y] == 0) && (livingN == 3)) {
					nextGeneration[x][y] = 1;
				} else {
					nextGeneration[x][y] = grid[x][y];
				}

			}
		}
		grid = nextGeneration;
	}

	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]);
		char choice = args[1].charAt(0);
		GameOfLife gameOfLife = new GameOfLife(N, choice);

		// Random
		if (choice == 'R') {
			gameOfLife.setupRandom();
			for (int i = 0; i <= N; i++) {
				// Some delay between iterations
				try {
					Thread.currentThread().sleep(120);
				} catch (Exception e) {
					System.out.println("Error occurred in frame generation.");
				}

				System.out.print(i);
				System.out.println();
				gameOfLife.step();
				gameOfLife.update();
			}
			// Jammer
		} else if (choice == 'J') {
			// SET JAMMER ALIVE VALUES;
			gameOfLife.setupJammer();
			for (int i = 0; i <= N; i++) {
				try {
					Thread.currentThread().sleep(120);
				} catch (Exception e) {
					System.out.println("Error in frame generation");
				}
				gameOfLife.step();
				gameOfLife.update();

			}
			// Dart glider
		} else if (choice == 'D') {
			gameOfLife.setupDartGlider();
			for (int i = 0; i <= N; i++) {
				try {
					Thread.currentThread().sleep(120);
				} catch (Exception e) {
					System.out.println("Error occurred in frame generation.");
				}
				gameOfLife.step();
				gameOfLife.update();

			}

		}
	}
}
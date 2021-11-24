package userinterface;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JColorChooser;

import ecs100.UI;
import ecs100.UIFileChooser;

public class UserInterface {

	private ArrayList<Shapes> shapes = new ArrayList<Shapes>();
	private String currentShape = "Circle";
	private Color color = Color.black;
	private boolean fill = true;
	private int fontSize = 20;
	private String mode = "draw";
	private double x1 = 0;
	private double y1 = 0;

	private Shapes newPos = null;

	public boolean gameComplete = false;
	
	public boolean youLose = false;

	// Mouse listener for draw, move, remove and color bucket
	public void doMouse(String action, double x, double y) {

		if (mode.equals("draw")) {
			if (action.equals("pressed")) {
				x1 = x;
				y1 = y;
			}
			if (action.equals("released")) {
				mouseDraw(action, x1, y1, x, y);
			}
		} else if (mode.equals("remove")) {
			if (action.equals("pressed")) {
				mouseRemove(action, x, y);
			}
		} else if (mode.equals("move")) {
			if (action.equals("pressed"))

				for (int i = 0; i < shapes.size(); i++) {
					// check if shape checks if the coordinates match any in the shape drawn
					boolean s = shapes.get(i).checkIfShape(x, y);
					if (s) {
						// stores shape to be removed
						newPos = shapes.get(i);
						
						break;
					}
				}

			else if(action.equals("released")) {
				mouseMove(action, x, y, newPos);
				newPos = null;
			}
		} else if (mode.equals("color")) {
			if (action.equals("pressed")) {
				mouseColor(action, x, y);
			}
		}

		else if (mode.equals("resize")) {
			if (action.equals("pressed"))
				for (int i = 0; i < shapes.size(); i++) {
					// check if shape checks if the coordinates match any in the shape drawn
					boolean s = shapes.get(i).checkIfShape(x, y);
					if (s) {
						// stores shape to be removed
						newPos = shapes.get(i);
						
						break;
					}
				}
			else if (action.equals("released")) {
				mouseResize(action, x, y, newPos);
				newPos =null;
			}

		}
	}

	// draw method
	public void mouseDraw(String action, double x1, double y1, double x, double y) {

		// circle draw
		if (currentShape.equals("Circle")) {
			
			
			double x2 = x;
			double y2 = y;
			double length = Math.abs(x2 - x1);
			Circle o = null;
			if (x1 < x2 && y1 < y2) {
				o = new Circle(x1 + length / 2, y1 + length / 2, length, fill, color);
			} else if (x2 < x1 && y1 < y2) {
				o = new Circle(x1 - length / 2, y1 + length / 2, length, fill, color);
			} else if (x2 < x1 && y2 < y1) {
				o = new Circle(x1 - length / 2, y1 - length / 2, length, fill, color);
			} else {
				o = new Circle(x1 + length / 2, y1 - length / 2, length, fill, color);
			}

			shapes.add(o);
			x1 = 0;
			y1 = 0;
			x2 = 0;
			y2 = 0;

		}
		// rectangle draw
		else if (currentShape.equals("Rectangles")) {

			double x2 = x;
			double y2 = y;
			double length = Math.abs(x2 - x1);
			double height = Math.abs(y2 - y1);
			Rectangles r = null;

			if (x1 < x2 && y1 < y2) {
				r = new Rectangles(x1 + length / 2, y1 + height / 2, length, height, fill, color);
			} else if (x2 < x1 && y1 < y2) {
				r = new Rectangles(x1 - length / 2, y1 + height / 2, length, height, fill, color);
			} else if (x2 < x1 && y2 < y1) {
				r = new Rectangles(x1 - length / 2, y1 - height / 2, length, height, fill, color);
			} else {
				r = new Rectangles(x1 + length / 2, y1 - height / 2, length, height, fill, color);
			}
			shapes.add(r);
			x1 = 0;
			y1 = 0;
			x2 = 0;
			y2 = 0;

		}

		// line draw, takes first click, if there is already a first click then it draws
		// and resets first click
		else if (currentShape.equals("Lines")) {

			double x2 = x;
			double y2 = y;

			Lines l = new Lines(x1, y1, x2, y2, color);

			shapes.add(l);
			l = null;
			x1 = 0;
			y1 = 0;
			x2 = 0;
			y2 = 0;

		}
		// Textbox draw
		else if (currentShape.equals("TextBox")) {
			UI.clearText();
			String text = UI.askString("Enter text for box");
			if (text.length() < 25) {
				TextBox t = new TextBox(x, y, text, fontSize, color, fill);
				shapes.add(t);
			} else
				UI.println("Too many characters entered, must be less than 25 characters");
		}

		// Run through array of shapes to draw them all to screen
		for (Shapes i : shapes) {

			i.draw();
		}
		x1 = 0;
		y1 = 0;

	}

	// Removes shapes from arraylist by clicking on them
	public void mouseRemove(String action, double x, double y) {

		for (int i = 0; i < shapes.size(); i++) {
			// check shape method returns true if these coordinates match with a shape
			boolean s = shapes.get(i).checkIfShape(x, y);
			if (s) {
				shapes.remove(i);
			}

		}
		UI.clearGraphics();

		for (Shapes i : shapes) {

			i.draw();
		}
	}

	public void mouseMove(String action, double x, double y, Shapes newPos) {
		if(newPos!=null) {
		// circle move
		if (newPos.getName().equals("Circle")) {
			Circle o = new Circle(x, y, newPos.getLength(), newPos.getFill(), newPos.getColor());
			shapes.add(o);
		shapes.remove(newPos);
			newPos = null;
			o = null;
		}
		// rectangle move
		else if (newPos.getName().equals("Rectangles")) {
			Rectangles r = new Rectangles(x, y, newPos.getLength(), newPos.getHeight(), newPos.getFill(),
					newPos.getColor());
			shapes.add(r);
		shapes.remove(newPos);
			newPos = null;
			r = null;
		}
		// line move, keeps the same size and orientation of line and draws it in the new location
		else if (newPos.getName().equals("Lines")) {
			double ox1 = newPos.getX1();
			double ox2 = newPos.getX2();
			double oy1 = newPos.getY1();
			double oy2 = newPos.getY2();

			double x2 = 0;
			double y2 = 0;
			double x1 = 0;
			double y1 = 0;

			double m = (oy2 - oy1) / (ox2 - ox1);
			double oc = oy1 - (m * ox1);
			double xdiff = 0;
			double ydiff = 0;
			// checks what direction the line is going for how the differences in the coordinates should be calculated
			if (ox2 > ox1 && oy2 > oy1) {
				xdiff = (ox2 - ox1) / 2;
				ydiff = (oy2 - oy1) / 2;
			} else if (ox2 < ox1 && oy2 > oy1) {
				xdiff = (ox1 - ox2) / 2;
				ydiff = (oy2 - oy1) / 2;
			}

			else if (ox2 < ox1 && oy2 < oy1) {
				xdiff = (ox1 - ox2) / 2;
				ydiff = (oy1 - oy2) / 2;
			} else if (ox2 > ox1 && oy2 < oy1) {
				xdiff = (ox2 - ox1) / 2;
				ydiff = (oy1 - oy2) / 2;
			}
			else if(ox1 == ox2 && oy1 < oy2) {
			
				ydiff = Math.abs((oy2 - oy1) / 2);
			}
			else if(ox1 == ox2 && oy1 > oy2) {
				
				ydiff = Math.abs((oy1 - oy2) / 2);
			}
			else if(ox1 < ox2 && oy1 == oy2) {
				xdiff = Math.abs((ox2 - ox1) / 2);
			}
			else if(ox1 < ox2 && oy1 == oy2) {
				xdiff = Math.abs((ox1 - ox2)/ 2);
			}



			//checks if gradient is positive or negative or something else for how the coordinates should be adjusted
			if (m < 0) {
				x1 = x - xdiff;
				x2 = x + xdiff;
				y1 = y + ydiff;
				y2 = y - ydiff;
			}
			else if (m > 0) {
				x1 = x - xdiff;
				x2 = x + xdiff;
				y1 = y - ydiff;
				y2 = y + ydiff;
			}
			else if(ox1==oy1) {
				x1 = x;
				x2 = x;
				y1 = y;
				y2 = y;
						
			}
			else if(oy1==oy2) {
				x1 = x -xdiff;
				x2 = x + xdiff;
				y1 = y;
				y2 = y;
			}
			else {
				x1 = x;
				x2 = x;
				y1 = y - ydiff;
				y2 = y + ydiff;
				
			}


			Lines l = new Lines(x1, y1, x2, y2, newPos.getColor());

		shapes.remove(newPos);
			newPos = null;

			shapes.add(l);
			l = null;
			x1 = 0;
			y1 = 0;
			x2 = 0;
			y2 = 0;

		}
		// Textbox draw
		else if (newPos.getName().equals("TextBox")) {

			TextBox t = new TextBox(x, y, newPos.getText(), newPos.getFontSize(), newPos.getColor(), newPos.getFill());
			shapes.add(t);
		shapes.remove(newPos);
			newPos = null;
			t = null;
		}

		UI.clearGraphics();
//		drawGridBoard();
		for (Shapes i : shapes) {

			i.draw();
		}
	}
	}

	public void mouseColor(String action, double x, double y) {

		for (int i = 0; i < shapes.size(); i++) {
			boolean s = shapes.get(i).checkIfShape(x, y);
			if (s) {
				// stores shape to be removed
				newPos = shapes.get(i);
			}
		}
		if (newPos == null);
			
		// circle draw
		else if (newPos.getName().equals("Circle")) {
			Circle o = new Circle(newPos.getX(), newPos.getY(), newPos.getLength(), newPos.getFill(), color);
			shapes.add(o);
			shapes.remove(newPos);
			newPos = null;
		}
		// rectangle draw
		else if (newPos.getName().equals("Rectangles")) {
			Rectangles r = new Rectangles(newPos.getX(), newPos.getY(), newPos.getLength(), newPos.getHeight(),
					newPos.getFill(), color);
			shapes.add(r);
			shapes.remove(newPos);
			newPos = null;
		}
		// line draw, takes first click, if there is already a first click then it draws
		// and resets first click
		else if (newPos.getName().equals("Lines")) {

			Lines l = new Lines(newPos.getX1(), newPos.getY1(), newPos.getX2(), newPos.getY2(), color);
			shapes.remove(newPos);
			newPos = null;
			shapes.add(l);
		}
		// Textbox draw
		else if (newPos.getName().equals("TextBox")) {

			TextBox t = new TextBox(newPos.getX(), newPos.getY(), newPos.getText(), newPos.getFontSize(), color,
					newPos.getFill());
			shapes.add(t);
			shapes.remove(newPos);
			newPos = null;

		}

		UI.clearGraphics();
		for (Shapes i : shapes) {

			i.draw();
		}
	}

	public void mouseResize(String action, double x, double y, Shapes newPos) {
		if(newPos!=null) {
		
		// circle resize
		if (newPos.getName().equals("Circle")) {
			double x1 = newPos.getX() - newPos.getLength()/2;
			double y1 = newPos.getY() - newPos.getLength()/2;
			double x2 = x;
			double y2 = y;
			double length = Math.abs(x2 - x1);
			Circle o = null;
			if (x1 < x2 && y1 < y2) {
				o = new Circle(x1 + length/2, y1+ length/2 , length, newPos.getFill(), newPos.getColor());
				
				shapes.remove(newPos);
				shapes.add(o);
			newPos = null;

			}
			else UI.println("Could not resize mouse was out of range");

		}
		// rectangle resize
		else if (newPos.getName().equals("Rectangles")) {
			double x1 = newPos.getX() - (newPos.getLength()/2);
			double y1 = newPos.getY() - (newPos.getHeight()/2);
			double x2 = x;
			double y2 = y;
			double length = Math.abs(x2 - x1);
			double height = Math.abs(y2 - y1);
			Rectangles r = null;

			if (x1 < x2 && y1 < y2) {
				r = new Rectangles(x1 + length/2, y1 + height/2 , length, height, newPos.getFill(), newPos.getColor());
				
				shapes.remove(newPos);
				shapes.add(r);
			newPos = null;

			}
			else UI.println("Could not resize mouse was out of range");
			

		}
		// line resize, takes first click, if there is already a first click then it draws
		// and resets first click
		else if (newPos.getName().equals("Lines")) {

			double ox1 = newPos.getX1();
			double oy1 = newPos.getY1();
			double ox2 = newPos.getX2();
			double oy2 = newPos.getY2();
			
			if(oy1 < oy2) {
				x1 = ox1;
				y1 = oy1;
			}
			
			
			else if(oy1 > oy2) {
				x1 = ox2;
				y1 = oy2;
			}
			
			double x2 = x;
			double y2 = y;
			
			Lines l = new Lines(x1, y1, x2, y2, newPos.getColor());

			shapes.remove(newPos);
			newPos = null;

			shapes.add(l);

			x1 = 0;
			y1 = 0;

			
		}
		
		// Textbox resize
		else if (newPos.getName().equals("TextBox")) {
			UI.clearText();
			int size = UI.askInt("Enter font size (0 - 50)");
			if (size > 0 && size <= 50) {
				int nfontSize = size;
				TextBox t = new TextBox(x, y, newPos.getText(), nfontSize, newPos.getColor(), newPos.getFill());
				shapes.add(t);
					shapes.remove(newPos);
				newPos = null;
				
			} else
				UI.println("Invalid font size, must be between 0 and 50.");
				}
		UI.clearGraphics();

		for (Shapes i : shapes) {

			i.draw();
		}
	}
	}

	// Save shapes to a text file
	public void saveShapes() {
		String filename = UI.askString("Enter save file name");
		File outfile = new File(filename + ".txt");
		try {

			PrintStream ps = new PrintStream((outfile));

			for (int i = 0; i < shapes.size(); i++) {
				ps.println(shapes.get(i).toString());
			}

			ps.close();
		}

		catch (Exception e) {
			UI.println(e);
		}

	}
	
	//Allows user to select a file to load
	public void readDrawSelect() {
		String filename = UIFileChooser.open();
		readDraw(filename);
	}

	// Read shapes from a text file (must be formatted like the saves)
	public void readDraw(String filename) {
		clearGraphics();

		try {
			Scanner scan = new Scanner(new File(filename));

			while (scan.hasNext()) {
				String shapetype = scan.next();
				// circle reader
				if (shapetype.equals("Circle")) {
					double x = scan.nextDouble();
					double y = scan.nextDouble();
					double length = scan.nextDouble();
					boolean fill = scan.nextBoolean();
					int blue = scan.nextInt();
					int green = scan.nextInt();
					int red = scan.nextInt();

					Color color = new Color(red, green, blue);
					Circle c = new Circle(x, y, length, fill, color);
					shapes.add(c);
				}
				// line reader
				else if (shapetype.equals("Lines")) {
					double x1 = scan.nextDouble();
					double y1 = scan.nextDouble();
					double x2 = scan.nextDouble();
					double y2 = scan.nextDouble();

					int blue = scan.nextInt();
					int green = scan.nextInt();
					int red = scan.nextInt();

					Color color = new Color(red, green, blue);
					Lines c = new Lines(x1, y1, x2, y2, color);
					shapes.add(c);

				}
				// rectangle reader
				else if (shapetype.equals("Rectangles")) {
					double x = scan.nextDouble();
					double y = scan.nextDouble();
					double length = scan.nextDouble();
					double height = scan.nextDouble();
					boolean fill = scan.nextBoolean();
					int blue = scan.nextInt();
					int green = scan.nextInt();
					int red = scan.nextInt();

					Color color = new Color(red, green, blue);
					Rectangles c = new Rectangles(x, y, length, height, fill, color);
					shapes.add(c);
				}
				// textbox reader
				else if (shapetype.equals("TextBox")) {
					double x = scan.nextDouble();
					double y = scan.nextDouble();
					String text = scan.next();
					while(!scan.hasNextInt()) {						
					String textadd = scan.next();
					text = text + " " + textadd;
					}
					int fontSize = scan.nextInt();

					int blue = scan.nextInt();
					int green = scan.nextInt();
					int red = scan.nextInt();
					boolean fill = scan.nextBoolean();

					Color color = new Color(red, green, blue);
					TextBox c = new TextBox(x, y, text, fontSize, color, fill);
					shapes.add(c);
				}

			}
			scan.close();
		} catch (IOException e) {
			UI.println("File error: " + e);
			e.printStackTrace();
		}
		UI.clearGraphics();
		for (Shapes i : shapes) {

			i.draw();
		}
	}


	// clears the shapes arraylist and clears the graphics
	public void clearGraphics() {
		UI.clearGraphics();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			shapes.remove(i);
		}
	}

	// key method for game
	public void doKey(String action) {
		double m = 5;
		// up key
		if (action.equals("Up")) {
			for (int index = 0; index < shapes.size(); index++) {
				Shapes i = shapes.get(index);
				if (i.getName().equals("Rectangles")) {
					boolean goingHit = false;
					Rectangles r = new Rectangles(i.getX(), i.getY() - m, i.getLength(), i.getHeight(), i.getFill(),
							i.getColor());
					goingHit = checkGoingHit(goingHit, r);
					if (!goingHit) {

						shapes.set(index, r);
					}
				}
			}
		}
		// down key
		if (action.equals("Down")) {
			for (int index = 0; index < shapes.size(); index++) {
				Shapes i = shapes.get(index);
				if (i.getName().equals("Rectangles")) {
					boolean goingHit = false;
					Rectangles r = new Rectangles(i.getX(), i.getY() + m, i.getLength(), i.getHeight(), i.getFill(),
							i.getColor());
					goingHit = checkGoingHit(goingHit, r);
					if (!goingHit) {

						shapes.set(index, r);
					}
				}
			}
		}
		// left
		if (action.equals("Left")) {
			for (int index = 0; index < shapes.size(); index++) {
				Shapes i = shapes.get(index);
				if (i.getName().equals("Rectangles")) {
					boolean goingHit = false;
					Rectangles r = new Rectangles(i.getX() - m, i.getY(), i.getLength(), i.getHeight(), i.getFill(),
							i.getColor());
					goingHit = checkGoingHit(goingHit, r);
					if (!goingHit) {

						shapes.set(index, r);
					}
				}
			}
		}
		// right
		if (action.equals("Right")) {
			for (int index = 0; index < shapes.size(); index++) {
				Shapes i = shapes.get(index);
				if (i.getName().equals("Rectangles")) {
					boolean goingHit = false;
					Rectangles r = new Rectangles(i.getX() + m, i.getY(), i.getLength(), i.getHeight(), i.getFill(),
							i.getColor());
					goingHit = checkGoingHit(goingHit, r);
					if (!goingHit) {

						shapes.set(index, r);
					}
				}
			}
		}
		UI.clearGraphics();
		if (gameComplete) {
			TextBox t = new TextBox(300, 300, "YOU WIN!", fontSize, Color.blue, fill);
			shapes.add(t);
		}
		if (youLose) {
			TextBox t = new TextBox(300, 300, "YOU LOSE!", fontSize, Color.blue, fill);
			shapes.add(t);
		}
		
		for (Shapes i : shapes) {

			i.draw();

		}
		if (gameComplete) {
			String playAgain = UI.askString("Play again? (Y or N)");
			if (playAgain.equals("Y")) {
				gameComplete = false;
				playGame();
			} else if (playAgain.equals("N")) {
				UI.quit();
			} else {
				UI.println("Invalid answer");
				UI.quit();
			}
		}
		if(youLose) {
			String playAgain = UI.askString("Play again? (Y or N)");
			if (playAgain.equals("Y")) {
				youLose = false;
				playGame();
			} else if (playAgain.equals("N")) {
				UI.quit();
			} else {
				UI.println("Invalid answer");
				UI.quit();
			}
		}
	}

	// checks if rectangle will hit any of the lines on the screen
	public boolean checkGoingHit(boolean goingHit, Shapes i) {
		Shapes circle = null;
		for (Shapes j : shapes) {
			// checks if rectangle is hitting any of the lines with the next move
			if (j.getName().equals("Lines")) {
				// check top line of rectangle
				for (double k = (i.getX() - i.getLength() / 2); k < i.getX() + i.getLength() / 2; k++) {
					double l = i.getY() - i.getHeight() / 2;
					if (j.checkIfShape(k, l)) {
						goingHit = j.checkIfShape(k, l);
						break;
					}
				}
				// check bottom line of rectangle
				for (double k = (i.getX() - i.getLength() / 2); k < i.getX() + i.getLength() / 2; k++) {
					double l = i.getY() + i.getHeight() / 2;
					if (j.checkIfShape(k, l)) {
						goingHit = j.checkIfShape(k, l);
						break;
					}
				}
				// check left hand side of rectangle
				for (double k = (i.getY() + i.getHeight() / 2); k > i.getY() - i.getHeight() / 2; k--) {
					double l = i.getX() - i.getLength() / 2;
					if (j.checkIfShape(l, k)) {
						goingHit = j.checkIfShape(l, k);
						break;
					}
				}
				// check right hand side of rectangle
				for (double k = (i.getY() + i.getHeight() / 2); k > i.getY() - i.getHeight() / 2; k--) {
					double l = i.getX() + i.getLength() / 2;
					if (j.checkIfShape(l, k)) {
						goingHit = j.checkIfShape(l, k);
						break;
					}
				}
				//Checks whether the rectangle is hitting circle with next move, circle is deleted if true
			} else if (j.getName().equals("Circle")) {
				for (double k = (i.getX() - i.getLength() / 2); k < i.getX() + i.getLength() / 2; k++) {
					double l = i.getY() - i.getHeight() / 2;
					if (j.checkIfShape(k, l)) {
						circle = j;
						break;
					}
				}
				for (double k = (i.getX() - i.getLength() / 2); k < i.getX() + i.getLength() / 2; k++) {
					double l = i.getY() + i.getHeight() / 2;
					if (j.checkIfShape(k, l)) {
						circle = j;
						break;
					}
				}
				for (double k = (i.getY() + i.getHeight() / 2); k > i.getY() - i.getHeight() / 2; k--) {
					double l = i.getX() - i.getLength() / 2;
					if (j.checkIfShape(l, k)) {
						circle = j;
						break;
					}
				}
				for (double k = (i.getY() + i.getHeight() / 2); k > i.getY() - i.getHeight() / 2; k--) {
					double l = i.getX() + i.getLength() / 2;
					if (j.checkIfShape(l, k)) {
						circle = j;
						break;
					}
				}
				//checks if rectangle hits textbox that says "finish" and all circles are gone
				//if true the game is won
			} else if (j.getName().equals("TextBox")) {
				for (double k = (i.getX() - i.getLength() / 2); k < i.getX() + i.getLength() / 2; k++) {
					double l = i.getY() - i.getHeight() / 2;
					if (j.checkIfShape(k, l)) {
						if (j.getText().equals("FINISH")) {
							int countCircle = 0;
							for (Shapes p : shapes) {
								if (p.getName().equals("Circle")) {
									countCircle = countCircle + 1;
								}
							}
							if (countCircle == 0) {
								gameComplete = true;
								break;
							}
						}
						else if(j.getText().equals("TRAP")) {
							youLose = true;
						}
					}
				}
				for (double k = (i.getX() - i.getLength() / 2); k < i.getX() + i.getLength() / 2; k++) {
					double l = i.getY() + i.getHeight() / 2;
					if (j.checkIfShape(k, l)) {
						if (j.getText().equals("FINISH")) {
							int countCircle = 0;
							for (Shapes p : shapes) {
								if (p.getName().equals("Circle")) {
									countCircle = countCircle + 1;
								}
							}
							if (countCircle == 0) {
								gameComplete = true;
								break;
							}
						}
						else if(j.getText().equals("TRAP")) {
							youLose = true;
						}
					}
				}
				for (double k = (i.getY() + i.getHeight() / 2); k > i.getY() - i.getHeight() / 2; k--) {
					double l = i.getX() - i.getLength() / 2;
					if (j.checkIfShape(l, k)) {
						if (j.getText().equals("FINISH")) {
							int countCircle = 0;
							for (Shapes p : shapes) {
								if (p.getName().equals("Circle")) {
									countCircle = countCircle + 1;
								}
							}
							if (countCircle == 0) {
								gameComplete = true;
								break;
							}
						}
						else if(j.getText().equals("TRAP")) {
							youLose = true;
						}
					}
				}
				for (double k = (i.getY() + i.getHeight() / 2); k > i.getY() - i.getHeight() / 2; k--) {
					double l = i.getX() + i.getLength() / 2;
					if (j.checkIfShape(l, k)) {
						if (j.getText().equals("FINISH")) {
							int countCircle = 0;
							for (Shapes p : shapes) {
								if (p.getName().equals("Circle")) {
									countCircle = countCircle + 1;
								}
							}
							if (countCircle == 0) {
								gameComplete = true;
								break;
							}
						}
						else if(j.getText().equals("TRAP")) {
							youLose = true;
						}
					}
				}
			}

		}
		if (circle != null) {
			shapes.remove(circle);
		}
		return goingHit;
	}

	// sets color for shapes
	public void setColor() {
		color = JColorChooser.showDialog(UI.getFrame(), "Pick a Color", Color.black);
	}

	// sets shapes
	public void setCircle() {
		currentShape = "Circle";
		mode = "draw";
	}

	public void setRect() {
		currentShape = "Rectangles";
		mode = "draw";
	}

	public void setLine() {
		currentShape = "Lines";
		mode = "draw";
	}

	public void setTextBox() {
		currentShape = "TextBox";
		mode = "draw";
	}

	// sets remove mode
	public void setRemove() {
		mode = "remove";
	}

	// sets move mode
	public void setMove() {
		mode = "move";
	}

	// sets draw mode
	public void setDraw() {
		mode = "draw";

	}

	// sets draw mode
	public void setResize() {
		mode = "resize";

	}
	
	//activates game mode
	public void playGame() {
		UI.initialise();

		readDraw("maze.txt");
		UI.setKeyListener(this::doKey);
		UI.setMouseMotionListener(this::doMouse);
	}

	// sets color mode
	public void setColorMode() {
		mode = "color";
		color = JColorChooser.showDialog(UI.getFrame(), "Pick a Color", Color.black);
	}

	// set whether fill or outline shape
	public void setFill() {
		UI.clearText();
		String askfill = UI.askString("Fill shape or Outline?");

		if (askfill.equalsIgnoreCase("Fill")) {
			fill = true;
		} else if (askfill.equalsIgnoreCase("Outline")) {
			fill = false;
		} else
			UI.println("Invalid selection, must be Fill or Outline");
	}

//		sets font size
	public void setFontSize() {
		UI.clearText();
		int size = UI.askInt("Enter font size");
		if (size > 0 && size <= 50) {
			fontSize = size;
		} else
			UI.println("Invalid font size, must be between 0 and 50.");
	}

	public UserInterface() {

		UI.initialise();
//		drawGridBoard();
		UI.addButton("Draw mode", this::setDraw);
		UI.addButton("Move Mode", this::setMove);
		UI.addButton("Remove Mode", this::setRemove);
		UI.addButton("Change Color Mode", this::setColorMode);
		UI.addButton("Resize shapes Mode", this::setResize);

		UI.addButton("Draw Circles", this::setCircle);
		UI.addButton("Draw Lines", this::setLine);
		UI.addButton("Draw Rectangle", this::setRect);
		UI.addButton("Draw Text Box", this::setTextBox);

		UI.addButton("Select color", this::setColor);
		UI.addButton("Set fill or outline", this::setFill);
		UI.addButton("Set fontsize", this::setFontSize);

		UI.addButton("Clear all", this::clearGraphics);
		UI.addButton("Save", this::saveShapes);
		UI.addButton("Read and Draw File", this::readDrawSelect);

		UI.addButton("Let's Play A Game!", this::playGame);

		UI.setLineWidth(2);

		UI.setMouseMotionListener(this::doMouse);

	}

	public static void main(String[] args) {
		new UserInterface();

	}

}

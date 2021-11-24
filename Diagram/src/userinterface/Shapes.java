package userinterface;

import java.awt.Color;

public interface Shapes {
	public void draw();
	public boolean checkIfShape(double x, double y);
	
	public String getName();
	public double getLength();
	public double getHeight();
	public boolean getFill();
	public Color getColor();
	public String getText();
	public int getFontSize();
	public double getX1();
	public double getX2();
	public double getY1();
	public double getY2();
	public String toString();
	public double getX();
	public double getY();
	
}

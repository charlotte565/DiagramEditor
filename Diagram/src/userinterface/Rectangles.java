package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Rectangles implements Shapes{
	private String name;
	private double x;
	private double y;
	private double length;
	private double height;
	private boolean fill;
	private Color color;
	
	public Rectangles (double x, double y, double length, double height, boolean fill, Color color) {
		this.setName("Rectangles");
		this.x = x;
		this.y = y;
		this.height = height;
		this.length = length;	
		this.fill = fill;
		this.color = color;
	}
	
	@Override
	public void draw() {
		if(fill) {
			UI.setColor(color);
			UI.fillRect(x - length/2, y - height/2, length, height);
		}
		else {
			UI.setColor(color);
			UI.drawRect(x - length/2, y - height/2, length, height);
		}
		
	}
	
	@Override
	public boolean checkIfShape(double clickx, double clicky) {
		double diffy = Math.abs(clicky - y) ;
		double diffx = Math.abs(clickx - x);
		if(diffx <= length/2 && diffy <= height/2) {
			return true;
		}
		
		
		return false;		
		
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean getFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}







	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int getFontSize() {
		// TODO Auto-generated method stub
		return 0;
	}







	public void setName(String name) {
		this.name = name;
	}







	@Override
	public double getX1() {
		// TODO Auto-generated method stub
		return 0;
	}







	@Override
	public double getX2() {
		// TODO Auto-generated method stub
		return 0;
	}







	@Override
	public double getY1() {
		// TODO Auto-generated method stub
		return 0;
	}







	@Override
	public double getY2() {
		// TODO Auto-generated method stub
		return 0;
	}







	@Override
	public String toString() {
		return "Rectangles " + x + " " + y + " " + length + " " + height
				+ " " + fill + " " + color.getBlue()+ " " + color.getGreen()+ " " + color.getRed() ;
	}

}

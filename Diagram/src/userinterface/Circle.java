package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Circle implements Shapes{
	private String name;
	private double x;
	private double y;
	private double length;
	private boolean fill;
	private Color color;
	
	public Circle (double x, double y, double length,  boolean fill, Color color) {
		this.setName("Circle");
		this.x = x;
		this.y = y;
		this.length = length;
		this.fill = fill;
		this.color = color;
	}
	
	public void draw() {
		if(fill) {
			UI.setColor(color);
			UI.fillOval(x - length/2, y - length/2, length, length);
		}
		else {
			UI.setColor(color);
			UI.drawOval(x - length/2, y - length/2, length, length);
			
		}
		
	}
	

	
	@Override
	public boolean checkIfShape(double clickx, double clicky) {

		
		double a = clickx - x;
		double b = clicky - y;
		
		double resultC = Math.sqrt(a*a + b*b);
		
		if(resultC <= length/2) {

		return true;
		
		
		}
		return false;
		
		
	}

	

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

//	public double getHeight() {
//		return height;
//	}
//
//	public void setWidth(double height) {
//		this.height = height;
//	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
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

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
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
		return "Circle " + x + " " + y + " " + length + " " + fill + " "
				+ color.getBlue()+ " " + color.getGreen()+ " " + color.getRed() ;
	}




	
	
}

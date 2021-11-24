package userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JTextField;

import ecs100.UI;

public class TextBox implements Shapes{
	private String name;
	private double x;
	private double y;
	private String text;
	private int fontSize;
	private Color color;
	private Boolean fill;
	int textLength;
	double rectLength;
	double rectHeight;
	
	
	public TextBox (double x, double y, String text, int fontSize, Color color,Boolean fill) {
		this.setName("TextBox");
		this.x = x;
		this.y = y;
		this.text = text;
		this.fontSize = fontSize;
		this.fill = fill;
		this.color = color;
		

	}

	@Override
	public void draw() {
		
		
		//I found this code online to calculate how long a printed string was
		//https://www.codegrepper.com/code-examples/java/how+to+get+the+width+and+height+of+a+string+in+java
		UI.setFontSize(fontSize);
		Font font = UI.getGraphics().getFont(); //new Font("Dialog", Font.PLAIN, fontSize);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		int textWidth = (int)(font.getStringBounds(text, frc).getWidth());
		int textHeight = (int)(font.getStringBounds(text, frc).getHeight());
		
		
		double rectLength = textWidth + 2;
		double rectHeight = textHeight;
		
		
			UI.setColor(color);
			
			//draw box around letters
			UI.drawRect(x - 2, y -rectHeight * 0.75, rectLength, rectHeight);
			//draw string inside box
			UI.drawString(text, x , y);
		
	}
	
	@Override
	public boolean checkIfShape(double clickx, double clicky) {
		int textLength = text.length();
		double rectLength = textLength * fontSize/2;
		double rectHeight = fontSize/0.75 ;
		
		double diffy = Math.abs(clicky - (y -rectHeight * 0.75 +rectHeight/2)) ;
		double diffx = Math.abs(clickx - (x -2 + rectLength/2) );
		if(diffx <= rectLength/2 && diffy <= rectHeight/2) {
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


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getFontSize() {
		return fontSize;
	}


	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getFill() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
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
		return "TextBox " + x + " " + y + " " + text + " " + fontSize + " " + color.getBlue() + " "+ color.getGreen()+ " " + color.getRed()
				+ " " + fill ;
	}



	
}

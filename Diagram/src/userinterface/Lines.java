package userinterface;

import java.awt.Color;
import java.lang.reflect.Array;

import ecs100.UI;

public class Lines implements Shapes{
	private String name;
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	

	private Color color;
//	double line[][][];
//	double numberPoints;
//	double lengthy;
	
	public Lines(double x1, double y1, double x2, double y2 , Color color) {
		this.setName("Lines");
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

		this.color = color;
		
//		double numberPoints = Math.abs(x2 - x1);
//		double lengthy = Math.abs(y2 - y1);
//		
//		line = new double [(int) numberPoints][1][2];
//		addToLine(numberPoints, lengthy);
	}
	
	@Override
	public void draw() {
			UI.setColor(color);
			UI.drawLine(x1, y1, x2, y2);

		
	}
	
	@Override
	public boolean checkIfShape(double clickx, double clicky) {
		
		double j = y1;
		double m = (y2 - y1)/(x2 - x1);
		double k = 5;
		
		double c = y1 - (m * x1);
		
		//range set to ensure an even iteration across x values
		double range = (Math.abs(x2 - x1))/50;
		
		if(x1 < x2) {
		for(double i = x1; i <= x2 ; ) {
			if(clickx <= (i + k) && clickx >= (i - k) && clicky >= (j - k) && clicky <= (j + k)) {
				return true;
			}
			j = i*m + c;		
			i = i + range;
		}		
		}
		else if(x1 > x2) {
		for(double i = x1; i >= x2;  ) {
			if(clickx <= (i + k) && clickx >= (i - k) && clicky >= (j - k) && clicky <= (j + k)) {
				return true;
			}
			j = i*m + c;	
			
			i = i - range;
			
		}		
		}
		//these two else ifs cover the situation if the line is vertical
		else if(x1 == x2 && y1 < y2) {
			for(double i = y1; i <= y2 ; i ++) {
				if(clickx <= (x1 + k) && clickx >= (x1 - k) && clicky >= (i - k) && clicky <= (i + k)) {
					return true;
				}
			}
		}
		else if(x1 == x2 && y2 < y1) {
			for(double i = y2; i <= y1 ; i ++) {
				if(clickx <= (x1 + k) && clickx >= (x1 - k) && clicky >= (i - k) && clicky <= (i + k)) {
					return true;
			}
		}
		}
		else if(x1==x2 && y1==y2) {
			if(clickx <= (x1 + k) && clickx >= (x1 - k) && clicky >= (y1 - k) && clicky <= (y1 + k)) {
				return true;
		}
		}
		
		return false;		
		
		
	}
	
	public Lines getline(Lines line) {
		
		return line;
	}
	

	public double getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY(int y1) {
		this.y1 = y1;
	}
	
	public double getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

//	public double getLength() {
//		return length;
//	}
//
//	public void setLength(double length) {
//		this.length = length;
//	}

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
	public String toString() {
		return "Lines " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.getBlue() + " "+ color.getGreen()+ " " + color.getRed() ;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

//	public double[][][] getLine() {
//		return line;
//	}
//
//	public void setLine(double[][][] line) {
//		this.line = line;
//	}
	
//	public void addToLine(double numberPoints, double lengthy) {
//		line = new double [(int) numberPoints][1][2];
//		for (int i = 0; i < numberPoints ; i++) {
//			line[i][0][0] = x1 + i;
//			UI.println(lengthy/numberPoints);
//			UI.println(lengthy/numberPoints*i);
//			line[i][1][0] = y1 + ((lengthy/numberPoints)*i) -1;
//			line[i][0][1] = x1 + i;
//			line[i][1][1] = y1 + (lengthy/numberPoints)*i;
//			line[i][0][2] = x1 + i;
//			line[i][1][2] = y1 + ((lengthy/numberPoints)*i) + 1;
// 		}
//		
//	}


	
}

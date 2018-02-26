package calc;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Grapher extends JPanel {
	int ySize=10;
	int xSize=10;
	Graphics g;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
		// Draw Vertical tic lines
		int j;
		int k;
		g.setColor(Color.gray);
		for(int i=0;i<600/ySize;i++) {
			j=i*ySize;
			k=i*xSize;
			g.drawLine(j, 0, j, 600);
			g.drawLine(0,k,600,k);
		}
		
		// Vertical Line
		g.setColor(Color.RED);
		g.drawLine(300, 0, 300, 600);
		
		
		// Horizontal Line
		g.drawLine(0,300, 600, 300);
		g.drawString("X Axis", 1, 299);
		g.drawString("Y Axis", 301, 10);
		g.drawOval(300, 300, 1, 1);
	}
	
	public void setDimensions(int ySize, int xSize) {
		this.ySize = ySize;
		this.xSize = xSize;
	}
	
}

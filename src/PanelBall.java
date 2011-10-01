
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;

public class PanelBall extends JPanel{
	/**
	 * @author Anh
	 */
	private static final long serialVersionUID = 1L;
	private static int width = 50;
	private static int height = 50;
	private int[] m;
	public void set(int[] m){
		this.m = m;
	}
	public void paint(Graphics gra){
		if (m == null) return;
		Graphics2D g = (Graphics2D)gra;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints(rh);
		g.clearRect(0, 0, getWidth(), getHeight());
		int startX = 10;
		int startY = 10;
		for (int i = 0; i < m.length; i++){
			g.setColor(Color.BLACK);
			g.drawRect(startX, startY, width, height);
			g.drawRect(startX+1, startY+1, width-2, height-2);
			if (m[i] == 1) g.setColor(Color.BLUE);
			else if (m[i]==0) {startX+=width; continue;}
			else g.setColor(Color.RED);
			g.fillOval(startX+10, startY+10 , width-20, height-20);
			startX += width;
		}
	}
}

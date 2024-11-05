package enshu2_report2;

import java.awt.Color;
import java.awt.Graphics;

public class Figure extends Coord {
	Color color;
	int w, h;
	int fill;
	Figure(){
		color = new Color(0, 0, 0, 255); //不透明な黒色
		w = h = 0;
		fill = 1;
	}
	
	public void paint(Graphics g) {}
	public void setWH(int w, int h) {
		this.w = w;
		this.h = h;
	}
	public void getColor(int r, int g, int b, int a) {
		color = new Color(r, g, b, a);
	}
}
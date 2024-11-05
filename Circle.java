package enshu2_report2;

import java.awt.Graphics;

public class Circle extends Figure {
	int dx, dy;
	Circle(){
		dx = dy = 0;
	}
	
	@Override public void paint(Graphics g) {
		g.setColor(color);
		if(fill == 1) { //塗り潰し
			g.fillOval(dx, dy, w, h);
		}else if(fill == 0) { //枠線
			g.drawOval(dx, dy, w, h);
		}
	}
	@Override public void setWH(int w, int h) {
		double root = Math.sqrt(2);
		if(w >= 0) {
			dx = x - (int)(w * (root - 1) / 2);
			this.w = (int)(w * root);
		}else {
			dx = x + (int)(w * (root + 1) / 2);
			this.w = (int)(-w * root);
		}
		if(h >= 0) {
			dy = y - (int)(h * (root - 1) / 2);
			this.h =(int)(h * root);
		}else {
			dy = y + (int)(h * (root + 1) / 2);
			this.h = (int)(-h * root);
		}
	}
}

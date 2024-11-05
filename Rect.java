package enshu2_report2;

import java.awt.Graphics;

public class Rect extends Figure {
	int dx, dy;
	Rect(){
		dx = dy = 0;
	}
	
	@Override public void paint(Graphics g) {
		g.setColor(color);
		if(fill == 1) { //塗り潰し
			g.fillRect(dx, dy, w, h);
		}else if(fill == 0) { //枠線
			g.drawRect(dx, dy, w, h);
		}
	}
	@Override public void setWH(int w, int h) {
		dx = x;
		dy = y;
		if(w >= 0) {
			this.w = w;
		}else {
			dx += w;
			this.w = -w;
		}
		if(h >= 0) {
			this.h = h;
		}else {
			dy += h;
			this.h = -h;
		}
	}
}
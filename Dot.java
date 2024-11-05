package enshu2_report2;

import java.awt.Graphics;

public class Dot extends Figure {
	int size;
	public Dot(){
		size = 10;
	}
	
	@Override public void paint(Graphics g) {
		g.setColor(color);
		if(fill == 1) { //塗り潰しが選択されている場合
			g.fillOval(x - size / 2, y - size / 2, size, size);
		}else if(fill == 0) { //枠線
			g.drawOval(x - size / 2, y - size / 2, size, size);
		}
	}
}
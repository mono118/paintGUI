package enshu2_report2;

import java.io.Serializable;

public class Coord implements Serializable {
	int x, y;
	Coord(){
		x = y = 0;
	}
	
	public void moveto(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
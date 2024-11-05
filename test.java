package enshu2_report2;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test {
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(300, 100);
		f.add(new MyScrollbar());
		f.addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setVisible(true);
	}
}

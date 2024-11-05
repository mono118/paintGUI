package enshu2_report2;

import java.awt.Button;
import java.awt.FlowLayout;
/*import javax.swing.JButton;
import javax.swing.JFrame;

class MyFrame extends JFrame {
	MyFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new JButton("ボタン"));
		setSize(330, 200);
		setVisible(true);
	}
}
*/
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ButtonTest{
	public static void main(String[] args) {
		new MyFrame();
	}
}
class MyFrame extends Frame{
	MyFrame(){
		setSize(300, 200);
		setTitle("Paint");
		Panel p = new Panel();
		MenuBar mb = new MenuBar();
		Menu file = new Menu("ファイル");
		Menu figure = new Menu("描画");
		MenuItem m1 = new MenuItem("1");
		MenuItem m2 = new MenuItem("2");
		MenuItem m3 = new MenuItem("3");
		MenuItem m4 = new MenuItem("4");
		file.add(m1);
		file.add(m2);
		figure.add(m3);
		figure.add(m4);
		mb.add(file);
		mb.add(figure);
		setMenuBar(mb);
		p.setLayout(new FlowLayout());
		p.add(new Button("1"));
		p.add(new Button("2"));
		//add(p);
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
	}
}
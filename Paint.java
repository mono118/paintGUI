package enshu2_report2;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Paint extends Frame implements MouseListener, MouseMotionListener{
	int x, y;
	ArrayList<Figure> objList, garbageList;
	Figure obj;
	int mode = 0;
	static Paint paint;
	
	public static void main(String[] args) {
		Paint f = new Paint();
 		f.setSize(640, 480);
		f.setTitle("Paint");
		f.addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setVisible(true);
		new ControlPanel();
		if(args.length == 1) {
			f.load(args[0]); //ファイル読み込み
		}
	}
	Paint(){
		objList = new ArrayList<Figure>();
		garbageList = new ArrayList<Figure>();
		paint = this;
		//メニューバーの追加
		setMenuBar(new MyMenuBar().mb);
		//マウス処理の登録
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override public void paint(Graphics g) {
		Figure f;
		for(int i = 0; i < objList.size(); i++) {
			f = objList.get(i);
			f.paint(g);
		}
		if(mode >= 1) {
			obj.paint(g);
		}
	}
	@SuppressWarnings("unchecked")
	public void load(String fname) { //読み込み
		try {
			FileInputStream fis = new FileInputStream(fname);
			ObjectInputStream ois = new ObjectInputStream(fis);
			objList = (ArrayList<Figure>)ois.readObject();
			ois.close();
			fis.close();
		}catch(IOException e) {
			System.out.println(e);
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		repaint();
	}
	public void save(String fname) { //保存
		try {
			FileOutputStream fos = new FileOutputStream(fname);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objList);
			oos.close();
			fos.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	@Override public void mousePressed(MouseEvent e) {
		String s, c;
		x = e.getX();
		y = e.getY();
		s = MyCheckbox.cbf.getSelectedCheckbox().getLabel(); //選択した図形のチェックボックスのラベルを取得
		c = MyCheckbox.cbc.getSelectedCheckbox().getLabel(); //選択した描画設定のチェックボックスのラベルを取得
		obj = null;
		if(s == "丸") { //ラベルによって図形を設定
			mode = 1;
			obj = new Dot();
		}else if(s == "円") {
			mode = 2;
			obj = new Circle();
		}else if(s == "四角") {
			mode = 2;
			obj = new Rect();
		}else if(s == "線") {
			mode = 2;
			obj = new Line();
		}
		//塗り潰し選択
		if(c == "塗り潰し") {
			obj.fill = 1;
		}else if(c == "枠線") {
			obj.fill = 0;
		}
		//描画
		if(obj != null) {
			obj.getColor(MyScrollbar.r, MyScrollbar.g, MyScrollbar.b, MyScrollbar.a); //スクロールバーで選択した色を参照
			obj.moveto(x, y);
			repaint();
		}
	}
	@Override public void mouseReleased(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if(mode == 1) {
			obj.moveto(x, y);
		}else if(mode == 2) {
			obj.setWH(x - obj.x, y - obj.y);
		}
		if(mode >= 1) {
			objList.add(obj);
			obj = null;
		}
		mode = 0;
		repaint();
	}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if(mode == 1) {
			obj.moveto(x, y);
		}else if(mode == 2) {
			obj.setWH(x - obj.x, y - obj.y);
		}
		repaint();
	}
	@Override public void mouseMoved(MouseEvent e) {}
}

class MyMenuBar extends MenuBar implements ActionListener {
	MenuBar mb;
	Menu file, panel;
	MenuItem save, read, end, cp;
	Paint paint = Paint.paint;
	FileDialog fd;
	MyMenuBar(){
		mb = new MenuBar();
		file = new Menu("ファイル");
		panel = new Menu("パネル");
		//メニューアイテムの設定
		save = new MenuItem("保存");
		read = new MenuItem("読込");
		end = new MenuItem("終了");
		cp = new MenuItem("操作パネル");
		//アクションリスナーの登録
		save.addActionListener(this);
		read.addActionListener(this);
		end.addActionListener(this);
		cp.addActionListener(this);
		//メニューアイテムの追加
		file.add(save);
		file.add(read);
		file.add(end);
		panel.add(cp);
		mb.add(file);
		mb.add(panel);
	}
	@Override public void actionPerformed(ActionEvent e) { //終了ボタン
		String cmdName = e.getActionCommand();
		if("終了" == cmdName) {
			paint.save("paint.dat");
			System.exit(0);
		}else if("操作パネル" == cmdName){
			if(!ControlPanel.panelStatas) {
				new ControlPanel();
			}
		}else {
			if("読込" == cmdName) {
				fd = new FileDialog(new Frame("File select"),"ファイル選択", FileDialog.LOAD);
				fd.setVisible(true);
				if(fd.getFile() != null) {
					paint.load(fd.getFile());
				}
			}else if("保存" == cmdName) {
				fd = new FileDialog(new Frame("File select"),"ファイル選択", FileDialog.SAVE);
				fd.setVisible(true);
				if(fd.getFile() != null) {
					paint.save(fd.getFile());
				}
			}
		}
	}
}
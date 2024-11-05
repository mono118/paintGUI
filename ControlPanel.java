package enshu2_report2;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControlPanel extends Frame {
	static boolean panelStatas = false; //このウィンドウの表示状態を管理
	ControlPanel(){
		//ウィンドウの設定
		setSize(400, 300);
		setTitle("操作パネル");
		setLayout(new GridLayout(4, 1));
		add(new MyCheckbox());
		add(new MyButton());
		add(new MyScrollbar());
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) { //ウィンドウを閉じる
				dispose();
				panelStatas = false;
			}
		});
		setVisible(true);
		panelStatas = true;
	}
}
class MyCheckbox extends Panel {
	static CheckboxGroup cbf, cbc;
	Checkbox f1, f2, f3, f4, fill, draw;
	MyCheckbox(){
		setLayout(new FlowLayout());
		cbf = new CheckboxGroup();
		cbc = new CheckboxGroup();
		//図形選択ボタン
		f1 = new Checkbox("丸", cbf, true);
		f2 = new Checkbox("円", cbf, false);
		f3 = new Checkbox("四角", cbf, false);
		f4 = new Checkbox("線", cbf, false);
		//塗りつぶしの選択
		fill = new Checkbox("塗り潰し", cbc, true);
		draw = new Checkbox("枠線", cbc, false);
		//パネルにチェックボックスを追加
		add(f1);
		add(f2);
		add(f3);
		add(f4);
		add(fill);
		add(draw);
	}
}
class MyButton extends Panel implements ActionListener {
	Button undo, redo, clear;
	Paint paint = Paint.paint; //Paintを取得
	MyButton(){
		setLayout(new FlowLayout());
		//undo, redo, clear ボタン
		undo = new Button("取り消し");
		redo = new Button("やり直し");
		clear = new Button("消去");
		//アクションリスナーの登録
		undo.addActionListener(this);
		redo.addActionListener(this);
		clear.addActionListener(this);
		//パネルに追加
		add(undo);
		add(redo);
		add(clear);
	}
	@Override public void actionPerformed(ActionEvent e) {
		String cmdName = e.getActionCommand(); //押されたボタンのラベルを取得
		if("取り消し" == cmdName) {
			if(paint.objList.size() != 0) {
				paint.garbageList.add(paint.objList.remove(paint.objList.size() - 1));
				paint.repaint();
			}
		}else if("やり直し" == cmdName) {
			if(paint.garbageList.size() != 0) {
				paint.objList.add(paint.garbageList.remove(paint.garbageList.size() - 1));
				paint.repaint();
			}
		}else if("消去" == cmdName) {
			paint.objList.clear();
			paint.garbageList.clear();
			paint.repaint();
		}
	}
}
class MyScrollbar extends Panel implements AdjustmentListener {
	Scrollbar redSlid, greenSlid, blueSlid, alphaSlid;
	static int r = 0, g = 0, b = 0, a = 255;
	Panel c; //現在の色の確認用
	MyScrollbar(){
		setLayout(new GridLayout(1, 2));
		c = new Panel();
		Panel p = new Panel();
		c.setBackground(new Color(r, g, b));
		p.setLayout(new GridLayout(4, 1));
		//スライドバーの設定
		redSlid = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		greenSlid = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		blueSlid = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		alphaSlid = new Scrollbar(Scrollbar.HORIZONTAL, 255, 1, 0, 256);
		//アクションリスナーの登録
		redSlid.addAdjustmentListener(this);
		greenSlid.addAdjustmentListener(this);
		blueSlid.addAdjustmentListener(this);
		alphaSlid.addAdjustmentListener(this);
		//パネルに追加
		p.add(redSlid);
		p.add(greenSlid);
		p.add(blueSlid);
		p.add(alphaSlid);
		add(c);
		add(p);
	}
	@Override public void adjustmentValueChanged(AdjustmentEvent e) {
		//スライドバーの値を取得
		r = redSlid.getValue();
		g = greenSlid.getValue();
		b = blueSlid.getValue();
		a = alphaSlid.getValue();
		c.setBackground(new Color(r, g, b));
		c.paintComponents(getGraphics()); //cのみを再描画
	}
}
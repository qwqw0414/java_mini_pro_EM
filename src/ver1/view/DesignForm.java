package ver1.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DesignForm extends JFrame {

	// 문항수
	private final int MAXNUM = 10;

	// 크기
	private final int MARGINHEIGHT = 40;

	// 배경색
	private final Color COL_MARGIN = new Color(150, 200, 200);
	private final Color COL_BACKGROUND = new Color(255, 255, 255);
	private final Color COL_LABEL = new Color(255, 200, 200);
	private final Color COL_SUB = new Color(234, 234, 234);

	// 글자색
	private final Color COL_PINK = new Color(207, 61, 130);
	private final Color COL_DARKPINK = new Color(84, 0, 33);
	private final Color COL_CHECKED = new Color(153, 0, 76);
	private final Color COL_WARNING = new Color(237, 0, 109);

	// 폰트
	private final String FONT = "맑은 고딕";
	private final Font FONT_A = new Font(FONT, Font.BOLD, 50);
	private final Font FONT_B = new Font(FONT, Font.BOLD, 30);
	private final Font FONT_ANSWER = new Font(FONT, Font.BOLD, 30);
	private final Font FONT_20 = new Font(FONT, Font.BOLD, 20);
	private final Font FONT_TITLE = new Font(FONT, Font.BOLD, 100);
	private final Font FONT_Q = new Font(FONT, Font.BOLD, 40);

	// 라벨
	public void labelInOut(JLabel label, Color on, Color off) {
		label.setOpaque(true);
		label.setBackground(off);
		label.addMouseListener(new LabelButtonListener(label, on, off));
	}

	// 리스너
	public class LabelButtonListener extends MouseAdapter {
		JLabel label;
		Color off;
		Color on;

		public LabelButtonListener(JLabel label, Color on, Color off) {
			this.label = label;
			this.off = off;
			this.on = on;
		}

		public void mouseEntered(MouseEvent e) {
			label.setBackground(on);
		}

		public void mouseExited(MouseEvent e) {
			label.setBackground(off);
		}
	}

	// Getter
	public int getMARGINHEIGHT() {
		return MARGINHEIGHT;
	}

	public Font getFONT_ANSWER() {
		return FONT_ANSWER;
	}

	public Color getCOL_LABEL() {
		return COL_LABEL;
	}

	public Color getCHECKED() {
		return COL_CHECKED;
	}

	public Font getFONT_TITLE() {
		return FONT_TITLE;
	}

	public Color getCOL_MARGIN() {
		return COL_MARGIN;
	}

	public Color getCOL_BACKGROUND() {
		return COL_BACKGROUND;
	}

	public String getFONT() {
		return FONT;
	}

	public Color getCOL_DARKPINK() {
		return COL_DARKPINK;
	}

	public Font getFONT_Q() {
		return FONT_Q;
	}

	public Font getFONT_A() {
		return FONT_A;
	}

	public Color getCOL_CHECKED() {
		return COL_CHECKED;
	}

	public Color getCOL_WARNING() {
		return COL_WARNING;
	}

	public Color getCOL_PINK() {
		return COL_PINK;
	}

	public Font getFONT_20() {
		return FONT_20;
	}

	public Font getFONT_B() {
		return FONT_B;
	}

	public Color getCOL_SUB() {
		return COL_SUB;
	}

	public int getMAXNUM() {
		return MAXNUM;
	}

}

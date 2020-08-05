package ver1.common;

import javax.swing.JPanel;

import ver1.view.MainFrame;

public class MyUtill { // 공용 유틸 클래스

	public static void changePanel(MainFrame f, JPanel currentPanel, JPanel nextPanel) {
		
		f.remove(currentPanel);
		f.add(nextPanel);
		f.repaint();//화면에 다시 그리기 메소드
	}
}

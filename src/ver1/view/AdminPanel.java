package ver1.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ver1.common.MyUtill;

public class AdminPanel extends JPanel {
	
	private DesignForm df = new DesignForm();
	private MainFrame f;
	private LoginPanel lp;
	private JButton btnBack;
	private JPanel panel;
	private Font font;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnSelect;
	private JButton btnExam;

	public AdminPanel(MainFrame f, LoginPanel lp) {

		this.f = f;
		this.lp = lp;

		btnBack();
		panel();

		setBackground(df.getCOL_MARGIN());
		setBounds(0, 0, f.getWidth(), f.getHeight());

		add(btnBack);
	}

	public void panel() {
		panel = new JPanel();
		btnInsert = new JButton("수험번호 발급");
		btnDelete = new JButton("수험번호 취하");
		btnSelect = new JButton("시험결과 조회");
		btnExam = new JButton("출제문제 관리");

		panel.setLayout(null);

		// 리스너
		btnInsert.addActionListener(new btnListener());
		btnDelete.addActionListener(new btnListener());
		btnSelect.addActionListener(new btnListener());
		btnExam.addActionListener(new btnListener());

		// 배경, 글자색 설정
		panel.setBackground(new Color(255, 255, 255));
		btnInsert.setFont(df.getFONT_A());
		btnDelete.setFont(df.getFONT_A());
		btnSelect.setFont(df.getFONT_A());
		btnExam.setFont(df.getFONT_A());

		// 위치 크기 지정
		int marginHeight = 300;
		panel.setBounds(0, marginHeight, f.getWidth(), 550);
		btnInsert.setBounds(200, 100, 400, 150);
		btnDelete.setBounds(650, 100, 400, 150);
		btnSelect.setBounds(200, 300, 400, 150);
		btnExam.setBounds(650, 300, 400, 150);

		panel.add(btnInsert);
		panel.add(btnDelete);
		panel.add(btnSelect);
		panel.add(btnExam);
		add(panel);
	}

	public void btnBack() {
		btnBack = new JButton("Back");
		btnBack.setBounds(f.getWidth() - 100, 0, 100, 50);
		btnBack.addActionListener(new goLoginPanel());
	}

	public class btnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnInsert) {
				// 수험번호 발급창으로 이동
				MyUtill.changePanel(f, AdminPanel.this, new InsertPanel(f, AdminPanel.this));
			} else if (e.getSource() == btnDelete) {
				// 수험번호 취하창으로 이동
				MyUtill.changePanel(f, AdminPanel.this, new DeletePanel(f, AdminPanel.this));
			} else if (e.getSource() == btnExam) {
				// 시험 관리창으로 이동
				MyUtill.changePanel(f, AdminPanel.this, new UpdatePanel(f, AdminPanel.this));
			} else if( e.getSource() == btnSelect) {
				MyUtill.changePanel(f, AdminPanel.this, new SelectPanel(f, AdminPanel.this));
			}
		}
	}

	public class goLoginPanel implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MyUtill.changePanel(f, AdminPanel.this, lp);
		}
	}
}

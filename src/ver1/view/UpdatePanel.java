package ver1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import ver1.common.MyUtill;
import ver1.controller.ExamController;
import ver1.view.InsertPanel.btnBackListner;
import ver1.vo.model.Exam;

public class UpdatePanel extends JPanel {

	DesignForm df = new DesignForm();
	ExamController ec = new ExamController();
	private int maxPage = 9;
	private MainFrame f;
	private AdminPanel ap;
	private JButton btnReturn;
	private JPanel panel;
	private int page = 0;
	private JRadioButton ans1;
	private JRadioButton ans2;
	private JRadioButton ans3;
	private JRadioButton ans4;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txtSubject;
	private JLabel labelSubject;
	private ButtonGroup ansGroup;
	private JButton btnNext;
	private JButton btnBack;
	private JTextArea txtQ;
	private JLabel labelSubNum;
	private JLabel labelNum;
	private JButton btnSub1;
	private JButton btnSub2;
	private JButton btnSub3;
	private JButton btnSave;

	public UpdatePanel(MainFrame f, AdminPanel ap) {
		this.f = f;
		this.ap = ap;

		setSize(f.getWidth(), f.getHeight());
		setBackground(df.getCOL_MARGIN());

		btnBack();
		panel();

		setLayout(null);
		selectInfo(labelSubNum.getText(), page);
	}

	public void panel() {

		panel = new JPanel();

		JLabel labelQ = new JLabel();
		labelNum = new JLabel((page + 1) + " 번");
		txtQ = new JTextArea();
		btnNext = new JButton("다음");
		btnBack = new JButton("이전");
		ans1 = new JRadioButton("1)");
		ans2 = new JRadioButton("2)");
		ans3 = new JRadioButton("3)");
		ans4 = new JRadioButton("4)");
		txt1 = new JTextField();
		txt2 = new JTextField();
		txt3 = new JTextField();
		txt4 = new JTextField();
		txtSubject = new JTextField("수학");
		labelSubject = new JLabel("과목명 : ");
		ansGroup = new ButtonGroup();
		labelSubNum = new JLabel("" + 1);
		btnSave = new JButton("저장");

		// 리스너
		btnBack.addActionListener(new MyActionListner());
		btnNext.addActionListener(new MyActionListner());
		btnSave.addActionListener(new MyActionListner());

		// 버튼 그룹
		ansGroup.add(ans1);
		ansGroup.add(ans2);
		ansGroup.add(ans3);
		ansGroup.add(ans4);

		// 글자수 제한
		txtQ.setDocument(new JTextFieldLimit(80));
		txt1.setDocument(new JTextFieldLimit(12));
		txt2.setDocument(new JTextFieldLimit(12));
		txt3.setDocument(new JTextFieldLimit(12));
		txt4.setDocument(new JTextFieldLimit(12));
		txtSubject.setDocument(new JTextFieldLimit(8));

		// 색
		panel.setBackground(df.getCOL_BACKGROUND());
		labelQ.setOpaque(true);
		labelQ.setBackground(new Color(255, 200, 200));
		txtQ.setBackground(new Color(255, 200, 200));
		ans1.setBackground(df.getCOL_BACKGROUND());
		ans2.setBackground(df.getCOL_BACKGROUND());
		ans3.setBackground(df.getCOL_BACKGROUND());
		ans4.setBackground(df.getCOL_BACKGROUND());
		txt1.setBackground(df.getCOL_BACKGROUND());
		txt2.setBackground(df.getCOL_BACKGROUND());
		txt3.setBackground(df.getCOL_BACKGROUND());
		txt4.setBackground(df.getCOL_BACKGROUND());

		// 위치
		int marginHeight = 100;
		panel.setBounds(0, marginHeight, f.getWidth(), 750);
		btnNext.setBounds(750, 650, 200, 80);
		btnBack.setBounds(300, 650, 200, 80);
		btnSave.setBounds(525, 650, 200, 80);
		labelNum.setBounds(50, 0, 200, 100);
		labelQ.setBounds(0, 100, f.getWidth(), 250);
		txtQ.setBounds(50, 35, 1200, 200);
		ans1.setBounds(150, 400, 80, 100);
		ans2.setBounds(650, 400, 80, 100);
		ans3.setBounds(150, 500, 80, 100);
		ans4.setBounds(650, 500, 80, 100);
		txt1.setBounds(245, 405, 380, 90);
		txt2.setBounds(745, 405, 380, 90);
		txt3.setBounds(245, 505, 380, 90);
		txt4.setBounds(745, 505, 380, 90);
		labelSubject.setBounds(350, 0, 250, 100);
		txtSubject.setBounds(600, 0, 380, 100);
		labelSubNum.setBounds(300, 0, 80, 100);

		// 폰트
		labelNum.setFont(df.getFONT_A());
		btnNext.setFont(df.getFONT_A());
		btnBack.setFont(df.getFONT_A());
		btnSave.setFont(df.getFONT_A());
		txtQ.setFont(df.getFONT_A());
		ans1.setFont(df.getFONT_A());
		ans2.setFont(df.getFONT_A());
		ans3.setFont(df.getFONT_A());
		ans4.setFont(df.getFONT_A());
		txt1.setFont(df.getFONT_A());
		txt2.setFont(df.getFONT_A());
		txt3.setFont(df.getFONT_A());
		txt4.setFont(df.getFONT_A());
		txtSubject.setFont(df.getFONT_A());
		labelSubject.setFont(df.getFONT_A());
		labelSubNum.setFont(df.getFONT_A());

		panel.setLayout(null);
		add(panel);
		panel.add(btnNext);
		panel.add(btnBack);
		panel.add(btnSave);
		panel.add(labelQ);
		panel.add(labelNum);
		panel.add(txtQ);
		labelQ.add(txtQ);
		panel.add(ans1);
		panel.add(ans2);
		panel.add(ans3);
		panel.add(ans4);
		panel.add(txt1);
		panel.add(txt2);
		panel.add(txt3);
		panel.add(txt4);
		panel.add(txtSubject);
		panel.add(labelSubject);
		panel.add(labelSubNum);

	}

	public void btnBack() {
		btnReturn = new JButton("Back");
		btnSub1 = new JButton("과목 1");
		btnSub2 = new JButton("과목 2");
		btnSub3 = new JButton("과목 3");

		// 위치
		btnReturn.setBounds(f.getWidth() - 100, 0, 100, 50);
		btnSub1.setBounds(100, 20, 120, 50);
		btnSub2.setBounds(250, 20, 120, 50);
		btnSub3.setBounds(400, 20, 120, 50);

		// 폰트
		btnSub1.setFont(df.getFONT_20());
		btnSub2.setFont(df.getFONT_20());
		btnSub3.setFont(df.getFONT_20());

		// 리스너
		btnReturn.addActionListener(new btnBackListner());
		btnSub1.addActionListener(new subChangeListner());
		btnSub2.addActionListener(new subChangeListner());
		btnSub3.addActionListener(new subChangeListner());

		add(btnReturn);
		add(btnSub1);
		add(btnSub2);
		add(btnSub3);
	}

	public void updateInfo() {

		String examType = labelSubNum.getText();
		String examTitle = txtSubject.getText();
		int examNum = page;
		String question = txtQ.getText();
		String[] questionMark = { txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText() };
		int answer = 0;

		if (ans1.isSelected())
			answer = 1;
		else if (ans2.isSelected())
			answer = 2;
		else if (ans3.isSelected())
			answer = 3;
		else if (ans4.isSelected())
			answer = 4;

		ec.insertExamInfo(examType, examTitle, examNum, question, questionMark, answer);

	}

	public void selectInfo(String examType, int examNum) {

		Exam exam = ec.selectExamInfo(examType, examNum);

		txtSubject.setText(exam.getExamTitle());
		txtQ.setText(exam.getQuestion());
		String[] str = exam.getQuestionMark();
		txt1.setText(str[0]);
		txt2.setText(str[1]);
		txt3.setText(str[2]);
		txt4.setText(str[3]);

		switch (exam.getAnswer()) {
		case 1:
			ans1.doClick();
			break;
		case 2:
			ans2.doClick();
			break;
		case 3:
			ans3.doClick();
			break;
		case 4:
			ans4.doClick();
			break;
		}
	}

	public class MyActionListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// null 유효성 검사
			if (txtSubject.getText().length() == 0) {
				System.out.println("과목명 null");
				return;
			} else if (txtQ.getText().length() == 0) {
				System.out.println("문제 null");
				return;
			} else if (txt1.getText().length() * txt2.getText().length() * txt3.getText().length()
					* txt4.getText().length() == 0) {
				System.out.println("문항 null");
				return;
			} else if (ansGroup.getSelection() == null) {
				System.out.println("정답 null");
				return;
			}

			if (e.getSource() == btnNext) {
				if (page < maxPage) {
					updateInfo();
					page++;
					labelNum.setText(page + 1 + "번");
					selectInfo(labelSubNum.getText(), page);
				} else
					return;
			} else if (e.getSource() == btnBack) {
				if (page > 0) {
					updateInfo();
					page--;
					labelNum.setText(page + 1 + "번");
					selectInfo(labelSubNum.getText(), page);
				} else
					return;
			} else if (e.getSource() == btnSave) {
				updateInfo();
			}

		}

	}

	public class subChangeListner implements ActionListener {// 과목 변경

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSub1) {
				labelSubNum.setText(1 + "");
			} else if (e.getSource() == btnSub2) {
				labelSubNum.setText(2 + "");
			} else if (e.getSource() == btnSub3) {
				labelSubNum.setText(3 + "");
			}
			selectInfo(labelSubNum.getText(), page);
		}

	}

	public class btnBackListner implements ActionListener { // 뒤로가기

		@Override
		public void actionPerformed(ActionEvent e) {
			MyUtill.changePanel(f, UpdatePanel.this, ap);
		}
	}

	public class JTextFieldLimit extends PlainDocument { // 글자수 제한

		private int limit;

		public JTextFieldLimit(int limit) {
			this.limit = limit;
		}

		@Override
		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}
}

package ver1.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import ver1.common.MyUtill;
import ver1.controller.ExamController;
import ver1.controller.MarkingController;
import ver1.vo.model.Exam;

public class ExamPanel extends JPanel {

	private DesignForm df = new DesignForm();
	private ExamController ec = new ExamController();
	private MarkingController mc = new MarkingController();
	private int[] answer = new int[df.getMAXNUM()]; // 선답 저장 배열
	private String examType;
	private int examNum = 0;
	private MainFrame f;
	private SubjectPanel sp;
	private JPanel panel;
	private JPanel subPanel;
	private JLabel label;
	private JTextArea examQ;
	private JButton btnBack;
	private JButton btnNext;
	private JLabel btnAns1;
	private JLabel btnAns2;
	private JLabel btnAns3;
	private JLabel btnAns4;
	private JButton btnSubmit;
	private JLabel labelNum;
	private JButton[] btnList = new JButton[df.getMAXNUM()];
	private int selected = 0;
	private JPanel listPanel;
	private JPanel timePanel;
	private JLabel timeLabel;
	private Timer timer;
	private long timeLeft = 600_000L;
	private JOptionPane finish;
	private String userId;

	public ExamPanel(MainFrame f, SubjectPanel sp, String examType, String userId) {

		this.f = f;
		this.sp = sp;
		this.examType = examType;
		this.userId = userId;

		panel();
		subPanel();
		loadInfo();

		setBackground(df.getCOL_MARGIN());
		setBounds(0, 0, f.getWidth(), f.getHeight());
		setLayout(null);
	}

	public void subPanel() {
		subPanel = new JPanel();
		btnSubmit = new JButton("시험 종료");
		listPanel = new JPanel();

		subPanel.setLayout(null);
		listPanel.setLayout(new GridLayout(df.getMAXNUM(), 2));
		listPanel();

		// 리스너
		btnSubmit.addActionListener(new SubmitListener());

		// 색
		subPanel.setBackground(df.getCOL_SUB());
		listPanel.setBackground(df.getCOL_SUB());

		// 위치 지정
		subPanel.setBounds(1050, 0, 230, 900);
		btnSubmit.setBounds(0, 770, 225, 80);
		listPanel.setBounds(0, 85, 230, 600);

		// 폰트
		btnSubmit.setFont(df.getFONT_ANSWER());

		// 추가
		subPanel.add(btnSubmit);
		subPanel.add(listPanel);
		panel.add(subPanel);
	}

	public void listPanel() {

		for (int i = 0; i < df.getMAXNUM(); i++) {

			btnList[i] = new JButton("" + (i + 1) + "번");
			btnList[i].setBackground(df.getCOL_SUB());
			btnList[i].setBorderPainted(false);
			btnList[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < df.getMAXNUM(); j++) {
						if (e.getSource() == btnList[j]) {
							examNum = j;
							loadInfo();
						}
					}
				}
			});
			listPanel.add(btnList[i]);
			btnList[i].setFont(df.getFONT_A());
		}
	}

	public void changeBtn() {
		for (int i = 0; i < answer.length; i++) {
			if (answer[i] != 0) {
				btnList[i].setForeground(df.getCOL_DARKPINK());
			}
		}
	}

	public class countDown implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() != null) {
				timeLeft -= 2;
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
				timeLabel.setText(sdf.format(new Date(timeLeft)));

				if (timeLeft <= 0) {
					timer.stop();
					finish = new JOptionPane();
					finish.showMessageDialog(null, "시간초과");
					MyUtill.changePanel(f, ExamPanel.this, sp);
				}
			}
		}
	}

	public void panel() {
		panel = new JPanel();
		label = new JLabel();
		examQ = new JTextArea();
		btnBack = new JButton("이전");
		btnNext = new JButton("다음");
		btnAns1 = new JLabel();
		btnAns2 = new JLabel();
		btnAns3 = new JLabel();
		btnAns4 = new JLabel();
		labelNum = new JLabel();
		timePanel = new JPanel();
		timeLabel = new JLabel();
		timer = new Timer(1, new countDown());
		timer.start();
		timePanel.setLayout(null);
		panel.setLayout(null);
		label.setLayout(null);
		examQ.setEditable(false);

		// 리스너
		btnBack.addActionListener(new MyActionListener());
		btnNext.addActionListener(new MyActionListener());
		btnAns1.addMouseListener(new AnsCheckListener(btnAns1, df.getCHECKED(), 1));
		btnAns2.addMouseListener(new AnsCheckListener(btnAns2, df.getCHECKED(), 2));
		btnAns3.addMouseListener(new AnsCheckListener(btnAns3, df.getCHECKED(), 3));
		btnAns4.addMouseListener(new AnsCheckListener(btnAns4, df.getCHECKED(), 4));

		// 메소드
		df.labelInOut(btnAns1, df.getCOL_LABEL(), df.getCOL_BACKGROUND());
		df.labelInOut(btnAns2, df.getCOL_LABEL(), df.getCOL_BACKGROUND());
		df.labelInOut(btnAns3, df.getCOL_LABEL(), df.getCOL_BACKGROUND());
		df.labelInOut(btnAns4, df.getCOL_LABEL(), df.getCOL_BACKGROUND());

		// 배경색
		panel.setBackground(df.getCOL_BACKGROUND());
		label.setOpaque(true);
		label.setBackground(df.getCOL_LABEL());
		examQ.setBackground(df.getCOL_LABEL());

		// 글자색
		labelNum.setForeground(df.getCOL_DARKPINK());
		examQ.setForeground(df.getCOL_DARKPINK());

		// 폰트
		examQ.setFont(df.getFONT_Q());
		btnAns1.setFont(df.getFONT_ANSWER());
		btnAns2.setFont(df.getFONT_ANSWER());
		btnAns3.setFont(df.getFONT_ANSWER());
		btnAns4.setFont(df.getFONT_ANSWER());
		labelNum.setFont(df.getFONT_A());
		btnBack.setFont(df.getFONT_B());
		btnNext.setFont(df.getFONT_B());
		timePanel.setFont(df.getFONT_B());
		// 정렬
		btnAns1.setHorizontalAlignment(2);
		btnAns2.setHorizontalAlignment(2);
		btnAns3.setHorizontalAlignment(2);
		btnAns4.setHorizontalAlignment(2);
		labelNum.setHorizontalAlignment(4);
		timeLabel.setHorizontalAlignment(0);
		// 위치
		int marginHeight = df.getMARGINHEIGHT();
		panel.setBounds(0, marginHeight, f.getWidth(), 850);
		label.setBounds(0, 150, f.getWidth() - 230, 250);
		examQ.setBounds(20, 20, f.getWidth() - 280, 210);
		labelNum.setBounds(10, 80, 200, 80);
		btnBack.setBounds(800, 45, 100, 50);
		btnNext.setBounds(920, 45, 100, 50);
		int x = 90;
		int y = 500;
		btnAns1.setBounds(x, y, 400, 80);
		btnAns2.setBounds(x + 475, y, 400, 80);
		btnAns3.setBounds(x, y + 120, 400, 80);
		btnAns4.setBounds(x + 475, y + 120, 400, 80);

		// 타이머 세팅
		timePanel.setBounds(1050, 0, 220, 50);
		timeLabel.setBounds(0, 0, 220, 50);
		timeLabel.setFont(df.getFONT_A());
		timeLabel.setForeground(df.getCOL_DARKPINK());
		timePanel.setBackground(Color.gray);

		// 추가
		add(panel);
		timePanel.add(timeLabel);
		panel.add(timePanel);
		panel.add(label);
		panel.add(btnBack);
		panel.add(btnNext);
		panel.add(btnAns1);
		panel.add(btnAns2);
		panel.add(btnAns3);
		panel.add(btnAns4);
		panel.add(labelNum);
		label.add(examQ);
	}

	public void loadInfo() {

		Exam exam = ec.selectExamInfo(examType, examNum);
		String[] mark = exam.getQuestionMark();

		examQ.setText(exam.getQuestion());
		btnAns1.setText(" ⑴ " + mark[0]);
		btnAns2.setText(" ⑵ " + mark[1]);
		btnAns3.setText(" ⑶ " + mark[2]);
		btnAns4.setText(" ⑷ " + mark[3]);
		labelNum.setText(1 + examNum + " / " + df.getMAXNUM());

		btnAns1.setForeground(Color.black);
		btnAns2.setForeground(Color.black);
		btnAns3.setForeground(Color.black);
		btnAns4.setForeground(Color.black);

		selected = answer[examNum];
		if (selected == 1)
			btnAns1.setForeground(df.getCHECKED());
		if (selected == 2)
			btnAns2.setForeground(df.getCHECKED());
		if (selected == 3)
			btnAns3.setForeground(df.getCHECKED());
		if (selected == 4)
			btnAns4.setForeground(df.getCHECKED());
		changeBtn();
		reCheck();
	}

	public void reCheck() {
		if (selected == 1) {
			btnAns1.setText(" ▶" + btnAns1.getText().substring(2, btnAns1.getText().length()));
		} else
			btnAns1.setText(" ⑴" + btnAns1.getText().substring(2, btnAns1.getText().length()));
		if (selected == 2) {
			btnAns2.setText(" ▶" + btnAns2.getText().substring(2, btnAns2.getText().length()));
		} else
			btnAns2.setText(" ⑵" + btnAns2.getText().substring(2, btnAns2.getText().length()));
		if (selected == 3) {
			btnAns3.setText(" ▶" + btnAns3.getText().substring(2, btnAns3.getText().length()));
		} else
			btnAns3.setText(" ⑶" + btnAns3.getText().substring(2, btnAns3.getText().length()));
		if (selected == 4) {
			btnAns4.setText(" ▶" + btnAns4.getText().substring(2, btnAns4.getText().length()));
		} else
			btnAns4.setText(" ⑷" + btnAns4.getText().substring(2, btnAns4.getText().length()));
	}

	public class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i : answer) {
				if (i == 0) {
					System.out.println("문제를 모두 풀어");
					return;
				}
			}
			mc.marking(userId, examType, answer);
			sp.repaint();
			sp.btnLock();
			MyUtill.changePanel(f, ExamPanel.this, sp);
		}

	}

	public class AnsCheckListener extends MouseAdapter {
		JLabel label;
		int num;
		Color on;

		public AnsCheckListener(JLabel label, Color on, int num) {
			this.label = label;
			this.num = num;
			this.on = on;
		}

		public void mouseReleased(MouseEvent e) {
			if (num != selected) {
				btnAns1.setForeground(new Color(0, 0, 0));
				btnAns2.setForeground(new Color(0, 0, 0));
				btnAns3.setForeground(new Color(0, 0, 0));
				btnAns4.setForeground(new Color(0, 0, 0));

				selected = num;
				answer[examNum] = selected;
				changeBtn();
				reCheck();

				label.setForeground(on);
				System.out.println(Arrays.toString(answer));

				if (examNum < df.getMAXNUM() - 1) {
					examNum++;
					loadInfo();
				}
			}
		}
	}

	public class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnBack) {
				if (examNum > 0) {
					examNum--;
					loadInfo();
				} else
					return;
			} else if (e.getSource() == btnNext) {
				if (examNum < df.getMAXNUM() - 1) {
					examNum++;
					loadInfo();
				} else
					return;
			}
		}

	}
}

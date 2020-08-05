package ver1.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ver1.common.MyUtill;
import ver1.controller.ExamController;
import ver1.controller.UserController;
import ver1.vo.model.Exam;
import ver1.vo.model.User;

public class SubjectPanel extends JPanel {

	ExamController ec = new ExamController();
	private DesignForm df = new DesignForm();
	private MainFrame f;
	private JPanel panel;
	private String userId;
	private JButton btnSubject1;
	private JButton btnSubject2;
	private JButton btnSubject3;
	private JButton btnSubmit;
	private LoginPanel lp;

	public SubjectPanel(MainFrame f, LoginPanel lp, String userId) {
		this.f = f;
		this.lp = lp;
		this.userId = userId;
		panel();
		setLayout(null);
		setBackground(df.getCOL_MARGIN());
		setSize(getWidth(), getHeight());
		btnLock();

	}

	public void panel() {
		panel = new JPanel();
		btnSubject1 = new JButton();
		btnSubject2 = new JButton();
		btnSubject3 = new JButton();
		btnSubmit = new JButton("제출하기");

		panel.setLayout(null);

		// 폰트
		btnSubject1.setFont(df.getFONT_B());
		btnSubject2.setFont(df.getFONT_B());
		btnSubject3.setFont(df.getFONT_B());
		btnSubmit.setFont(df.getFONT_A());

		// 색
		panel.setBackground(df.getCOL_BACKGROUND());

		// 위치,크기
		int marginHeight = 100;
		panel.setBounds(0, marginHeight, f.getWidth(), 720);
		btnSubject1.setBounds(f.getWidth() / 2 - 300, 80, 600, 100);
		btnSubject2.setBounds(f.getWidth() / 2 - 300, 250, 600, 100);
		btnSubject3.setBounds(f.getWidth() / 2 - 300, 420, 600, 100);
		btnSubmit.setBounds(f.getWidth() / 2 - 200, 580, 370, 80);

		// 리스너
		btnSubmit.addActionListener(new MyActionListener());
		btnSubject1.addActionListener(new MyActionListener());
		btnSubject2.addActionListener(new MyActionListener());
		btnSubject3.addActionListener(new MyActionListener());

		// 과목명 설정
		btnSubject1.setText(ec.getExamTitle("1"));
		btnSubject2.setText(ec.getExamTitle("2"));
		btnSubject3.setText(ec.getExamTitle("3"));

		// 활성화
		btnSubmit.setEnabled(false);

		panel.add(btnSubmit);
		panel.add(btnSubject1);
		panel.add(btnSubject2);
		panel.add(btnSubject3);
		add(panel);
	}

	public void btnLock() {
		UserController uc = new UserController();
		User user = uc.selectUserId(userId);
		boolean[] examComp = user.getExamComp();

		if (examComp[0])
			btnSubject1.setEnabled(false);
		if (examComp[1])
			btnSubject2.setEnabled(false);
		if (examComp[2])
			btnSubject3.setEnabled(false);
		if (examComp[0] && examComp[1] && examComp[2])
			btnSubmit.setEnabled(true);
	}

	public class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnSubmit)
				MyUtill.changePanel(f, SubjectPanel.this, new ResultPanel(f, SubjectPanel.this, userId));

			else if (e.getSource() == btnSubject1)
				MyUtill.changePanel(f, SubjectPanel.this, new ExamPanel(f, SubjectPanel.this, "1", userId));

			else if (e.getSource() == btnSubject2)
				MyUtill.changePanel(f, SubjectPanel.this, new ExamPanel(f, SubjectPanel.this, "2", userId));

			else if (e.getSource() == btnSubject3)
				MyUtill.changePanel(f, SubjectPanel.this, new ExamPanel(f, SubjectPanel.this, "3", userId));

		}
	}
}

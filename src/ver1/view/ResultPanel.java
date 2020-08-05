package ver1.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ver1.common.MyUtill;
import ver1.controller.ExamController;
import ver1.controller.UserController;
import ver1.vo.model.User;

public class ResultPanel extends JPanel {

	private ExamController ec = new ExamController();
	private UserController uc = new UserController();
	private DesignForm df = new DesignForm();
	private MainFrame f;
	private Font font;
	private JButton btnReturn;
	private SubjectPanel sp;
	private LoginPanel lp;
	private String userId;
	private JLabel userResult;
	private JLabel subjectResult;
	private JLabel subjectResult1;
	private JLabel subjectResult2;
	private JLabel subjectResult3;

	public ResultPanel(MainFrame f, SubjectPanel sp, String userId) {
		this.f = f;
		this.sp = sp;
		this.userId = userId;

		panel();

		setLayout(null);
		setBackground(df.getCOL_MARGIN());
		setSize(f.getWidth(), f.getHeight());
	}

	public void panel() {
		User u = new User();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel resultHeader = new JLabel("시험 결과");
		userResult = new JLabel();
		subjectResult = new JLabel();
		subjectResult1 = new JLabel();
		subjectResult2 = new JLabel();
		subjectResult3 = new JLabel();

		btnReturn = new JButton("처음으로");

		userResult.setText(passOrFail());
		subjectResult1.setText(printResult(ec.getExamTitle("1"), 0));
		subjectResult2.setText(printResult(ec.getExamTitle("2"), 1));
		subjectResult3.setText(printResult(ec.getExamTitle("3"), 2));
		// 배경, 글자색 설정
		panel.setBackground(df.getCOL_BACKGROUND());
		// 글꼴
		resultHeader.setFont(df.getFONT_B());
		userResult.setFont(df.getFONT_ANSWER());
		subjectResult1.setFont(df.getFONT_ANSWER());
		subjectResult2.setFont(df.getFONT_ANSWER());
		subjectResult3.setFont(df.getFONT_ANSWER());
		btnReturn.setFont(df.getFONT_A());
		// 정렬
		resultHeader.setHorizontalAlignment(0);
		userResult.setHorizontalAlignment(0);
		subjectResult1.setHorizontalAlignment(0);
		subjectResult2.setHorizontalAlignment(0);
		subjectResult3.setHorizontalAlignment(0);
		// 위치, 크기 설정
		int marginHeight = 100;
		panel.setBounds(0, marginHeight, f.getWidth(), 760);
		resultHeader.setBounds(f.getWidth() / 2 - 200, 10, 400, 100);
		userResult.setBounds(f.getWidth() / 2 - 800 / 2, 100, 800, 130);
		subjectResult.setBounds(f.getWidth() / 2 - 800 / 2, 240, 800, 300);
		subjectResult1.setBounds(0, 20, 800, 65);
		subjectResult2.setBounds(0, 95, 800, 65);
		subjectResult3.setBounds(0, 165, 800, 65);
		btnReturn.setBounds(f.getWidth() / 2 - 300 / 2, 600, 300, 100);
		// 박스
		userResult.setBorder(new LineBorder(Color.BLACK, 1));
		subjectResult.setBorder(new LineBorder(Color.BLACK, 1));
//		subjectResult1.setBorder(new LineBorder(Color.BLACK, 1));
//		subjectResult2.setBorder(new LineBorder(Color.BLACK, 1));
//		subjectResult3.setBorder(new LineBorder(Color.BLACK, 1));

		btnReturn.addActionListener(new returnToLogin());

		panel.add(resultHeader);
		panel.add(btnReturn);
		panel.add(subjectResult);
		subjectResult.add(subjectResult1);
		subjectResult.add(subjectResult2);
		subjectResult.add(subjectResult3);
		panel.add(userResult);
		add(panel);
	}

	// 사용자 점수결과 확인
	public String printResult(String examTitle, int num) {
		User user = uc.selectUserId(userId);
		int index = uc.listLoad().indexOf(user);
		int[] examScore;
		boolean pass = true;

		examScore = user.getExamScore();
		user = uc.listLoad().get(index);

		if (num == 0 && examScore[num] < 40)
			subjectResult1.setForeground(df.getCOL_WARNING());
		else if (num == 1 && examScore[num] < 40)
			subjectResult2.setForeground(df.getCOL_WARNING());
		else if (num == 2 && examScore[num] < 40)
			subjectResult3.setForeground(df.getCOL_WARNING());

		return examTitle + ": " + examScore[num] + "점";

	}

	// 사용자의 합격 여부
	public String passOrFail() {
		User user = uc.selectUserId(userId);
		int index = uc.listLoad().indexOf(user);
		int[] examScore;
		double avg = 0;
		boolean pass = true;

		user = uc.listLoad().get(index);
		examScore = user.getExamScore();
		for (int s : examScore) {
			if (s < 40) {
				pass = false;
				break;
			}
			avg += s;
		}

		if (avg != 0)
			avg = avg / 3;

		// 합격 여부 검사후 저장
		if (avg >= 60 && pass) {

			List<User> userList = uc.listLoad();
			user.setAccept(true);
			userList.set(index, user);
			uc.listSave(userList);

			System.out.println(user);
			return user.getUserName() + "님 합격을 진심으로 축하드립니다.";
		}

		System.out.println(user);
		return user.getUserName() + "님은 불합격 하셨습니다.";
	}

	public class returnToLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			MyUtill.changePanel(f, ResultPanel.this, new LoginPanel(f));

		}

	}
}

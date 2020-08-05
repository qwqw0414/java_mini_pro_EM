package ver1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import ver1.common.MyUtill;
import ver1.controller.UserController;

public class LoginPanel extends JPanel {

	private DesignForm df = new DesignForm();
	private MainFrame f;
	private AdminPanel ap;
	private JLabel labelPro;
	private JTextField txtId;
	private JTextField txtName;

	private int popupWidth = 400; // 넓이
	private int popupHeight = 200; // 높이
	private MyDialog dialog;
	private TextField txtPassword;

	public LoginPanel(MainFrame f) {

		this.f = f;
		dialog = new MyDialog(this);
		ap = new AdminPanel(f, this);

		// 타이틀
		JLabel labelTitle = new JLabel("시험");
		labelTitle.setBounds(f.getWidth() / 2 - 300, 50, 600, 200);
		labelTitle.setFont(df.getFONT_TITLE());
		labelTitle.setForeground(df.getCOL_BACKGROUND());
		labelTitle.setHorizontalAlignment(0);

		adminBtn();
		panel();
		
		dialog.getContentPane().setBackground(df.getCOL_MARGIN());
		
		add(labelTitle);
		setLayout(null);
		setBackground(df.getCOL_MARGIN());
		setSize(f.getWidth(), f.getHeight());

	}

	public class MyDialog extends JDialog {
		public MyDialog(LoginPanel lp) {

			txtPassword = new TextField(8);
			txtPassword.setEchoChar('*');
			JButton btnCheck = new JButton("확인");
			setTitle("암호 확인");

			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension screenSize = tk.getScreenSize();
			int x = screenSize.width / 2 - popupWidth / 2;
			int y = screenSize.height / 2 - popupHeight / 2;
			setBounds(x, y, popupWidth, popupHeight);
			setLayout(null);

			txtPassword.setBounds(100, 50, 150, 32);
			btnCheck.setBounds(250, 50, 80, 32);
			txtPassword.setFont(df.getFONT_20());
			btnCheck.setFont(df.getFONT_20());

			add(txtPassword);
			add(btnCheck);

			btnCheck.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (txtPassword.getText().equals(f.getPASSWORD())) {
						MyUtill.changePanel(f, LoginPanel.this, new AdminPanel(f, LoginPanel.this));
						txtPassword.setText("");
						setVisible(false);
					} else {
						txtPassword.setText("");
						setVisible(false);
					}
				}
			});

			// 암호입력후 엔터로 진입
			txtPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 10) {
						if (txtPassword.getText().equals(f.getPASSWORD())) {
							MyUtill.changePanel(f, LoginPanel.this, new AdminPanel(f, LoginPanel.this));
							txtPassword.setText("");
							setVisible(false);
						} else {
							txtPassword.setText("");
							setVisible(false);
						}
					}

				}

			});
		}
	}

	public void adminBtn() {

		JButton btnAdmin = new JButton("관리자 모드");
		btnAdmin.setBounds(f.getWidth() - 150, 0, 150, 50);
		btnAdmin.setBackground(df.getCOL_MARGIN());
		btnAdmin.setBorderPainted(false);
		btnAdmin.setFont(df.getFONT_20());

		btnAdmin.addActionListener(new adminLoadListener());
		add(btnAdmin);
	}

	public void panel() {

		JPanel panel = new JPanel();
		JLabel labelId = new JLabel("수 험 번 호 : ");
		JLabel labelName = new JLabel("성        명 : ");
		JButton btnStart = new JButton("시험 시작");
		txtId = new JTextField();
		txtName = new JTextField();
		labelPro = new JLabel();

		panel.setLayout(null);

		// 리스너 설정
		btnStart.addActionListener(new MyActionListener());

		// 배경, 글자색 설정
		panel.setBackground(df.getCOL_BACKGROUND());
		labelPro.setForeground(df.getCOL_WARNING());

		// 텍스트 입력 수 제한
		txtId.setDocument(new JTextFieldLimit(8));
		txtName.setDocument(new JTextFieldLimit(5));

		// 글꼴
		labelId.setFont(df.getFONT_A());
		labelName.setFont(df.getFONT_A());
		txtId.setFont(df.getFONT_A());
		txtName.setFont(df.getFONT_A());
		labelPro.setFont(df.getFONT_ANSWER());
		btnStart.setFont(df.getFONT_A());

		// 정렬 (right = 4, left = 2, center = 0)
		labelId.setHorizontalAlignment(4);
		labelName.setHorizontalAlignment(4);
		txtId.setHorizontalAlignment(0);
		txtName.setHorizontalAlignment(0);
		labelPro.setHorizontalAlignment(0);

		// 위치 크기 지정
		int marginHeight = 300;
		panel.setBounds(0, marginHeight, f.getWidth(), 550);
		labelId.setBounds(150, 80, 400, 100);
		labelName.setBounds(150, 200, 400, 100);
		txtId.setBounds(600, 80, 400, 100);
		txtName.setBounds(600, 200, 400, 100);
		labelPro.setBounds(f.getWidth() / 2 - 500, 325, 1000, 50);
		btnStart.setBounds(f.getWidth() / 2 - 200, 400, 400, 100);

		panel.add(labelId);
		panel.add(labelName);
		panel.add(txtId);
		panel.add(txtName);
		panel.add(labelPro);
		panel.add(btnStart);
		add(panel);
	}

	public class MyActionListener implements ActionListener {

		// 수험 시작 버튼 상호작용
		@Override
		public void actionPerformed(ActionEvent e) {

			if (txtId.getText().equals("")) {
				labelPro.setText("수험번호를 입력해주세요.");
				return;
			} else if (txtName.getText().equals("")) {
				labelPro.setText("성명을 입력해주세요.");
				return;
			} else {
				UserController u = new UserController();
				labelPro.setText(u.login(txtId.getText(), txtName.getText()));
			}

			if (labelPro.getText().equals("approve"))
				MyUtill.changePanel(f, LoginPanel.this, new SubjectPanel(f, LoginPanel.this, txtId.getText()));

		}

	}

	public class adminLoadListener implements ActionListener { // 관리창으로 전환
		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.setVisible(true);
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

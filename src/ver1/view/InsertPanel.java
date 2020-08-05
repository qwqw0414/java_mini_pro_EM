package ver1.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import ver1.common.MyUtill;
import ver1.controller.UserController;
import ver1.view.LoginPanel.JTextFieldLimit;

public class InsertPanel extends JPanel {

	private DesignForm df = new DesignForm();
	private MainFrame f;
	private AdminPanel ap;
	private JButton btnBack;
	private JPanel panel;
	private JTextField txtId;
	private JTextField txtName;
	private JButton btnInsert;
	private JLabel labelPro;

	public InsertPanel(MainFrame f, AdminPanel ap) {

		this.f = f;
		this.ap = ap;

		setSize(f.getWidth(), f.getHeight());
		setBackground(df.getCOL_MARGIN());

		btnBack();
		panel();

		setLayout(null);
	}

	public void panel() {
		panel = new JPanel();
		JLabel labelId = new JLabel("수 험 번 호 : ");
		JLabel labelName = new JLabel("성        명 : ");
		labelPro = new JLabel("");
		txtId = new JTextField();
		txtName = new JTextField();
		btnInsert = new JButton("발급 신청");

		// 리스너
		btnInsert.addActionListener(new btnInsertListner());

		// 색
		labelPro.setForeground(df.getCOL_WARNING());
		panel.setBackground(df.getCOL_BACKGROUND());

		// 글꼴
		labelId.setFont(df.getFONT_A());
		labelName.setFont(df.getFONT_A());
		txtId.setFont(df.getFONT_A());
		txtName.setFont(df.getFONT_A());
		btnInsert.setFont(df.getFONT_A());
		labelPro.setFont(df.getFONT_ANSWER());

		// 위치
		int marginHeight = 200;
		panel.setBounds(0, marginHeight, f.getWidth(), 650);
		labelId.setBounds(150, 80, 400, 100);
		labelName.setBounds(150, 200, 400, 100);
		txtId.setBounds(600, 80, 400, 100);
		txtName.setBounds(600, 200, 400, 100);
		labelPro.setBounds(f.getWidth() / 2 - 500, 400, 1000, 50);
		btnInsert.setBounds(f.getWidth() / 2 - 200, 500, 400, 100);

		// 정렬 (right = 4, left = 2, center = 0)
		labelId.setHorizontalAlignment(4);
		labelName.setHorizontalAlignment(4);
		txtId.setHorizontalAlignment(0);
		txtName.setHorizontalAlignment(0);
		labelPro.setHorizontalAlignment(0);

		// 텍스트 입력 수 제한
		txtId.setDocument(new JTextFieldLimit(8));
		txtName.setDocument(new JTextFieldLimit(5));

		panel.setLayout(null);
		add(panel);
		panel.add(labelId);
		panel.add(labelName);
		panel.add(labelPro);
		panel.add(txtId);
		panel.add(txtName);
		panel.add(btnInsert);
	}

	public void btnBack() {
		btnBack = new JButton("Back");
		btnBack.setBounds(f.getWidth() - 100, 0, 100, 50);

		btnBack.addActionListener(new btnBackListner());
		add(btnBack);
	}

	public class btnInsertListner implements ActionListener { // 수험번호 발급

		@Override
		public void actionPerformed(ActionEvent e) {
			if (txtId.getText().equals("") || txtId.getText().length() < 8) {
				labelPro.setText("수험번호 8자리를 입력해주세요");
				return;
			} else if (txtName.getText().equals("") || txtName.getText().length() < 2) {
				labelPro.setText("성명을 입력해주세요.");
				return;
			}

			UserController u = new UserController();
			labelPro.setText(u.insertUserInfo(txtId.getText(), txtName.getText()));
		}

	}

	public class btnBackListner implements ActionListener { // 뒤로가기

		@Override
		public void actionPerformed(ActionEvent e) {
			MyUtill.changePanel(f, InsertPanel.this, ap);
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

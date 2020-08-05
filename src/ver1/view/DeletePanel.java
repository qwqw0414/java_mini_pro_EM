package ver1.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import ver1.common.MyUtill;
import ver1.controller.UserController;

public class DeletePanel extends JPanel {
	private DesignForm df = new DesignForm();
	private MainFrame f;
	private AdminPanel ap;
	private JButton btnBack;
	private JPanel panel;
	private JTextField txtId;
	private JLabel labelPro;
	private JButton btnDelete;

	public DeletePanel(MainFrame f, AdminPanel ap) {

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
		txtId = new JTextField();
		labelPro = new JLabel("");
		btnDelete = new JButton("취하 신청");

		// 리스너
		btnDelete.addActionListener(new btnDeleteListener());

		// 색
		labelPro.setForeground(df.getCOL_WARNING());
		panel.setBackground(df.getCOL_BACKGROUND());

		// 글꼴
		labelId.setFont(df.getFONT_A());
		txtId.setFont(df.getFONT_A());
		btnDelete.setFont(df.getFONT_A());
		labelPro.setFont(df.getFONT_ANSWER());

		// 위치
		int marginHeight = 200;
		panel.setBounds(0, marginHeight, f.getWidth(), 650);
		labelId.setBounds(150, 220, 400, 100);
		txtId.setBounds(600, 220, 400, 100);
		labelPro.setBounds(f.getWidth() / 2 - 500, 400, 1000, 50);
		btnDelete.setBounds(f.getWidth() / 2 - 200, 500, 400, 100);

		// 정렬 (right = 4, left = 2, center = 0)
		labelId.setHorizontalAlignment(4);
		txtId.setHorizontalAlignment(0);
		labelPro.setHorizontalAlignment(0);

		// 글자수 제한
		txtId.setDocument(new JTextFieldLimit(8));

		panel.setLayout(null);
		add(panel);
		panel.add(labelId);
		panel.add(txtId);
		panel.add(labelPro);
		panel.add(btnDelete);

	}

	public void btnBack() {
		btnBack = new JButton("Back");
		btnBack.setBounds(f.getWidth() - 100, 0, 100, 50);

		btnBack.addActionListener(new btnBackListner());
		add(btnBack);
	}

	public class btnDeleteListener implements ActionListener { // 수험번호 발급

		@Override
		public void actionPerformed(ActionEvent e) {
			if (txtId.getText().equals("") || txtId.getText().length() < 8) {
				labelPro.setText("수험번호 8자리를 입력해주세요");
				return;
			}

			UserController u = new UserController();
			labelPro.setText(u.deleteUserInfo(txtId.getText()));
		}

	}

	public class btnBackListner implements ActionListener { // 뒤로가기

		@Override
		public void actionPerformed(ActionEvent e) {
			MyUtill.changePanel(f, DeletePanel.this, ap);
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

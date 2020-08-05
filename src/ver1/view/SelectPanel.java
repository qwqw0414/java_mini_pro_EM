package ver1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ver1.common.MyUtill;
import ver1.controller.UserController;
import ver1.vo.model.User;

public class SelectPanel extends JPanel {

	private DesignForm df = new DesignForm();
	private MainFrame f;
	private AdminPanel ap;
	private JTable data;
	private JScrollPane scr;
	private List<User> userList;
	private JCheckBox pass;
	private JComboBox<String> filter;
	private JLabel label;
	private boolean isPassSelected;
	private int cnt = 1;
	private JTextField searchText;
	private JButton back;
	private TableRowSorter<TableModel> sorter;

	public SelectPanel(MainFrame f, AdminPanel ap) {
		this.f = f;
		this.ap = ap;
		panel();

		setLayout(new BorderLayout());
		setBackground(df.getCOL_MARGIN());
		setSize(f.getWidth(), f.getHeight());
	}

	public void panel() {
		// 배경 패널 생성
		JPanel panel = new JPanel();
		panel.setLayout(null);
		label = new JLabel("결과");
		panel.setBackground(df.getCOL_BACKGROUND());

		// 배경 패널 크기 설정
		int marginHeight = 100;
		panel.setBounds(0, marginHeight, f.getWidth(), 760);
		label.setBounds(0, marginHeight, f.getWidth(), 760);
		// 결과표 생성 및 설정
		UserController uc = new UserController();
		userList = uc.listLoad();
		String[] column = { "수험번호", "성명", "상식", "수도", "신조어", "수험여부", "합격여부" };
		Object[][] users = new Object[userList.size()][column.length];
		for (int i = 0; i < userList.size(); i++) {
			User u = userList.get(i);
			users[i][0] = u.getUserId();
			users[i][1] = u.getUserName();
			users[i][2] = u.getExamScore()[0];
			users[i][3] = u.getExamScore()[1];
			users[i][4] = u.getExamScore()[2];
			if (u.getExamComp()[0] && u.getExamComp()[1] && u.getExamComp()[2]) {
				users[i][5] = "응시";
			} else {
				users[i][5] = "미응시";
			}
			if (u.isAccept()) {
				users[i][6] = "합격";
			} else if (users[i][5].equals("미응시")) {
				users[i][6] = " ";
			} else {
				users[i][6] = "불합격";
			}

		}
		// 결과표 크기 설정
		data = new JTable(users, column);
		// 셀 내용 수정 불가
		DefaultTableModel tableModel = new DefaultTableModel(users, column) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		data.getTableHeader().setReorderingAllowed(false);// 셀 이동 불가
		data.setRowHeight(30);
		data.getTableHeader().setResizingAllowed(false);// 셀 크기 조절 불가
		data.setModel(tableModel);
		data.setFont(new Font(df.getFONT(), Font.PLAIN, 15));
		// JTable 필터 달기
		sorter = new TableRowSorter<TableModel>(data.getModel());
		data.setRowSorter(sorter);
		scr = new JScrollPane(data, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		data.setPreferredScrollableViewportSize(data.getPreferredSize());
		scr.setSize(label.getSize());

		// 검색 라벨 생성 및 설정
		JLabel searchLabel = new JLabel("수험생 정보 조회 : ");
		searchLabel.setBounds(400, 20, 400, 50);
		searchLabel.setFont(df.getFONT_B());
		
		// 검색 텍스트필드 생성 및 설정
		searchText = new JTextField("");
		searchText.setBounds(655, 23, 300, 50);
		searchText.setFont(df.getFONT_B());
		searchText.setHorizontalAlignment(0);
		searchText.getDocument().addDocumentListener(new DocumentListener() {
			// 검색 텍스트필드에 검색어 입력시 바로바로 JTable을 해당값으로 정렬
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = searchText.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = searchText.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");

			}
		});
		// 검색 버튼 생성 및 설정
//		JButton searchBtn = new JButton("검색");
//		searchBtn.setFont(df.getFONT_B());
//		searchBtn.setBounds(760, 20, 100, 50);
//		searchBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String text = searchText.getText();
//				if (text.length() == 0) {
//					sorter.setRowFilter(null);
//				} else {
//					sorter.setRowFilter(RowFilter.regexFilter(text));
//				}
//				searchText.setText("");
//			}
//
//		});

		back = new JButton("Back");
		back.setBounds(f.getWidth() - 100, 0, 100, 50);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MyUtill.changePanel(f, SelectPanel.this, ap);
			}

		});

		label.add(scr);
		panel.add(searchLabel);
		panel.add(searchText);
//		panel.add(searchBtn);
		panel.add(label);
		add(back);
		add(panel, BorderLayout.CENTER);

	}
}

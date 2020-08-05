package ver1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private final String PASSWORD = "123";
	private int width = 1280; // 넓이
	private int height = 960; // 높이
	private String title = "exam management"; // 타이틀

	// 메인 프레임
	public MainFrame() {
		// 프레임 위치 가운데 설정
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();

		int x = screenSize.width / 2 - width / 2;
		int y = screenSize.height / 2 - height / 2;
		setBounds(x, y, width, height);
		
		// 아이콘 설정
//		Image img = new ImageIcon("images/icon.JPG").getImage();
//		setIconImage(img);
		
		// 로그인 패널 로드
		JPanel loginPanel = new LoginPanel(this);
		add(loginPanel);

		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		setVisible(true);
	}

	// 공통 변수 getter 설정
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}
}

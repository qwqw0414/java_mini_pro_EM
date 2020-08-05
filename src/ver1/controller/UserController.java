package ver1.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ver1.vo.model.Exam;
import ver1.vo.model.User;

public class UserController {

	private final String USERDATALINK = "src/ver1/vo/data/userInfo.dat"; // 데이터베이스 경로 상수

	// 수험번호 데이터 조회
	public User selectUserId(String userId) {
		List<User> userList = listLoad();
		for (User u : userList) {
			if (u.getUserId().equals(userId))
				return u;
		}
		User user = new User();
		return user;
	}

	// 로그인
	public String login(String userId, String userName) {
		List<User> userList = new ArrayList<>();
		User user = new User();
		int index = 0;

		// 데이터 불러오기
		userList = listLoad();

		user.setUserId(userId);
		user.setUserName(userName);

		// 데이터 확인
		if (userList.contains(user))
			index = userList.indexOf(user);
		else
			return "존재하지 않는 수험번호입니다.";

		if (userList.get(index).getUserName().equals(userName)) {

			boolean examComp[] = userList.get(index).getExamComp();
			if (examComp[0] && examComp[1] && examComp[2])
				return "이미 시험을 완료했습니다.";
			else
				return "approve";
		} else
			return "성명이 일치하지 않습니다.";
	}

	// 수험번호 취하
	public String deleteUserInfo(String userId) {
		List<User> userList = new ArrayList<>();
		User user = new User();
		int index = 0;

		// 데이터 불러오기
		userList = listLoad();

		user.setUserId(userId);

		// 데이터 확인
		if (userList.contains(user))
			index = userList.indexOf(user);
		else
			return "존재하지 않는 수험번호입니다.";

		// 데이터 제거
		userList.remove(index);

		// 데이터 저장
		listSave(userList);

		// 데이터 확인
//		listView(userList);

		return "수험번호가 정상적으로 취하 처리되었습니다.";
	}

	// 수험번호 발급
	public String insertUserInfo(String userId, String userName) {
		List<User> userList = new ArrayList<>();
		int[] examScroe = { 0, 0, 0 };
		boolean[] examComp = { false, false, false };
		User user = new User(userId, userName, examScroe, examComp, false);

		// 데이터 불러오기
		userList = listLoad();

//		user.setUserId(userId);
//		user.setUserName(userName);

		// 데이터 중복 검사
		if (userList.contains(user))
			return "중복된 수험번호입니다.";

		// 데이터 추가
		userList.add(user);

		// 데이터 저장하기
		listSave(userList);

		// 데이터 확인
//		listView(userList);
		return "발급이 정상 처리되었습니다.";
	}

	// 리스트 저장
	public void listSave(List<User> list) {

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(USERDATALINK)))) {

			oos.writeObject(list);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 리스트 불러오기
	public List<User> listLoad() {
		List<User> list = new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(USERDATALINK)))) {

			list = (List<User>) (ois.readObject());

		} catch (IOException e) {
			System.out.println("userInfo.data 찾을 수 없습니다.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 리스트 정보 출력
	public void listView(List<User> list) {
		System.out.println("=================================");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public String getUSERDATALINK() {
		return USERDATALINK;
	}
}

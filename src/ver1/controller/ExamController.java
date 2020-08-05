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

public class ExamController {

	private final String USERDATALINK = "src/ver1/vo/data/examInfo.dat"; // 데이터베이스 경로 상수

	// 과목명 불러오기
	public String getExamTitle(String examType) {
		Exam exam = new Exam();
		int index;

		exam.setExamNum(0);
		exam.setExamType(examType);
		index = listLoad().indexOf(exam);

		return listLoad().get(index).getExamTitle();
	}

	// 문제 불러오기
	public Exam selectExamInfo(String examType, int examNum) {
		List<Exam> examList = new ArrayList<>();
		Exam exam = new Exam();
		int index = 0;

		exam.setExamType(examType);
		exam.setExamNum(examNum);
		// 데이터 불러오기
		examList = listLoad();

		// 데이터 검색
		if (examList.contains(exam)) {
			index = examList.indexOf(exam);
			// 데이터 확인
			examList.get(index);
			return examList.get(index);
		} else {
			return exam;
		}
	}

	// 문제 저장
	public void insertExamInfo(String examType, String examTitle, int examNum, String question, String[] questionMark,
			int answer) {
		List<Exam> examList = new ArrayList<>();
		Exam exam = new Exam(examType, examTitle, examNum, question, questionMark, answer);
		int index = 0;

		// 데이터 불러오기
		examList = listLoad();

		// 데이터 중복 검사
		if (examList.contains(exam)) {
			index = examList.indexOf(exam);
			examList.remove(index);
		}
		// 데이터 추가
		examList.add(exam);

		// 데이터 저장
		listSave(examList);

		// 데이터 확인
//		listView(examList);

	}

	// 리스트 저장
	public void listSave(List<Exam> list) {

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(USERDATALINK)))) {

			oos.writeObject(list);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 리스트 불러오기
	public List<Exam> listLoad() {
		List<Exam> list = new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(USERDATALINK)))) {

			list = (List<Exam>) (ois.readObject());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 리스트 정보 출력
	public void listView(List<Exam> list) {
		System.out.println("=================================");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public String getUSERDATALINK() {
		return USERDATALINK;
	}
}

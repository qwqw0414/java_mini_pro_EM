package ver1.controller;

import java.util.ArrayList;
import java.util.List;

import ver1.vo.model.Exam;
import ver1.vo.model.User;

public class MarkingController {
	ExamController ec = new ExamController();
	UserController uc = new UserController();

	// 문제 채점후 저장
	public void marking(String userId, String examType, int[] answer) {
		List<Exam> examList = ec.listLoad();
		List<User> userList = uc.listLoad();
		int sum = 0;
		int index;
		Exam exam = new Exam();

		exam.setExamType(examType);

		for (int i = 0; i < answer.length; i++) {

			index = 0;
			exam.setExamNum(i);
			index = examList.indexOf(exam);

			if (examList.get(index).getAnswer() == answer[i]) {
				sum += 10;
			}
		}

		for (User u : userList) {

			if (u.getUserId().equals(userId)) {

				int[] examScore = u.getExamScore();
				boolean[] examComp = u.getExamComp();
				int type = Integer.parseInt(examType) - 1;
				
				examScore[type] = sum;
				examComp[type] = true;
				
				u.setExamScore(examScore);
				u.setExamComp(examComp);

				userList.set(userList.indexOf(u), u);
				uc.listSave(userList);
				uc.listView(userList);
				return;
			}
		}

	}
}

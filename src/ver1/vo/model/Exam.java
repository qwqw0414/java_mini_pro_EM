package ver1.vo.model;

import java.io.Serializable;
import java.util.Arrays;

public class Exam implements Serializable{
	
	private static final long serialVersionUID = 2L;
	private String examType; // 과목 구분 - 기본키
	private String examTitle; // 과목 명
	private int examNum; // 문제 번호
	private String question;// 문제 내용
	private String[] questionMark;// 문항 내용
	private int answer;// 정답

	public Exam() {

	}

	public Exam(String examType, String examTitle, int examNum, String question, String[] questionMark, int answer) {
		this.examType = examType;
		this.examTitle = examTitle;
		this.examNum = examNum;
		this.question = question;
		this.questionMark = questionMark;
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Exam [examType=" + examType + ", examTitle=" + examTitle + ", examNum=" + examNum + ", question="
				+ question + ", questionMark=" + Arrays.toString(questionMark) + ", answer=" + answer + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + examNum;
		result = prime * result + ((examType == null) ? 0 : examType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		if (examNum != other.examNum)
			return false;
		if (examType == null) {
			if (other.examType != null)
				return false;
		} else if (!examType.equals(other.examType))
			return false;
		return true;
	}

	public String getExamType() {
		return examType;
	}

	public String getExamTitle() {
		return examTitle;
	}

	public int getExamNum() {
		return examNum;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getQuestionMark() {
		return questionMark;
	}

	public int getAnswer() {
		return answer;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	public void setExamNum(int examNum) {
		this.examNum = examNum;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestionMark(String[] questionMark) {
		this.questionMark = questionMark;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

}

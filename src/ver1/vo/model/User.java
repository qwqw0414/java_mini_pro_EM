package ver1.vo.model;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable { // 사용자 필드 부모클래스

	private static final long serialVersionUID = 1L;

	private String userId; // 수험번호 - 기본키
	private String userName; // 성명
	private int[] examScore; // 성적 - 과목별 배열 저장
	private boolean[] examComp; // 수험여부
	private boolean accept; // 합격여부

	public User() {

	}

	public User(String userId, String userName, int[] examScore, boolean[] examComp, boolean accept) {
		this.userId = userId;
		this.userName = userName;
		this.examScore = examScore;
		this.examComp = examComp;
		this.accept = accept;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", examScore=" + Arrays.toString(examScore)
				+ ", examComp=" + Arrays.toString(examComp) + ", accept=" + accept + "]";
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public int[] getExamScore() {
		return examScore;
	}

	public boolean[] getExamComp() {
		return examComp;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setExamScore(int[] examScore) {
		this.examScore = examScore;
	}

	public void setExamComp(boolean[] examComp) {
		this.examComp = examComp;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

}

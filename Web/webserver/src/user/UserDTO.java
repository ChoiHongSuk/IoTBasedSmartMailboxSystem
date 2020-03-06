package user;

public class UserDTO {//데이터 접근 명령어
	String stack;
	
	public String getStack() {//현재의 기록된 데이터를 가져옴
		return stack;
	}
	public void setUserID(String userID) {//어떠한 데이터를 기록하는 명령어
		this.stack = stack;
	}
}
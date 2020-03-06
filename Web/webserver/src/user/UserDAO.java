package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import util.DatabaseUtil;

public class UserDAO {//실적적으로 데이터베이스에 관여(읽기, 가져오기 가능)[데이터 접근 객체]
	//회원가입 접속 예제
	public int join(String stack) {
		String SQL="INSERT INTO USER VALUES (?, ?)";
		try {
			Connection conn=DatabaseUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(1, timestamp); // (?,
			pstmt.setString(2, stack);// ?)
			return pstmt.executeUpdate(); //INSERT 구문을 실행한 결과를 반환 ->결과를 0, 1로 반환
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //오류가 발생했을 경우 -1 반환
	}
}
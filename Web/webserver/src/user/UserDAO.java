package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import util.DatabaseUtil;

public class UserDAO {//���������� �����ͺ��̽��� ����(�б�, �������� ����)[������ ���� ��ü]
	//ȸ������ ���� ����
	public int join(String stack) {
		String SQL="INSERT INTO USER VALUES (?, ?)";
		try {
			Connection conn=DatabaseUtil.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(1, timestamp); // (?,
			pstmt.setString(2, stack);// ?)
			return pstmt.executeUpdate(); //INSERT ������ ������ ����� ��ȯ ->����� 0, 1�� ��ȯ
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //������ �߻����� ��� -1 ��ȯ
	}
}
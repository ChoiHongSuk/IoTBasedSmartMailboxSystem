<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Mail Log</title>
</head>
<body>
	<table border="1" cellspacing="1" cellpadding="1">
		<tr>
			<td>&nbsp;Time&nbsp;</td><td>&nbsp;Mail&nbsp;</td>
		</tr>
	<%
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/STACK","root","mingky1218");
			ps=conn.prepareStatement("SELECT * FROM MESSAGE ORDER BY TIMESTAMP DESC");
			rs=ps.executeQuery();
			while(rs.next()){
				String time=rs.getString("timeStamp");
				String stack=rs.getString("STACK");
				out.println("<tr><td>&nbsp;"+time+"&nbsp;</td><td>&nbsp;"+stack+"&nbsp;</td></tr>");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	%>
	</table>
</body>
</html>
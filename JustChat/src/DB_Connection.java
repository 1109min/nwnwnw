import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DB_Connection {
	public Connection con;
	
	public DB_Connection() {
		String port = "3306";
		String dbName = "network";
		
//		String url = "jdbc:mysql://localhost:"+port+"/"+dbName;
//		String userid = "madang";
//		String pwd = "1234"; //원래는 1234
		
		String url = "jdbc:mysql://localhost:3306/network";
		String userid = "network";
		String pwd = "1234"; //원래는 1234
		
		try {
			con = DriverManager.getConnection(url,userid,pwd);
			System.out.println("Connection Success!\n");
		} catch (SQLException e) {
			e.printStackTrace();
			}
	}
	
	public User setUser(User user) {
		String query = "SELECT * FROM user WHERE id = " +  "\"" + user.id + "\"";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {				
				user.pw = rs.getString(4);
				user.name = rs.getString(5);
				user.nickName = rs.getString(6);
				user.email = rs.getString(7);
				user.birth = rs.getString(8);
				user.phone = rs.getString(9);
				user.address = rs.getString(10);
				user.todayM = rs.getString(11);
				user.state = rs.getString(12);
				user.outTime = rs.getString(13);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public String logIn(String id, String pw) {
		String query = "SELECT pw FROM user WHERE id = " + "\"" + id + "\"";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				if(rs.getString(1).equals(pw)) {
					System.out.println(rs.getString(1));
					return "Success";
				}
				else
					return "Wrong Password";
			}
			else
				return "Wrong ID";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Failed";
	}
	
	public void logOut(User me) {
		String myId = me.id;
		String outTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String query = "UPDATE user SET state = off, outTime = " + "\"" + outTime + "\"" + " WHERE id = " + "\"" + myId + "\"";
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String dupCheck(String id, String nickName) {
		String query = "SELECT id FROM user where id = " + "\"" + id + "\"";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) 
				return "Check your ID";		
			else {
				query = "SELECT nickName FROM user where nickName = " + "\"" + nickName + "\"";
				rs = stmt.executeQuery(query);
				if (rs.next()) 
					return "Check your NickName";
				else
					return "Success";
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	public void signUp(User user) {
		String query = "INSERT INTO user VALUES (\"" + user.ip + "\", " + user.port + ", \"" + user.id + "\", \"" + 
						user.pw + "\", \"" + user.name + "\", \"" + user.nickName + "\", \"" + user.email + "\", \"" 
						+ user.birth + "\", \"" + user.phone + "\", \"" + user.address + "\", \"" + user.todayM + 
						"\", \"" + user.state + "\", \"" + user.outTime + "\")"; 
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String findId(String name, String email) {
		String query = "SELECT id FROM user WHERE name = " + "\"" + name + "\" and email = " + "\"" + email + "\""; ;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return "Wrong name or email";
	}
	
	public String changePw(String id, String npw) {
		String query = "UPDATE user SET pw = " + "\"" + npw + "\" WHERE id = " + "\"" + id + "\"";
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(query);
			if (rs == 1) {
				return "Password change success";
			}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return "Failed";
	}
	
	public void readTalk() {
		
	}
	
	public void writeTalk(Message talk) {
		String sender = talk.sender.id;
		int roomId = talk.roomId;
		String content = talk.content;
		int year = talk.now.getYear();  // 연도
		int monthValue = talk.now.getMonthValue();  // 월(숫자)
		int dayOfMonth = talk.now.getDayOfMonth();  // 일(월 기준)
		int hour = talk.now.getHour();
		int minute = talk.now.getMinute();
		int second = talk.now.getSecond();
		
	  	String query="INSERT INTO Room VALUES(" + "\"" +  sender + "\", "  + roomId + ", " + "\"" + content + "\"," + year + ", " + monthValue + ", " + dayOfMonth + ", " + hour + ", " + minute + ", " + second + ")"; 
	  	System.out.println(query);
	  	try { 
	  	  	Statement stmt=con.createStatement();
	  	  	stmt.execute(query);

	  	} catch(SQLException e) {
	  	  	e.printStackTrace();
	  	}
	}
}
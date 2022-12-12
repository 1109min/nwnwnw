import java.io.*;
import java.sql.*;

public class DB_Connection {
	public static Connection con;
	
	public DB_Connection() {
		String port = "3306";
		String dbName = "madang";
		
//		String url = "jdbc:mysql://localhost:"+port+"/"+dbName;
//		String userid = "madang";
//		String pwd = "1234"; //원래는 1234
		
		String url = "jdbc:mysql://localhost:3306/madang";
		String userid = "madang";
		String pwd = "1234"; //원래는 1234
		
		try {
			con = DriverManager.getConnection(url,userid,pwd);
			System.out.println("Connection Success!\n");
		} catch (SQLException e) {
			e.printStackTrace();
			}
	}

//회원가입
	public static int checkSign(String id, String pw) {		
				String selectSignQuery = "SELCET count(*) FROM User WHERE ID = ?";
				String insertSignQuery ="INSERT INTO User value(?,?)";
				
				PreparedStatement pstmt = null;
				
				try {
		            pstmt = con.prepareStatement(selectSignQuery);
		            pstmt.setString(1, id);
		            ResultSet rs = pstmt.executeQuery();
		            			
					if(rs.next() == false) {
						try {
				            pstmt = con.prepareStatement(insertSignQuery);
				            pstmt.setString(1, id);
				            pstmt.setString(2, pw);
				            
				            int result = pstmt.executeUpdate();
				            if(result==1) {
				                System.out.println("ID:"+id + " SignUp Success!");
				                return 1;
				            }
				        }catch (Exception e) {
			                System.out.println("ID:"+id + " SignUp Fail!");
				        }
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
		return 0;
	}
	
//로그인
public static int checkLogin(String id, String pw) {
		
		String selectLoginQuery = "SELCET ID,PW FROM User WHERE ID = ? AND PW = ?";
		
		PreparedStatement pstmt = null;
		
		try {
            pstmt = con.prepareStatement(selectLoginQuery);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            ResultSet rs = pstmt.executeQuery();
            			
			while(rs.next()) {
				System.out.println("ID:"+id + " Login Success!");
				return 1;
			}
		       
		} catch(SQLException e) {
            System.out.println("ID:"+id + " Login Fail!");
			e.printStackTrace();
		}
		
		return 0;
	}
	
//비밀번호 변경
public static int UpdatePw(String id, String newPw) {
	
	String updatePw = "UPDATE User SET PW = '" + newPw + "' WHERE ID = '" + id + "';";
	try {
		Statement stmt = con.createStatement();
		stmt.executeUpdate(updatePw);
	} catch(SQLException e) {
		e.printStackTrace();
		return 1;
	}		
	return 0;
}

//친구 목록확인 
public static String[] getFriendList(String id) {
		
		String selectFriendListQuery = "SELCET ID FROM FollowInfo WHERE FromID = ?";
		String[] List = null;
		
		PreparedStatement pstmt = null;
		
		try {
          pstmt = con.prepareStatement(selectFriendListQuery);
          pstmt.setString(1, id);
          ResultSet rs = pstmt.executeQuery();
          			int i = 0;
			while(rs.next()) {
				List[i] = rs.getString(1);
				i++;
			}
			return List;
		       
		} catch(SQLException e) {
          System.out.println("ID:"+id + " Login Fail!");
			e.printStackTrace();
		}
		
		return new String[1];
	}

//친구 정보
public static String[] getFriendiInfo(String id) {
	
	String selectFriendInfoQuery = "SELCET * FROM User WHERE ID = ?";
	String[] List = null;
	String friendID = null;
	String friendName = null;
	String friendNick = null;
	String friendIntro = null;
	String friendEmail = null;
	String friendBithday = null;
	String friendLoginTime = null;
	String friendLoginCount = null;
	String friendTelephoneNum = null;
	String friendgitHub = null;
	

	PreparedStatement pstmt = null;
	
	try {
      pstmt = con.prepareStatement(selectFriendInfoQuery);
      pstmt.setString(1, id);
      ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			friendID = rs.getString(1);
			friendName = rs.getString(2);
			friendNick = rs.getString(3);
			friendIntro = rs.getString(4);
			friendEmail = rs.getString(5);
			friendBithday = rs.getString(6);
			friendLoginTime = rs.getString(7);
			friendLoginCount = rs.getString(8);
			friendTelephoneNum = rs.getString(9);
			friendgitHub = rs.getString(10);
		}
		List[0] = friendID;
		List[1] = friendName;
		List[2] = friendNick;
		List[3] = friendIntro;
		List[4] = friendEmail;
		List[5] = friendBithday;
		List[6] = friendLoginTime;
		List[7] = friendLoginCount;
		List[8] = friendTelephoneNum;
		List[9] = friendgitHub;

	
		return List;
	       
	} catch(SQLException e) {
      System.out.println("ID:"+id + " info Fail!");
		e.printStackTrace();
	}
	
	return new String[1];
}
	
	public String[][] getWord(String receiver) {
	   String query = "SELECT * FROM Word WHERE receiver = \"" + receiver + "\" ORDER BY year DESC, month DESC, day DESC, hour DESC, minute DESC, second DESC";
	   String[][] array = new String[100][3];
	   try { 
	  	  	 Statement stmt=con.createStatement();
	  	  	 ResultSet rs=stmt.executeQuery(query);
	  	  	 int count = 0;
	  	  	 while(rs.next() || (count == 100)) {
	  	  		 array[count][0] = rs.getString(1);	  	  		 
	  	  		 array[count][1] = rs.getString(3);
	  	  		 array[count][2] = rs.getString(4) + "." + rs.getString(5) + "." + rs.getString(6) + "." + rs.getString(7) + "." + rs.getString(8) + "." + rs.getString(9);
	  	  		 count++;
	  	  	 }	  	  	 
	  	  } catch(SQLException e) {
	  	  	   e.printStackTrace();
	  	    }
	   return array;
   }

   public void setWord(String writer, String receiver, String content, int year, int month, int day, int hour, int minute, int second) {
	  	  String query="INSERT INTO Word VALUES(" + "\"" +  writer + "\", " + "\"" + receiver + "\", " + "\"" + content + "\"," + year + "," + month + "," + day + "," + hour + "," + minute + "," + second + ")"; 
	  	  System.out.println(query);
	  	  try { 
	  	  	 Statement stmt=con.createStatement();
	  	  	 stmt.execute(query);

	  	  } catch(SQLException e) {
	  	  	   e.printStackTrace();
	  	    }
	}
	
	public void sqlSetting() {
		String drop_all="DELETE FROM users";

		String get_infos_num="SELECT count(*) FROM loginInfo";
		String get_infos="SELECT nickName, id FROM loginInfo";

		try {
			Statement stmt1 = con.createStatement();
			PreparedStatement pstmt1 = con.prepareStatement(drop_all);

			
			int rs1 =pstmt1.executeUpdate();
			
			Integer nums =0;
			ResultSet rs=stmt1.executeQuery(get_infos_num);
			
			while(rs.next()) {
				nums = rs.getInt(1);
				System.out.println("유저수:" + nums.toString());
			}
			
			rs=stmt1.executeQuery(get_infos);
			
			String[] get_nick = new String[nums];
			String[] get_id = new String[nums];
			
			int i =0;
			while(rs.next()) {
				get_nick[i]= rs.getString(1);
				get_id[i]= rs.getString(2);
				i++;
			}
			String plus ="Insert INTO users value(?,?)";
			
			PreparedStatement pstmt = null;
	        try {
	        	i = 0;
	        	while(i < get_nick.length) {
		            pstmt = con.prepareStatement(plus);
		            pstmt.setString(1, get_nick[i].toString());
		            pstmt.setString(2, get_id[i] );
		            
		            int result = pstmt.executeUpdate();
		            i++;
		            if(result==1) {
		                System.out.println("Board데이터 삽입 성공!");
		            }
	        	}
	        }catch (Exception e) {
	            System.out.println("Board데이터 삽입 실패!");
	        }
			

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String sqlUserNameById(String id) {
		
		String user_name="SELECT name FROM users Where id = '"+id+"'";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(user_name);
			
			String name = "";
			while(rs.next()) {
				name = rs.getString(1);
			}
			

			return name;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String();
		}
	}
	
	
	public String[] sqlUserName() {
		String userNum="SELECT count(*) FROM users";
		
		String users="SELECT name FROM users";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(userNum);
			
			int num = 0;
			while(rs.next()) {
				num = rs.getInt(1);
			}
			
			String[] user_name = new String[num];

			System.out.println(" hihi \n"+num);
			
			int i =0;
			rs=stmt1.executeQuery(users);

			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				user_name[i] = rs.getString(1);
				i++;
				
			}

			return user_name;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	public String[] sqlUserId() {
		String userNum="SELECT count(*) FROM loginInfo";
		
		String users="SELECT id FROM loginInfo";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(userNum);
			
			int num = 0;
			while(rs.next()) {
				num = rs.getInt(1);
			}
			
			String[] user_id = new String[num];

			System.out.println(" hihi \n"+num);
			
			int i =0;
			rs=stmt1.executeQuery(users);

			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				user_id[i] = rs.getString(1);
				i++;
				
			}

			return user_id;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	public String[] sqlUserIdBySearching(String searchId) {
		String userNum="SELECT count(*) FROM users where id like \'%"+searchId+"%\'";
		
		String users="SELECT id FROM users where id like \'%" + searchId + "%\'";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(userNum);
			
			int num = 0;
			while(rs.next()) {
				num = rs.getInt(1);
			}
			
			String[] user_id = new String[num];

			System.out.println(" search \n"+num);
			
			int i =0;
			rs=stmt1.executeQuery(users);

			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				user_id[i] = rs.getString(1);
				i++;
				
			}

			return user_id;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	
	public String[] sqlFollower(String id) {
		
		String[] followerInfo;
		
//		String QmyId ="SELECT id FROM users WHERE name =\"" + name+"\"";
//		String id = "";
//		
		
		int num = 0;

		try {
			Statement stmt = con.createStatement();
			
//			ResultSet rs=stmt.executeQuery(QmyId);
//			
//			//현재 사용자의 아이디를 확인
//			while(rs.next()) {
//				id = rs.getString(1);
//			}
			
			//팔로워 수
			String QmyFollower="SELECT from_id FROM follows WHERE to_id =\"" + id+"\"";
			
			String followerNum="SELECT count(from_id) FROM follows WHERE to_id =\"" + id+"\"";
			ResultSet rs=stmt.executeQuery(followerNum);

			int i=0;
			while(rs.next()) {
				
				num = rs.getInt(1);
				System.out.println("나를 팔로우하는 아이디"+num);

			}
			followerInfo = new String[num];
			
			rs=stmt.executeQuery(QmyFollower);

			
			i = 0;
			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				followerInfo[i] = rs.getString(1);
				i++;
			
			}				

			
			return followerInfo;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	
public String[] sqlFollowee(String id) {
		
		String[] followeeInfo;
		
//		String QmyId ="SELECT id FROM users WHERE name =\"" + name+ "\"";
//		String id = "";
	
		
		int num = 0;

		try {
			Statement stmt2 = con.createStatement();
			
//			ResultSet rs=stmt2.executeQuery(QmyId);
//			
//			//현재 사용자의 아이디를 확인
//			while(rs.next()) {
//				id = rs.getString(1);
//			}
			
			//팔로잉 수
			String QmyFollowee="SELECT to_id FROM follows WHERE from_id =\"" + id+"\"";
			
			String followeeNum="SELECT count(to_id) FROM follows WHERE from_id =\"" + id+"\"";
			ResultSet rs=stmt2.executeQuery(followeeNum);

			int i=0;
			while(rs.next()) {
				
				num = rs.getInt(1);
				System.out.println("내가 팔로잉하는 아이디"+num);
			}
			followeeInfo = new String[num];
			
			rs=stmt2.executeQuery(QmyFollowee);

			
			i = 0;
			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				followeeInfo[i] = rs.getString(1);
				i++;
			
			}				
			
			
			return followeeInfo;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
public void sqlPlus(String myid, String toid) {
	
	String[] followerInfo;
	
	
//	String QmyId ="SELECT id FROM users WHERE name =\"" + name+ "\"";
//	String toid = "";
//		
	
	int num = 0;

	try {
		Statement stmt = con.createStatement();
		
//		ResultSet rs=stmt.executeQuery(QmyId);
//		
//		//현재 사용자의 아이디를 확인
//		while(rs.next()) {
//			toid = rs.getString(1);
//		}
		
		//팔로워 수
		String plus ="Insert INTO follows value(?,?)";
		
		PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(plus);
            pstmt.setString(1, myid.toString());
            pstmt.setString(2, toid );
            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("Board데이터 삽입 성공!");
                
            }
        }catch (Exception e) {
            System.out.println("Board데이터 삽입 실패!");
        }
	} catch(SQLException e) {
		e.printStackTrace();
	}
}
public void sqlremove(String myid, String toid) {
	
	String[] followerInfo;
	
	
//	String QmyId ="SELECT id FROM users WHERE name =\"" + name+ "\"";
//	String toid = "";
//		
//	
	int num = 0;

	try {
		Statement stmt = con.createStatement();
//		
//		ResultSet rs=stmt.executeQuery(QmyId);
//		
		//현재 사용자의 아이디를 확인
//		while(rs.next()) {
//			toid = rs.getString(1);
//		}
		
		
		String remove ="delete from follows where from_id = ? and to_id = ?";
		
		PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(remove);
            pstmt.setString(1, myid.toString());
            pstmt.setString(2, toid );
            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("Board데이터 삭제 성공!");
                
            }
        }catch (Exception e) {
            System.out.println("Board데이터 삽입 실패!");
        }
	} catch(SQLException e) {
		e.printStackTrace();
	}
}

}
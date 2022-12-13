import java.util.*;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws Exception {
		String ip = "127.0.0.1";
		int port = 9800;
		ServerSocket listener = new ServerSocket(port);
		System.out.println("Server open");

		ExecutorService pool = Executors.newFixedThreadPool(20);
		while (true) {
			Socket sock = listener.accept();
			pool.execute(new Messenger(sock));
		}
	}

	private static class Messenger implements Runnable {
		private Socket socket;

		Messenger(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("Connected");
			
			try {
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());	// 소켓 출력 스트림 생성
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // 소켓 입력 스트림 생성				
				DB_Connection DB = new DB_Connection();
				
				while (true) {
					String request = (String)in.readObject();

					if(request.equals("setUser")) {
						User user = (User)in.readObject();
						out.writeObject(DB.setUser(user));
					}				
					else if(request.equals("logIn")) {
						String id = (String)in.readObject();
						String pw = (String)in.readObject();
						
						if(DB.logIn(id, pw).equals("Success"))
							out.writeObject(new User(id));
						else 
							out.writeObject(DB.logIn(id, pw)); 
					}
					else if(request.equals("logOut")) {
						User me = (User)in.readObject();
						DB.logOut(me);					
					}
					else if(request.equals("dupCheck")) {
						String id = (String)in.readObject();
						String nickName = (String)in.readObject();
						out.writeObject(DB.dupCheck(id, nickName));
					}									
					else if(request.equals("signUp")) {
						DB.signUp((User)in.readObject());
					}					
					else if(request.equals("findId")) {
						String name = (String)in.readObject();
						String email = (String)in.readObject();
						out.writeObject(DB.findId(name, email));
					}
					else if(request.equals("changePw")) {
						String id = (String)in.readObject();
						String pw = (String)in.readObject();
						if(!DB.logIn(id, pw).equals("Success")) {
							out.writeObject(DB.logIn(id, pw));
							break;
						}
						else 
							out.writeObject("continue");
						String npw = (String)in.readObject();
						out.writeObject(DB.changePw(id, npw));									
					}
					else if(request.equals("talking")) {
						Message talk = (Message)in.readObject(); // 클라이언트의 메세지를 읽음
						DB.writeTalk(talk);
					}
					
						
				
					//ArrayList<Message> talkList = DB.readTalk();
					//out.writeObject(talkList);
				}
			} catch (IOException e) {
				System.out.println("IO Exception");
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFound Exception");
			} finally {
				try {
					socket.close(); // 통신용 소켓 닫기
				} catch (IOException e) {
					System.out.println("IO Exception");
				}
			}
		}
	}
}
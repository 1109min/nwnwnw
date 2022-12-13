import java.io.*; //import io
import java.net.*; //import socket
import javax.swing.JFrame;
 
public class Main{
    LoginWindow loginView;
    SignUpView signupView;
    ChatRoomView chatroomview;  
    MainBoardView mainboardView;
    
	static String serverIp = "127.0.0.1";	//���� ip
	static int serverPort = 9800;			//���� ��Ʈ
	public static Socket clientSocket = null;		//Ŭ�� ����
    public static ObjectInputStream in = null;
    public static ObjectOutputStream out = null;
   
    public static void main(String[] args) {
       
        // ����Ŭ���� ����
    	Main main = new Main();
    	try {
			clientSocket = new Socket(serverIp, serverPort); //Ŭ����� ����
			out = new ObjectOutputStream(clientSocket.getOutputStream());	// ���� ��� ��Ʈ�� ����
			in = new ObjectInputStream(clientSocket.getInputStream()); // ���� �Է� ��Ʈ�� ����
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        main.loginView = new LoginWindow(); // �α���â ���̱�
        main.loginView.setMain(main); // �α���â���� ���� Ŭ����������
    	
       // main.chatroomview = new ChatRoomView();
        //main.chatroomview.setMain(main);

        //main.mainboardView = new MainBoardView("�¹�");
        //main.mainboardView.setMain(main);
    }
    
    public Object logIn(String id, String pw){   		
    	Object user = null;
    	try {
			out.writeObject("logIn");
			out.writeObject(id);
			out.writeObject(pw);
			user = in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return user;
	} 
    
	public static String findId(String name, String email){
		String id = "";
		try {
			out.writeObject("findId");
			out.writeObject(name);
			out.writeObject(email);
			id = (String)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static String findPw(String id, String pw){
		String check = "";
		try {
			out.writeObject("changePw");
			out.writeObject(id);
			out.writeObject(pw);
			check = (String)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return check;
	}

	public static String changePw(String newPw){
		String check = "";
		try {
			out.writeObject(newPw);
			check = (String)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public static User setUser(User user) {
		try {
			out.writeObject("setUser");
			out.writeObject(user);
			user = (User)in.readObject();
			user.ip = clientSocket.getLocalAddress();
			user.port = clientSocket.getLocalPort();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static String dupCheck(String id, String nickName){
		String check = "";
		try {
			out.writeObject("dupCheck");
			out.writeObject(id);
			out.writeObject(nickName);
			check = (String)in.readObject();		
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public static void signUp(User user){
		try {
			out.writeObject("signUp");
			out.writeObject(user);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    // ���κ���â
    public void showMainBoardView(User user){
        loginView.dispose(); // �α���â�ݱ�
        user = this.setUser(user);
        this.mainboardView = new MainBoardView(user); // �׽�Ʈ������ ����
        this.mainboardView.setMain(loginView.getMain());
    }
    // ȸ������ â
    public void showSignUpView(){
        this.signupView = new SignUpView(); // �׽�Ʈ������ ����
    }
   
    // �� ä��â
    public void showChatRoomView(){
        this.chatroomview = new ChatRoomView(); // �׽�Ʈ������ ����
    }
}

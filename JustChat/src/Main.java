 
import javax.swing.JFrame;
 
public class Main{
    LoginWindow loginView;
    SignUpView signupView;
    ChatRoomView chatroomview;
    
    MainBoardView mainboardView;
    
    String id;
   
    public static void main(String[] args) {
       
        // ����Ŭ���� ����
    	Main main = new Main();
    	new DB_Connection();

        main.loginView = new LoginWindow(); // �α���â ���̱�
        main.loginView.setMain(main); // �α���â���� ���� Ŭ����������
    	
        main.chatroomview = new ChatRoomView();
        main.chatroomview.setMain(main);

        main.mainboardView = new MainBoardView("�¹�");
        main.mainboardView.setMain(main);
    }

    
    // ���κ���â
    public void showMainBoardView(String id){
        loginView.dispose(); // �α���â�ݱ�
        this.mainboardView = new MainBoardView(this.id); // �׽�Ʈ������ ����
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

 
import javax.swing.JFrame;
 
public class Main{
    LoginWindow loginView;
    SignUpView signupView;
    ChatRoomView chatroomview;
    UserInfo userinfo;
    MainBoardView mainboardView;
    
    String id;
   
    public static void main(String[] args) {
       
        // ����Ŭ���� ����
    	Main main = new Main();

        //main.loginView = new LoginWindow(); // �α���â ���̱�
        //main.loginView.setMain(main); // �α���â���� ���� Ŭ����������
    	
       // main.chatroomview = new ChatRoomView();
        //main.chatroomview.setMain(main);

        main.mainboardView = new MainBoardView("�¹�");
        main.mainboardView.setMain(main);
        main.userinfo = new UserInfo("dd");
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

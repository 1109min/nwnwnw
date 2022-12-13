 
import javax.swing.JFrame;
 
public class Main{
    LoginWindow loginView;
    SignUpView signupView;
    ChatRoomView chatroomview;
    UserInfo userinfo;
    MainBoardView mainboardView;
    
    String id;
   
    public static void main(String[] args) {
       
        // 메인클래스 실행
    	Main main = new Main();

        //main.loginView = new LoginWindow(); // 로그인창 보이기
        //main.loginView.setMain(main); // 로그인창에게 메인 클래스보내기
    	
       // main.chatroomview = new ChatRoomView();
        //main.chatroomview.setMain(main);

        main.mainboardView = new MainBoardView("승민");
        main.mainboardView.setMain(main);
        main.userinfo = new UserInfo("dd");
    }

    
    // 메인보드창
    public void showMainBoardView(String id){
        loginView.dispose(); // 로그인창닫기
        this.mainboardView = new MainBoardView(this.id); // 테스트프레임 오픈
    }
    // 회원가입 창
    public void showSignUpView(){
        this.signupView = new SignUpView(); // 테스트프레임 오픈
    }
    
    // 각 채팅창
    public void showChatRoomView(){
        this.chatroomview = new ChatRoomView(); // 테스트프레임 오픈
    }
 
}

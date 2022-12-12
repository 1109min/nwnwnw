import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Objects;

public class ChatRoomView extends JFrame {
	
	static String myId;
	static String roomId;
	
	private Main mymain;
	private Colors colors;
	
	private JPanel infoPanel = new JPanel();
	private JPanel notionPanel = new JPanel();
	private JPanel chatPanel = new JPanel();
	private JPanel enterPanel = new JPanel();
	
	Button_Round loginButton = new Button_Round("�α���");
	Button_Round registerButton = new Button_Round("ȸ������");
    private CardLayout card;

    
	void setMain(Main main) {
		this.mymain = main;
	}
	
	ChatRoomView(){
        Container con = getContentPane();

        setLayout(card = new CardLayout());
        //setUndecorated(true);
        
        setLocationRelativeTo(null);

        setSize(400, 700);
        
        con.setBackground(colors.chat_back);
        //con.setLayout(new FlowLayout());
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        
        infoPanel = new JPanel(new FlowLayout()); //ä��â ���� ä��â ����
        infoPanel.setBackground(colors.chat_back);
        // -----------------------------ä�ù� ���� -------------------------
        Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/kakaotalkmain.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        
        JPanel roomNameCountPanel = new JPanel(new GridLayout(2,1,5,5));
        roomNameCountPanel.setBackground(colors.chat_back);
        JLabel roomName = new JLabel("�̽¹�");
        JLabel roomCount = new JLabel("2");
        roomNameCountPanel.add(roomName);
        roomNameCountPanel.add(roomCount);
        
        JPanel functionLogoPanel = new JPanel(new FlowLayout(2));
        functionLogoPanel.setBackground(colors.chat_back);
        Button_Round function1 = new Button_Round("1");
        function1.setColor(colors.btn_back, colors.btn_text);
        
        Button_Round function2 = new Button_Round("2");
        function2.setColor(colors.btn_back, colors.btn_text);

        Button_Round function3 = new Button_Round("3");
        function3.setColor(colors.btn_back, colors.btn_text);

        functionLogoPanel.add(function1);
        functionLogoPanel.add(function2);
        functionLogoPanel.add(function3); //��ɵ� ���� ��
        
        infoPanel.add(new JLabel(new ImageIcon(logoIcon)));
        infoPanel.add(roomNameCountPanel);
        infoPanel.add(functionLogoPanel);
        infoPanel.setBorder(new EmptyBorder(10,10,10,10));

        
        // -----------------------------ä�ù� ���� -------------------------       
        
        
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));        
        chatPanel.setBackground(colors.chat_back);
        JScrollPane chatScroll = new JScrollPane(chatPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatScroll.getVerticalScrollBar().setUnitIncrement(20);
        //chatPanel.setMinimumSize(new Dimension(400,400));
        
        addChatMessage(this.roomId); // ä�� ���� �ҷ�����
        //ä�� �޽��� ǥ��---------------------------------------------------------------
        for(int i = 0; i < 15; i++) {
	        
			messages new_msg = new messages("�¹�","��¿","����");
			chatPanel.add(new_msg);
			chatPanel.add(new JLabel(""));
		}
        setVisible(true);
        chatScroll.setViewportView(chatPanel);
        chatScroll.setBorder(new EmptyBorder(10,10,10,10));

        //ä�� �޽��� ǥ��-------------------------------------------------------------------
        
        enterPanel = new JPanel();
        enterPanel.setLayout(new BoxLayout(enterPanel, BoxLayout.Y_AXIS));
        enterPanel.setBackground(Color.white);
        enterPanel.setBorder(BorderFactory.createEmptyBorder(30,45,0,45));
        
        JTextField inputField = new JTextField(50);
        inputField.setBorder(new EmptyBorder(10,10,10,10));
       
        Font gainFont = new Font("���� ���", Font.PLAIN, 14);
        Font lostFont = new Font("���� ���", Font.PLAIN, 14);
        inputField.setText("");
        inputField.setFont(gainFont);
        inputField.setForeground(Color.BLACK);
        inputField.setMargin(new Insets(10,10,10,10));
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        Button_Round chatButton = new Button_Round("����");
        chatButton.setColor(colors.light_gray,Color.gray);
        chatButton.setDark(true);
        
        enterPanel.add(inputField);
        enterPanel.add(bottomPanel);
        enterPanel.setBorder(new EmptyBorder(10,10,10,10));

        
        Timer t1=new Timer(100,new ActionListener(){
            public void actionPerformed(ActionEvent e)
                {
    			String checktext1 = inputField.getText();
    			//System.out.println(checktext1);

    			if(!checktext1.equals("�����")) {
    				if(checktext1.length() <1) {
    					chatButton.setColor(colors.light_gray, Color.gray);
    					chatButton.setEnabled(false);
    					enterPanel.repaint();
	    			
	    			}else if(checktext1.length() >=1) {
	    				chatButton.setColor(colors.background, colors.btn_text);
	    				chatButton.setEnabled(true);
	    				enterPanel.repaint();
	
	    			}
    			}
                }
                });
                t1.start();
       
        bottomPanel.add(chatButton);
        bottomPanel.setBackground(colors.chat_other);
        
        add(infoPanel);
        add(chatScroll);
        add(enterPanel);
        
        
        
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
	
	public void addChatMessage(String roomID) {
		
	}
	static class messages extends JPanel {
		Colors colors;
		
		messages(String id, String msg, String time) {
			
			
    		if(id.equals("�¹�")) { //�ڱ� ���̵��� ��
    			setLayout(new FlowLayout(2)); //��� ����
    			setBackground(colors.chat_back);
    			
    			JLabel message = new JLabel();
    			message.setText(msg);
    			message.setBackground(colors.chat_me);
    			message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    			message.setFont(new Font("���� ���", Font.BOLD, 15));
    			message.setOpaque(true);

    			JLabel timeLabel = new JLabel();
    			timeLabel.setText(time);
    			timeLabel.setBackground(colors.chat_back);
    			timeLabel.setForeground(colors.chat_time);
    			timeLabel.setOpaque(true);
    			timeLabel.setFont(new Font("���� ���", Font.BOLD, 11));

    			add(timeLabel);
    			add(message);
    		}else { //Ÿ���� ���̵��� ��
    			setLayout(new FlowLayout(0)); //�·� ����
    			setBackground(colors.chat_back);
    			
    			JLabel name = new JLabel();
    			name.setText(id);
    			name.setBackground(colors.chat_back);
    			name.setFont(new Font("���� ���", Font.BOLD, 13));

    			
    			JLabel message = new JLabel();
    			message.setText(msg);
    			message.setBackground(colors.chat_other);
    			message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    			message.setFont(new Font("���� ���", Font.BOLD, 15));
    			message.setOpaque(true);

    			JLabel timeLabel = new JLabel();
    			timeLabel.setText(time);
    			timeLabel.setBackground(colors.chat_back);
    			timeLabel.setForeground(colors.chat_time);
    			timeLabel.setOpaque(true);
    			timeLabel.setFont(new Font("���� ���", Font.BOLD, 11));
    			
    			add(name);
    			add(message);    			
    			add(timeLabel);
    			
    		}
    		
            
    	}
    }
}
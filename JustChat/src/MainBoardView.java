import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class MainBoardView extends JFrame{
	
	static String myId;
	private Main mymain;
	private Colors colors;
	private static MyMouseListener mouse = new MyMouseListener();
	
	private JPanel topUserInfoPanel = new JPanel();
	private JPanel leftListPanel = new JPanel();
	private JPanel centerChatPanel = new JPanel();
	
	private JScrollPane leftListScroll = new JScrollPane();
	private JScrollPane centerChatScroll = new JScrollPane();

	Button_Round loginButton = new Button_Round("�α���");
	Button_Round registerButton = new Button_Round("ȸ������");
    private CardLayout card;

    
	void setMain(Main main) {
		this.mymain = main;
	}
	
	public MainBoardView(String id) {
        Container con = getContentPane();

        setLocationRelativeTo(null);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        con.setBackground(colors.background);
        setLayout(new BorderLayout());
        
        setTopSide();
        setLeftSide();
        setCenterSide();
        
        
        
        add(topUserInfoPanel,BorderLayout.NORTH);
        add(leftListScroll,BorderLayout.WEST);
        add(centerChatScroll,BorderLayout.CENTER);
        
        setVisible(true);
    }
	
	public void setTopSide() {
		//�������� db��û�ؼ� �� ������ �޾ƿ� - ��ü��
		topUserInfoPanel.setLayout(new FlowLayout(0));
		Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/kakaotalkmain.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        
        JPanel infoPanel = new JPanel(new GridLayout(2,1,5,5));
        infoPanel.setBackground(colors.chat_back);
        
        JLabel userName = new JLabel("�̽¹�");
        JLabel userId = new JLabel("tmdals");
        JLabel loginCount = new JLabel("2");
        JLabel userIntro = new JLabel("�ȳ��ϼ���~");
        
        JPanel topinfo = new JPanel();
        JPanel bottominfo = new JPanel();
        
        topinfo.add(userName);
        topinfo.add(userId);
        topinfo.add(loginCount);
        
        bottominfo.add(userIntro);
               
        infoPanel.add(topinfo);
        infoPanel.add(bottominfo);
        infoPanel.setBorder(new EmptyBorder(10,10,10,10));
        
        Button_Round searchButton = new Button_Round("search");
        
        
        topUserInfoPanel.add(new JLabel(new ImageIcon(logoIcon)));
		topUserInfoPanel.add(infoPanel);
		topUserInfoPanel.add(searchButton);
		
	}
	public void setLeftSide() {
		leftListScroll.getViewport().setBackground(colors.chat_other);
		//���� ���̵�� ģ���� �ٸ� ����ڵ��� ����Ʈ���� ���
		JPanel allList = new JPanel();
		leftListPanel.setLayout(new BoxLayout(leftListPanel,BoxLayout.Y_AXIS));
		leftListPanel.setBackground(colors.chat_other);
		leftListPanel.setOpaque(true);

		
		
		JPanel onlineList = new JPanel();
		onlineList.setLayout(new BoxLayout(onlineList, BoxLayout.Y_AXIS));        
		onlineList.setBackground(colors.chat_back);
		
		JPanel offlineList = new JPanel();
		offlineList.setLayout(new BoxLayout(offlineList, BoxLayout.Y_AXIS));        
		offlineList.setBackground(colors.chat_back);
		
        leftListScroll = new JScrollPane(leftListPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftListScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        //chatPanel.setMinimumSize(new Dimension(400,400));
        
        //ä�� �޽��� ǥ��---------------------------------------------------------------
        for(int i = 0; i < 15; i++) {
	        
			users oneUser = new users("�ѽ�"+i,"gkstmf","���� �����̴�");
			if(i%2!=0) {
				oneUser.setBackground(colors.light_gray);
			}
			leftListPanel.add(oneUser);
		}
        leftListPanel.repaint();
        
        setVisible(true);
        leftListScroll.setViewportView(leftListPanel);
        leftListScroll.setBorder(new EmptyBorder(10,10,10,10));
		
	}
	
	static class users extends JPanel {		
		Colors colors;
		
		users(String name, String id, String intro) {
			setLayout(new GridLayout(1,2,5,5)); //�·� ����
			setBackground(colors.chat_other);
			//setBorder(new LineBorder(Color.gray,1));
			
			JLabel nameLabel = new JLabel();
			nameLabel.setText(name);
			nameLabel.setBackground(colors.chat_other);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			nameLabel.setFont(new Font("���� ���", Font.BOLD, 15));
			nameLabel.setOpaque(false);

			JLabel idLabel = new JLabel();
			idLabel.setText(id);
			idLabel.setBackground(colors.chat_other);
			idLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			idLabel.setFont(new Font("���� ���", Font.BOLD, 13));
			idLabel.setOpaque(false);

			JLabel introLabel = new JLabel();
			introLabel.setText(intro);
			introLabel.setBackground(colors.chat_other);
			introLabel.setForeground(Color.black);
			introLabel.setOpaque(false);
			introLabel.setFont(new Font("���� ���", Font.BOLD, 12));
			
			JPanel nameIdPanel = new JPanel(new FlowLayout());
			nameIdPanel.setBackground(colors.transparent);
			nameIdPanel.add(nameLabel);
			nameIdPanel.add(idLabel);
			
			add(nameIdPanel);
			add(introLabel);
			
			addMouseListener(mouse);
		}
		
	}
	
	public void setCenterSide() {
		centerChatScroll.getViewport().setBackground(colors.chat_other);
		//���� ���̵�� ģ���� �ٸ� ����ڵ��� ����Ʈ���� ���
		centerChatPanel.setLayout(new BoxLayout(centerChatPanel,BoxLayout.Y_AXIS));
		centerChatPanel.setBackground(colors.chat_other);
		centerChatPanel.setOpaque(true);

		
		
		JPanel onlineList = new JPanel();
		onlineList.setLayout(new BoxLayout(onlineList, BoxLayout.Y_AXIS));        
		onlineList.setBackground(colors.chat_back);
		
		JPanel offlineList = new JPanel();
		offlineList.setLayout(new BoxLayout(offlineList, BoxLayout.Y_AXIS));        
		offlineList.setBackground(colors.chat_back);
		
		centerChatScroll = new JScrollPane(centerChatPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerChatScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        //chatPanel.setMinimumSize(new Dimension(400,400));
        
		//ä�� ����� ��������
		for(int i = 0; i < 15; i++) {
	        String[] users = new String[2];
	        users[0] = "�¹�";
	        users[1] = "�ѽ�";
			chatRooms oneRoom = new chatRooms("52352"+i,users,"�� ����� �׳�");
			if(i%2!=0) {
				oneRoom.setBackground(colors.light_gray);
			}
			oneRoom.addMouseListener(new chatListener());
			centerChatPanel.add(oneRoom);
		}
        centerChatPanel.repaint();
        
        setVisible(true);
        centerChatScroll.setViewportView(centerChatPanel);
        centerChatScroll.setBorder(new EmptyBorder(10,10,10,10));
	}
	
	static class chatRooms extends JPanel {		
		Colors colors;
		
		chatRooms(String roomId, String[] id, String lastMessage) {
			setLayout(new GridLayout(2,1,5,5)); //�·� ����
			setBackground(colors.chat_other);
			//setBorder(new LineBorder(Color.gray,1));
			
			JLabel nameLabel = new JLabel();
			nameLabel.setText(roomId);
			nameLabel.setBackground(colors.chat_other);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			nameLabel.setFont(new Font("���� ���", Font.BOLD, 15));
			nameLabel.setOpaque(false);

			JLabel idLabel = new JLabel();
			idLabel.setText(id[0]+id[1]);
			idLabel.setBackground(colors.chat_other);
			idLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			idLabel.setFont(new Font("���� ���", Font.BOLD, 13));
			idLabel.setOpaque(false);

			JLabel introLabel = new JLabel();
			introLabel.setText(lastMessage);
			introLabel.setBackground(colors.chat_other);
			introLabel.setForeground(Color.black);
			introLabel.setOpaque(false);
			introLabel.setFont(new Font("���� ���", Font.BOLD, 12));
			
			JPanel nameIdPanel = new JPanel(new FlowLayout());
			nameIdPanel.setBackground(colors.transparent);
			nameIdPanel.add(nameLabel);
			nameIdPanel.add(idLabel);
			
			add(nameIdPanel);
			add(introLabel);
						
		}

		
		
	}
	class chatListener implements MouseListener, MouseMotionListener{
		
		@Override
		public void mouseEntered(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			p.setBackground(color.darker());
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			p.setBackground(color.brighter());			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			mymain.showChatRoomView();
			
			p.setBackground(color.darker());				
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			p.setBackground(color.darker());			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			p.setBackground(color.brighter().brighter().brighter());		
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}


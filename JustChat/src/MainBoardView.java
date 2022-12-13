import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class MainBoardView extends JFrame{
	
	static String myId;
	private Main mymain;
	private Colors colors;
	private static MyMouseListener mouse = new MyMouseListener();
	
	private JPanel topUserInfoPanel = new JPanel();
	
	private JPanel leftListPanel = new JPanel();
	private JPanel leftListPanel1 = new JPanel();
	private JPanel leftListPanel2 = new JPanel();
	
	private JPanel rightListPanel = new JPanel();
	private JPanel rightListPanel1 = new JPanel();
	private JPanel rightListPanel2 = new JPanel();
	
	private JPanel BottomSidePanel = new JPanel();

	private JPanel centerChatPanel = new JPanel();
	
	private JScrollPane leftListScroll = new JScrollPane();
	private JScrollPane leftListScrollOff = new JScrollPane();
	
	private JScrollPane rightListScroll = new JScrollPane();
	
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
        setSize(1000, 700);
        setLocationRelativeTo(null);

        con.setBackground(colors.background);
        setLayout(new BorderLayout());
        
        setTopSide();
        setLeftSide();
        setCenterSide();
        setBottomSide();
        setRightSide("");
        
        JPanel mergeCenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.weightx = 0.15;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mergeCenterPanel.add(leftListPanel,gbc);
                
        gbc.weightx = 0.7;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        mergeCenterPanel.add(centerChatScroll,gbc);
        
        gbc.weightx = 0.15;
        gbc.weighty = 1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        mergeCenterPanel.add(rightListPanel,gbc);
        
        add(topUserInfoPanel,BorderLayout.NORTH);
//        add(leftListPanel,BorderLayout.WEST);
//        add(centerChatScroll,BorderLayout.CENTER);
//        add(rightListScroll,BorderLayout.EAST);
        add(mergeCenterPanel,BorderLayout.CENTER);
        add(BottomSidePanel,BorderLayout.SOUTH);

        //add(BottomSidePanel,BorderLayout.EAST);
        
        setVisible(true);
    }
	
	public void setTopSide() {
		//�������� db��û�ؼ� �� ������ �޾ƿ� - ��ü��
		topUserInfoPanel.setLayout(new GridLayout(1,3));
		JPanel topUserInfoLeftPanel = new JPanel(new FlowLayout(0));
		
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
        
        Font gainFont = new Font("���� ���", Font.PLAIN, 13);
        Font lostFont = new Font("���� ���", Font.PLAIN, 13);
        
		JPanel topUserInfoRightPanel = new JPanel(new FlowLayout(2));

        
        JTextField searchField = new JTextField(18);
        searchField.setBorder(new EmptyBorder(10,10,10,10));
        searchField.setMargin(new Insets(10,10,10,10));
        
        searchField.setText("���̵� �Ǵ� �г����� �˻��ϼ���");
        searchField.setFont(lostFont);
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {	// �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

            @Override
            public void focusLost(FocusEvent e) {	// ��Ŀ���� �Ҿ��� ��,
                if (searchField.getText().equals("")) {
                	searchField.setText("���̵� �Ǵ� �г����� �˻��ϼ���");
                	searchField.setFont(lostFont);
                	searchField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {	// ��Ŀ���� ����� ��,
                if (searchField.getText().equals("���̵� �Ǵ� �г����� �˻��ϼ���")) {
                	searchField.setText("");
                	searchField.setFont(gainFont);
                	searchField.setForeground(Color.BLACK);
                }
            }
        });
        //�˻���ư ���� ��
        Button_Round searchButton = new Button_Round("�˻�");
        searchButton.setRound(20,20);
        searchButton.setFont(gainFont);
        searchButton.setColor(colors.btn_back, colors.btn_text);
        searchButton.setFont(new Font("���� ���", Font.BOLD, 15));
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String wantSearch = searchField.getText();
            	setRightSide(wantSearch);
                 // id�� ���̵�, newPw�� �ٲ� ��й�ȣ�� ��
                 // ���̵� ���� �����ؼ� ��й�ȣ�� newPw�� �ٲ��ָ� �˴ϴ�
            }
        });
        
        
        
        
        topUserInfoLeftPanel.add(new JLabel(new ImageIcon(logoIcon)));
        topUserInfoLeftPanel.add(infoPanel);
		topUserInfoRightPanel.add(searchField);
		topUserInfoRightPanel.add(searchButton);
		
		topUserInfoPanel.add(topUserInfoLeftPanel);
		topUserInfoPanel.add(topUserInfoRightPanel);

	}
	public void setLeftSide() {
		leftListScroll.getViewport().setBackground(colors.light_gray);
		leftListScroll.getVerticalScrollBar().setUnitIncrement(20);

		leftListScrollOff.getViewport().setBackground(colors.chat_other); //offline
		leftListScrollOff.getVerticalScrollBar().setUnitIncrement(20);

		//���� ���̵�� ģ���� �ٸ� ����ڵ��� ����Ʈ���� ���
		JPanel allList = new JPanel();
		leftListPanel.setLayout(new GridLayout(2,1));
		
		JPanel onlinePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        
		JPanel ButtonPanel = new JPanel(new GridLayout(0,2,10,0));

        gbc.weightx = 0.4;
        gbc.weighty = 0.2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        Button_Round onlineButton = new Button_Round();
        onlineButton.setColor(colors.btn_back, colors.btn_text);
        onlineButton.setRound(20, 20);
        onlineButton.setText("�¶���");
        onlineButton.setFont(new Font("���� ���", Font.BOLD, 18));

        ButtonPanel.add(onlineButton);
        
        gbc.weightx = 0.4;
        gbc.weighty = 0.2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        Button_Round offlineButton = new Button_Round();
        offlineButton.setColor(colors.btn_back, colors.btn_text);
        offlineButton.setRound(20, 20);
        offlineButton.setText("��������");
        offlineButton.setFont(new Font("���� ���", Font.BOLD, 18));

        ButtonPanel.add(offlineButton);
        
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        //onlinePanel.add(ButtonPanel,gbc);//��ư�� �� �� ���� ���� ��
        
        onlinePanel.add(onlineButton,gbc); //���� ���� ��

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        onlinePanel.add(leftListScroll,gbc); 

		leftListPanel.add(onlinePanel);
		
		JPanel offlinePanel = new JPanel(new GridBagLayout());
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        offlinePanel.add(offlineButton,gbc);

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        offlinePanel.add(leftListScrollOff,gbc); 

		leftListPanel.add(offlinePanel);
		
        
		//leftListPanel.add(leftListScrollOff);
		
		leftListPanel1.setLayout(new BoxLayout(leftListPanel1,BoxLayout.Y_AXIS));
		leftListPanel1.setBackground(colors.chat_other);
		leftListPanel1.setOpaque(true);
		
		leftListPanel2.setLayout(new BoxLayout(leftListPanel2,BoxLayout.Y_AXIS));
		leftListPanel2.setBackground(colors.chat_other);
		leftListPanel2.setOpaque(true);
		
		
		JPanel onlineList = new JPanel();
		onlineList.setLayout(new BoxLayout(onlineList, BoxLayout.Y_AXIS));        
		onlineList.setBackground(colors.chat_back);
		
		
		JPanel offlineList = new JPanel();
		offlineList.setLayout(new BoxLayout(offlineList, BoxLayout.Y_AXIS));        
		offlineList.setBackground(colors.chat_back);
		
        //leftListScroll = new JScrollPane(leftListPanel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //leftListScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        //chatPanel.setMinimumSize(new Dimension(400,400));
        
        //ä�� �޽��� ǥ��---------------------------------------------------------------
        for(int i = 0; i < 15; i++) {
	        
			users oneUser = new users("�ѽ�"+i,"gkstmf","���� �����̴�");
			if(i%2!=0) {
				//oneUser.setBackground(colors.light_gray);

			}
			
			leftListPanel1.add(oneUser);
		}
        leftListPanel1.repaint();
        
        for(int i = 0; i < 15; i++) {
	        
			users oneUser = new users("����"+i,"tjrgus","���� ī�����̴�");
			if(i%2!=0) {
				//oneUser.setBackground(colors.light_gray);
			}
			leftListPanel2.add(oneUser);
		}
        leftListPanel2.repaint();
        
        
        leftListScroll.setViewportView(leftListPanel1);
        leftListScroll.setBorder(new EmptyBorder(0,0,2,0));
        
        leftListScrollOff.setViewportView(leftListPanel2);
        leftListScrollOff.setBorder(new EmptyBorder(2,0,10,0));
		
	}
	public void setRightSide(String wantSearch) {
		rightListScroll.getViewport().setBackground(colors.chat_other);
		rightListScroll.getVerticalScrollBar().setUnitIncrement(20);

		JPanel chatPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        Button_Round chatButton = new Button_Round();
        chatButton.setColor(colors.btn_back, colors.btn_text);
        chatButton.setRound(20, 20);
        chatButton.setText("�˻� ���");
        chatButton.setFont(new Font("���� ���", Font.BOLD, 18));
        chatPanel.add(chatButton,gbc);

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 9;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        chatPanel.add(rightListScroll,gbc); 

        rightListPanel.setLayout(new GridLayout(1,0));
        rightListPanel.add(chatPanel);

        rightListPanel1.setLayout(new BoxLayout(rightListPanel1,BoxLayout.Y_AXIS));

		rightListPanel1.setBackground(colors.chat_other);
		rightListPanel1.setOpaque(true);
			
        
        //ä�� �޽��� ǥ��---------------------------------------------------------------
        //���⼭ wantSearch�� �̿��� ����� ã�� 
		
		
		for(int i = 0; i < 15; i++) {
	        
			users searchUser = new users("�ѽ�"+i,"gkstmf","���� �����̴�");
			if(i%2!=0) {
				//searchUser.setBackground(colors.light_gray);
			}
			rightListPanel1.add(searchUser);
		}
        rightListPanel1.repaint();
  
        
        rightListScroll.setViewportView(rightListPanel1);
        rightListScroll.setBorder(new EmptyBorder(0,0,10,0));
        		
	}
	
	static class users extends JPanel {		
		Colors colors;
		
		users(String name, String id, String intro) {
			
			
			setLayout(new GridLayout(1,2,5,5)); //�·� ����
			setBackground(colors.chat_other);
			setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
			
			JLabel nameLabel = new JLabel();
			nameLabel.setText(name);
			nameLabel.setBackground(colors.chat_other);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
			nameLabel.setFont(new Font("���� ���", Font.PLAIN, 13));
			nameLabel.setOpaque(false);

			JLabel idLabel = new JLabel();
			idLabel.setText(id);
			idLabel.setBackground(colors.chat_other);
			idLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
			idLabel.setFont(new Font("���� ���", Font.PLAIN, 12));
			idLabel.setOpaque(false);

			JLabel introLabel = new JLabel();
			introLabel.setText(intro);
			introLabel.setBackground(colors.chat_other);
			introLabel.setForeground(Color.gray);
			introLabel.setOpaque(false);
			introLabel.setFont(new Font("���� ���", Font.PLAIN, 11));
			
			JPanel nameIdPanel = new JPanel(new GridLayout(2,1));
			nameIdPanel.setBackground(colors.transparent);
			nameIdPanel.add(nameLabel);
			nameIdPanel.add(idLabel);
			
			add(nameIdPanel);
			add(introLabel);
			
			addMouseListener(mouse);
			
			//��Ŭ�� �̺�Ʈ
			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem detail = new JMenuItem("������ ����");
			detail.setFont(new Font("���� ���", Font.PLAIN, 12));

			JMenuItem chat = new JMenuItem("1:1 ä���ϱ�");
			chat.setFont(new Font("���� ���", Font.PLAIN, 12));

			JMenuItem file = new JMenuItem("���� �����ϱ�");
			file.setFont(new Font("���� ���", Font.PLAIN, 12));


			//JLabel detailsLabel = new JLabel("������ ����");
			//menus.add(detailsLabel);
			
		      popupMenu.add(detail);
		      popupMenu.add(chat);
		      popupMenu.add(file);

		      setComponentPopupMenu(popupMenu);
		      
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
				//oneRoom.setBackground(colors.light_gray);
			}
			oneRoom.addMouseListener(new chatListener());
			centerChatPanel.add(oneRoom);
		}
        centerChatPanel.repaint();
        
        setVisible(true);
        centerChatScroll.setViewportView(centerChatPanel);
        centerChatScroll.setBorder(new EmptyBorder(10,0,10,0));
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
			nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 2, 10));
			nameLabel.setFont(new Font("���� ���", Font.BOLD, 15));
			nameLabel.setOpaque(false);

			JLabel idLabel = new JLabel();
			idLabel.setText(id[0]+id[1]);
			idLabel.setBackground(colors.chat_other);
			idLabel.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 10));
			idLabel.setFont(new Font("���� ���", Font.BOLD, 13));
			idLabel.setOpaque(false);

			JLabel introLabel = new JLabel();
			introLabel.setText(lastMessage);
			introLabel.setBackground(colors.chat_other);
			introLabel.setForeground(Color.black);
			introLabel.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 10));

			introLabel.setOpaque(false);
			introLabel.setFont(new Font("���� ���", Font.BOLD, 12));
			
			JPanel nameIdPanel = new JPanel(new FlowLayout(0));
			nameIdPanel.setBackground(colors.transparent);
			nameIdPanel.add(nameLabel);
			nameIdPanel.add(idLabel);
			
			add(nameIdPanel);
			add(introLabel);
						
		}

		
		
	}
	
	
	public void setBottomSide() {
		BottomSidePanel.setLayout(new FlowLayout());
		
		JLabel test = new JLabel();
		test.setText("Dddddddddddddddddddddddddd");
		test.setBackground(colors.background);
		test.setOpaque(true);
		BottomSidePanel.add(test);
		
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


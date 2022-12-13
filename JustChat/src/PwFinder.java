import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PwFinder extends JFrame {
    PwFinder() {
        new InputInfo();
    }
    
    static String id;
    static String newPw = "";
    static Colors colors;
    
    static class InputInfo extends JFrame {
        InputInfo() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(colors.background);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(colors.background);

            JLabel inputDesc = new JLabel("비밀번호 찾기");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel inputInfoDesc = new JLabel("아이디를 입력하세요");
            inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
            inputInfoDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            
            JTextField inputId = new JTextField();
            
            Font gainFont = new Font("맑은 고딕", Font.PLAIN, 15);
            Font lostFont = new Font("맑은 고딕", Font.PLAIN, 15);
            inputId.setBorder(new EmptyBorder(10,10,10,10));
            inputId.setMargin(new Insets(10,10,10,10));
            inputId.setText("아이디를 입력하세요");
            inputId.setFont(lostFont);
            inputId.setForeground(Color.GRAY);
            inputId.addFocusListener(new FocusListener() {	// 텍스트 필드 포커스 시 이벤트

                @Override
                public void focusLost(FocusEvent e) {	// 포커스를 잃었을 때,
                    if (inputId.getText().equals("")) {
                    	inputId.setText("아이디를 입력하세요");
                    	inputId.setFont(lostFont);
                    	inputId.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// 포커스를 얻었을 때,
                    if (inputId.getText().equals("아이디를 입력하세요")) {
                    	inputId.setText("");
                    	inputId.setFont(gainFont);
                    	inputId.setForeground(Color.BLACK);
                    }
                }
            });
            
            JPasswordField inputPw = new JPasswordField(20);
            inputPw.setBorder(new EmptyBorder(10,10,10,10));
            inputPw.setEchoChar((char)0);

            inputPw.setMargin(new Insets(10,10,10,10));
            
            inputPw.setText("현재 비밀번호를 입력하세요");
            inputPw.setFont(lostFont);
            inputPw.setForeground(Color.GRAY);
            inputPw.addFocusListener(new FocusListener() {	// 텍스트 필드 포커스 시 이벤트

                @Override
                public void focusLost(FocusEvent e) {	// 포커스를 잃었을 때,
                    if (inputPw.getText().equals("")) {
                    	inputPw.setEchoChar((char)0);
                    	inputPw.setText("비밀번호를 입력하세요");
                    	inputPw.setFont(lostFont);
                    	inputPw.setForeground(Color.GRAY);
                    }else {
                    	inputPw.setEchoChar('*');
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// 포커스를 얻었을 때,
                    if (inputPw.getText().equals("현재 비밀번호를 입력하세요")) {
                    	inputPw.setText("");
                    	inputPw.setEchoChar('*');
                    	inputPw.setFont(gainFont);
                    	inputPw.setForeground(Color.BLACK);
                    }
                }
            });

            Button_Round toChangePw = new Button_Round("다음");
            toChangePw.setColor(Color.white, Color.gray);
            toChangePw.setBackground(new Color(0x371D1E)); 
            toChangePw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
            toChangePw.setForeground(Color.WHITE);
            
            toChangePw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	//실험용
                	id = inputId.getText();
                    dispose();
                    new ChangePw();
                    //
                    
                    /* 실제 코드
                     * if(id pw)가 타당한 유저정보일 때,
                     * id = inputId,getText();
                     * dispose();
                     * new ChangePw();
                     * 
                     * 
                     * 
                     */
                    
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
        			String checktext1 = inputId.getText();
        			char[] checktext2 = inputPw.getPassword();
        			String checkPw = String.valueOf(checktext2);
        			
        			if(!checktext1.equals("아이디를 입력하세요") && !checkPw.equals("비밀번호를 입력하세요")) {
    	            	if(checktext1.length()<3 || checkPw.length()<3) {        					toChangePw.setColor(Color.white, Color.gray);
        					toChangePw.setEnabled(false);
        					inputPanel.repaint();
    	    			
    	    			}else if(checktext1.length() >=3 && checkPw.length() >= 3) {
    	    				toChangePw.setColor(colors.btn_back, colors.btn_text);
    	    				toChangePw.setEnabled(true);
    	    				inputPanel.repaint();
    	
    	    			}
        			}
                    }
                    });
                    t1.start();
                    
            inputPanel.add(inputDesc);
            inputPanel.add(inputInfoDesc);
            inputPanel.add(inputId);
            inputPanel.add(inputPw);
            inputPanel.add(toChangePw);

            JPanel margin = new JPanel();
            margin.setBackground(colors.background);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    static class ChangePw extends JFrame {
    	Colors colors;
    	
        ChangePw() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(colors.background);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(colors.background);

            JLabel inputDesc = new JLabel("비밀번호 변경");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("새 비밀번호를 입력하세요");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

            JPasswordField inputPw = new JPasswordField();
            Font gainFont = new Font("맑은 고딕", Font.PLAIN, 14);
            Font lostFont = new Font("맑은 고딕", Font.PLAIN, 14);
            
            inputPw.setBorder(new EmptyBorder(10,10,10,10));
            inputPw.setMargin(new Insets(10,10,10,10));
            inputPw.setEchoChar((char)0);
            inputPw.setText("비밀번호를 입력하세요");
            inputPw.setFont(lostFont);
            inputPw.setForeground(Color.GRAY);
            
            
            Button_Round toLogin = new Button_Round("변경 완료");
            toLogin.setColor(Color.white, Color.gray);
            toLogin.setBackground(new Color(0x371D1E));
            toLogin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
            toLogin.setForeground(Color.WHITE);
            // ---------------------- 비밀번호 변경 ----------------------------
            toLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	for(char pwd : inputPw.getPassword()) {
                		newPw += pwd;
                	}
                	if (DB_Connection.UpdatePw(id, newPw) == 0) {
            			System.out.println("password is updated");
            			dispose();
            			new LoginWindow();                		
                	}
	                 // id에 아이디, newPw에 바꿀 비밀번호가 들어감
	                 // 아이디를 통해 접근해서 비밀번호를 newPw로 바꿔주면 됩니다
                }
            });
            
            inputPw.addFocusListener(new FocusListener() {	// 텍스트 필드 포커스 시 이벤트

                @Override
                public void focusLost(FocusEvent e) {	// 포커스를 잃었을 때,
                    if (inputPw.getText().equals("")) {
                    	inputPw.setEchoChar((char)0);
                    	inputPw.setText("비밀번호를 입력하세요");
                    	inputPw.setFont(lostFont);
                    	inputPw.setForeground(Color.GRAY);
                    }else {
                    	inputPw.setEchoChar('*');
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// 포커스를 얻었을 때,
                    if (inputPw.getText().equals("비밀번호를 입력하세요")) {
                    	inputPw.setText("");
                    	inputPw.setEchoChar('*');
                    	inputPw.setFont(gainFont);
                    	inputPw.setForeground(Color.BLACK);
                    }
                }
            });
            
            
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
        			String checktext1 = inputPw.getText();
        			//System.out.println(checktext1 + checkPw);

        			if(!checktext1.equals("새 비밀번호를 입력하세요")) {
        				if(checktext1.length() <3) {
        					toLogin.setColor(Color.white, Color.gray);
        					toLogin.setEnabled(false);
        				inputPanel.repaint();
    	    			
    	    			}else if(checktext1.length() >=3) {
    	    				toLogin.setColor(colors.btn_back, colors.btn_text);
    	    				toLogin.setEnabled(true);
    	    				inputPanel.repaint();
    	
    	    			}
        			}
                    }
                    });
                    t1.start();
            // ---------------------- 비밀번호 변경 ----------------------------

            inputPanel.add(inputDesc);
            inputPanel.add(inputPwDesc);
            inputPanel.add(inputPw);
            inputPanel.add(toLogin);

            JPanel margin = new JPanel();
            margin.setBackground(colors.background);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}

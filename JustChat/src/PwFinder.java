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

            JLabel inputDesc = new JLabel("��й�ȣ ã��");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputInfoDesc = new JLabel("���̵� �Է��ϼ���");
            inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
            inputInfoDesc.setFont(new Font("���� ���", Font.PLAIN, 12));
            
            JTextField inputId = new JTextField();
            
            Font gainFont = new Font("���� ���", Font.PLAIN, 15);
            Font lostFont = new Font("���� ���", Font.PLAIN, 15);
            inputId.setBorder(new EmptyBorder(10,10,10,10));
            inputId.setMargin(new Insets(10,10,10,10));
            inputId.setText("���̵� �Է��ϼ���");
            inputId.setFont(lostFont);
            inputId.setForeground(Color.GRAY);
            inputId.addFocusListener(new FocusListener() {	// �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {	// ��Ŀ���� �Ҿ��� ��,
                    if (inputId.getText().equals("")) {
                    	inputId.setText("���̵� �Է��ϼ���");
                    	inputId.setFont(lostFont);
                    	inputId.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// ��Ŀ���� ����� ��,
                    if (inputId.getText().equals("���̵� �Է��ϼ���")) {
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
            
            inputPw.setText("���� ��й�ȣ�� �Է��ϼ���");
            inputPw.setFont(lostFont);
            inputPw.setForeground(Color.GRAY);
            inputPw.addFocusListener(new FocusListener() {	// �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {	// ��Ŀ���� �Ҿ��� ��,
                    if (inputPw.getText().equals("")) {
                    	inputPw.setEchoChar((char)0);
                    	inputPw.setText("��й�ȣ�� �Է��ϼ���");
                    	inputPw.setFont(lostFont);
                    	inputPw.setForeground(Color.GRAY);
                    }else {
                    	inputPw.setEchoChar('*');
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// ��Ŀ���� ����� ��,
                    if (inputPw.getText().equals("���� ��й�ȣ�� �Է��ϼ���")) {
                    	inputPw.setText("");
                    	inputPw.setEchoChar('*');
                    	inputPw.setFont(gainFont);
                    	inputPw.setForeground(Color.BLACK);
                    }
                }
            });

            Button_Round toChangePw = new Button_Round("����");
            toChangePw.setColor(Color.white, Color.gray);
            toChangePw.setBackground(new Color(0x371D1E)); 
            toChangePw.setFont(new Font("���� ���", Font.BOLD, 15));
            toChangePw.setForeground(Color.WHITE);
            
            toChangePw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	//�����
                	id = inputId.getText();
                    dispose();
                    new ChangePw();
                    //
                    
                    /* ���� �ڵ�
                     * if(id pw)�� Ÿ���� ���������� ��,
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
        			
        			if(!checktext1.equals("���̵� �Է��ϼ���") && !checkPw.equals("��й�ȣ�� �Է��ϼ���")) {
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

            JLabel inputDesc = new JLabel("��й�ȣ ����");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("�� ��й�ȣ�� �Է��ϼ���");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JPasswordField inputPw = new JPasswordField();
            Font gainFont = new Font("���� ���", Font.PLAIN, 14);
            Font lostFont = new Font("���� ���", Font.PLAIN, 14);
            
            inputPw.setBorder(new EmptyBorder(10,10,10,10));
            inputPw.setMargin(new Insets(10,10,10,10));
            inputPw.setEchoChar((char)0);
            inputPw.setText("��й�ȣ�� �Է��ϼ���");
            inputPw.setFont(lostFont);
            inputPw.setForeground(Color.GRAY);
            
            
            Button_Round toLogin = new Button_Round("���� �Ϸ�");
            toLogin.setColor(Color.white, Color.gray);
            toLogin.setBackground(new Color(0x371D1E));
            toLogin.setFont(new Font("���� ���", Font.BOLD, 15));
            toLogin.setForeground(Color.WHITE);
            // ---------------------- ��й�ȣ ���� ----------------------------
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
	                 // id�� ���̵�, newPw�� �ٲ� ��й�ȣ�� ��
	                 // ���̵� ���� �����ؼ� ��й�ȣ�� newPw�� �ٲ��ָ� �˴ϴ�
                }
            });
            
            inputPw.addFocusListener(new FocusListener() {	// �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {	// ��Ŀ���� �Ҿ��� ��,
                    if (inputPw.getText().equals("")) {
                    	inputPw.setEchoChar((char)0);
                    	inputPw.setText("��й�ȣ�� �Է��ϼ���");
                    	inputPw.setFont(lostFont);
                    	inputPw.setForeground(Color.GRAY);
                    }else {
                    	inputPw.setEchoChar('*');
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// ��Ŀ���� ����� ��,
                    if (inputPw.getText().equals("��й�ȣ�� �Է��ϼ���")) {
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

        			if(!checktext1.equals("�� ��й�ȣ�� �Է��ϼ���")) {
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
            // ---------------------- ��й�ȣ ���� ----------------------------

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

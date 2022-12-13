import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SignUpView extends JFrame {
    SignUpView() {
        new RegisterName();
    }
    
    static String name;
    static String id;
    static String pw = "";
    
    // �̸� �Է�
    static class RegisterName extends JFrame {
    	Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);

        RegisterName() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(yellow);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(yellow);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputNameDesc = new JLabel("�̸��� �Է��ϼ���");
            inputNameDesc.setHorizontalAlignment(JLabel.CENTER);
            inputNameDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JTextField inputName = new JTextField();
            inputName.setBorder(new EmptyBorder(10,10,10,10));

            Button_Round toInputId = new Button_Round("����");

            toInputId.setColor(Color.white, Color.gray);
            toInputId.setBackground(new Color(0x371D1E));
            toInputId.setFont(new Font("���� ���", Font.BOLD, 15));
            toInputId.setForeground(Color.WHITE);
           
            toInputId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	name = inputName.getText();
                    dispose();
                    new RegisterId();
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
        			String checktext1 = inputName.getText();
        			//System.out.println(checktext1);

        			if(!checktext1.equals("���̵� �Է��ϼ���")) {
        				if(checktext1.length() <3) {
        					toInputId.setColor(Color.white, Color.gray);
        					toInputId.setEnabled(false);
        				inputPanel.repaint();
    	    			
    	    			}else if(checktext1.length() >=3) {
    	    				toInputId.setColor(btn_back, btn_text);
    	    				toInputId.setEnabled(true);
    	    				inputPanel.repaint();
    	
    	    			}
        			}
                    }
                    });
                    t1.start();
            inputPanel.add(inputDesc);
            inputPanel.add(inputNameDesc);
            inputPanel.add(inputName);
            inputPanel.add(toInputId);

            JPanel margin = new JPanel();
            margin.setBackground(yellow);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    // ID �Է�
    static class RegisterId extends JFrame {
    	Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);

        RegisterId() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(yellow);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(yellow);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputIdDesc = new JLabel("���̵� �Է��ϼ���");
            inputIdDesc.setHorizontalAlignment(JLabel.CENTER);
            inputIdDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JTextField inputId = new JTextField();
            inputId.setBorder(new EmptyBorder(10,10,10,10));

            Button_Round toInputPw = new Button_Round("����");
            toInputPw.setColor(Color.white, Color.gray);
            toInputPw.setBackground(new Color(0x371D1E));
            toInputPw.setFont(new Font("���� ���", Font.BOLD, 15));
            toInputPw.setForeground(Color.WHITE);
            
            toInputPw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	//���̵� �ߺ�ó��
                	/*
                	 * ���� �ߺ��� ���̵�� �Ѿ�� ����.
                	 * �ߺ���ǥ��
                	 * inputIdDesc.setText("�ߺ��� ���̵��Դϴ�");
                	 * inputIdDesc.setBackground(Color.RED);
                	 * 
                	 * 
                	 * else{
                	 */
                	inputIdDesc.setText("���̵� �Է��ϼ���");
                	id = inputId.getText();
                    dispose();
                    new RegisterPw();
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
        			String checktext1 = inputId.getText();
        			//System.out.println(checktext1 + checkPw);

        			if(!checktext1.equals("���̵� �Է��ϼ���")) {
        				if(checktext1.length() <3) {
        				toInputPw.setColor(Color.white, Color.gray);
        				toInputPw.setEnabled(false);
        				inputPanel.repaint();
    	    			
    	    			}else if(checktext1.length() >=3) {
    	    				toInputPw.setColor(btn_back, btn_text);
    	    				toInputPw.setEnabled(true);
    	    				inputPanel.repaint();
    	
    	    			}
        			}
                    }
                    });
                    t1.start();
            inputPanel.add(inputDesc);
            inputPanel.add(inputIdDesc);
            inputPanel.add(inputId);
            inputPanel.add(toInputPw);

            JPanel margin = new JPanel();
            margin.setBackground(yellow);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    // ��й�ȣ �Է�
    static class RegisterPw extends JFrame {
    	Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);

        RegisterPw() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(yellow);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(yellow);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("��й�ȣ�� �Է��ϼ���");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            Font gainFont = new Font("���� ���", Font.PLAIN, 14);
            Font lostFont = new Font("���� ���", Font.PLAIN, 14);
            
            JPasswordField inputPw = new JPasswordField();
            inputPw.setBorder(new EmptyBorder(10,10,10,10));
        	inputPw.setEchoChar('*');
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
                    }else {
                    	//inputPw.setEchoChar('*');

                    }
                }
            });
            
            Button_Round registerFinish = new Button_Round("ȸ������");
            registerFinish.setColor(Color.white, Color.gray);
            registerFinish.setBackground(new Color(0x371D1E));
            registerFinish.setFont(new Font("���� ���", Font.BOLD, 15));
            registerFinish.setForeground(Color.WHITE);
            
            // ---------------------- ȸ������ ----------------------------
            registerFinish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	for(char pwd : inputPw.getPassword()) {
                		pw += pwd;
                	}
                	
                	if(DB_Connection.checkSign(id, pw) == 0) {
                		System.out.println("new register");
                		dispose();
                		new LoginWindow();                		
                	}

                    // name, id, pw�� ���� ������ �Է��� �̸�, ���̵�, ��й�ȣ�� �����
                    // ����� �������� ���� ���� ���̺� �ű� ���� ��� 
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
        			char[] checktext2 = inputPw.getPassword();
        			String checkPw = String.valueOf(checktext2);

        			if(!checkPw.equals("��й�ȣ�� �Է��ϼ���")) {
    	            	if(checkPw.length()<3) {
    	            		registerFinish.setColor(Color.white, Color.gray);
    	            		registerFinish.setEnabled(false);
    	            		inputPanel.repaint();
    	    			
    	    			}else if(checkPw.length() >= 3) {
    	    				registerFinish.setColor(btn_back, btn_text);
    	    				registerFinish.setEnabled(true);
    	    				inputPanel.repaint();
    	
    	    			}
        			}
                    }
                    });
                    t1.start();
            
            // ---------------------- ȸ������ ----------------------------
            
            inputPanel.add(inputDesc);
            inputPanel.add(inputPwDesc);
            inputPanel.add(inputPw);
            inputPanel.add(registerFinish);

            JPanel margin = new JPanel();
            margin.setBackground(yellow);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
    
    
}

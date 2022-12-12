import javax.swing.*;
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

    static class InputInfo extends JFrame {
        InputInfo() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("��й�ȣ ã��");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ����", Font.BOLD, 24));

            JLabel inputInfoDesc = new JLabel("���̵� �Է��ϼ���");
            inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
            inputInfoDesc.setFont(new Font("���� ����", Font.PLAIN, 12));
            
            JTextField inputId = new JTextField();
            
            Font gainFont = new Font("���� ����", Font.PLAIN, 15);
            Font lostFont = new Font("���� ����", Font.PLAIN, 15);

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

            JButton toChangePw = new JButton("����");
            toChangePw.setBackground(new Color(29,161,242));
            toChangePw.setFont(new Font("���� ����", Font.BOLD, 12));
            toChangePw.setForeground(Color.WHITE);
            toChangePw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	id = inputId.getText();
                    dispose();
                    new ChangePw();
                }
            });

            inputPanel.add(inputDesc);
            inputPanel.add(inputInfoDesc);
            inputPanel.add(inputId);
            inputPanel.add(toChangePw);

            JPanel margin = new JPanel();
            margin.setBackground(Color.WHITE);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    static class ChangePw extends JFrame {
        ChangePw() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("��й�ȣ ����");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ����", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("�� ��й�ȣ�� �Է��ϼ���");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("���� ����", Font.PLAIN, 12));

            JPasswordField inputPw = new JPasswordField();

            JButton toLogin = new JButton("���� �Ϸ�");
            toLogin.setBackground(new Color(29,161,242));
            toLogin.setFont(new Font("���� ����", Font.BOLD, 12));
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
            // ---------------------- ��й�ȣ ���� ----------------------------

            inputPanel.add(inputDesc);
            inputPanel.add(inputPwDesc);
            inputPanel.add(inputPw);
            inputPanel.add(toLogin);

            JPanel margin = new JPanel();
            margin.setBackground(Color.WHITE);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
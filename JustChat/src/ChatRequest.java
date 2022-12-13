import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

class ChatRequest extends JDialog {
		
		public ChatRequest(String id) {
			setSize(150,80);
			
	        Container con = getContentPane();
	        con.setLayout(new GridLayout(2,1));
	        
	        JOptionPane.showConfirmDialog(con, "1대1 채팅을 수락하시겠습니까?","1대1 채팅 수락",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null);
	        
			setVisible(true);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}
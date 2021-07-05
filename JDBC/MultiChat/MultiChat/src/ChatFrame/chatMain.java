package ChatFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class chatMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatMain frame = new chatMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public chatMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPortNumber = new JLabel("포트번호");
		lblPortNumber.setBounds(62, 117, 83, 18);
		contentPane.add(lblPortNumber);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(79, 179, 35, 18);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(159, 114, 116, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 176, 116, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnGotoMain = new JButton("접속하기");
		btnGotoMain.setBounds(159, 349, 105, 27);
		contentPane.add(btnGotoMain);
	}

}

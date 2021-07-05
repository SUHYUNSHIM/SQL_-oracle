package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	static String nickname;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login2 frame = new Login2(nickname);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login2(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(160, 86, 175, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 134, 175, 27);
		contentPane.add(textField_1);
		
		JLabel lblPortNo = new JLabel("Port no");
		lblPortNo.setFont(new Font("����", Font.PLAIN, 15));
		lblPortNo.setBounds(91, 86, 57, 27);
		contentPane.add(lblPortNo);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font("����", Font.PLAIN, 15));
		lblIp.setBounds(91, 134, 57, 27);
		contentPane.add(lblIp);
		
		JButton btnNewButton = new JButton("\uC785\uC7A5\uD558\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p = textField.getText();
				String i = textField_1.getText();
				System.out.println(p +"\t"+ i+"\t"+nickname);
				new Client(p, i, nickname);
			}
		});

		btnNewButton.setBounds(177, 204, 97, 23);
		contentPane.add(btnNewButton);
	}
}

package chatting;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.List;

import javax.swing.JTextArea;

public class FState extends JFrame {

	private JPanel contentPane;
	static ArrayList<String> userlist = new ArrayList<String>();


	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FState frame = new FState(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FState(ArrayList<String> userlist) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 10, 260, 241);
		contentPane.add(textArea);
		String[] array = new String[userlist.size()];
		int size = 0;
		for(String list : userlist) {
			array[size++]=list;
		}
		textArea.setText(Arrays.toString(array));
		
		

		
	}
	

}

package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ChatFrame.chatMain;
import JDBCInfo.Member;
import JDBCInfo.MemberDao;
import JDBCInfo.MemberDaoImpl;
import chatSC.ServerClass;
import chatting.FState;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtNickName;
	private JTextField txtRegion;
	private JTextField txtBirthDay;
	String nickname;
	ArrayList<String> userlist = new ArrayList<String>();
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNickName = new JLabel("닉네임");
		lblNickName.setBounds(59, 147, 62, 18);
		contentPane.add(lblNickName);

		
		JLabel lblRegion = new JLabel("지역구");
		lblRegion.setBounds(59, 192, 62, 18);
		contentPane.add(lblRegion);
		
		JLabel lblAge = new JLabel("생년월일");
		lblAge.setBounds(59, 257, 62, 18);
		contentPane.add(lblAge);
		
		txtNickName = new JTextField();
		txtNickName.setBounds(151, 144, 116, 24);
		contentPane.add(txtNickName);
		txtNickName.setColumns(10);
		
		txtRegion = new JTextField();
		txtRegion.setBounds(151, 189, 116, 24);
		contentPane.add(txtRegion);
		txtRegion.setColumns(10);
		
		txtBirthDay = new JTextField();
		txtBirthDay.setBounds(151, 254, 116, 24);
		contentPane.add(txtBirthDay);
		txtBirthDay.setColumns(10);
		
		JLabel lblLoginBanner = new JLabel("아래의 정보를 입력 후 채팅 시작하자");
		lblLoginBanner.setBounds(72, 46, 260, 18);
		contentPane.add(lblLoginBanner);
		
		JButton btnSave = new JButton("가입"); //데이터베이스에 회원 정보를 넘겨주어야 한다. 
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MemberDao memberDao = new MemberDaoImpl();
				//테이블 생성
				memberDao.createMember();
				memberDao.insertMember(new Member(txtNickName.getText(), txtRegion.getText(), txtBirthDay.getText()));
				 //Member member =  memberDao.getMemberByRegion("동작구");
				//System.out.println("해당 구에 거주하는 유저의 닉네임은 : " +member.getNickName());
				Member member = memberDao.getMemberByRegion("동작구"); //나중에 스캐너든, inputdialog 든 값을 입력받아서 여기에 넣을 것이다.
				//System.out.println("해당 구에 거주하는 유저의 닉네임은 "+member.getNickName());
				System.out.println("가입이 완료되었습니다.");
				
			}
		});
		btnSave.setBounds(72, 349, 105, 27);
		contentPane.add(btnSave);
		
		///------------------------------------------------------------//
		JButton btnChatStart = new JButton("채팅하러 가기");
		btnChatStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ip와 port를 입력하는 창으로 넘어간다. 
				//이 버튼을 누르면 서버가 동작하는 것은 어떨까
				nickname = txtNickName.getText();
				new Login2(nickname); 
				userlist.add(nickname);
				new FState(userlist);
				System.out.println(nickname);
				dispose();
				setVisible(false); 
				new Login2(nickname).setVisible(true);
				System.out.println("채팅 메인 화면으로 전환 성공.");
				
				
				
			/*	try {
					new ServerClass(12700); //미리 포트번호를 넣어두고 버튼 누를 시 실행되도록 하였음.
					System.out.println("서버 동작 중");
				} catch (IOException e1) {					
					e1.printStackTrace();
				}*/
				
			}
		});
		btnChatStart.setBounds(191, 349, 141, 27);
		contentPane.add(btnChatStart);
	}
}

package chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientGUI extends JFrame implements Runnable, ActionListener{
	
	//console 모드에서 넘어오는 3개 인자를 받아 저장할 준비 필드
	DataOutputStream outputStream;
	DataInputStream inputStream;
	String nickname;

	
	
	//그래픽디자인 컴포넌트를 준비

	JLabel jlabel1 = new JLabel("채팅방");
	JTextArea jtarea1 = new JTextArea();
	JTextField jtfield1 = new JTextField();
	JTextArea jtarea2 = new JTextArea(); //접속 목록
	JScrollPane jScrollPane = new JScrollPane(jtarea1);
	
	Toolkit tk1 = Toolkit.getDefaultToolkit();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu1 = new JMenu("상태창");
	JMenuItem item1 = new JMenuItem("온라인");
	JMenuItem item2 = new JMenuItem("종료");
	
	
	public ClientGUI(DataOutputStream outputStream,
					DataInputStream inputStream, String nickname) {
		this.outputStream = outputStream;
		this.inputStream = inputStream;
		this.nickname = nickname;
		getContentPane().setLayout(new BorderLayout());
		
		jlabel1.setFont(new Font("굴림", Font.BOLD, 22));
		getContentPane().add("North", jlabel1); //채팅방
		
		
		//chat문자열이 출력되는 곳
		jtarea1.setBackground(Color.white); 
		jtarea1.setForeground(Color.blue); //노랑 배경색 위에 파랑색 글씨
		jtarea1.setFont(new Font("굴림", Font.BOLD, 22));
		//new Font(글씨체, 스타일, 크기)
		
		jtarea1.setEditable(false); //편집x
		
		getContentPane().add("Center", jScrollPane);
		// 이미 연결 선언 되어있음
		// 그러므로 이것 대신 위에 것 사용
		
		//chat 입력
		jtfield1.setBackground(Color.white);
		jtfield1.setForeground(Color.BLACK);
		jtfield1.setFont(new Font("굴림", Font.BOLD, 25));
		getContentPane().add("South", jtfield1);
		jtfield1.addActionListener(this);//이벤트 등록
		
		setSize(800, 800);
		
		setJMenuBar(menuBar);
		menu1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuBar.add(menu1);
		
		menu1.add(item1);
		menu1.add(item2);
		setVisible(true);
		
		item1.addActionListener(new MenuActionListener());
		item2.addActionListener(new MenuActionListener());
		
		//창문 닫기
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0); //이걸 애주어야 퇴장 이라는 메시지 나옴
				//단 1명접속은 보낼 사람이 없으니 안나오고 2명 이상일 때 나옴
				setVisible(false);
			}
		});
		
		//================================
		//서버로부터 받아 textarea에 뿌려주는 쓰레드
		Thread th1 = new Thread(this);
		th1.start();
		//================================
	}//생성자-end
	
	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String s = arg0.getActionCommand();
			if(s == "온라인") {
				new FState(null).setVisible(true);
			}
			else if(s == "종료") {
				dispose();
				setVisible(false);
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == jtfield1) {
			try {
				outputStream.writeUTF(nickname + " --> " + jtfield1.getText());
				//nickname과 client의 chat을 서버로
			} catch (IOException e) {
				//e.printStackTrace();
			}
			jtfield1.setText("");//서버로 보내고 해당 칸을 빈문자열로 클리어
		}
		
	}//actionPerformed - end	
	
	@Override
	public void run() {//for 받는 thread
		try {
		while(true) {
			String strServer1 = inputStream.readUTF(); //서버로 부터
			
			if(strServer1 == null) {
				jtarea1.append("\n"+"종료");
				return;
			}
		jtarea1.append("\n" + strServer1);//서버에서 온 것 textarea에 추가
		//------ 이것해야 스크롤바가 생긴 후 맨 마지막 내용이 잘 보임 -----
		int kkeut = jtarea1.getText().length();
		jtarea1.setCaretPosition(kkeut);//커서를 맨 뒤로
		//jtarea1.setCaretPosition(0) 커서를 맨처음에
		tk1.beep();//chat이 올때마다 beep음 
		}
		}catch(Exception eee) {
			jtarea1.append("\n" + eee);
		}
	}//run-end

}//class-end

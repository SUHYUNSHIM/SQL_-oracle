package day5_2;

import java.net.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

//client console to client GUI  // KajaClientGUI.java 
public class KajaClientGUI extends JFrame implements Runnable, ActionListener {
	                                //thread 위해
	
	//console모드에서 넘어오는 3개 인자를 받아 저장할 준비필드 
	DataOutputStream outputStream;  
	DataInputStream inputStream;
	String nickname;
	
	//그래픽디자인 컴포넌트 를 준비
	JLabel jlabel1 = new JLabel("우리반 채팅~~~");
	JTextArea jtarea1 = new JTextArea();
	JTextField jtfield1 = new JTextField();
	JScrollPane jScrollPane = new JScrollPane(jtarea1);///////////

    // 클라이언트의 	 
	// new KajaClientGUI(outputStream, inputStream, args[2])
	//{에서 여기로 찾아옴
	//GUI 만의 생성자처리 시작
	public KajaClientGUI(DataOutputStream outputStream, 
	                         DataInputStream inputStream, String nickname) {// 생성자
		this.outputStream = outputStream;//필드(속성) 초기치 
		this.inputStream = inputStream;
		this.nickname = nickname;		 
		
		setLayout(new BorderLayout());///////////////
		// North, South, West, East, Center

		jlabel1.setFont(new Font("굴림", Font.BOLD, 22));
		add("North", jlabel1);//"우리반 채팅"
		
		// chat문자열이 출력되는 곳 
		jtarea1.setBackground(Color.yellow);//보색대비 
		jtarea1.setForeground(Color.blue); //노랑 배경색 위에 파랑색  글씨 
		jtarea1.setFont(new Font("굴림", Font.BOLD, 22));
		                  // new Font(글씨체, 스타일, 크기)
		
		jtarea1.setEditable(false); // 편집 x
		add("Center",jScrollPane);
		   //new JScrollPane(jtarea1); //  이미 연결선언되어있음 
		       //add("Center", jtarea1) //그러므로 이것 대신 위에것사용;//////////
		
		
		//chat 입력
		jtfield1.setBackground(Color.white);
		jtfield1.setForeground(Color.BLACK);
		jtfield1.setFont(new Font("굴림", Font.BOLD, 25));
		add("South", jtfield1);///////////
		jtfield1.addActionListener(this);//이벤트 등록 ////////////

		setSize(800, 800);//채팅창 크기 
		setVisible(true); //항상 보여줘 
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0); 
				setVisible(false);
			}
		});

		
		//=============================================================
		// 서버로 부터 받아 textarea에 뿌려주는 쓰레드
		Thread th1 = new Thread(this); 
		th1.start();
		//=======================================================
	}// 생성자-end

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jtfield1) {
			try {
  outputStream.writeUTF(nickname+" --> " + jtfield1.getText());
			        //nickname과 client의 chat을 서버로 
  
			} catch (IOException ee) {
				// TODO Auto-generated catch block
				//ee.printStackTrace();
			}
			jtfield1.setText("");//서버로 보내고 해당 칸을 클리어 
		}
	}// actionPerformed - end

	
	
	
	
	Toolkit tk1=Toolkit.getDefaultToolkit(); 
	//chat 올때마다 beep음 울리게 하려고 
	
public void run() { //for 받는 thread
 try {
	 while (true) {
		String strServer1 = inputStream.readUTF(); // 서버로 부터
		if (strServer1 == null) {
			jtarea1.append("\n"+"종료");
			return; 
		}
	jtarea1.append("\n" + strServer1);//서버에서온것 textarea에추가
				
	//---------이것해야 스크롤바가 생긴후 맨 마지막 내용이 잘 보임 -----
		int kkeut=jtarea1.getText().length();
		jtarea1.setCaretPosition(kkeut);//커서를 맨뒤로 
		   //jtarea1.setCaretPosition(0);//커서를 맨처음에 
		tk1.beep();////////////chat이 올때마다 beep음 (띠링~~~)    
	//-----------------------------------------------	
		}
	} catch (Exception eee) {
			jtarea1.append("\n" + eee); 	}
	}//run-end
}//class-end
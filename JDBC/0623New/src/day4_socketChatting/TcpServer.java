package day4_socketChatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//1:1 나와 서버가.
//send 담당 스레드, receive 담당 스레드

class ThreadRcv extends Thread{ //받거니
	Socket s1;
	DataInputStream dis1;
	//what1 = "미팅중==>" ----> 보내는 쪽이 처리하므로 안 씀
	ThreadRcv(Socket s1) throws IOException{
		this.s1 = s1;
		dis1 = new DataInputStream(s1.getInputStream()); //read
	}
	public void run() {
		while(dis1 !=null) {
			try {
				System.out.println(dis1.readUTF());
			}catch(IOException e) {
				
			}
		}
	}	
}

class ThreadSend extends Thread{ //주거니
	Socket s1; //서버에서  client에게 전달. 전화번호.  
	DataOutputStream dos1; //out -> "방가방가"를 보낼 것이므로. 데이터를 보내는 통로.
	//기본데이터타입별로 처리하기 (예) writeInt, writeUTF (문자열처리)
	String what1; //채팅 시 맨앞에 나올 문자열 (예) 미팅중 ---> 
	
	//readLine() 문자열 스트림
	
	ThreadSend(Socket s1) throws IOException { //생성자. send할 때 제일 먼저 방문하는 것.
		this.s1 =s1; //s1 받아서 s1 s1 필드에 넣기 
		dos1 = new DataOutputStream(s1.getOutputStream()); //send를 위한 i/o 스트림. 자료를 보내는 통로.
		//dos1은 자료를 내보내는 객체이다.
		what1 = "미팅중==>"; //대화 시 앞에 항상 붙는 단어 		
	}
	
	public void run() {
		Scanner sc1 = new Scanner(System.in);
		while(dos1 !=null) {
			try {
				dos1.writeUTF(what1 + sc1.nextLine()); //scanner로 받는 것이 nextLine으로 표시.
				//io 스트림을 통해 상대에게 chat 보내기
			}catch(IOException e) {
				
			}
		}
	}
}

public class TcpServer { //이 안에 클래스는 3개이다. 
	//TcpServer, ThreadRcv, ThreadSend
	//저장할 때 번역이 되므로 저장과 동시에 .class가 3개가 만들어진다. 
	//번역된 기계어. byte
	//class까지 포함해서 전체 총 4개의 .class가 만들어 진다.
	//같은 패키지에 있으므로 패키지 안에 4개가 서로 맞물려 돌아간다.
	//.java는 2개이다.
	

	public static void main(String args[]) throws IOException {
		if(args.length !=1) {
			System.out.println("사용법은 java 파일명 포트번호");
			System.exit(1);
		}
		
		//listen 											//포트번호
		ServerSocket ss1 = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("서버 잘 준비됨..");
		
		while(true){
			System.out.println("미팅 대화 기다리는 중..");
			Socket s1= ss1.accept(); //connect을 받자. 
			
			//아래는 원래 receive, send 가 오는 자리
			//여기에 thread를 사용 
			//이제는 무전기 방식 아님. 전화기로
			ThreadSend tsendSer1 = new ThreadSend(s1); //초기치, 주거니
			ThreadRcv trcSer1 = new ThreadRcv(s1); //받거니를 위해 
			tsendSer1.start(); //받거니를 위해 
			trcSer1.start();
		}
		}
}

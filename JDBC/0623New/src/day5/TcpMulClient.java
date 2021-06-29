package day5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


class ThreadClientSendClass implements Runnable{
	/* class ThreadClientSendClass extendsd Thread {*/
	Socket socket; //필드들
	DataOutputStream outputStream;
	String nickname; 
	
	public ThreadClientSendClass(Socket socket, String nickname) throws IOException{
		this.socket = socket;
		outputStream = new DataOutputStream(socket.getOutputStream());
		this.nickname = nickname;
	}//send 생성자

	@Override
	public void run() {
		Scanner in1 = new Scanner(System.in); //채팅내용을 키보드로 입력
		try {
			if(outputStream !=null)
				outputStream.writeUTF(nickname); //닉네임 send
				while(outputStream !=null) {
					//io스트림 통해 상대방에게 chat 보냄
					outputStream.writeUTF("(**" +nickname +"**)" + in1.nextLine());
				}
		}catch(IOException e) {
			
		}
		
	}
}

class ThreadClientRcvClass implements Runnable{
	Socket socket; //rcv 클래스 필드들 
	DataInputStream inputStream;
	public ThreadClientRcvClass(Socket socket) throws IOException{ //생성자
		this.socket = socket;
		inputStream = new DataInputStream(socket.getInputStream());
	}
	@Override
	public void run() {
		while(inputStream !=null) {
			try{ //서버가 보낸 것 받아 출력
				System.out.println(inputStream.readUTF());
			}catch(IOException e) {
				
			}
		}
	}
}


public class TcpMulClient {

	public static void main(String args[]) {
		if(args.length !=3) {
			System.out.println("사용법 : 클라이언트 실행은 \'java  "
								+ "패지키명.파일명 ip주소 포트번호 닉네임\' 형식으로 입력");
			System.exit(1);
	}try {
		Socket s1 = new Socket(args[0], Integer.parseInt(args[1]));
		System.out.println("서버에 연결....");
		//console, dos 모드일 경우 
		ThreadClientSendClass tcc1 = new ThreadClientSendClass(s1,args[2]);
		ThreadClientRcvClass tcr1 = new ThreadClientRcvClass(s1);
		
		Thread tsend1 = new Thread(tcc1); //보내는 chat위해서
		Thread trcv1 = new Thread(tcr1);  //받는 chat위해서 
		tsend1.start();
		trcv1.start();
		}catch(Exception e) {
			
		}
	}
}

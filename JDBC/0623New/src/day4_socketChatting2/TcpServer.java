package day4_socketChatting2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	public static void main(String[] args) throws IOException{
		ServerSocket ss1 = new ServerSocket(7780); //포트넘버는 1024이후 마음대로 쓸 수 있음.
		System.out.println("전화는 준비되어 있음...");
		
		while(true) {
			System.out.println("전화기다리는 중....");
			Socket s1 = ss1.accept(); //accept
			System.out.println(s1.getInetAddress() + "에서 전화왔네"); //프로세스 번호
			//파일 문법 = 네트워킹 문법
			DataOutputStream dos1 //network stream
			 =new DataOutputStream(s1.getOutputStream());
			dos1.writeUTF("오랜만이다!"); //UTF 문자열 처리할때 
			//----
			dos1.close();
			s1.close();
		}
	}

}

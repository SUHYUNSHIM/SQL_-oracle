package day4_socketChatting;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
	//클래스가 1개로 구성.

	public static void main(String args[]) throws UnknownHostException, IOException{
		if(args.length !=2) {
			System.out.println("사용법은 java 파일명 ip주소 포트번호");
			System.exit(1);
		}
		Socket s1 = new Socket(args[0], Integer.parseInt(args[1])); //그냥 소켓은 클라이언트가 사용하는
								//ip address //port#
		ThreadSend tsendcli1 = new ThreadSend(s1); //TcpServer에 정의된 클래스를 사용했다.
		ThreadRcv trcvcli1 = new ThreadRcv(s1);
		
		tsendcli1.start();
		trcvcli1.start();		

	}

}

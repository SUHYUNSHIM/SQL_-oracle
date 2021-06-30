package day6_2;

import java.io.IOException;
import java.net.ServerSocket;

//문자열 전송은 writeUTF()를 사용했는데 이것이 바이트 단위 전송이다.
//그림파일 음악, 문서 파일은 바이트 단위 전송을 해야 제대로 간다.



public class TcpServer {

	public static void main(String args[]) throws IOException {
		
		ServerSocket ss1 = new ServerSocket(37782); // your ip, your port 
		System.out.println("서버 준비되어 있음");
	}

}

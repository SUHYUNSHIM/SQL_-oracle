package day6_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//문자열 전송은 writeUTF()를 사용했는데 이것이 바이트 단위 전송이다.
//그림파일 음악, 문서 파일은 바이트 단위 전송을 해야 제대로 간다.
public class TcpServer {

	public static void main(String args[]) throws IOException {
		
		ServerSocket ss1 = new ServerSocket(37782); // your ip, your port 
		System.out.println("서버 준비되어 있음");
		while(true) {
			System.out.println("기다리는 중....");
			
			Socket s1 = ss1.accept();
			System.out.println(s1.getInetAddress()+"에서 접속");
			
			File file1 = new File("C:\\Users\\USER\\Pictures\\slide-show\\abc.jpg"); //그림 파일을 전송한다. 파일 뚜껑을 열었다.
			FileInputStream fis1 = new FileInputStream(file1); // 파일을 받아들이는 객체 생성. file을 인자로 한.
			DataInputStream dis1 = new DataInputStream(fis1);  //이진수 . 데이터 byte로 받아들임.
			
			DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream()); //네트워크 stream. 
			//클라이언트, 서버가 데이터를 통신하기 위해. 이 경우는 데이터를 전송하기 위한 통로. 원통에 비유.
			
			byte[] byteBae = new byte[(int) file1.length()];
			//파일의 길이만큼 바이트 배열 잡음
			
			System.out.println("file --> byte 중 ...");
			dis1.readFully(byteBae); //파일 내용을  바이트 배열로 통째로 넣는다. 
			
			dos1.writeInt(byteBae.length); //파일 길이 먼저 전송, 4byte 확보.
			dos1.write(byteBae); //파일 자체를 바이트 배열 전송
			System.out.println("전송했다~~");
		}
	}

}

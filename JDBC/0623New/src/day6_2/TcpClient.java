package day6_2;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

	public static void main(String args[]) throws UnknownHostException, IOException {
		
		Socket s1 = new Socket("127.0.0.1",37782); //thread로 돌아가는 것이 아니니 무전기의 원리이다.
		DataInputStream dis1 = new DataInputStream(s1.getInputStream()); //네트워크 스트림. 원통 통로로 받는다.
		
		//file 길이, 내용을 받아 byte 배열로. 
		int len1 = dis1.readInt(); //서버가 보낸 파일 길이 먼저 받아온다
		byte[] byteBae2 = new byte[len1];
		dis1.readFully(byteBae2);

		//byte 배열 --> file로 저장
		FileOutputStream fos1 = new FileOutputStream("C:\\Users\\USER\\Desktop\\수업파일.docx"); //C:\Users\USER\Desktop -> 이미지 --> 문서파일로 수정
		fos1.write(byteBae2);
		System.out.println("끝!");
	}

}

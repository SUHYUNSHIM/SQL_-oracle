package day5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.net.*;
import java.io.*;
import java.util.*;

class ServerClass {

	 ArrayList<ThreadServerClass> threadList = new ArrayList<ThreadServerClass>();

	Socket socket;
	DataOutputStream outputStream;
	
	public ServerClass(int portno) throws IOException {//생성자 
	//public ServerClass() throws IOException {

		Socket s1 = null;

		//ServerSocket ss1 = new ServerSocket(6788);
		ServerSocket ss1 = new ServerSocket(portno);
		System.out.println("서버가동.......");/////////////////	
		
		
	while (true) {
		s1 = ss1.accept();
	System.out.println("접속주소: " + s1.getInetAddress() + " , 접속포트: " + s1.getPort());
			ThreadServerClass tServer1 = new ThreadServerClass(s1);
			tServer1.start();////////
            // 한명이 접속하면  ThreadServerClass 쓰레드에 올려놓음 
			
			threadList.add(tServer1);//컬렉션에 add
			System.out.println("접속자 수 : " + threadList.size());
		}//whle-end 

	}

		// 접속중인 쓰레드에게 chat 내용 보냄
	public void sendChat(String chat) throws IOException {
		for (int i = 0; i < threadList.size(); i++)
			threadList.get(i).outputStream.writeUTF(chat);
		       //처음에 nickname이  채팅관련 모든 사람에게 전송 

	}//sendChat-end

		
	class ThreadServerClass extends Thread {//한명 접속마다 처리할 쓰레드 클래스 
		Socket socket1;
		DataInputStream inputStream;
		DataOutputStream outputStream;

		public ThreadServerClass(Socket s1) throws IOException {
			socket1 = s1;
			inputStream = new DataInputStream(s1.getInputStream());

			outputStream = new DataOutputStream(s1.getOutputStream());
		}
		
		@Override
		public void run() { //remember !!!!!!  한사람 서버로 접속한 경우임 
			String nickname = "";
			try {
				if (inputStream != null) {
					nickname = inputStream.readUTF();//초록왕자 , 은빛공주 
					sendChat(nickname + " 님 입장~~~~~ (^^) (^^) (^^) ");
				}
				while (inputStream != null) {
					// System.out.println(inputStream.readUTF());
					sendChat(inputStream.readUTF());  //방가방가 ~~~~
					  // 클라이언트가 보낸 채팅 내용을 접속한  모두에게 보냄
				}//정상채팅의 경우는 계속  while 문안에서 반복 loop 
				
			} catch (IOException e) { //여기로 왔단 얘기는 에러가 발생한 것 //나가버린 경우 
				//e.printStackTrace(); 

			} finally {
				// 나간 쓰레드의 인덱스 찾기
				for (int i = 0; i < threadList.size(); i++) {
					if (socket1.equals(threadList.get(i).socket1)) {//이 소켓이 누구거???????
						threadList.remove(i);//찾았다 홍길동 소켓 - 삭제하자는 
						try {
							sendChat(nickname + " 님 퇴장~~~~~  (ㅠ.ㅠ) (ㅠ.ㅠ) (ㅠ.ㅠ) ");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println("접속자 수 : " + threadList.size()+" 명");
			}//finally-end 

		}//run-end 
	}//ThreadServerClass-end

}//ServerClass-end

public class TcpMulServer {//TcpMulServer.java 
	public static void main(String args[]) throws IOException {
		if(args.length !=1) {
			
		System.out.println("사용법 : 서버실행은   \'java  패키지명.파일명   포트번호\'  형식으로 입력 ");
		}
	//	new ServerClass();////////////
		new ServerClass(Integer.parseInt(args[0]));////////////

	}

}


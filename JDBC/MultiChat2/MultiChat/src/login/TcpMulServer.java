package login;

import java.net.*;
import java.io.*;
import java.util.*;

class ServerClass {

	 ArrayList<ThreadServerClass> threadList = new ArrayList<ThreadServerClass>();
	 ArrayList<TcpMulClient> user_list = new ArrayList<TcpMulClient>();
	
	Socket socket;
	DataOutputStream outputStream;
	String nickname;
	
	
	public ServerClass(int portno) throws IOException {//생성자
	//public ServerClass() throws IOException {

		Socket s1 = null;
		
		//ServerSocket ss1 = new ServerSocket(6788);
		ServerSocket ss1 = new ServerSocket(portno);
		System.out.println("서버가동.......");
		
		
		
	while (true) {
		s1 = ss1.accept();
	System.out.println("접속주소: " + s1.getInetAddress() + " , 접속포트: " + s1.getPort());
			ThreadServerClass tServer1 = new ThreadServerClass(s1);
			tServer1.start();////////
            //ThreadServerClass run 호출
			
			threadList.add(tServer1);//컬렉션 add
			System.out.println("접속자수 : " + threadList.size()+"명");
		}//whle-end 

	}
	
	
		// �������� �����忡�� chat ���� ����
	public void sendChat(String chat) throws IOException {
		for (int i = 0; i < threadList.size(); i++)
			threadList.get(i).outputStream.writeUTF(chat);
		       //ó���� nickname��  ä�ð��� ��� ������� ���� 

	}//sendChat-end

		
	class ThreadServerClass extends Thread {//�Ѹ� ���Ӹ��� ó���� ������ Ŭ���� 
		Socket socket1;
		DataInputStream inputStream;
		DataOutputStream outputStream;

		public ThreadServerClass(Socket s1) throws IOException {
			socket1 = s1;
			inputStream = new DataInputStream(s1.getInputStream());

			outputStream = new DataOutputStream(s1.getOutputStream());
		}
		
		
		@Override
		public void run() { //remember !!!!!!  �ѻ�� ������ ������ ����� 
			//String nickname = "";
			try {
				/*if (inputStream != null) {
					nickname = inputStream.readUTF();//�ʷϿ��� , �������� 
					sendChat(nickname + "님 입장 ");
				}*/			
				while (inputStream != null) {
					// System.out.println(inputStream.readUTF());
					sendChat(inputStream.readUTF());  //�氡�氡 ~~~~
					  // Ŭ���̾�Ʈ�� ���� ä�� ������ ������  ��ο��� ����
				}//����ä���� ���� ���  while ���ȿ��� �ݺ� loop 
				
			} catch (IOException e) { //����� �Դ� ���� ������ �߻��� �� //�������� ��� 
				//e.printStackTrace(); 

			} finally {
				// ���� �������� �ε��� ã��
				for (int i = 0; i < threadList.size(); i++) {
					if (socket1.equals(threadList.get(i).socket1)) {//�� ������ ������???????
						threadList.remove(i);//ã�Ҵ� ȫ�浿 ���� - �������ڴ� 
						try {
							sendChat(nickname + " 님 퇴장) ");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println("접속자 수: " + threadList.size()+"명");
			}//finally-end 

		}//run-end 
		

	}//ThreadServerClass-end

}//ServerClass-end



public class TcpMulServer {//TcpMulServer.java 
	public static void main(String args[]) throws IOException {
		if(args.length !=1) {
			
		System.out.println("사용법은: 서버실행은ㅁ \'java 패키지명.파일명 포트번호/' 형식으로 입력 ");
		}
	//	new ServerClass();////////////
		new ServerClass(Integer.parseInt(args[0]));////////////

	}

}

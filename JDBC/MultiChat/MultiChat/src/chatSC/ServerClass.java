package chatSC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClass{
	ArrayList<ThreadServerClass> threadList = new ArrayList<ThreadServerClass>();
	Socket socket;
	DataOutputStream outputStream;
	
	public ServerClass(int portno) throws IOException {
		Socket s=  null;
		ServerSocket ss = new ServerSocket(portno); //서버 소켓 생성
		System.out.println("서버가 먼저 돌아가기 시작합니다.");
		while(true) {
			s = ss.accept();
			System.out.println("접속 주소:"+s.getInetAddress() + "접속포트:" +s.getPort());
			ThreadServerClass ts = new ThreadServerClass(s); //한명이 들어올 때마다 스레드에 올린다.
			ts.start();
			threadList.add(ts);
			System.out.println("접속자 수: " + threadList.size()+"명"); //이것을 실행화면에 넘겨야 한다.
		}
	} //서버 생성자 끝
	
	class ThreadServerClass extends Thread{ //ServerClass의 내부 클래스 
		Socket socket1;
		DataInputStream inputStream;
		DataOutputStream outputStream;
		
		public ThreadServerClass(Socket s1) throws IOException{
			socket1 = s1;
			inputStream = new DataInputStream(s1.getInputStream());
			outputStream = new DataOutputStream(s1.getOutputStream());
		}
		@Override
		public void run() {
			String nickname=""; //앞 login 페이지에서 등록한 닉네임이 넘어와야 한다.
			try {
				if(inputStream !=null) {
					nickname = inputStream.readUTF();
					sendChat(nickname +"님 입장하였습니다"); //전체 채팅방
				}
				while(inputStream !=null) {
					sendChat(nickname +"님 입장");
				}
			}catch(IOException e) {
				
			}finally {
				//채팅으로부터 나왔다.
				//나간 스레드의 인덱스 찾기.
				for(int i=0;i<threadList.size();i++) {
					if(socket1.equals(threadList.get(i).socket1)) {
						threadList.remove(i); //퇴장 시 remove
						try {
							sendChat(nickname+" 님 퇴장!");
							//서버는 모든 클라이언트에게 누구누구님 퇴장이라고 보냄 
						}catch(IOException e) {
							//e.printStackTrace();
						}
					}//id- end
				}//for- end
				System.out.println("접속자 수 : "+threadList.size()+ " 명");
			}
		}
		public void sendChat(String chat) throws IOException{
			for(int i=0;i<threadList.size();i++) {
				threadList.get(i).outputStream.writeUTF(chat); //각 사람마다, 내용을 나가는 통로에 넣는다.
				//각각의 회원을 찾아가서 별명 or 채팅 내용을 보냄. 
			}
		}// sendChat -end
		
	}
	
} //ServerClass 끝 


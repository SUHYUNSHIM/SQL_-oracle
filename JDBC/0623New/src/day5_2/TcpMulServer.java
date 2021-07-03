package day5_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ServerClass{
	ArrayList<ThreadServerClass> threadList = new ArrayList<ThreadServerClass>(); //제너릭
	Socket socket; //클라이언트 받아 저장할 곳
	DataOutputStream outputStream; //출력 스트림 
	
	public ServerClass(int portno) throws IOException{
		Socket s1 = null;
		ServerSocket ss1 = new ServerSocket(portno); //서버 소켓 생성
		System.out.println("서버가동......."); //listen
		while(true) {
			s1 = ss1.accept(); //클라이언트의 접속을 허용한다.
			System.out.println("접속주소: "+s1.getInetAddress() + ", 접속포트: "+s1.getPort());
			ThreadServerClass tServer1 = new ThreadServerClass(s1); //s1초기치. 한 명이 들어올 때마다 thread에 올리고
			tServer1.start();
			threadList.add(tServer1); //arraylist에 추가한다. 
			
			System.out.println("접속자 수: "+ threadList.size()+" 명"); //컬렉션의 길이? 요소의 개수는 size이다.
		}
	}//생성자 end 
	
	class ThreadServerClass extends Thread{
		Socket socket1;
		DataInputStream inputStream;
		DataOutputStream outputStream;
		
		public ThreadServerClass(Socket s1) throws IOException{ //생성자
			socket1 = s1;
			//입출력 스트림 
			inputStream = new DataInputStream(s1.getInputStream());  //보내고 받을 수 있는 원통이 만들어진다.
			outputStream = new DataOutputStream(s1.getOutputStream());
		}
		
		@Override
		public void run() {
			String nickname = "";
			try { //이 부분이 진행되고 있다는 것은 안정적으로 채팅 중이라는 것.
				if(inputStream !=null) {
					nickname = inputStream.readUTF();
					sendChat(nickname +" 님 입장~~~"); //메소드 2형식 호출문. 클라이언트 처음 보내는 것이 별명
					//전체 회원에게 지금 들어온 별명을 보내기 위한 작업이다.
				}
				while(inputStream !=null) {
					//System.out.println(inputStream.readUTF());
					sendChat(inputStream.readUTF());
					//이번에는 별명이 아니라 클라이언트가 보낸 채팅 내용을 접속한 모두에게 보냄.
				}
			}catch(IOException e) {
				//에러내용을 출력 안하려면 주석을 단다
			} finally {
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
			} //finally-end
		}//run-end
		
		public void sendChat(String chat) throws IOException{
			for(int i=0;i<threadList.size();i++) {
				threadList.get(i).outputStream.writeUTF(chat); //각 사람마다, 내용을 나가는 통로에 넣는다.
				//각각의 회원을 찾아가서 별명 or 채팅 내용을 보냄. 
			}
		}// sendChat -end
	} //ThreadServerClass
	
	} //ServerClass -end 



public class TcpMulServer {

	public static void main(String args[]) throws IOException {
		if(args.length !=1) {
			System.out.println("사용법: 서버실행은 \'java 패키지명.파일명 포트번호\'  형식으로 입력"); //  \' : 따옴표 자체를 출력하고 싶을 때
		}
		new ServerClass(Integer.parseInt(args[0])); //port#
		//객체 생성 - 초기치. 제일먼저  생성자를 찾아간다.  
	}

}

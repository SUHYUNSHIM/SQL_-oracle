package TCP_MultiChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//GUI로 구현하기위해 GUI를 전담하는 .java를 만들어 거기서 처리하도록함
//그러기 위해서는 거기서 필요한 내용을 모두 인자로 보내야함
public class TcpMulClient {//TcpMulClient.java
	//전에 만든 send용 thread, rcv용 Thread는 여기서는 사용안함.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length !=3) {
			System.out.println("사용법: 클라이언트 실행은 \'java 패키지명.파일명 ip주소 포트번호 닉네임\' 형식으로 입력");
			
			System.exit(1);
		}
try {						//ip주소 		//포트번호
	Socket s1 = new Socket(args[0], Integer.parseInt(args[1]));
	String nickname1 = args[2];
	System.out.println("서버에 연결 . . . . . .");//connect
	// i/o network stream : readUTF() writeUTF() 사용을 위해준비
		DataOutputStream outputStream = new DataOutputStream(s1.getOutputStream());
		DataInputStream inputStream = new DataInputStream(s1.getInputStream());
		outputStream.writeUTF("##"+ args[2]);//닉네임 먼저 보내는 것은 같다
		
		//그래서 GUI로 가자(kajaClientGUI.java는 GUI파일 (단, main x))
		//============================================
		//client console --> client GUI
	new KajaClientGUI(outputStream, inputStream, args[2]) {//new 클래스명(인자들) //nickname1 = args[2]
					//ip스트림 및 닉네임을 인자로 전달
		public void closeWork() throws IOException{
			outputStream.close();
			inputStream.close();
			System.exit(0);
		}
	};//new KajaClientGUI -end
//======================================================================================	
		
		
}catch (IOException e) {
		//e.printStackTrace();//주석을 달아야 에러시 화면이 부드럽게 진행
	}//try-catch-end
		
	
		
	}//main-endk

}//main class-end

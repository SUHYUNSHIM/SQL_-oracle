package day5_2;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TcpMulClient {
   //전에 만든 send용 thread ,recv용 thread 는 여기서는 사용안함  
	public static void main(String args[]) {
		if (args.length != 3) {
			System.out.println
 ("사용법: 클라이언트실행은   \'java  패키지명.파일명    ip주소    포트번호   닉네임\'  형식으로 입력 ");
			System.exit(1);
		}
try {                       // ip주소         //포트번호
	Socket s1 = new Socket(args[0], Integer.parseInt(args[1]));
	System.out.println("서버에 연결.....");//connect 
	
	
// i/o network stream :   readUTF() writeUTF() 사용을 위해 준비   
	DataOutputStream outputStream
                = new DataOutputStream(s1.getOutputStream());
	DataInputStream inputStream 
                = new DataInputStream(s1.getInputStream());
	outputStream.writeUTF("##" + args[2]); 
	                            // 닉네임 먼저 보내는 것은 같다 
	
	
	//그래서 GUI로 가자 
			 // KajaClientGUI.java는 GUI 파일(단, main  x) 
			//==================================================
			//client console --> client GUI 
         new KajaClientGUI(outputStream, inputStream, args[2]) {////////
                   // io스트림    및     닉네임을 인자로 전달 
	          public void closeWork() throws IOException {
		           outputStream.close();
		           inputStream.close();
		           System.exit(0);
	       }
        }; //new KajaClientGUI -end
          //=================================================
} catch (Exception e) {
			//e.printStackTrace();//주석을 달아야 에러시 화면이 부드럽게 진행 
		}
	}//main-end
} //main class- end


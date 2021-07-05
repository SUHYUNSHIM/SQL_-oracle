package login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import chatting.ClientGUI;

class Client {
	String p;
	String i;
	String nickname;

	public Client(String p, String i, String nickname) {
		this.p = p;
		this.i = i;
		this.nickname = nickname;
		try {
			Socket s1 = new Socket(i, Integer.parseInt(p));
			DataOutputStream outputStream = new DataOutputStream(s1.getOutputStream());
			DataInputStream inputStream = new DataInputStream(s1.getInputStream());
			
			new ClientGUI(outputStream, inputStream, nickname){
				public void closeWork() throws IOException {
					
					outputStream.close();
					inputStream.close();
					System.exit(0);
				}
			};

		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

public class TcpMulClient {

	public static void main(String[] args) {


	}

}

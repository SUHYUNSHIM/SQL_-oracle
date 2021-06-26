package day3;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPMainClass {

	public static void main(String[] args) throws UnknownHostException {
	
		String juso = "www.naver.com";
		
		InetAddress inet1 = InetAddress.getByName(juso);

		System.out.println(inet1.getHostAddress());
		
		http://125.209.222.142
			
		
		System.out.println(inet1.getHostName());
	}

}

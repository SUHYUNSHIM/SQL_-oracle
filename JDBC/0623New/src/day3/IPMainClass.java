package day3;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPMainClass {

	public static void main(String[] args) throws UnknownHostException {
	
		String juso = "www.naver.com";
		
		InetAddress inet1 = InetAddress.getByName(juso);

		System.out.println(inet1.getHostAddress()); //십진수 출력
		
		http://125.209.222.142
			
		
		System.out.println(inet1.getHostName());
		
		byte [] byte4 = inet1.getAddress(); //2진수 출력
		for(int i=0; i<byte4.length;i++) {
			System.out.print((int)byte4[i]+ ""+"\t");
		}
		System.out.println();
	}

}

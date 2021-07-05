package TCP_MultiChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//TcpMulServer.java, //TcpMulClient.java

class ServerClass{

   ArrayList<String> memberList = new ArrayList<String>();
   ArrayList<ThreadServerClass> threadList = new ArrayList<ThreadServerClass>(); //제네릭
   //ㄴ> 자바에서 객체를 저장해서 한번에 쓸 때
   Socket socket; //클라이언트 받아 저장할 곳
   DataOutputStream outputStream; //출력스트림
   //ㄴ>바깥으로 데이터 객체를 전솔할때 필요한 것

   //생성자를 만나게해야 port 번호같은초기치 처리
   public ServerClass(int portno) throws IOException{//생성자
      Socket s1 = null;
      ServerSocket ss1 = new ServerSocket(portno); //서버소켓생성
      System.out.println("서버가동 . . . . . . .. ."); //listen////

      while(true) {
         s1 = ss1.accept();///기다리는 중
         
         System.out.println("접속주소 : " + s1.getInetAddress() + ", 접속포트 : " + s1.getPort());
         ThreadServerClass tserver1 = new ThreadServerClass(s1);//s1 초기치
         tserver1.start();///run()을 호출
         threadList.add(tserver1); //컬렉션에 add

         System.out.println("접속자 수 : " + threadList.size() + " 명");

      }//while-end
   }//생성자-end

   class ThreadServerClass extends Thread {
      Socket socket1;
      DataInputStream inputStream;
      DataOutputStream outputStream;

      public ThreadServerClass(Socket s1) throws IOException{//생성자
         socket1 = s1;

         //입출력 스트림
         inputStream = new DataInputStream(s1.getInputStream());
         outputStream = new DataOutputStream(s1.getOutputStream());

      }//ThreadServerClass-생성자-end

      @Override
      public void run() {//java 패키지명..... 9887 은빛공주
         String nickname = "";
         try {//이부분이 진행되고 있다는 것은 안정적으로 채팅중 //
            if(inputStream != null) {
               nickname = inputStream.readUTF(); //inputStream에서 문자열 타입을 받아서 nickname에 집어넣음 
               sendChat(nickname + " 님 입장~~~");   //sendChat은 개발자가 만든 함수
               memberList.add(nickname.substring(2, nickname.length()));
               //메소드 2형식 호출문 //클라이언트 처음 보내는것잉 별명
            }//전체 회원에게 금 들어온 별명을 보내기 위한 작업
            while(inputStream != null) {
               //System.out.println(inputStream.readUTF());
               sendChat(inputStream.readUTF());//
               //이번에는 별명이 아니라 클라이언트가 보낸 채팅 내용을 접속한 모두에게보냄
            }
         } catch(IOException e) {
            //e.printStackTrace(); //에러내용 출력을 안하려면 주석단다
         } finally {
            //어찌됐든 채팅으로부터 나왔다
            //나간 쓰레드의 인덱스찾기.

            for(int i=0; i<threadList.size(); i++) {
               if(socket1.equals(threadList.get(i).socket1)){
                  threadList.remove(i); ////퇴장시 remove
                  try {
                     sendChat(nickname + " 님 퇴장 ~");
                     memberList.remove(i);
                     //섯버는 모든 클라이언트에게 누구누구님퇴장이라고 보냄
                  } catch (IOException e ) {
                     //e.printStackTrace();
                  }

               }//if-end
            }//for -end
            System.out.println("접속자 수 : " + threadList.size() + " 명");
         }//finally-end
      }//run-end
      public void sendChat(String chat) throws IOException{
         
         System.out.println(chat);
         if(chat.split("/귓속말").length != 1) {
            String[] splitChat = chat.split("/귓속말");
            chat = "-->";
            
            splitChat[1] = splitChat[1].substring(1, splitChat[1].length());
            
            String[] splitChat2 = splitChat[1].split(" ");
            String from = splitChat2[0];
            String to = splitChat[0].split("-->")[0];
            
            for(int i=1; i<splitChat2.length; i++) chat += splitChat2[i] + " ";
            
            System.out.println("chat : " + chat);
            
            System.out.println("받는사람 : " + from);
            System.out.println("보내는사람 : " + to);
            
            if(from.equals(to)) {
               for(int i=0; i<threadList.size(); i++) {
                  if(memberList.get(i).equals(from)) {
                     String send = "[자신에게는 귓속말을 할 수 없습니다]";
                     threadList.get(i).outputStream.writeUTF(send);
                  }
               }
            } else {
               for(int i=0; i<threadList.size(); i++) {
                  if(memberList.get(i).equals(from)) {
                     String send = "[To]" + to + chat;
                     threadList.get(i).outputStream.writeUTF(send);
                     
                  } else if(memberList.get(i).equals(to)) {
                     String send = "[From]" + from + chat;
                     threadList.get(i).outputStream.writeUTF(send);
                  }
               }
            }
            
         } else {
            for(int i=0; i<threadList.size(); i++) {
               threadList.get(i).outputStream.writeUTF(chat);
            }
         }
         
         //각각의 회원을 찾아가서 별명 or 채팅내용을보냄
      }//sendChat-end
   }//ThreadServerClass-end

}//ServerClass-end

public class TcpMulServer {//TcpMulServer.java
   public static void main(String[] args) throws IOException {
      if(args.length != 1) {
         System.out.println("사용법 : 서버실행은  \'java 패키지명.파일명 포트번호\' 형식으로 입력");
      }                   //sysout("ondal");    //sysout(""ondal"");x
      //new ServerClass();/////////////////
      new ServerClass(Integer.parseInt(args[0])); //port no.
      //객체생성 -초기치 생성자를 주면 제일먼저 생성자를 찾아간다. 
   }
}
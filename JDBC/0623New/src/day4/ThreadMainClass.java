package day4;

class ThreadClass1 extends Thread //thread를 상속받아서 thread가 되었다.
{						//start는 run메소드를 찾아온다. 
	public void run() { //ThreadClass1 클래스가 수행할 작업 
		for(int i=0;i<10;i++) {
			System.out.println(getName()+ "내가 쏘고 있다!");
			try {
				Thread.sleep(500); // 500ms. 0.5초 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} //ThreadClass1 -end

class ThreadClass2 extends Thread
{
	public void run() { //ThreadClass2 클래스가 수행할 작업 
		for(int i=0;i<10;i++) {
			System.out.println(getName()+ "네가 쏘고 있느냐!");
			//getName은 스레드 이름이 찍힌다.
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} //ThreadClass2 - end



public class ThreadMainClass { //클래스1
	//ThreadMainClass.java
	static public void main(String[] args) {
		ThreadClass1 tc1 = new ThreadClass1(); //클래스2
		ThreadClass2 tc2 = new ThreadClass2(); //클래스3
		
		tc1.start(); //ThreadClass1의 run()호출
		tc2.start(); //ThreadClass2의 run()호출
		//프로세스에 두개의 스레드가 올라간다.
		//한쪽이 쉬면 다른 한쪽이 일한다.
		
		
	}
}

package day4_2;

class RunnableClass1 implements Runnable{
	public void run() {
		for(int i=1;i<=10;i++) {
			Thread.currentThread().setName("나는 무적의 :");
			System.out.println(Thread.currentThread().getName()+"내가 쏘고 있네");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// for end
	}// run end
} //RunnableClass1 end

class RunnableClass2 implements Runnable{
	public void run() {
		for(int i=1;i<=10;i++) {
			Thread.currentThread().setName("나는 최고~");
			System.out.println(Thread.currentThread().getName()+"네가 쏘는 건감");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// for end
	}// run end
} //RunnableClass1 end





public class RunnableMainClass {

	public static void main(String[] args) {
		// class ThreadClass2 extends Thread{
		//상속은 멀티 상속 안되므로 다른 상속이 필요 없을 때 이것 사용
		
		//상속해야 할 것이 있을 것 같으면 이것 사용
		Thread th1 = new Thread(new RunnableClass1());
		//RunnableClass r1 = new RunnableClass();
		//Thread th1 = new Thread(r1); //두 줄로 한 것
		
		//상속해야 할 다른 것이 있을 것 같으면 이것 가용.
		Thread th2 = new Thread(new RunnableClass2());
		
		th1.start();
		th2.start();

	}

}

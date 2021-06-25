package telinfoPrintMain;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import telinfoDAO.TelInfoDAO;
import telinfoVO.TelInfoVO;

public class TelInfoMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int sel =0;
		TelInfoDAO tidao = new TelInfoDAO();
		do {
			System.out.println("비상연락망관리");
			System.out.println("--------------------");
			System.out.println("1: 입력");
			System.out.println("2: 수정");
			System.out.println("3: 삭제");
			System.out.println("4: 전체출력");
			System.out.println("5: 종료");
			System.out.println("--------------------");
			System.out.println("이용할 메뉴를 선택하세요");
			
			sel = Integer.parseInt(JOptionPane.showInputDialog("메뉴선택"));
			switch(sel) {
				case 4: //전체 출력 DB- DAO-VO 처리 
					ArrayList<TelInfoVO> tiArray = tidao.getAllInfo();
					//DAO에 있는 전체 출력 메소드를 호출한다. 위에서 tidao 객체를 생성했음.
					//DAO에서 올 때는 tiarray를 가지고 온다. 
					for(TelInfoVO imsi : tiArray) {
						System.out.print(imsi.getId()+"\t");
						System.out.print(imsi.getName()+"\t");
						System.out.println(imsi.getTel()+"\t");
						System.out.println(imsi.getD());
					}
					break;
				//삽입
				case 1: 
					int id = Integer.parseInt(JOptionPane.showInputDialog("아이디는?"));
					String name  = JOptionPane.showInputDialog("이름은?");
					String tel = JOptionPane.showInputDialog("전화번호는?");
					String sDate = JOptionPane.showInputDialog("날짜는?");
				
					boolean b1 = tidao.insert_change_nametel(id, name, tel, sDate);
					if(b1)
						System.out.println("insert ok");
					else
						System.out.println("insert error");
					break;
				//수정
				//update는 이름만 변경
				case 2:
					String b_name  = JOptionPane.showInputDialog("수정할 이름은?");
					String a_name = JOptionPane.showInputDialog("바꾼 이름은?");
					
					boolean complete = tidao.update_name(b_name,a_name);
					if(complete) 
						System.out.println("update 완료");
					else
						System.out.println("update 에러");
					break;
				//삭제
				//id를 입력받아 삭제.
				case 3:
					int d_id = Integer.parseInt(JOptionPane.showInputDialog("삭제할 아이디는?"));
					 					
					boolean complete2 = tidao.delete_id(d_id);
					if(complete2) 
						System.out.println("delete 완료");
					else
						System.out.println("delete 에러");					
					break;
				case 5:
					System.out.println("종료합니다");
					System.exit(1);
					break;
			}
		}while(sel!=5);
		
		
		
		
	}
	

}

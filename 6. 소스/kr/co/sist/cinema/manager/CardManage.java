package kr.co.sist.cinema.manager;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.ManagerMenu;

/**
 * 카드제휴 정보 관리
 * @author sehoon
 *
 */
public class CardManage extends FileRead{
	
	/**
	 * 관리자가 실행할 수 있는 카드 제휴 기능 목록 및 실행
	 */
	public void CardManageStart() {
		
			//메뉴 출력 / 항목 선택 / 기능 실행
			try {
				UIcard.begin();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int input = scan.nextInt();
			
			while(input < 0 || input > 3) {
				System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("                                   메뉴에 없는 번호 입니다. 다시 입력해주세요.");
				System.out.println("                                   1. 카드제휴 정보 추가");
				System.out.println("                                   2. 카드제휴 정보 수정");
				System.out.println("                                   3. 카드제휴 정보 보기");
				System.out.println("                                   0. 관리자 메뉴로 돌아가기");
				System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.print("                                   선택(번호) : ");
				
				System.out.print("선택(번호) : ");
				input = scan.nextInt();
			}
			
			scan.nextLine(); // nextInt()에서 버리고 가는 \r\n을 받기위한 구문
			System.out.print("                                   계속 하시려면 엔터를 입력하세요.");
			scan.nextLine();
			
			if(input == 1) {
				cardAdd();
			}else if (input == 2) {
				cardInfoModify();
			}else if (input == 3) {
				cardView();
			}else if (input == 0) {
				try {
					ManagerMenu managerMenu = new ManagerMenu();
					managerMenu.ManagerInitialMenu();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
	}//start
	
	/**
	 * 현재 카드 제휴 데이터를 보여줌
	 */
	public void cardView() { // 메모장에 만들어진 데이터 및 수정된 카드할인율을 확인할수 있게 해주는 카드뷰
		
		initialReader("card.dat");
		
		for(int i = 0; i < list.size(); i++) {
			
			String[] temp = list.get(i).split("\\■");
			
			System.out.printf("                                   카드번호 : %s\t카드명 : %s\t할인율 : %s\r\n", temp[0], temp[1], temp[2]);
			
		}
		
		pause();
	}
	
	/**
	 * 카드 정보 추가
	 */
	public void cardAdd() {
		
		System.out.print("                                   카드사를 입력해주세요(예시 : 신한 카드 -> 신한) : ");
		String cardCompany = scan.nextLine();
		
		UIcard.menu();
		int area = scan.nextInt();
		
		while(area < 1 || area > 5) {
			System.out.println("                                   없는 지점입니다. 다시입력해주세요.");
			UIcard.menu();
			area = scan.nextInt();
		}
		
		
		System.out.print("                                       할인율을 입력해주세요(0%~90%까지)(5%할인이면 5로 입력) : ");
		int discount = scan.nextInt();
		
		while(discount < 0 || discount > 90) {
			discount = scan.nextInt();
		}
		
		initialReader("card.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if( division[0].equals(""+area)
					&& division[1].equals(cardCompany+" 카드")
					
			) {
				
					System.out.println("                                   입력한 정보는 존재하는 데이터입니다. 다시 입력해주세요.");
					cardAdd();
				
			}
		}
		
		try(
				BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "card.dat",true));	
			){
			writer.append(String.format("%d■%s■%s\r\n"
					, area
					, cardCompany + " 카드"
					, discount + "%" )
					);
			
			System.out.println("                                       작성 완료");
		}
		catch(FileNotFoundException e) {
			System.out.println("card.dat 경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		pause();
		
	}//card 추가
	
	/**
	 * 카드 제휴 정보 수정
	 */
	public void cardInfoModify() {
		
		ArrayList<String> listModify = new ArrayList<String>();
		
		System.out.print("                                   할인율을 조정할 카드사를 입력해주세요(예시 : 신한 카드 -> 신한) : ");
		String cardCompany = scan.nextLine();
		
		System.out.println("                                   1.강남 2. 강북 3. 관악 4. 잠실 5. 홍대");
		System.out.print("                                   할인율을 조정할 지점을 입력해주세요(번호) : ");
		String area = scan.nextLine();
		
		initialReader("card.dat");
		int k = 0; // 수정하려는 카드가 있는지 없는지 보여주기위한 변수
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if(division[0].equals(area) && division[1].equals(cardCompany + " 카드")) {
				System.out.print("                                   할인율을 입력해주세요(0%~90%까지)(5%할인이면 5로 입력) : ");
				int discount = scan.nextInt();
				k++;
				
				while(discount < 0 || discount > 90) {
					discount = scan.nextInt();
				}
				listModify.add(area + "■" + cardCompany + " 카드" + "■" + discount + "%");
			}else {
				listModify.add(division[0] + "■" + division[1] + "■" + division[2]);
			}
			
		}
		if(k == 0 ) {
			System.out.println("                                   없는 카드 입니다.");
		}
		
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "card.dat"));	
			){
			for(int i = 0; i < listModify.size(); i++) {
				writer.write(listModify.get(i) + "\r\n");
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("                                   card.dat 데이터 경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		pause();
		
	}
	
	public void pause() {
		
		System.out.print("                                       계속 하시려면 엔터를 입력하세요.");
		scan.nextLine();
		
		CardManageStart();
		
	}
	// 중간에 멈춰주고 메뉴로 돌아갈지 묻는 메소드
}






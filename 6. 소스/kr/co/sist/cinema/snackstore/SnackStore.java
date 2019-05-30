package kr.co.sist.cinema.snackstore;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.GeneralMenu;
import kr.co.sist.cinema.initialscreen.InitialScreen;
/**
 * 매점
 * @author sehoon
 *
 */
public class SnackStore extends FileRead {
	
	private int pointUse;
	private int priceSum;
	private int areaNum;
	/**
	 * 기본 생성자
	 */
	public SnackStore() {
		this.pointUse = 0;
		this.priceSum = 0;
	}
	/**
	 * 지점 번호를 입력받아오는 생성자
	 * @param areaNum 지점 번호
	 */
	public SnackStore(int areaNum) {
		this.pointUse = 0;
		this.priceSum = 0;
		this.areaNum = areaNum;
		
	}
	/**
	 * 주문하기
	 */
	public void order() {
		
		pause();
		
		if(InitialScreen.getId().equals("")) {
			System.out.println("                                   로그인을 해주세요.");
			InitialScreen start = new InitialScreen();
			start.initial();
		}
		
		ArrayList<String> selectProduct = new ArrayList<String>();
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       원하는 메뉴 선택");
		System.out.println("                                       ex) 11");
		System.out.println();
		System.out.println("                                       0. 뒤로돌아가기");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		
		initialReader("store.dat");
		
		boolean loop = true;
		
		while(loop) {
			System.out.print("                                       메뉴 선택(번호) : ");
			int num = scan.nextInt();
			scan.nextLine();
			
			if(num == 0 ) {
				GeneralMenu generalMenu = new GeneralMenu(InitialScreen.getId());
				generalMenu.generalMenuSelect();
				
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			
			
			while( num < 0 || num > list.size() ) {
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.print("                                        선택(번호) : ");
				num = scan.nextInt();
			}
			
			selectProduct.add(list.get(num -1));
			
			System.out.print("                                       추가 주문 하시겠습니까?(y/n) : ");
			String addOrder = scan.nextLine();
			
			while( !(addOrder.equals("y")) && !(addOrder.equals("n")) )   {
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.print("                                        선택(y/n) : ");
				addOrder = scan.nextLine();
			}
			if(addOrder.equals("n")) {
				loop = false;
			}
		}
		
		showProduct(selectProduct);
	}
	/**
	 * 물품 목록 확인하기
	 * @param selectProduct
	 */
	public void showProduct(ArrayList<String> selectProduct) {
		
		// temp, addNum, addPrice 들의 size는 같음
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> productName = new ArrayList<String>();
		ArrayList<Integer> addNum = new ArrayList<Integer>();
		ArrayList<Integer> addPrice = new ArrayList<Integer>();
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0; i<selectProduct.size(); i++) {
			
			String[] division = selectProduct.get(i).split("\\■");
			
			temp.add(division[0]);
			addNum.add(1);
			productName.add(division[2]);
			addPrice.add(Integer.parseInt(division[3]));
			
			for(int j = 0; j < temp.size() -1; j++) {
				if( temp.get(temp.size()-1).equals(temp.get(j) )){
					
					addNum.set(j, addNum.get(j) +1);
					addPrice.set(j, (addPrice.get(j) + Integer.parseInt(division[3])));
					
				}
			}
		}
		
		//추가한 값들에서 최종 누적값을 제외한 나머지값들을 0으로 만듬
		for(int i = 0; i < temp.size(); i++) {
			for(int j = i+1; j < temp.size(); j++) {
				if(temp.get(i).equals(temp.get(j))) {
					temp.set(j, "0");
					addNum.set(j, 0);
					addPrice.set(j, 0);
					productName.set(j, "0");
				}
			}
		}
		
//		0인 값들을 제외하고 result 에 값을 입력
		for(int i = 0; i < temp.size(); i++) {
			if(!(temp.get(i).equals("0")) ) {
				result.add(temp.get(i) + ":" + addNum.get(i) + ":" + addPrice.get(i) + ":" + productName.get(i) );
			}
		}
		//result값은 최종 물품번호 : 구매개수 : 해당물품 총 합계
		for(int i = 0; i < result.size(); i++) {
			String[] division = result.get(i).split(":");
			System.out.printf( "                                       상품명 : %s 개수 : %s 가격 : %,d원\n"
								, division[3]
								, division[1]
								, Integer.parseInt(division[2])
								);
			this.priceSum += Integer.parseInt(division[2]);
		}
			storePointCheck();
			storePayMent(result);
	}
	/**
	 * 물품 구입시 포인트 사용 및 결제
	 */
	public void storePointCheck() {
		System.out.print("                                       포인트를 사용하시겠습니까?(y/n)");
		String check = scan.nextLine();
		
		while( !(check.equalsIgnoreCase("y")) && !(check.equalsIgnoreCase("n")) ){
			System.out.println("                                       잘못된 입력입니다. 다시 입력해주세요.");
			System.out.print("                                       포인트를 사용하시겠습니까?(y/n)");
			check = scan.nextLine();
		}
		
		
		if(check.equalsIgnoreCase("y")) {
			int holdPoint = 0;
			
			initialReader("회원.txt");
			for(int i = 0; i < list.size(); i++ ) {
				String[] division = list.get(i).split("\\■");
				
				if(division[0].equals(InitialScreen.getId()) ) {
					holdPoint = Integer.parseInt(division[9]);
				}
				
			}
			System.out.println("                                       사용 가능 포인트 : " + holdPoint);
			
			System.out.print("                                       얼마의 포인트를 사용하시겠습니까?");
			this.pointUse = scan.nextInt();
			scan.nextLine(); //엔터 없애기
			
			while(this.pointUse > holdPoint) {
				System.out.println("                                       사용가능한 포인트를 넘었습니다. 다시 입력해주세요.");
				System.out.print("                                       얼마의 포인트를 사용하시겠습니까?");
				this.pointUse = scan.nextInt();
				scan.nextLine();
			}
			
			while(this.pointUse < 0) {
				System.out.println("                                       잘못된 입력입니다. 다시 입력해주세요.");
				System.out.print("                                       얼마의 포인트를 사용하시겠습니까?");
				this.pointUse = scan.nextInt();
				scan.nextLine();
			}
		}
		System.out.println();
		System.out.println("                                       결제가 완료되었습니다.");
		
		this.priceSum -= this.pointUse;
		
		pause();
	}
	
	/**
	 * 물품결제데이터.dat 파일에 결제 내역 저장
	 * @param result
	 */
	public void storePayMent(ArrayList<String> result) {
		
		Calendar today = Calendar.getInstance();
		
		initialReader("store.dat");
		
		int pointUsed = this.pointUse;
		
		
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH+"물품결제데이터.dat",true));
			)
		{
			for(int i = 0; i < result.size(); i++) {
				String[] division = result.get(i).split(":");
				for(int j = 0; j < list.size(); j++) {
				}
				writer.append(InitialScreen.getId() + "■" + division[0]+"■" + division[3] + "■" + ((Integer.parseInt(division[2])) - this.pointUse) + "■"
							+ division[1] + "■" + String.format("%tF %tT", today, today) + "■" + this.pointUse + "■" + this.areaNum + "■\r\n" );
				this.pointUse = 0;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("물품결제데이터.dat 경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		pointUpdate(pointUsed);
		
	}
	
	/**
	 * 사용한 포인트와 누적 포인트를 계산하여 회원 정보에 수정
	 * @param pointUsed
	 */
	public void pointUpdate(int pointUsed) {
		
		ArrayList<String> pointUpdateList = new ArrayList<String>();
		
		initialReader("회원.txt");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			
			if(InitialScreen.getId().equals(division[0]) ) {
				pointUpdateList.add(division[0] + "■" + division[1] + "■" + division[2] + "■" + division[3] + "■"
									+ division[4] + "■" + division[5] + "■" + division[6] + "■" + division[7] + "■"
									+ division[8] + "■" + ( Integer.parseInt(division[9]) - pointUsed + (this.priceSum /10 ) )  + "■"
									+ division[10] + "■" + division[11] + "■" + division[12] + "■" + division[13] + "■"
									+ division[14] + "■" + division[15] + "■" + division[16] + "■"
									);
			}else {
				pointUpdateList.add(list.get(i));
			}
		}
		
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "회원.txt"));
			)
		{
			for(int i = 0; i < pointUpdateList.size(); i++) {
				writer.write(pointUpdateList.get(i) + "\r\n");
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("회원.txt 경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}



package kr.co.sist.cinema.reservation;

import java.util.ArrayList;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.InitialScreen;
/**
 * 예매하기
 * @author sehoon
 *
 */
public class Ticketing extends FileRead{

	private int numberPeople;
	private int cardTypeNumber;
	private int pointUse;
	private String check;
	private String cardType;
	private Event event;
	/**
	 * 기본 생성자
	 */
	public Ticketing() {
		this.pointUse = 0;
		this.numberPeople = 0;
		this.cardTypeNumber = 0;
		this.check = "";
		this.cardType = "";
		
	}

	/**
	 * 티켓 예매 수 읽기
	 * @return
	 */
	public int getNumberPeople() {
		return numberPeople;
	}
	/**
	 * 이벤트 읽기
	 * @return
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * 티켓 구입 수 입력
	 */
	public void paymentInformation() {
		System.out.print("                                   몇명인가요?(숫자만입력) : ");
		this.numberPeople = scan.nextInt();

	}
	/**
	 * 카드 종류 선택
	 * @param areaNum
	 */
	public void cardTypeSelect(String areaNum) {
		
		ArrayList<String> cardCompany = new ArrayList<String>();
		
		initialReader("card.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
				if(areaNum.equals(division[0]) ) {
					cardCompany.add(division[1] + "(" + division[2] + "할인)");
				}
		}
		
		String guidePlate = "조조할인, vip할인, 문화의날 할인일 경우는 카드할인이 되지않습니다. 카드종류를 선택해주세요(번호) : ";
		do {
			
			
			for(int i = 1; i <= cardCompany.size(); i++) {
				System.out.println("                                   "+i + ". " + cardCompany.get(i-1));
			}
			System.out.print("                                   " + guidePlate);
			
			guidePlate = "없는 카드 종류입니다. 다시 입력해주세요(번호) : ";
			this.cardTypeNumber = scan.nextInt();
			
			this.cardType = cardCompany.get(cardTypeNumber -1 );
			
		} while (this.cardTypeNumber < 1 || this.cardTypeNumber > cardCompany.size());
		
		// nextInt()에서 남은 \r\n을 제거
		scan.nextLine();
		
		PointCheck();
		check(areaNum);
		
	}
	/**
	 * 포인트 사용 유무
	 */
	public void PointCheck() {
		System.out.print("                                   포인트를 사용하시겠습니까?(y/n)");
		String check = scan.nextLine();
		
		while( !(check.equalsIgnoreCase("y")) && !(check.equalsIgnoreCase("n")) ){
			System.out.println("                                   잘못된 입력입니다. 다시 입력해주세요.");
			System.out.print("                                   포인트를 사용하시겠습니까?(y/n)");
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
			System.out.println("                                   사용 가능 포인트 : " + holdPoint);
			
			System.out.print("                                   얼마의 포인트를 사용하시겠습니까?");
			this.pointUse = scan.nextInt();
			
			while(this.pointUse > holdPoint) {
				System.out.println("                                   사용가능한 포인트를 넘었습니다. 다시 입력해주세요.");
				System.out.print("                                   얼마의 포인트를 사용하시겠습니까?");
				this.pointUse = scan.nextInt();
			}
			
			while(this.pointUse < 0) {
				System.out.println("                                   잘못된 입력입니다. 다시 입력해주세요.");
				System.out.print("                                   얼마의 포인트를 사용하시겠습니까?");
				this.pointUse = scan.nextInt();
			}
			
			scan.nextLine();
		}else {
			
		}
		pause();
	}
	/**
	 * 결제 확인 유무
	 * @param areaNum
	 */
	public void check(String areaNum) {
		System.out.print("                                   계속 결제를 원하시면 y 예매하기 첫화면으로 돌아가길 원하시면 n을 입력해주세요.");
		String checkInput = scan.nextLine();
		
		while( !(checkInput.equalsIgnoreCase("y")) && !(checkInput.equalsIgnoreCase("n")) ) {
			System.out.print("                                   잘못된 값을 입력하셨습니다.");
			System.out.print("                                   계속 결제를 원하시면 y 예매하기 첫화면으로 돌아가길 원하시면 n을 입력해주세요.");
			checkInput = scan.nextLine();
			
		}
		
		if(checkInput.equalsIgnoreCase("y") ) {
			// 카드종류와 인원, 사용할 포인트를 넘겨줌
			event = new Event(this.cardType, this.numberPeople, this.pointUse);
			event.applyEvent(areaNum);
		}
		else {
			AreaSelect a = new AreaSelect();
			a.areaSelect();
			a.movieSelectScreen();
		}
		
	}

}

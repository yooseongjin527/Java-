package kr.co.sist.cinema.mypage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.FileRead;

/**
 * 해당 회원의 멤버십 정보 열람
 * @author sehoon
 *
 */
public class MyMembership extends FileRead {

	public static final String PATH_MEMBER = "D:\\class\\java\\cinema\\data\\회원.txt";
	public static final String PATH_RESERVATION = "D:\\class\\java\\cinema\\data\\예매.txt";
	public static final String ISSUED_CARD_NUM = "D:\\class\\java\\cinema\\data\\issuedCardNum.dat";
	
	private static ArrayList<Member> listMember;
	private static ArrayList<Reservation> listReservation;
	private static Scanner scan;
	private static Calendar c;
	private static boolean loopCancelCard;

	static {
		listMember = new ArrayList<Member>();
		listReservation = new ArrayList<Reservation>();
		scan = new Scanner(System.in);
		c = Calendar.getInstance();
		loopCancelCard = true;
	}

	private String id;

	/**
	 * 아이디를 초기화하는 생성자
	 * @param id
	 */
	public MyMembership(String id) {
		this.id = id;
	}

	//----------------------------------------------밑에서부터 MyMembership의 메소드들
	
	/**
	 * 간략한 멤버십 정보를 출력
	 */
	public void info() {
		for (int i = 0; i < listMember.size(); i++) {
			if (listMember.get(i).getId().equals(id)) {
				System.out.println();
				System.out.print("                                       [멤버십 정보]  ");
				System.out.printf("                                       %s님은 '%s %s' 회원입니다.\n", listMember.get(i).getName(), thisYear(), listMember.get(i).getGrade());
				break;
			}
		}
	}

	/**
	 * [멤버십 정보] 페이지에서 선택가능한 메뉴를 보여주는 메소드
	 */
	public void menu() {
		info();
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 포인트 현황 (현재 보유, 소멸 예정 포인트)");
		System.out.printf("                                       현재 보유 포인트 %,15dp\n", Integer.parseInt(point()));
		System.out.println("                                       2. 멤버십 카드 정보 (카드 등록 및 해지)");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       카드명		        카드번호		발급일");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.printf("                                       %s\n", myCard());
		System.out.println("                                       0. 이전 메뉴로 돌아가기");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                       선택(번호) : ");
	}
	
	/**
	 * 선택한 [멤버십 정보] 메뉴를 불러오는 메소드
	 */
	public void selectMenu() {
		boolean loop = true;
		while (loop) {
			menu();
			switch (scan.nextLine()) {
			case "1":
				selectPointStatusMenu();
				break;
			case "2":
				selectRegisterCardMenu();
				loopCancelCard = true;
				break;
			case "0":
				loop = false;
				break;
			default:
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	/**
	 * '카드가 존재(o) > [멤버십 카드 해지하기], 카드가 존재(x) > [멤버십 카드 등록하기]' 메뉴를 불러옴
	 */
	private void selectRegisterCardMenu() {
		for (int i = 0; i < listMember.size(); i++) {
			if (listMember.get(i).getId().equals(id)) {
				if (listMember.get(i).getIsRegistered().equals("true")) {
					cancelCard();
					break;
				} else {
					registerCard();
					break;
				}
			}
		}
	}
	
	/**
	 * 멤버십 카드 등록
	 */
	private void registerCard() {
		boolean loop = true;
		while (loop) {
			System.out.println();
			System.out.println("                                       [멤버십 카드 등록하기]");
			System.out.println("                                       1. 멤버십 카드 등록");
			System.out.println("                                       0. 이전 메뉴로 돌아가기");
			System.out.print("                                       선택(번호) : ");
			switch(scan.nextLine()) {
			case "1":
				registration();
				loop = false;
				break;
			case "0":
				loop = false;
				break;
			default:
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	/**
	 * 주어진 멤버십 카드 번호를 이용하여 정보 등록하기
	 */
	private void registration() {
		boolean loop = true;
		while (loop) {
			System.out.println();
			System.out.println("카드 번호를 입력하세요.");
			System.out.println("((예) 7127-1400-2323-0000 와 같이  카드 번호를 입력해 주세요.)");
			System.out.print("카드 번호 : ");
			String input = scan.nextLine(); 
			if (isValid(input)) {
				boolean loopRegisterOrCancel = true;
				while (loopRegisterOrCancel) {
					System.out.println("===========================================");
					System.out.println("1. 등록");
					System.out.println("0. 취소");
					System.out.println("===========================================");
					System.out.print("선택(번호) : ");
					String inputRegisterOrCancel = scan.nextLine();
					for (int i=0; ; i++) {
						if (inputRegisterOrCancel.equals("1")) {
							completeRegistration(input);
							loopRegisterOrCancel = false;
							loop = false;
							break;
						} else if (inputRegisterOrCancel.equals("2")) {
							loopRegisterOrCancel = false;
							loop = false;
							break;
						} else {
							System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
							pause();
							break;
						}
					}
				}
			} else {
				System.out.println();
				System.out.println("유효하지 않은 번호입니다. 다시 입력하시려면 '1', 취소하시려면 '0' 을 입력해주세요.");
				System.out.print("선택(번호) : ");
				input = scan.nextLine();
				switch (input) {
				case "1":
					
					break;
				case"0":
					loop = false;
					break;
				}
			}
		}
	}
	
	/**
	 * 멤버십 카드 등록 완료되었는지 보여줌
	 * @param input
	 */
	private void completeRegistration(String input) {
		for (int i=0; i<listMember.size(); i++) {
			if (listMember.get(i).getId().equals(id)) {
				c = Calendar.getInstance();
				Member m = new Member(listMember.get(i).getId(), listMember.get(i).getPassword(), listMember.get(i).getGender(), listMember.get(i).getName(), listMember.get(i).getAge(), listMember.get(i).getTel(), listMember.get(i).getAddress(), listMember.get(i).getLastMonthViewCount(), listMember.get(i).getThisMonthViewCount(), listMember.get(i).getPoint(), listMember.get(i).getIsAdmin(), input, listMember.get(i).getRegistrationDate(), listMember.get(i).getGrade(), "true", String.format("%tF", c), listMember.get(i).getCardName());
				listMember.set(i, m);
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(ISSUED_CARD_NUM));
					String txt = "";
					String line = null;
					//등록된 카드번호 input을 issuedCardNum.dat에서 삭제
					while ((line = reader.readLine()) != null) {
						if (line.equals(input)) {
							
						} else {
							txt += line + "\r\n";
						}
					}
					reader.close();
					BufferedWriter writer = new BufferedWriter(new FileWriter(ISSUED_CARD_NUM));
					writer.write(txt);
					writer.close();
				} catch (Exception e) {
					System.out.println("completeRegistration : " + e.toString());
				}
				
				System.out.println();
				System.out.printf("%s님의 카드등록이 완료되었습니다.\n", listMember.get(i).getName());
				pause();
				break;
			} 
		}
	}
	
	/**
	 * 유효한 멤버십 카드 번호인지 확인
	 * @param input
	 * @return
	 */
	private boolean isValid(String input) {
		boolean isValid = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ISSUED_CARD_NUM));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (input.equals(line)) {
					isValid = true;
					break;
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return isValid;
	}

	/**
	 * 삭제할 멤버십 카드 정보와 삭제 여부
	 */
	private void cancelCard() {
		while (loopCancelCard) {
			System.out.println();
			System.out.println("                                       [멤버십 카드 해지하기]");
			System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
			System.out.println("                                       카드명		        카드번호		발급일");
			System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
			System.out.printf("                                       %s\n", myCard());
			System.out.println("                                       한번 삭제하신 카드 번호는 다시 등록하실 수 없습니다. 삭제하시겠습니까? (y/n)");
			System.out.print("                                       선택(y/n) : ");
			switch(scan.nextLine()) {
			case "y":
				completeDelete();
				break;
			case "n":
				loopCancelCard = false;
				break;
			default:
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	/**
	 * 멤버십 카드 삭제 진행완료 여부
	 */
	private void completeDelete() {
		for (int i=0; i<listMember.size(); i++) {
			if (listMember.get(i).getId().equals(id)) {
				listMember.get(i).setIsRegistered("false");
			}
		}
		System.out.println();
		System.out.print("                                       카드 삭제가 완료되었습니다. ");
		loopCancelCard = false;
		pause();
	}

	/**
	 * 멤버십 카드 정보 보기
	 * @return
	 */
	private String myCard() {
		for (int i = 0; i < listMember.size(); i++) {
			if (listMember.get(i).getId().equals(id)) {
				if (Boolean.parseBoolean(listMember.get(i).getIsRegistered())) {
					String cardName = listMember.get(i).getCardName();
					String cardNum = listMember.get(i).getCardNum();
					String issuedDate = listMember.get(i).getIssuedDate();
					String result = String.format("%s\t%s\t%s\n", cardName, cardNum, issuedDate);
					return result;
				} else {
					return "                                       등록된 카드가 없습니다. 등록하시려면 '2'를 입력하세요.";
				}
			}
		}
		return "                                       아이디정보없음";
	}
	
	//--------------------------------------
	
	/**
	 * 포인트 현황 메뉴 UI
	 */
	private void pointStatusMenu() {
		System.out.println();
		System.out.println("                                       [포인트 현황]");
		System.out.printf("                                       현재 보유 포인트 %,10dp\n", Integer.parseInt(point()));
		System.out.printf("                                       소멸 예정일 \t\t-%s일\n", dDay());
		System.out.printf("                                       (%s년 12월 31일 영업 종료시)\n", thisYear());
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 포인트 내역 조회");
		System.out.println("                                       2. 소멸 예정일이란?");
		System.out.println("                                       0. 이전 메뉴로 돌아가기");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                       선택(번호) : ");
	}

	/**
	 * 포인트 현황 메뉴에서 선택한 번호에 대한 기능 실행
	 */
	private void selectPointStatusMenu() {
		boolean loop = true;
		while (loop) {
			pointStatusMenu();
			switch (scan.nextLine()) {
			case "1":
				selectPointDetailsMenu();
				break;
			case "2":
				expectedToPerish();
				break;
			case "0":
				loop = false;
				break;
			default:
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	/**
	 * 포인트 내역 조회하기
	 */
	private void pointDetails() {
		System.out.println();
		System.out.println("                                       [포인트 내역 조회]   ▶[전체] | 적립 | 사용 | 소멸");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       일자		지점	이용내용					이용금액	적립포인트	사용포인트	      구분");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		
		for (int i=0; i<listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id)) {
				
				if(i >= 1) {
					if( listReservation.get(i).getReservationDate().equals(listReservation.get(i-1).getReservationDate()) 
						&& listReservation.get(i).getTheater().equals(listReservation.get(i-1).getTheater())
						&& listReservation.get(i).getTitle().equals(listReservation.get(i-1).getTitle())
						&& listReservation.get(i).getPrice().equals(listReservation.get(i-1).getPrice())
						&& occuredPoint(i).equals(occuredPoint(i-1))
						&& usedPoint(i).equals(usedPoint(i-1))
						&& sortation(i).equals(sortation(i-1))
						) {
						continue;
					}
				}
				
				System.out.printf("                                       %s\t%s\t%-37s\t%7s\t%5s\t%7s\t%9s\n"
						, listReservation.get(i).getReservationDate()
						, listReservation.get(i).getTheater().equals("1") ? "강남" 
								: listReservation.get(i).getTheater().equals("2") ? "강북"
								: listReservation.get(i).getTheater().equals("3") ? "관악"
								: listReservation.get(i).getTheater().equals("4") ? "잠실" : "홍대"
						, "티켓구입(10%적립) - " + listReservation.get(i).getTitle()
						, String.format("%,d원", Integer.parseInt(listReservation.get(i).getPrice()) )
						, occuredPoint(i)
						, usedPoint(i)
						, sortation(i));
			}
			
			
		}
		
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
	}
	
	/**
	 * 포인트 현황 기능 선택 메뉴 UI
	 */
	private void pointDetailsMenu() {
		pointDetails();
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 적립");
		System.out.println("                                       2. 사용");
		System.out.println("                                       3. 소멸");
		System.out.println("                                       0. 이전 메뉴로 돌아가기");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                       선택(번호) : ");
	}
	
	/**
	 * 포인트 현황 기능 메뉴에서 선택한 기능 실행
	 */
	private void selectPointDetailsMenu() {
		boolean loop = true;
		while (loop) {
			pointDetailsMenu();
			switch (scan.nextLine()) {
			case "1":
				reservedPoint();
				break;
			case "2":
				UsedPoint();
				break;
			case "3":
				extinctPoint();
				break;
			case "0":
				loop = false;
				break;
			default:
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	/**
	 * 적립된 포인트 내역 조회
	 */
	private void reservedPoint() {
		
		ArrayList<Reservation> rs = new ArrayList<Reservation>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i<listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id) && (sortation(i).equals("적립") || sortation(i).equals("사용&적립"))) {
				index.add(i);
				rs.add(listReservation.get(i));
			}
		}
		System.out.println();
		System.out.println("                                       [포인트 내역 조회]    전체 | ▶[적립] | 사용 | 소멸");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       일자		지점	이용내용						이용금액   적립포인트  사용포인트	구분");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");

		for (int i=0; i<rs.size(); i++) {
			System.out.printf("                                       %s\t%s\t%-37s\t%7s\t%5s\t%7s\t%9s\n"
					, rs.get(i).getReservationDate()
					, rs.get(i).getTheater().equals("1") ? "강남" 
						: rs.get(i).getTheater().equals("2") ? "강북"
						: rs.get(i).getTheater().equals("3") ? "관악"
						: rs.get(i).getTheater().equals("4") ? "잠실" : "홍대"
					, "티켓구입(10%적립) - " + rs.get(i).getTitle()
					, String.format("%,d원", Integer.parseInt(rs.get(i).getPrice()))
					, occuredPoint(index.get(i))
					, usedPoint(index.get(i))
					, sortation(index.get(i)));
		}
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		pause();
	}

	/**
	 * 사용한 포인트 내역 조회
	 */
	private void UsedPoint() {
		
		ArrayList<Reservation> rs = new ArrayList<Reservation>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id) && (sortation(i).equals("사용") || sortation(i).equals("사용&적립"))) {
				index.add(i);
				rs.add(listReservation.get(i));
			}
		}
		System.out.println();
		System.out.println("                                       [포인트 내역 조회]    전체 | 적립 | ▶[사용] | 소멸");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       일자		지점	이용내용						이용금액   적립포인트  사용포인트	구분");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");

		for (int i=0; i<rs.size(); i++) {
			System.out.printf("                                       %s\t%s\t%-37s\t%7s\t%5s\t%7s\t%9s\n"
					, rs.get(i).getReservationDate()
					, rs.get(i).getTheater().equals("1") ? "강남" 
						: rs.get(i).getTheater().equals("2") ? "강북"
						: rs.get(i).getTheater().equals("3") ? "관악"
						: rs.get(i).getTheater().equals("4") ? "잠실" : "홍대"
					, "티켓구입(10%적립) - " + rs.get(i).getTitle()
					, String.format("%,d원", Integer.parseInt(rs.get(i).getPrice()))
					, occuredPoint(index.get(i))
					, usedPoint(index.get(i))
					, sortation(index.get(i)));
		}
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		pause();
	}
	
	/**
	 * 소멸된 포인트 내역 조회
	 */
	private void extinctPoint() {
		
		ArrayList<Reservation> rs = new ArrayList<Reservation>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id) && sortation(i).equals("소멸")) {
				index.add(i);
				rs.add(listReservation.get(i));
			}
		}
		System.out.println();
		System.out.println("                                       [포인트 내역 조회]    전체 | 적립 | 사용 | ▶[소멸]");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       일자		지점	이용내용						이용금액   적립포인트  사용포인트	구분");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");

		for (int i=0; i<rs.size(); i++) {
			System.out.printf("                                       %s\t%s\t%-37s\t%7s\t%5s\t%7s\t%9s\n"
					, rs.get(i).getReservationDate()
					, rs.get(i).getTheater().equals("1") ? "강남" 
						: rs.get(i).getTheater().equals("2") ? "강북"
						: rs.get(i).getTheater().equals("3") ? "관악"
						: rs.get(i).getTheater().equals("4") ? "잠실" : "홍대"
					, "티켓구입(10%적립) - " + rs.get(i).getTitle()
					, String.format("%,d원", Integer.parseInt(rs.get(i).getPrice()))
					, occuredPoint(index.get(i))
					, usedPoint(index.get(i))
					, sortation(index.get(i)));
		}
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		pause();
	}

	/**
	 * 포인트 내역을 내림차순으로 정렬
	 * @param rs
	 */
	private void rsDescSort(ArrayList<Reservation> rs) {
		Reservation[] temp = new Reservation[listReservation.size()];//뒤에 빈방 남게 만듬***
		int j=0;
		for (int i=0; i<rs.size(); i++) {
			//if (rs.get(i).getId().equals(id)) {
				temp[j] = rs.get(i);
				j++;
			//}
		}
		int tempSize = j;
		for (int i=0; i<tempSize; i++) {
			for (int m=0; m<tempSize-1; m++) {
				for (int n=0; n<tempSize-1-m; n++) {
					
					int tempY = Integer.parseInt(temp[n].getReservationDate().substring(0, 4));
					int tempM = Integer.parseInt(temp[n].getReservationDate().substring(5, 7)) - 1;
					int tempD = Integer.parseInt(temp[n].getReservationDate().substring(8, 10));
					c.set(tempY, tempM, tempD);
					long t1 = c.getTime().getTime();
					
					tempY = Integer.parseInt(temp[n+1].getReservationDate().substring(0, 4));
					tempM = Integer.parseInt(temp[n+1].getReservationDate().substring(5, 7)) - 1;
					tempD = Integer.parseInt(temp[n+1].getReservationDate().substring(8, 10));
					c.set(tempY, tempM, tempD);
					long t2 = c.getTime().getTime();
					
					if (t1 < t2) {//내림차순 정리
						Reservation tempReservation = temp[n];
						temp[n] = temp[n+1];
						temp[n+1] = tempReservation;
					}
				}
			}
			System.out.printf("                                       %s\t%s\t%-37s\t%7s\t%5s\t%7s\t%9s\n"
					, temp[i].getReservationDate()
					, temp[i].getTheater().equals("1") ? "강남" 
							: temp[i].getTheater().equals("2") ? "강북"
							: temp[i].getTheater().equals("3") ? "관악"
							: temp[i].getTheater().equals("4") ? "잠실" : "홍대"
					, "티켓구입(10%적립) - " + temp[i].getTitle()
					, String.format("%,d원", Integer.parseInt(temp[i].getPrice()))
					, occuredPoint(i)
					, usedPoint(i)
					, sortation(i));
		}
	}
	
	/**
	 * 해당 년도 값 읽기
	 * @return
	 */
	public String thisYear() {
		c = Calendar.getInstance();
		String thisYear = c.get(Calendar.YEAR) + "";
		return thisYear;
	}
	
	/**
	 * 해당 아이디의 포인트 값 읽기
	 * @return
	 */
	public String point() {
		for (int i = 0; i < listMember.size(); i++) {
			if (listMember.get(i).getId().equals(id)) {
				return listMember.get(i).getPoint();
			}
		}
		return "                                       아이디정보없음";
	}

	/**
	 * 12월 31일에 대한 틱값 읽기
	 * @return
	 */
	private String dDay() {
		int dDay = 0;
		for (int i = 0; i<listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id)) {
				int y = Integer.parseInt(thisYear());
				c.set(y, 11, 31);
				long endOfYearTick = c.getTime().getTime();
				long nowTick = System.currentTimeMillis();
				long tick = endOfYearTick - nowTick;
				dDay = (int) (tick / 1000 / 60 / 60 / 24);
				return dDay + "";
			} 
		}
		return "                                       아이디정보없음";		
	}
	
	/**
	 * 포인트가 생성된지 1년이 됬으면 true 아니면 false
	 * @param i
	 * @return
	 */
	private boolean extinct(int i) {
		boolean isExtinct = false;
		int y = Integer.parseInt(listReservation.get(i).getReservationDate().substring(0, 4));
		int m = Integer.parseInt(listReservation.get(i).getReservationDate().substring(5, 7)) - 1;
		int d = Integer.parseInt(listReservation.get(i).getReservationDate().substring(8, 10));
		c.set(y, m, d);
		long reservationDateTick = c.getTime().getTime();
		c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR) - 1, 11, 31);
		long endOfLastYearTick = c.getTime().getTime();
		if (reservationDateTick <= endOfLastYearTick) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 소멸되지 않은 포인트만 값으로 넘김
	 * @param i
	 * @return
	 */
	private String occuredPoint(int i) {
		String result = "";
		if (!extinct(i)) {//소멸(x)
			if (!(listReservation.get(i).getOccuredPoint().equals("0"))) {
				result = String.format("%,d", Integer.parseInt(listReservation.get(i).getOccuredPoint()));
			}
		}
		return result;
	}

	/**
	 * 사용한 포인트 정보 넘김
	 * @param i
	 * @return
	 */
	private String usedPoint(int i) {
		String result = "";
		if (extinct(i)) {
			if (listReservation.get(i).getOccuredPoint().equals("0")) {
				result = "-" + String.format("%,d", Integer.parseInt(listReservation.get(i).getUsedPoint()));
			} else {
				if (!(listReservation.get(i).getUsedPoint().equals("0"))) {
					result = "-" + String.format("%,d", Integer.parseInt(listReservation.get(i).getUsedPoint()) + Integer.parseInt(listReservation.get(i).getOccuredPoint()));
				} else {
					result = "-" + String.format("%,d", Integer.parseInt(listReservation.get(i).getOccuredPoint()));
				}
			}
		} else {
			if (!(listReservation.get(i).getUsedPoint().equals("0"))) {
				result = "-" + String.format("%,d", Integer.parseInt(listReservation.get(i).getUsedPoint()));
			}
		}
		return result;
	}

	/**
	 * 포인트 현황 메뉴에서 선택한 메뉴에 대해 나타냄
	 * @param i
	 * @return
	 */
	private String sortation(int i) {
		String result = "";
		if (extinct(i)) {
			if (!(listReservation.get(i).getOccuredPoint().equals("0"))) {
				result = "소멸";
			} else {
				result = "사용";
			}
		} else {
			if (!(listReservation.get(i).getOccuredPoint().equals("0"))) {
				if (!(listReservation.get(i).getUsedPoint().equals("0"))) {
					result = "사용&적립";
				} else {
					result = "적립";
				}
			} else {
				if (!(listReservation.get(i).getUsedPoint().equals("0"))) {
					result = "사용";
				}
			}
		}
		return result;
	}

	/**
	 * 포인트 소멸 예정일에 대한 정보를 설명
	 */
	private void expectedToPerish() {
		System.out.println();
		System.out.println("                                       [소멸 예정일]");
		System.out.printf("                                       회원님의 포인트 소멸 예정일은 %s년 12월 31일로 D-%s일 남았습니다.\n", thisYear(), dDay());
		System.out.println("                                       (현재 시각 기준)");
		System.out.println("                                       ▶ 소멸예정일이란?");
		System.out.println("                                       쌍용 적립 포인트의 유효기간은 최초 적립시점인 해당 년 말일이며, \r\n"
							+ "                                       해당 기간 안에 사용 하지 못한 잔여포인트가 그 해 말일 영업 종료 후\r\n"
							+ "                                       소멸 됩니다.");
		System.out.println();
		
		pause();
	}

	/**
	 * ArrayList<Member> list에 회원정보 담기
	 */
	public void loadMember() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH_MEMBER));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				Member m = new Member(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8],
						temp[9], temp[10], temp[11], temp[12], temp[13], temp[14], temp[15], temp[16]);
				listMember.add(m);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * ArrayList<Reservation> list에 예약정보 담기
	 */
	public void loadReservation() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH_RESERVATION));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				Reservation r = new Reservation(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7],
						temp[8], temp[9], temp[10], temp[11], temp[12], temp[13]);
				listReservation.add(r);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
}
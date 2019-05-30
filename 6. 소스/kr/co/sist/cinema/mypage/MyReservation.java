package kr.co.sist.cinema.mypage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 해당 회원의 예매내역
 * @author sehoon
 *
 */
public class MyReservation {

	public static final String PATH_RESERVATION = "D:\\class\\java\\cinema\\data\\예매.txt";
	
	private static ArrayList<Reservation> listReservation;
	private static ArrayList<Reservation> rs;
	private static ArrayList<Integer> index;
	private static Scanner scan;
	
	static {
		listReservation = new ArrayList<Reservation>();
		rs = new ArrayList<Reservation>();
		index = new ArrayList<Integer>();
		scan = new Scanner(System.in);
		
	}
	
	private String id;

	/**
	 * 아이디를 초기화하는 생성자
	 * @param id 해당 회원의 아이디
	 */
	public MyReservation(String id) {
		this.id = id;
	}
	
	//----------------------------------------------밑에서부터 MyReservation의 메소드들
	
	/**
	 * 해당 회원의 예매내역에 관한 정보 및 기능 선택
	 */
	public void confirmAndCancel() {
		System.out.println();
		System.out.print("[예매 확인/취소]  ");
		System.out.println("예매하신 영화 내역과 취소 내역을 확인할 수 있습니다.");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("NO	예매번호	영화명					영화관		상영일		예매일		예매취소");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		for (int i=0; i<listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id)) {
				System.out.printf("%d\t%s\t%-37s\t%s\t%s\t%s\t%s\n"
						, i + 1
						, listReservation.get(i).getReservationNum()
						, listReservation.get(i).getTitle()
						, listReservation.get(i).getTheater().equals("1") ? "강남" 
								: listReservation.get(i).getTheater().equals("2") ? "강북"
								: listReservation.get(i).getTheater().equals("3") ? "관악"
								: listReservation.get(i).getTheater().equals("4") ? "잠실" : "홍대"
						, listReservation.get(i).getShowDate()
						, listReservation.get(i).getReservationDate()
						, listReservation.get(i).getCancelDate().equals("0") ? "" : "예매취소");
			}
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("=========================================================================================================================");
		System.out.println("1. 예매 내역  & 예매 취소");
		System.out.println("2. 지난 내역");
		System.out.println("3. 취소 내역");
		System.out.println("4. 유의사항  ( 예매 및 결제  | 티켓 교환방법  | 취소 및 환불 규정  | 관람 유의사항 )");
		System.out.println("0. 이전 메뉴로 돌아가기");
		System.out.println("=========================================================================================================================");
		System.out.print("선택(번호) : ");
	}
	
	/**
	 * 예매내역 메뉴에서 기능 선택
	 */
	public void selectMenu() {
		boolean loop = true;
		while (loop) {
			confirmAndCancel();
			switch (scan.nextLine()) {
			case "1":
				confirmDetails();
				break;
			case "2":
				pastDetails();
				break;
			case "3":
				cancelDetails();
				break;
			case "4":
				notice();
				break;
			case "0":
				loop = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	
	/**
	 * 예매내역 보여줌
	 */
	private void confirmDetails() {
		boolean loop = true;
		while (loop) {
			rs.clear();
			for (int i=0; i<listReservation.size(); i++) {
				if (listReservation.get(i).getId().equals(id) && sortation(i).equals("current")) {
					index.add(i);
					rs.add(listReservation.get(i));
				}
			}
			System.out.println();
			System.out.println("[예매 확인/취소]   ▶[예매 내역] | 지난 내역 | 취소 내역 | 유의사항");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			System.out.println("NO	예매번호	영화명					영화관		상영일		예매일");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			for (int i=0; i<rs.size(); i++) {
				System.out.printf("%d\t%s\t%-37s\t%s\t%s\t%s\n"
						, i + 1
						, rs.get(i).getReservationNum()
						, rs.get(i).getTitle()
						, rs.get(i).getTheater().equals("1") ? "강남" 
								: rs.get(i).getTheater().equals("2") ? "강북"
								: rs.get(i).getTheater().equals("3") ? "관악"
								: rs.get(i).getTheater().equals("4") ? "잠실" : "홍대"
						, rs.get(i).getShowDate()
						, rs.get(i).getReservationDate());
			}
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			System.out.println("========================================");
			System.out.println("1. 예매 취소");
			System.out.println("0. 이전 메뉴로 돌아가기");
			System.out.println("========================================");
			System.out.print("선택(번호) : ");
			switch (scan.nextLine()) {
			case "1":
				cancel();
				break;
			case "0":
				loop = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	/**
	 * 예매 취소
	 */
	private void cancel() {
		System.out.print("취소할 영화 번호(NO) : ");
		String input = scan.nextLine();
		Integer j = Integer.parseInt(input) - 1;
		if ( j >=0 && j < rs.size()) {
			if (listReservation.get(index.get(j)).getIsIssued().equals("0")) {
				//상영 1일전인가?
				if (isOneDayAgo(index.get(j))) {
					System.out.printf("예매한 영화 '%s'를 취소하시겠습니까? (y/n)\n", listReservation.get(index.get(j)).getTitle());
					System.out.print("선택(y/n) : ");
					input = scan.nextLine();
					if (input.equals("y")) {

						Calendar c = Calendar.getInstance();
						Reservation r = new Reservation(listReservation.get(index.get(j)).getId(), listReservation.get(index.get(j)).getReservationNum(), listReservation.get(index.get(j)).getTitle(), listReservation.get(index.get(j)).getShowDate(), listReservation.get(index.get(j)).getShowtime(), listReservation.get(index.get(j)).getTheater(), listReservation.get(index.get(j)).getSeat(), listReservation.get(index.get(j)).getPrice(), listReservation.get(index.get(j)).getReservationDate(), listReservation.get(index.get(j)).getIsIssued(), listReservation.get(index.get(j)).getIsExpired(), String.format("%tF", c), listReservation.get(index.get(j)).getOccuredPoint(), listReservation.get(index.get(j)).getUsedPoint());
						listReservation.set(index.get(j), r);
						
						System.out.println("예매가 취소되었습니다.");
					} else {
						System.out.println("'예매 취소하기'가 취소되었습니다.");
						pause();
					}
				} else {
					System.out.println("상영 1일전까지만 예매 취소가 가능합니다.");
				}
			} else {
			System.out.println("발권된 티켓은 현장 방문만 취소 가능합니다.");
			pause();
			}
		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

	/**
	 * 예매한 티켓이 상영하기까지 하루가 안남았으면 false 하루이상 남았으면 true
	 * @param i
	 * @return
	 */
	private boolean isOneDayAgo(int i) {
		Calendar c = Calendar.getInstance();
		int y = Integer.parseInt(listReservation.get(i).getShowDate().substring(0, 4));
		int m = Integer.parseInt(listReservation.get(i).getShowDate().substring(5, 7)) - 1;
		int d = Integer.parseInt(listReservation.get(i).getShowDate().substring(8, 10));
		c.set(y, m, d);
		long OneDayBeforeShowTick = c.getTime().getTime();
		c = Calendar.getInstance();
		long nowTick = c.getTime().getTime();
		if (nowTick < OneDayBeforeShowTick) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 지난 예매 내역 확인
	 */
	private void pastDetails() {
		
		ArrayList<Reservation> rs = new ArrayList<Reservation>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i<listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id) && sortation(i).equals("past")) {
				index.add(i);
				rs.add(listReservation.get(i));
			}
		}
		System.out.println();
		System.out.println("[예매 확인/취소]   예매 내역 | ▶[지난 내역] | 취소 내역 | 유의사항");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("NO	예매번호	영화명					영화관		상영일		예매일");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		for (int i=0; i<rs.size(); i++) {
				System.out.printf("%d\t%s\t%-37s\t%s\t%s\t%s\n"
						, i + 1
						, rs.get(i).getReservationNum()
						, rs.get(i).getTitle()
						, rs.get(i).getTheater().equals("1") ? "강남" 
								: rs.get(i).getTheater().equals("2") ? "강북"
								: rs.get(i).getTheater().equals("3") ? "관악"
								: rs.get(i).getTheater().equals("4") ? "잠실" : "홍대"
						, rs.get(i).getShowDate()
						, rs.get(i).getReservationDate());
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		pause();
	}
	
	/**
	 * 취소 예매 내역 확인
	 */
	private void cancelDetails() {
		
		ArrayList<Reservation> rs = new ArrayList<Reservation>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i<listReservation.size(); i++) {
			if (listReservation.get(i).getId().equals(id) && sortation(i).equals("cancel")) {
				index.add(i);
				rs.add(listReservation.get(i));
			}
		}
		System.out.println();
		System.out.println("[예매 확인/취소]   예매 내역 | 지난 내역 | ▶[취소 내역] | 유의사항");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.println("NO	예매번호	영화명					영화관		상영일		예매일		취소일시");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		for (int i=0; i<rs.size(); i++) {
				System.out.printf("%d\t%s\t%-37s\t%s\t%s\t%s\t%s\n"
						, i + 1
						, rs.get(i).getReservationNum()
						, rs.get(i).getTitle()
						, rs.get(i).getTheater().equals("1") ? "강남" 
								: rs.get(i).getTheater().equals("2") ? "강북"
								: rs.get(i).getTheater().equals("3") ? "관악"
								: rs.get(i).getTheater().equals("4") ? "잠실" : "홍대"
						, rs.get(i).getShowDate()
						, rs.get(i).getReservationDate()
						, rs.get(i).getCancelDate());
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		pause();
	}
	/**
	 * 유의사항 메뉴
	 */
	private void notice() {
		boolean loop = true;
		while (loop) {
			System.out.println();
			System.out.println("[유의사항]    ▶[예매 및 결제] | 티켓 교환방법 | 취소 및 환불규정 | 관람유의사항");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("▷ 관람 등급을 반드시 확인해주시기 바랍니다.");
			System.out.println("▷ 만 4세(48개월) 이상부터는 영화티켓을 반드시 구매하셔야 입장 가능합니다.");
			System.out.println("▷ 홈페이지 예매 현황이 매진인 경우에도 영화관 현장에 잔여석이 남아있는 경우, 현장에서 구매 가능합니다.");
			System.out.println("▷ 할인 카드로 결제 시, 할인 내역은 청구서에서 확인 가능합니다.");
			System.out.println("▷ 예매 변경은 불가능하며, 취소 후 재 예매를 하셔야만 합니다.");
			System.out.println("▷ 상영시간 이후 관람하신 영화의 영수증 출력을 원하실 경우, 관람하신 영화관에서 출력 가능합니다.");
			System.out.println("▷ 취소하신 내역이 나타나지 않거나 궁금하신 사항이 있으시면, 고객센터 > 1:1문의 또는 ARS(1544-0070 09:00~21:00)로 문의해 주시기 바랍니다.");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("========================================");
			System.out.println("1. 티켓 교환방법");
			System.out.println("2. 취소 및 환불규정");
			System.out.println("3. 관람유의사항");
			System.out.println("0. 이전 메뉴로 돌아가기");
			System.out.println("========================================");
			System.out.print("선택(번호) : ");
			switch (scan.nextLine()) {
			case "1":
				noticeTicketExchange();
				break;
			case "2":
				noticeCancelAndRefund();
				break;
			case "3":
				noticeNote();
				break;
			case "0":
				loop = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	/**
	 * 티켓 교환방법 유의사항
	 */
	private void noticeTicketExchange() {
		System.out.println();
		System.out.println("[유의사항]    예매 및 결제 | ▶[티켓 교환방법] | 취소 및 환불규정 | 관람유의사항");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("▷ 무인 발매기(키오스크)에서 발권하실 경우 예매번호를 입력하시면 티켓을 편하게 발권하실 수 있습니다.");
		System.out.println("▷ 매표소에서 발권하실 경우 티켓교환권을 출력하여 매표소에 방문하시면 티켓으로 교환하실 수 있습니다.");
		System.out.println("▷ (티켓교환권 출력이 어려운 경우, 예매번호와 신분증을 지참하시면 매표소에서 티켓을 수령하실 수 있습니다.)");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		pause();
	}
	/**
	 * 티켓 취소 유의사항
	 */
	private void noticeCancelAndRefund() {
		System.out.println();
		System.out.println("[유의사항]    예매 및 결제 | 티켓 교환방법 | ▶[취소 및 환불규정] | 관람유의사항");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("▷ 예매 취소는 상영시간 1일전까지 입니다.");
		System.out.println("▷ 홈페이지 또는 모바일에서 예매한 내역을 취소 할 경우 부분 취소는 불가능합니다.");
		System.out.println("▷ 위탁 예매 사이트 이용 시 취소 및 환불 규정은 해당 사이트 규정을 따릅니다.");
		System.out.println("▷ 공연 관람시 시작 시간 이후에는 입장이 제한 됩니다.");
		System.out.println("▷ **발권된 티켓은 상영시간 전까지 현장 방문 시에만 취소가 가능합니다.**");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		pause();
	}
	/**
	 * 관람 유의사항
	 */
	private void noticeNote() {
		System.out.println();
		System.out.println("[유의사항]    예매 및 결제 | 티켓 교환방법 | 취소 및 환불규정 | ▶[관람유의사항]");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("▷ 지연입장에 의한 관람불편을 최소화하고자 본 영화는 약 10분 후 시작됩니다.");
		System.out.println("▷ 쾌적한 관람 환경을 위해 상영시간 이전에 입장 부탁드립니다.");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		pause();
	}
	
	/** 
	 * 상영일시가 현재시간을 넘었는지 확인
	 * @param i
	 * @return
	 */
	private boolean isPast(int i) {
		Calendar c = Calendar.getInstance();
		int y = Integer.parseInt(listReservation.get(i).getShowDate().substring(0, 4));
		int m = Integer.parseInt(listReservation.get(i).getShowDate().substring(5, 7)) - 1;
		int d = Integer.parseInt(listReservation.get(i).getShowDate().substring(8, 10));
		int hour = Integer.parseInt(listReservation.get(i).getShowtime().substring(0, 2));
		int min = Integer.parseInt(listReservation.get(i).getShowtime().substring(3, 5));
		c.set(y, m, d, hour, min);
		long showTick = c.getTime().getTime();
		c = Calendar.getInstance();
		long nowTick = c.getTime().getTime();
		if (showTick < nowTick) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 지난예매인지 취소예매인지 현재예매인지 구분
	 * @param i
	 * @return
	 */
	private String sortation(int i) {
		if (!(listReservation.get(i).getCancelDate().equals("0"))) {
			return "cancel";
		} else {
			if (isPast(i)) {
				return "past";
			} else {
				return "current";
			}
		}
	}

	/**
	 * 다음 출력문으로 바로 이동하지 않고 출력문을 보여주기위함
	 */
	private void pause() {
		System.out.println("메뉴로 돌아가시려면 엔터를 입력하세요.");
		scan.nextLine();
	}
	
	/**
	 * 예약정보 데이터 읽어옴
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

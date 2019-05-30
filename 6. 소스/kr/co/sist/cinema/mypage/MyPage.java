package kr.co.sist.cinema.mypage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.GeneralMenu;

/**
 * 마이페이지 기능
 * @author sehoon
 *
 */
public class MyPage {
	
	public static final String PATH_MEMBER = "D:\\class\\java\\cinema\\data\\회원.txt";
	private static ArrayList<Member> list;
	private static Scanner scan;
	
	static {
		list = new ArrayList<Member>();
		scan = new Scanner(System.in);
	}
	
	private String id;
	
	/**
	 * 아이디를 초기화하는 생성자
	 * @param id 회원 아이디
	 */
	public MyPage(String id) {
		this.id = id;
	}

	/**
	 * 회원 정보를 출력하는 메소드
	 */
	public void info() {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				System.out.println();
				System.out.println("                                       [회원 정보]");
				System.out.printf("                                       %s님\t\t\t등급| %s\n", list.get(i).getName(), list.get(i).getGrade());
				System.out.printf("                                       멤버십 포인트\t\t\t %,5dp\n", Integer.parseInt(list.get(i).getPoint()));
				System.out.printf("                                       이번달 관람 회수\t\t %,5d번\n", Integer.parseInt(list.get(i).getThisMonthViewCount()));
				break;
			}
		}
	}
	
	/**
	 * mypage에서 선택가능한 메인 메뉴를 보여주는 메소드
	 */
	public void menu() {
		info();
		System.out.println("                                       [메뉴]");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 멤버십 정보");
		System.out.println("                                       2. 예매 확인/취소");
		System.out.println("                                       3. 스토어 구매내역");
		System.out.println("                                       0. 메뉴로 돌아가기");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                       선택(번호) : ");
	}
	
	/**
	 * 선택한 메인 메뉴를 불러오는 메소드
	 */
	public void selectMenu() {
		boolean loop = true;
		while (loop) {
			menu();
			MyMembership myMembership = new MyMembership(id);
//			MyReservation1231 myReservation = new MyReservation1231(id);
			MyReservation myReservation = new MyReservation(id);
			switch(scan.nextLine()) {
			case "1":
				myMembership.loadMember();
				myMembership.selectMenu();
				break;
			case "2":
				myReservation.loadReservation();
				myReservation.selectMenu();
//				myMembership.saveMember();
//				myMembership.saveReservation();
				break;
			case "3":
				MyPerchaseHistory myPurchaseHistory = new MyPerchaseHistory();
				myPurchaseHistory.perchaseLoad();
				myPurchaseHistory.perchaseResult();
				break;
			case "0":
				GeneralMenu generalmenu = new GeneralMenu(this.id);
				generalmenu.generalMenuSelect();
				loop = false;
				break;
			default:
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
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
				Member m = new Member(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10], temp[11], temp[12], temp[13], temp[14], temp[15], temp[16]);
				list.add(m);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

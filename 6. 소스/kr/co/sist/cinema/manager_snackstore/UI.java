package kr.co.sist.cinema.manager_snackstore;
import java.util.Scanner;

/**
 * 매점 관리 메뉴 UI
 * @author sehoon
 *
 */
public class UI {
	
	public final static int ADD = 1;
	public final static int AFTERREMOVE = 5;
	public final static int RESTORE = 6;
	public final static int REMOVE = 2;
	public final static int PRINT = 3;
	public final static int SALES = 4;
	public final static int EXIT = 0;
	
	/**
	 * 매점 관리 UI
	 */
	public void menu() {
		System.out.println("                                   [매점 관리]");
		System.out.println("                                   ==============================");
		System.out.println("                                   1. 판매중인 품목 현황");
		System.out.println("                                   2. 품목 추가");
		System.out.println("                                   3. 품목 삭제");
		System.out.println("                                   0. 이전메뉴로");
		System.out.println("                                   ==============================");
		System.out.print("                                   선택(번호) : ");
		
	}
	
	/**
	 * 매점 품목 추가 UI
	 */
	public void putItemSubMenu() {
		System.out.println("                                   ============품목추가============");
		System.out.println("                                   1. 삭제된 품목 복구");
		System.out.println("                                   2. 직접 품목 추가");
		System.out.println("                                   0. 이전메뉴로");
		System.out.println("                                   ==============================");
		System.out.println("                                   선택(번호) : ");
		
	}
	
	/**
	 * 해당 기능이 무엇인지 나타냄
	 * @param n
	 */
	public void title(int n) {
		if (n == UI.ADD) {
			System.out.println("                                   [품목 추가]");
		} else if (n == UI.REMOVE) {
			System.out.println("                                   [품목 삭제]");
		} else if (n == UI.PRINT) {
			System.out.println("                                   [품목 리스트 확인]"); 
		} else if (n == UI.EXIT) {
			System.out.println("                                   [종료]");
		} else if (n == UI.SALES) {
			System.out.println("                                   [매출 확인]");
		} else if (n == UI.RESTORE) {
			System.out.println("                                   [삭제된 품목 복구]");
		}
		
	}
	
	/**
	 * 어떤 기능을 실행했는지 보여줌
	 * @param n
	 */
	public void pause(int n) {
		
		Scanner scan = new Scanner(System.in);
		
		if (n == UI.ADD) {
			System.out.println("                                   품목 추가 완료\n계속하시려면 엔터를 입력하세요.");
		} else if (n == UI.REMOVE) {
			System.out.println("                                   품목 삭제 완료\n계속하시려면 엔터를 입력하세요.");
		} else if (n == UI.PRINT) {
			System.out.println("                                   계속 하시려면 엔터를 입력하세요.");
		} else if (n == UI.SALES) {
			System.out.println("                                   매점관리로 돌아가시려면 엔터를 입력하세요.");
		}
		scan.nextLine();
	}
	
}
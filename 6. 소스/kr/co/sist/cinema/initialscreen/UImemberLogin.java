package kr.co.sist.cinema.initialscreen;

import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

/**
 * 일반 회원 메뉴 및 첫페이지 메뉴
 * @author sehoon
 *
 */
public class UImemberLogin {

	public final static int SNACKSTORE = 5;
	public final static int MOVIEREPLAY = 6;
	
	/**
	 * 첫 페이지 메뉴를 보여줌
	 * @throws IOException
	 */
	public static void begin()throws IOException{
		
		String asciiArt1 = FigletFont.convertOneLine("D:\\class\\java\\cinema\\data\\smslant.flf", "Welcome");
		String asciiArt2 = FigletFont.convertOneLine("D:\\\\class\\\\java\\\\cinema\\\\data\\\\smslant.flf", "SSANGYONG");
		String asciiArt3 = FigletFont.convertOneLine("D:\\\\class\\\\java\\\\cinema\\\\data\\\\smslant.flf", "CINEMA");
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("         ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		System.out.println("         ▣                                                                         ▣");
		System.out.println("         ▣                                                                         ▣");
		System.out.println("         ▣                 _      __        __                                     ▣\r\n" + 
						   "         ▣                | | /| / / ___   / / ____ ___   __ _  ___                ▣\r\n" + 
				           "         ▣                | |/ |/ / / -_) / / / __// _ \\ /  ' \\/ -_)               ▣\r\n" + 
				           "         ▣                |__/|__/  \\__/ /_/  \\__/ \\___//_/_/_/\\__/                ▣\r\n" + 
				           "         ▣              						           ▣\r\n" + 
				           "         ▣									   ▣\r\n" + 
				           "         ▣       ____   ____   ___    _  __  _____ __  __  ____    _  __  _____    ▣\r\n" + 
				           "         ▣      / __/  / __/  / _ |  / |/ / / ___/ \\ \\/ / / __ \\  / |/ / / ___/    ▣\r\n" + 
				           "         ▣     _\\ \\   _\\ \\   / __ | /    / / (_ /   \\  / / /_/ / /    / / (_ /     ▣\r\n" + 
				           "         ▣    /___/  /___/  /_/ |_|/_/|_/  \\___/    /_/  \\____/ /_/|_/  \\___/      ▣\r\n" + 
				           "         ▣                                                        	           ▣\r\n" + 
				           "         ▣                                                                         ▣\r\n" + 
				           "         ▣               _____   ____   _  __   ____   __  ___   ___               ▣\r\n" + 
				           "         ▣              / ___/  /  _/  / |/ /  / __/  /  |/  /  / _ |              ▣\r\n" + 
				           "         ▣             / /__   _/ /   /    /  / _/   / /|_/ /  / __ |              ▣\r\n" + 
						   "         ▣             \\___/  /___/  /_/|_/  /___/  /_/  /_/  /_/ |_|              ▣");
		System.out.println("         ▣                                                                         ▣");
		System.out.println("         ▣                                                                         ▣");
		System.out.println("         ▣                                                                         ▣");
		System.out.println("         ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                   영화관               ");
		System.out.println("                                   메뉴                 ");
		System.out.println("                                   1. 로그인");
		System.out.println("                                   2. 회원가입");
		System.out.println("                                   3. 아이디 찾기");
		System.out.println("                                   4. 비밀번호 찾기");
		System.out.println("                                   5. 비회원으로 접속");
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		
		
	}
	
	/**
	 * 일반 회원 메뉴를 보여줌
	 */
	public static void menu() {
		System.out.println();
		System.out.println(); 
		System.out.println("                                     [SSANGYONG_CINEMA]");
		System.out.println();
		System.out.println();
	
		System.out.println("                                           [MENU]");
		System.out.println();
		System.out.println();
		
		
		System.out.println("               ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             1. 예매하기        2. 마이페이지                ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             3. 상영영화정보    4. 개봉예정영화정보          ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             5. 매점            6. 영화다시보기              ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             7. 포스트 작성하기                              ▣");
		System.out.println("               ▣                                                             ▣");		
		System.out.println("               ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 예매하기");
		System.out.println("                                       2. 마이페이지");
		System.out.println("                                       3. 상영영화정보");
		System.out.println("                                       4. 개봉예정영화정보");
		System.out.println("                                       5. 매점");
		System.out.println("                                       6. 영화다시보기");
		System.out.println("                                       7. 포스트 작성하기");
		System.out.println("                                       0. 로그아웃");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		
	}
	
	public void end() {
		System.out.println("                                   종료.");
	}
	
	
	public void title(int n) {
		
		if(n == UImemberLogin.SNACKSTORE) {
			System.out.println("                                   [메모 쓰기]");
		} else if (n == UImemberLogin.SNACKSTORE) {
			System.out.println("                                   [메모 읽기]");
		}
	}

	public void pause(int n) {
		
		Scanner scan = new Scanner(System.in);
		
		if(n == UImemberLogin.SNACKSTORE) {
			System.out.println("                                   메모 쓰기 완료.\n계속하시려면 엔터를 입력하세요.");
		} 
		else if (n == UImemberLogin.SNACKSTORE) {
			System.out.println("                                   메모 읽기 완료.\n계속하시려면 엔터를 입력하세요.");
			
		}
		
		scan.nextLine();
	}


}

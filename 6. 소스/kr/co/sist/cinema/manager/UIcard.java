package kr.co.sist.cinema.manager;

import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

/**
 * 카드 제휴 메뉴 목록
 * @author sehoon
 *
 */
public class UIcard {

	public final static int SNACKSTORE = 5;
	public final static int MOVIEREPLAY = 6;
	
	
	public static void begin()throws IOException{
		
		String asciiArt1 = FigletFont.convertOneLine("D:\\\\class\\\\java\\\\cinema\\\\data\\\\smslant.flf", "Welcome");
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
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                   1. 카드제휴 정보 추가");
		System.out.println("                                   2. 카드제휴 정보 수정");
		System.out.println("                                   3. 카드제휴 정보 보기");
		System.out.println("                                   0. 관리자 메뉴로 돌아가기");
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                   선택(번호) : ");
	
		
		
	}
	
	/**
	 * 지점 선택 메뉴
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
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             1. 강남        2. 강북              	     ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             3. 관악        4. 잠실                 	     ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             5. 홍대              			     ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");	
		System.out.println("               ▣                                                             ▣");		
		System.out.println("               ▣                                                             ▣");		
		System.out.println("               ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 강남");
		System.out.println("                                       2. 강북");
		System.out.println("                                       3. 관악");
		System.out.println("                                       4. 잠실");
		System.out.println("                                       5. 홍대");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                       지점 선택(번호) : ");
		
	}
	


}

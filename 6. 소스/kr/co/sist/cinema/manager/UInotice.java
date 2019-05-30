package kr.co.sist.cinema.manager;

import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

/**
 * 공지사항 또는 FAQ 관리 메뉴
 * @author sehoon
 *
 */
public class UInotice {

	public final static int SNACKSTORE = 5;
	public final static int MOVIEREPLAY = 6;
	
	/**
	 * 공지사항 또는 FAQ 관리 선택 메뉴
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
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                   1. 공지사항 관리");
		System.out.println("                                   2. 문의사항 관리");
		System.out.println("                                   0. 관리자 메뉴로 돌아가기");
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                   선택(번호) : ");
	
		
		
	}
	
	/**
	 * 공지사항 또는 FAQ 기능 선택메뉴
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
		System.out.println("               ▣             1. 글쓰기        2. 삭제              	     ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             3. 수정        4. 글 목록 보기                  ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣             0. 이전메뉴로                                   ▣");
		System.out.println("               ▣                                                             ▣");
		System.out.println("               ▣                                                             ▣");	
		System.out.println("               ▣                                                             ▣");		
		System.out.println("               ▣                                                             ▣");		
		System.out.println("               ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 글쓰기");
		System.out.println("                                       2. 삭제");
		System.out.println("                                       3. 수정");
		System.out.println("                                       4. 글 목록 보기");
		System.out.println("                                       0. 이전메뉴로");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                       메뉴 선택(번호) : ");
		
	}
	
}
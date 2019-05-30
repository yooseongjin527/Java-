package kr.co.sist.cinema.initialscreen;

import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

/**
 * 관리자 메뉴창
 * @author sehoon
 *
 */
public class UImanagerLogin {

	public final static int SNACKSTORE = 5;
	public final static int MOVIEREPLAY = 6;
	
	/**
	 * 관리자 메뉴를 보여줌
	 * @throws IOException
	 */
	public static void begin()throws IOException{
		
		String asciiArt1 = FigletFont.convertOneLine("D:\\class\\java\\cinema\\data\\smslant.flf", "Welcome");
		String asciiArt2 = FigletFont.convertOneLine("D:\\class\\java\\cinema\\data\\smslant.flf", "SSANGYONG");
		String asciiArt3 = FigletFont.convertOneLine("D:\\class\\java\\cinema\\data\\smslant.flf", "CINEMA");
		
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
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                   영화관               ");
		System.out.println("                                   관리자 메뉴          ");
		System.out.println("                                   1. 회원관리");
		System.out.println("                                   2. 이벤트/제휴 관리");
		System.out.println("                                   3. 공지사항 관리");
		System.out.println("                                   4. 영화정보 관리");
		System.out.println("                                   5. 매점 관리");
		System.out.println("                                   6. 포스트 열람/삭제");
		System.out.println("                                   0. 로그아웃");
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                   메뉴 선택(번호) : ");
		
		
	}
}
package kr.co.sist.cinema.replaycinema;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.GeneralMenu;
import kr.co.sist.cinema.initialscreen.InitialScreen;
import kr.co.sist.cinema.initialscreen.UImemberLogin;

public class ReplayCinemaMain extends FileRead{

	/**
	 * 영화 다시보기 메뉴 및 선택
	 */
	public void ReplayMenu() {
		
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       1. 무료 영화 보기");
		System.out.println("                                       2. 유료 영화 보기");
		System.out.println("                                       0. 이전 메뉴로 돌아가기");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                       선택(번호) : ");
		
		int num = scan.nextInt();
		
		while(num < 0 || num > 2) {
			System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
			System.out.print("                                       선택(번호) : ");
			num = scan.nextInt();
		}
		
		if(num == 1) {
			FreeReplay freeReplay = new FreeReplay();
			freeReplay.freeReplayCinemaList();
			pause();
		}else if(num == 2) {
			ChargedReplay chargedReplay = new ChargedReplay();
			chargedReplay.chargedReplayCinemaList();
		}else{
			GeneralMenu generalMenu = new GeneralMenu(InitialScreen.getId());
			generalMenu.generalMenuSelect();
			
		}
		
	}
	
}

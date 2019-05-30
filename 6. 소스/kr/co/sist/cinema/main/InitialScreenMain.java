package kr.co.sist.cinema.main;

import kr.co.sist.cinema.initialscreen.InitialScreen;
import kr.co.sist.cinema.manager.MemberManage;

/**
 * 영화관 실행 클래스
 * @author sehoon
 *
 */
public class InitialScreenMain {

	public static void main(String[] args) {
		
		MemberManage memberManage = new MemberManage();
		memberManage.numberViews();
		
		InitialScreen start = new InitialScreen();
		start.initial();
		
	}
}

package kr.co.sist.cinema.initialscreen;

import java.io.IOException;
import java.util.Scanner;

import kr.co.sist.cinema.manager.CardManage;
import kr.co.sist.cinema.manager.MemberManage;
import kr.co.sist.cinema.manager.NoticeBoard;
import kr.co.sist.cinema.manager_movieinfo.MovieInfoManager;
import kr.co.sist.cinema.manager_snackstore.SnackManager;
import kr.co.sist.cinema.manager_snackstore.UI;
import kr.co.sist.cinema.post.manager.PostManager;

/**
 * 관리자 메뉴
 * @author sehoon
 *
 */
public class ManagerMenu {

	/**
	 * 메뉴에 해당하는 번호를 입력받아 기능 실행
	 * @throws IOException
	 */
	public void ManagerInitialMenu() throws IOException {
		
		Scanner scan = new Scanner(System.in);
		
		try {
			UImanagerLogin.begin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int menuNum = scan.nextInt();
		
		while(menuNum < 0 || menuNum > 6) {
			System.out.println("                                   잘못 입력하셨습니다.");
			System.out.print("                                   메뉴를 입력하세요(번호)");
			menuNum = scan.nextInt();
		}
		
		if(menuNum == 1) {
			MemberManage memberManage = new MemberManage();
			memberManage.MemberManageStart();
		}
		else if(menuNum == 2){
			CardManage cardManage = new CardManage();
			cardManage.CardManageStart();
		}
		else if(menuNum == 3){
			NoticeBoard noticeBoard = new NoticeBoard();
			noticeBoard.NoticeBoardstart();
		}
		else if(menuNum == 4){
			MovieInfoManager movieInfoManager = new MovieInfoManager();
			movieInfoManager.menu();
			
		}
		else if(menuNum == 5){
			System.out.println();
			scan.nextLine();
			UI ui = new UI();
			
			SnackManager store = new SnackManager();
			
			boolean loop = true;
			
			store.load();
			while(loop) {
				
				ui.menu();
				
				switch(scan.nextLine()) {
				case "1":
					store.printItemList();
					break;
				case "2":
					store.putItem();
					break;
				case "3":
					store.pullItem();
					break;
				default:
					ManagerInitialMenu();
					loop = false;
					break;
				}
			}
			store.save();
		}
		else if(menuNum == 6){
			PostManager postManager = new PostManager();
			postManager.PostManagerStart();
		}
		else {
			InitialScreen start = new InitialScreen();
			start.initial();
		}
		
		
	}
	
}

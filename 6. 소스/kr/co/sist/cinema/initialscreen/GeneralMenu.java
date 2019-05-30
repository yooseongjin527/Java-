package kr.co.sist.cinema.initialscreen;

import java.util.Scanner;

import kr.co.sist.cinema.movieinfo.FutureMovieInfoList;
import kr.co.sist.cinema.movieinfo.ShowingMovieInfoList;
import kr.co.sist.cinema.mypage.MyMembership;
import kr.co.sist.cinema.mypage.MyPage;
import kr.co.sist.cinema.post.member.PostMain;
import kr.co.sist.cinema.replaycinema.ReplayCinemaMain;
import kr.co.sist.cinema.reservation.AreaSelect;
import kr.co.sist.cinema.snackstore.SnackStore;
import kr.co.sist.cinema.snackstore.UI;

/**
 * 일반회원 메뉴창
 * @author sehoon
 *
 */
public class GeneralMenu {

	public static final String PATH_MEMBER = "D:\\class\\java\\cinema\\data\\member.dat";
	Scanner scan;
	private int generalMenuSelectInput;
	private String id; //회원인지 비회원인지 구분하기 위해 받아오는 값, 회원이면 아이디 비회원이면 빈칸
	
	/**
	 * 아이디를 입력받는 생성자
	 * @param id
	 */
	public GeneralMenu(String id) {
		this.id = id;
		scan = new Scanner(System.in);
	}
	
	/**
	 * 메뉴 선택 번호를 받아 실행
	 */
	public void generalMenuSelect() {
		
		UImemberLogin.menu();

		System.out.print("                                       메뉴 선택(번호) : ");
		generalMenuSelectInput = scan.nextInt();
		
		while(generalMenuSelectInput < 0 || generalMenuSelectInput > 7) {
			
			UImemberLogin.menu();
			
			System.out.print("                                   메뉴 재선택(번호)");
			generalMenuSelectInput = scan.nextInt();
			
		}
		
		if( generalMenuSelectInput == 1 ) {
			AreaSelect a = new AreaSelect();
			a.areaSelect();
			a.movieSelectScreen();
		}
		else if( generalMenuSelectInput == 2 ) {
			if(InitialScreen.getId().equals("")) {
				System.out.println("                                   로그인을 해주세요.");
				InitialScreen start = new InitialScreen();
				start.initial();
			}
//			MyPage myPage = new MyPage("iszlgz319");
//			MyMembership myMembership = new MyMembership("iszlgz319");
			MyPage myPage = new MyPage(InitialScreen.getId());
			MyMembership myMembership = new MyMembership(InitialScreen.getId());

			myPage.loadMember();//ArrayList<Member> list에 회원정보 담기
			myMembership.loadReservation();//ArrayList<Reservation> list에 예약정보 담기
			
			myPage.selectMenu();
			
//			myMembership.saveMember();
//			myMembership.saveReservation();
			
		}
		
		else if ( generalMenuSelectInput == 3 ) {
			ShowingMovieInfoList showingMovieInfoList = new ShowingMovieInfoList();
			showingMovieInfoList.load();
			showingMovieInfoList.movieList();
		}
		
		else if ( generalMenuSelectInput == 4 ) {
			FutureMovieInfoList futureMovieInfoList = new FutureMovieInfoList();
			futureMovieInfoList.load();
			futureMovieInfoList.movieList();
		}
		
		else if( generalMenuSelectInput == 5) {
			AreaSelect a = new AreaSelect();
			a.areaSelect();
			UI ui = new UI();
			ui.load();
			ui.Storelist();
			
			SnackStore s = new SnackStore(a.getAreaNum());
			s.order();
			generalMenuSelect();
		}
		
		else if(generalMenuSelectInput == 6 ) {
			ReplayCinemaMain replayCinemaMain = new ReplayCinemaMain();
			replayCinemaMain.ReplayMenu();
		}else if( generalMenuSelectInput == 7 ) {
			
			if(InitialScreen.getId().equals("")) {
				System.out.println("                                   로그인을 해주세요.");
				InitialScreen start = new InitialScreen();
				start.initial();
			}
			
			PostMain postStart = new PostMain();
			postStart.PostWrite();
			
		}else if(generalMenuSelectInput == 0 ) {
			InitialScreen start = new InitialScreen();
			start.initial();
		}
		
	}
	
}

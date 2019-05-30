package kr.co.sist.cinema.reservation;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.GeneralMenu;
import kr.co.sist.cinema.initialscreen.InitialScreen;
/**
 * 지역선택
 * @author sehoon
 *
 */
public class AreaSelect extends FileRead {

	private String stop;
	private int areaNum;
	private int movieTitleNum;
	ArrayList<String> movieTitle;

	/**
	 * 기본생성자
	 */
	public AreaSelect() {
		stop = "";
		areaNum = 0;
		movieTitle = new ArrayList<String>();
	}
	
	/**
	 * 선택한 지점번호 값 읽기
	 * @return
	 */
	public int getAreaNum() {
		return areaNum;
	}
	/**
	 * 지점번호 선택
	 */
	public void areaSelect() {
		
		UIreservation.menu();

		Boolean flag = true;
		while (flag) {
			this.areaNum = scan.nextInt();
			if (this.areaNum >= 0 && this.areaNum <= 5) {
				flag = false;
				continue;
			}
			System.out.println("                                   값을 다시 입력해주세요(1~5사이 값) : ");
		}
		
		if(areaNum == 0 ) {
			GeneralMenu generalMenu = new GeneralMenu(InitialScreen.getId());
			generalMenu.generalMenuSelect();
			
		}

		// 화면을 보여주기위해 잠시 멈춤
		this.stop = scan.nextLine();
		pause();
	}

	/**
	 * 선택한 지점에 대한 영화목록
	 */
	public void movieSelectScreen() {

		initialReader("movieinfo.dat");

		for (int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if (division[1].equals("1")) {
				movieTitle.add(division[2]);
			}
		}

		System.out.println(
				" ===================================================================================================================================================");
		System.out.println(
				"|                                                                                                                                                   |");
		System.out.println(
				"|                                                                                                                                                   |");
		System.out.print("|      ");
		for (int i = 1; i < movieTitle.size(); i++) {
			System.out.print("\t" + i + "." + movieTitle.get(i-1));
			if (i % 3 == 0) {
				System.out.println();
				System.out.print("|     ");
			}
		}
		System.out.println();
		System.out.println(
				"|                                                                                                                                                   |");
		System.out.println(
				"|                                                                                                                                                   |");
		System.out.println(
				" ===================================================================================================================================================");
		System.out.println();
		System.out.println();
		System.out.print("                                   영화제목을 선택해주세요(1~" + (movieTitle.size()-1) + ") : ");

		Boolean flag = true;
		//해당 상영 영화 번호가 아니면 다시 돌아가게끔 함
		while (flag) {
			this.movieTitleNum = scan.nextInt();
			// 1번부터 영화 수만큼
			if (movieTitleNum >= 1 && movieTitleNum <= movieTitle.size()) {
				flag = false;
				continue;
			}
			System.out.print("                                   값을 다시 입력해주세요(1~)" + (movieTitle.size()-1) + " : ");
		}

		// 화면을 보여주기위해 잠시 멈춤
		scan.nextLine();
		pause();
		
		String movieUnique = "";
		int movieTime = 0;
		
		for (int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if (division[2].equals( movieTitle.get(movieTitleNum-1) )) {
				movieUnique = division[0];
				movieTime = Integer.parseInt(division[4].substring(0,division[4].indexOf("분")));
			}
		}
		// 상영시간표로 선택한 영화명과 지역번호를 넘겨준다
		CinemaSchedule schedule = new CinemaSchedule(areaNum+"", movieUnique, movieTime);
		schedule.time();
	}
}
	
	

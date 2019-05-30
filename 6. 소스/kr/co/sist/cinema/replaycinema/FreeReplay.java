package kr.co.sist.cinema.replaycinema;

import java.util.ArrayList;

import kr.co.sist.cinema.initialscreen.FileRead;
/**
 * 무료 영화 다시보기
 * @author sehoon
 *
 */
public class FreeReplay extends FileRead {

	ArrayList<String> freeList;
	/**
	 * 기본 생성자
	 */
	public FreeReplay() {
		freeList = new ArrayList<String>();
	}
	/**
	 * 무료 영화 목록
	 */
	public void freeReplayCinemaList() {
		
		initialReader("movieinfo.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if(division[1].equals("4")) {
				freeList.add(list.get(i));
			}
		}
		
		System.out.println("                                       [영화제목(등급)]         [개봉일]     [상영시간] [감독]          [배우]");
		for(int i = 0; i < freeList.size(); i++) {
			String[] division = freeList.get(i).split("\\■");
			System.out.printf("                                       %d.%s\t%s\t%s\t%s\t%s\t\n"
								, (i+1),division[2], division[3], division[4], division[5], division[6]);
							
								
		}
		pause();
		freeView();
	}
	/**
	 * 선택한 무료영화 다시보기
	 */
	public void freeView() {
		System.out.println();
		System.out.println("                                       영화를 보시려면 해당 번호를 골라주세요.");
		System.out.print("                                       영화 번호를 골라 주세요: ");
		int cinemaNum = scan.nextInt();
		
		while(cinemaNum < 1 || cinemaNum > freeList.size()) {
			System.out.println("                                       없는 번호 입니다.");
			System.out.print("                                       영화 번호를 다시 골라 주세요: ");
			cinemaNum = scan.nextInt();
		}
		scan.nextLine(); // 엔터없애기
		
		String[] division = freeList.get(cinemaNum-1).split("\\■");
		System.out.println();
		System.out.println("                                       "+division[2] + "상영 시작합니다.");
		
		System.out.print("                                       종료 하시려면 y를 누르세요: ");
		String exit = scan.nextLine();
		
		if(exit.equalsIgnoreCase("y")) {
			ReplayCinemaMain replayMenu = new ReplayCinemaMain();
			System.out.println();
			replayMenu.ReplayMenu();
		}else {
			System.out.println("                                       영화가 종료되었습니다. 화면으로 돌아갑니다.");
			System.out.println();
			ReplayCinemaMain replayMenu = new ReplayCinemaMain();
			replayMenu.ReplayMenu();
		}
		
	}
	
}

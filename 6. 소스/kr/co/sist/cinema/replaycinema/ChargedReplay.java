package kr.co.sist.cinema.replaycinema;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.InitialScreen;
/**
 * 유료 영화 다시보기
 * @author sehoon
 *
 */
public class ChargedReplay extends FileRead {

	ArrayList<String> chargedList;
	
	/**
	 * 기본 생성자
	 */
	public ChargedReplay() {
		chargedList = new ArrayList<String>();
	}
	/**
	 * 유료 영화보기 목록
	 */
	public void chargedReplayCinemaList() {
		
		initialReader("movieinfo.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if(division[1].equals("3")) {
				chargedList.add(list.get(i));
			}
		}
		
		System.out.println("                                       [영화제목(등급)] [개봉일]     [상영시간] [감독]  [배우]");
		for(int i = 0; i < chargedList.size(); i++) {
			String[] division = chargedList.get(i).split("\\■");
			System.out.printf("                                       %d.%s\t%s\t%s\t%s\t%s\t\n"
								, (i+1), division[2], division[3], division[4], division[5], division[6]
								);
		}
		
		System.out.println();
		pause();
		if(InitialScreen.getId().equals("")) {
			System.out.println("                                   로그인을 해주세요.");
			InitialScreen start = new InitialScreen();
			start.initial();
		}
		
		replayPayment();
		
	}
	/**
	 * 유료 영화보기 결제
	 */
	public void replayPayment() {
		
		ArrayList<Integer> date = new ArrayList<Integer>();
		ArrayList<Integer> datePrice = new ArrayList<Integer>();
		
		System.out.println();
		System.out.println("                                       영화를 보시려면 해당 번호를 골라주세요.");
		System.out.print("                                       영화 번호를 골라 주세요: ");
		int cinemaNum = scan.nextInt();
		
		while(cinemaNum < 1 || cinemaNum > chargedList.size()) {
			System.out.println("                                       없는 번호 입니다.");
			System.out.print("                                       영화 번호를 다시 골라 주세요: ");
			cinemaNum = scan.nextInt();
		}
		scan.nextLine(); // 엔터없애기
		
		//선택한 유료영화 데이터 나누기
		String[] division = chargedList.get(cinemaNum-1).split("\\■");

		initialReader("유료영화가격데이터.txt");
		
		for(int i = 0; i < list.size(); i++) {
			String[] chargedDate = list.get(i).split("\\■");
			date.add(Integer.parseInt(chargedDate[0]));
			datePrice.add(Integer.parseInt(chargedDate[1]));
		}
		
		int price = 0;
		
		//date 와 datePrice 는 같은 데이터에서 뽑아오기 때문에 사이즈가 같다
		for(int i = date.size()-1; i >= 0 ; i--) {
			if( dateCalculate(division[3]) <= date.get(i) ) {
				price = datePrice.get(i);
			}
		}
		System.out.println();
		System.out.printf("                                       %s는 %,d원 입니다.\n",division[2], price); //가격입력
		System.out.print("                                       영화 결제하시겠습니까?(y/n): ");
		
		String check = scan.nextLine();
		
		while( !(check.equalsIgnoreCase("y")) && !(check.equalsIgnoreCase("n")) ) {
			System.out.println();
			System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
			System.out.printf("                                       %s는 %,d원 입니다.",division[2], price); //가격입력
			System.out.print("                                       영화 결제하시겠습니까?(y/n): ");
			check = scan.nextLine();
		}
		
		if(check.equalsIgnoreCase("y")) {
			//결제 데이터 넘기기, 회원아이디 넘겨야됨
			try(
				BufferedWriter writer = new BufferedWriter(new FileWriter(PATH+"유료영화결제정보.dat",true));	
				){
				writer.append( InitialScreen.getId() + "■" + division[2] + "■\r\n");
			}
			catch(FileNotFoundException e) {
				System.out.println("                                       유료영화결제정보.dat 경로 없음");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			System.out.println();
			System.out.println("                                       "+division[2] + "영화 시청중입니다.");
			System.out.print("                                       종료 하시려면 y를 누르세요: ");
		
			check = scan.nextLine();
			System.out.println();
			
			while( !(check.equalsIgnoreCase("y")) && !(check.equalsIgnoreCase("n")) ) {
				System.out.println("                                       잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.print("                                       "+division[2] + "영화 종료하시겠습니까?(y/n): ");
				check = scan.nextLine();
			}
			
			if(check.equalsIgnoreCase("y")) {
				ReplayCinemaMain replayMenu = new ReplayCinemaMain();
				replayMenu.ReplayMenu();
			}else {
				System.out.println("                                       영화가 종료되었습니다. 화면으로 돌아갑니다.");
				ReplayCinemaMain replayMenu = new ReplayCinemaMain();
				replayMenu.ReplayMenu();
			}
			
		}else {
			ReplayCinemaMain replayMenu = new ReplayCinemaMain();
			replayMenu.ReplayMenu();
		}
		
	}
	/**
	 * 영화 기간 계산
	 * @param date 영화 개봉일을 받아옴
	 * @return
	 */
	public int dateCalculate(String date) {
		
		Calendar calendar = Calendar.getInstance();
		Calendar today =  Calendar.getInstance();
		
		String[] dateDivision = date.split("-");
		
		calendar.set(Integer.parseInt(dateDivision[0])
				,Integer.parseInt(dateDivision[1]) -1
				,Integer.parseInt(dateDivision[2]));
		
		int dateDifference = (int)(( (today.getTime().getTime()) - (calendar.getTime().getTime()) ) /1000 /60 / 60/24 );
		
		return dateDifference;
		
	}
	
}

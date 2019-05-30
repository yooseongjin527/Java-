package kr.co.sist.cinema.reservation;

import java.util.ArrayList;
import java.util.Calendar;

import kr.co.sist.cinema.initialscreen.FileRead;
/**
 * 좌석 선택
 * @author sehoon
 *
 */
public class Seat extends FileRead {

	private String[] seatInfo;
	private String seatNumber;
	private ArrayList<String> seatNumberList;
	/**
	 * 기본 생성자
	 */
	public Seat() {
		seatInfo = new String[50];
		seatNumber = "";
		seatNumberList = new ArrayList<String>();
	}
	
	/**
	 * 예매된 좌석 정보 읽기
	 * @return
	 */
	public ArrayList<String> getSeatNumberList() {
		return seatNumberList;
	}

	/**
	 * 좌석 정보
	 * @param areaNum 지점 번호
	 * @param movieUnique 영화 고유번호
	 * @param hour 영화 상영시작 시간
	 * @param minute 영화 상영시작 분
	 * @param cal 영화 상영날짜
	 * @param numberPeople 티켓 구입 수
	 */
	public void SeatInfo(String areaNum, String movieUnique, int hour, int minute, Calendar cal, int numberPeople) {
		
		initialReader("좌석.txt");
		
		//seatInfo (좌석위치 정보)를 나타낼 인덱스
		int k = 0;
		// 배열에 좌석들을 입력, 있는 좌석은 다른 문자로 바꾸기 위해서
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			
			for(int j = 0; j < division.length; j++) {
				seatInfo[k] = division[j];
				k++;
			}
		}
		
		initialReader("지정된좌석번호.txt");
		String timeConversion = String.format("%02d", hour) + " : " + String.format("%02d",minute);
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			
			if(division[0].equals(areaNum)
				&& division[1].equals(movieUnique)
				&& division[2].equals( timeConversion)
				&& division[4].equals(String.format("%tF", cal)) ) 
			{
				for(int j = 0; j < seatInfo.length; j++) {
					if(division[3].equals(seatInfo[j]+"")) {
						seatInfo[j] = "▣";
					}
				}
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println("               ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		System.out.println("               ▣                                                                           ▣");
		System.out.println("               ▣                                                                           ▣");
		System.out.println("               ▣                                   SCREEN                                  ▣");
		System.out.println("               ▣                                                                           ▣");
		System.out.println("               ▣                                                                           ▣");
		System.out.println("               ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣ ▣");
		for(int i = 1; i <= seatInfo.length; i++) {
			if(i % 10 == 1)System.out.print("               ");
			System.out.print(seatInfo[i-1]+"\t");
			if( i % 10 == 0) {
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		for(int j = 0; j < numberPeople; j++) {
			System.out.print("                                   좌석을 입력해주세요.");
			seatNumber = scan.nextLine();
			boolean flag = true;
			
			while(flag) {
				for(int i = 0; i < seatInfo.length; i++) {
					if(seatNumber.equals(seatInfo[i]) ) {
						flag = false;
					}
				}
				if(flag) {
					System.out.print("                                   예약된 좌석입니다. 다시 입력해주세요 : ");
					seatNumber = scan.nextLine();
				}
			}
			seatNumberList.add(seatNumber);
		}
		
	}
	
}

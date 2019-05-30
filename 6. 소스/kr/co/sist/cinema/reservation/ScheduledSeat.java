package kr.co.sist.cinema.reservation;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.GeneralMenu;
import kr.co.sist.cinema.initialscreen.InitialScreen;
/**
 * 예매된 좌석
 * @author sehoon
 *
 */
public class ScheduledSeat extends FileRead {

	private String areaNum;
	private String movieUnique;
	private ArrayList<String> seatNumberList;
	private int hour;
	private int minute;
	private Calendar cal;

	/**
	 * 생성자
	 * @param areaNum 지점번호
	 * @param movieUnique 영화 고유번호
	 * @param hour 영화시작 시간
	 * @param minute 영화 시작 분
	 * @param seatNumberList 지정된 좌석 정보
	 * @param cal 영화 상영 날짜
	 */
	public ScheduledSeat(String areaNum, String movieUnique, int hour, int minute, ArrayList seatNumberList, Calendar cal) {
		
		this.areaNum = areaNum;
		this.movieUnique = movieUnique;
		this.seatNumberList = seatNumberList;
		this.hour = hour;
		this.minute = minute;
		this.cal = cal;
		
	}
	
	/**
	 * 예매한 좌석을 데이터에 입력
	 */
	public void scheduledSeatUpdate() {
		
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\class\\java\\cinema\\data\\지정된좌석번호.txt",true));
			)
		{
			for(int i = 0; i < seatNumberList.size(); i++) {
				writer.append(this.areaNum + "■" + this.movieUnique + "■" + String.format("%02d", this.hour) + " : " + String.format("%02d", this.minute)
						 		+ "■" + this.seatNumberList.get(i)+ "■" + String.format("%tF", cal) + "■\r\n");
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 예매한 티켓 정보를 데이터에 입력
	 * @param calendar 상영날짜
	 * @param occurrencePoint 생성된 포인트
	 * @param pointUse 사용 포인트
	 * @param price 가격
	 */
	public void ticketInformation(Calendar calendar, int occurrencePoint, int pointUse, int price) {
		
		Calendar today = Calendar.getInstance();
		String movieTitle = "";
		String year = calendar.get(Calendar.YEAR) + "";
		year = year.substring(2, year.length());
		int month =  calendar.get(Calendar.MONTH)+1;
		int date =  calendar.get(Calendar.DATE);
		
		initialReader("movieinfo.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if(division[0].equals(this.movieUnique) ) {
				movieTitle = division[2];
			}
		}
		
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH+"예매.txt",true));
			)
		{
			for(int i = 0; i < seatNumberList.size(); i++) {
				writer.append(InitialScreen.getId() + "■" 
								+ this.movieUnique + "-" + this.areaNum +"-" 
								+ year + String.format("%02d", month) + date + "-" + seatNumberList.get(i) + "■" 
								+ movieTitle + "■" + String.format("%tF", calendar) + "■"
								+ String.format("%02d", this.hour) + ":" + String.format("%02d", this.minute)
								+ "■" + this.areaNum + "■" + this.seatNumberList.get(i) + "■" + price
								+ "■" + String.format("%tF", today) + "■"
								+ "0" + "■" + "0" + "■" + "0" + "■" + occurrencePoint + "■" + pointUse + "■\r\n"
								);
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("예매.txt 경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		pause();
		GeneralMenu generalmenu = new GeneralMenu(InitialScreen.getId());
		generalmenu.generalMenuSelect();
		
		
	}
	
}

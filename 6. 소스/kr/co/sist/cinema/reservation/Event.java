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
 * 영화 할인
 * @author sehoon
 *
 */
public class Event extends FileRead{

	private String cardType;
	private int price;
	private int pointUse;
	private int occurrencePoint;
	/**
	 * 생성자
	 * @param cardType 카드종류
	 * @param numberPeople 예매 인원수
	 * @param pointUse 사용할 포인트
	 */
	public Event(String cardType, int numberPeople, int pointUse) {
		this.cardType = cardType;
		this.price = 10000 * numberPeople;
		this.pointUse = pointUse;
		this.occurrencePoint = 0;
	}
	/**
	 * 사용할 포인트 값 읽어옴
	 * @return
	 */
	public int getPointUse() {
		return pointUse;
	}

	/**
	 * 결제 후 생길 포인트 값 읽어옴
	 * @return
	 */
	public int getOccurrencePoint() {
		return occurrencePoint;
	}

	/**
	 * 가격 정보 읽어옴
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 카드 이벤트
	 * @param areaNum
	 */
	public void cardEvent(String areaNum) {
		
		initialReader("card.dat");
		String conversion = "";
		
		for(int i = 0; i < list.size(); i++) {
			
			String[] division = list.get(i).split("\\■");// + "(" + division[2] + "할인)"
			conversion = division[1] + "(" + division[2] + "할인)";
			if(conversion.equals(this.cardType) && areaNum.equals(division[0]) ) {
				this.price = this.price - (this.price / 100 * Integer.parseInt(division[2].substring(0, division[2].indexOf("%")))) - pointUse;
			}
			
		}
	}
	/**
	 * 이벤트 적용
	 * @param areaNum
	 */
	public void applyEvent(String areaNum) {
		
		
		Calendar calendar = Calendar.getInstance();
		
		if( CinemaSchedule.getTimeSelect() == 1 ) {
			this.price = (this.price / 100 * 60)  - pointUse;
		}
		else if( calendar.get(Calendar.DATE) == cultureDayCalculation() ) {
			this.price = (this.price /100 * 65)  - pointUse;
		}
		//vip인 경우, 값을 받아와서 비교
//		else if() {
//		}
		else {
			cardEvent(areaNum);
		}
		System.out.println("                                   " + this.price + "원 결제 되었습니다.");
		this.occurrencePoint = this.price / 10;
		pointModify();
		
	}
	/**
	 * 해당 회원데이터 포인트 수정
	 */
	public void pointModify() {
		
		ArrayList<String> modify = new ArrayList<String>();
		
		initialReader("회원.txt");
		
		for(int i = 0; i < list.size(); i++) {
			
			String[] division = list.get(i).split("\\■");
			
			if(division[0].equals(InitialScreen.getId()) ) {
				modify.add(division[0] + "■"
							+ division[1] + "■"
							+ division[2] + "■"
							+ division[3] + "■"
							+ division[4] + "■"
							+ division[5] + "■"
							+ division[6] + "■"
							+ division[7] + "■"
							+ division[8] + "■"
							+ (Integer.parseInt(division[9]) - this.pointUse + this.occurrencePoint ) + "■"
							+ division[10] + "■"
							+ division[11] + "■"
							+ division[12] + "■"
							+ division[13] + "■"
							+ division[14] + "■"
							+ division[15] + "■"
							+ division[16] + "■"
							);
			}else {
				modify.add(list.get(i));
			}
			
		}
		
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "회원.txt"));	
			)
		{
			
			for( int i = 0; i < modify.size(); i++) {
				writer.write(modify.get(i) + "\r\n");
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("회원.txt 경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 해당 달의 마지막 수요일 구하기
	 * @return
	 */
	public int cultureDayCalculation() {
			
			Calendar calendarComparison = Calendar.getInstance();
			
			int lastDay = calendarComparison.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			calendarComparison.set(calendarComparison.get(Calendar.YEAR), calendarComparison.get(Calendar.MONTH),lastDay);
			
			int dayNumber = calendarComparison.get(Calendar.DAY_OF_WEEK);
			
			if(dayNumber == 4) return lastDay;
			
			switch(dayNumber) {
				case 1 : 
					lastDay -= 4;
					break;
				case 2 : 
					lastDay -= 5;
					break;
				case 3 :
					lastDay -= 6;
					break;
				case 5 :
					lastDay -= 1;
					break;
				case 6 :
					lastDay -= 2;
					break;
				case 7 :
					lastDay -= 3;
					break;
			}
			return lastDay;
	}
	
}

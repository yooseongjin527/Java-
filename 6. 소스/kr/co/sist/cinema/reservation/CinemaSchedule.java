package kr.co.sist.cinema.reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.InitialScreen;
/**
 * 상영 영화 스케줄
 * @author sehoon
 *
 */
public class CinemaSchedule extends FileRead {

	private String areaNum;
	private String movieUnique;
	private int movieTime;
	private int cinemaDateNum;
	private static int timeSelect;

	static {
		timeSelect = 0;
	}
	
	/**
	 * 생성자
	 * @param areaNum 선택한 지점번호
	 * @param movieUnique 선택한 영화의 고유번호
	 * @param movieTime 선택한 영화의 상영시간
	 */
	public CinemaSchedule(String areaNum, String movieUnique, int movieTime) {
		this.areaNum = areaNum;
		this.movieUnique = movieUnique;
		this.movieTime = movieTime;
	}
	

	/**
	 * 선택한 영화의 시간을 읽음
	 * @return
	 */
	public static int getTimeSelect() {
		return timeSelect;
	}

	/**
	 * 해당 지점의 영화 상영시간을 보여줌
	 */
	public void time() {

		Calendar calendar = Calendar.getInstance();

		initialReader("지역.txt");
		ArrayList<Integer> hourInfo = new ArrayList<Integer>();
		ArrayList<Integer> minuteInfo = new ArrayList<Integer>();

		int k=1;
		for (int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if (division[0].equals(movieUnique) && division[3].equals(areaNum)) {
				// 상영 시작 시간과 분, 상영시간 앞에 단순히 번호를 붙여서 보여주기위한 k
				for (int j = 0; j < 3; j++) {
					k = 1;
					System.out.printf("                                   %d. %tF\n", j+1, calendar);
					calendar.add(Calendar.DATE, 1);

					int hour = Integer.parseInt(division[1]);
					int minute = Integer.parseInt(division[2]);

					System.out.println("                                   상영시간표");
					do {
						hourInfo.add(hour);
						minuteInfo.add(minute);
						System.out.printf("                                   %d. %02d시 %02d분 ~ ", k, hour, minute);
						hour = hour + (minute + this.movieTime + 40) / 60;
						minute = (minute + this.movieTime + 40) % 60;
						System.out.printf("                                   %02d시 %02d분\n", hour, minute);

						k++;
					} while (hour < 22);
					System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				}
				if(InitialScreen.getId().equals("")) {
					System.out.println("                                   로그인을 해주세요.");
					InitialScreen start = new InitialScreen();
					start.initial();
				}
				
				String guidePlate = "상영 일자를 선택해주세요(번호) : ";
				System.out.print("                                   "+guidePlate);
				this.cinemaDateNum = scan.nextInt();
				while(this.cinemaDateNum < 1 || this.cinemaDateNum > 3) {
					guidePlate = "없는 상영일자입니다 다시 선택해주세요(번호) : ";
					System.out.print("                                   "+guidePlate);
					cinemaDateNum = scan.nextInt();
				}
				
				guidePlate = "상영시간을 선택해주세요(번호) : ";
				
				do {
					System.out.print("                                   "+guidePlate);
					timeSelect = scan.nextInt();
					guidePlate = "상영시간을 다시 선택해주세요(번호)";
				} while (timeSelect <= 0 || timeSelect > k - 1);

				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, this.cinemaDateNum -1);
				
				Ticketing ticketing = new Ticketing();
				ticketing.paymentInformation();
				Seat seat = new Seat();
				seat.SeatInfo(this.areaNum, this.movieUnique, hourInfo.get(timeSelect-1), minuteInfo.get(timeSelect-1),cal, ticketing.getNumberPeople());
				ticketing.cardTypeSelect(this.areaNum);
				
				ScheduledSeat scheduledSeat = new ScheduledSeat(this.areaNum, this.movieUnique, hourInfo.get(timeSelect-1), minuteInfo.get(timeSelect-1),seat.getSeatNumberList(),cal);
				scheduledSeat.scheduledSeatUpdate();
				scheduledSeat.ticketInformation(cal, ticketing.getEvent().getOccurrencePoint(), ticketing.getEvent().getPointUse(), ticketing.getEvent().getPrice());
//				String areaNum, String movieUnique, int movieTime, int hour, int minute, Calendar cal

			}
		}

	}

}

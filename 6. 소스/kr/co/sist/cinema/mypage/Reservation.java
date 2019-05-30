package kr.co.sist.cinema.mypage;
/**
 * 예약 정보 데이터에서 각 데이터를 분류하여 저장하고 읽기
 * @author sehoon
 *
 */
public class Reservation {
	private String id;
	private String reservationNum;
	private String title;
	private String showDate;
	private String showtime;
	private String theater;
	private String seat;
	private String price;
	private String reservationDate;
	private String isIssued;
	private String isExpired;
	private String cancelDate;
	private String occuredPoint;
	private String usedPoint;
	
	public Reservation(String id, String reservationNum, String title, String showDate, String showtime, String theater,
			String seat, String price, String reservationDate, String isIssued, String isExpired, String cancelDate,
			String occuredPoint, String usedPoint) {
		this.id = id;
		this.reservationNum = reservationNum;
		this.title = title;
		this.showDate = showDate;
		this.showtime = showtime;
		this.theater = theater;
		this.seat = seat;
		this.price = price;
		this.reservationDate = reservationDate;
		this.isIssued = isIssued;
		this.isExpired = isExpired;
		this.cancelDate = cancelDate;
		this.occuredPoint = occuredPoint;
		this.usedPoint = usedPoint;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReservationNum() {
		return reservationNum;
	}
	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public String getTheater() {
		return theater;
	}
	public void setTheater(String theater) {
		this.theater = theater;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getIsIssued() {
		return isIssued;
	}
	public void setIsIssued(String isIssued) {
		this.isIssued = isIssued;
	}
	public String getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getOccuredPoint() {
		return occuredPoint;
	}
	public void setOccuredPoint(String occuredPoint) {
		this.occuredPoint = occuredPoint;
	}
	public String getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(String usedPoint) {
		this.usedPoint = usedPoint;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationNum=" + reservationNum + ", title=" + title + ", showDate="
				+ showDate + ", showtime=" + showtime + ", theater=" + theater + ", seat=" + seat + ", price=" + price
				+ ", reservationDate=" + reservationDate + ", isIssued=" + isIssued + ", isExpired=" + isExpired
				+ ", cancelDate=" + cancelDate + ", occuredPoint=" + occuredPoint + ", usedPoint=" + usedPoint + "]";
	}
}

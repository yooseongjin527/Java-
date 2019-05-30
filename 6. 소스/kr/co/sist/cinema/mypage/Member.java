package kr.co.sist.cinema.mypage;

/**
 * 회원 정보 데이터에서 각 데이터를 분류하여 저장하고 읽기
 * @author sehoon
 *
 */
public class Member {
	private String id;
	private String password;
	private String gender;
	private String name;
	private String age;
	private String tel;
	private String address;
	private String lastMonthViewCount;
	private String thisMonthViewCount;
	private String point;
	private String isAdmin;
	private String cardNum;
	private String registrationDate;
	private String grade;
	private String isRegistered;
	private String issuedDate;
	private String cardName;
	public Member(String id, String password, String gender, String name, String age, String tel, String address,
			String lastMonthViewCount, String thisMonthViewCount, String point, String isAdmin, String cardNum,
			String registrationDate, String grade, String isRegistered, String issuedDate, String cardName) {
		super();
		this.id = id;
		this.password = password;
		this.gender = gender;
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.address = address;
		this.lastMonthViewCount = lastMonthViewCount;
		this.thisMonthViewCount = thisMonthViewCount;
		this.point = point;
		this.isAdmin = isAdmin;
		this.cardNum = cardNum;
		this.registrationDate = registrationDate;
		this.grade = grade;
		this.isRegistered = isRegistered;
		this.issuedDate = issuedDate;
		this.cardName = cardName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLastMonthViewCount() {
		return lastMonthViewCount;
	}
	public void setLastMonthViewCount(String lastMonthViewCount) {
		this.lastMonthViewCount = lastMonthViewCount;
	}
	public String getThisMonthViewCount() {
		return thisMonthViewCount;
	}
	public void setThisMonthViewCount(String thisMonthViewCount) {
		this.thisMonthViewCount = thisMonthViewCount;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIsRegistered() {
		return isRegistered;
	}
	public void setIsRegistered(String isRegistered) {
		this.isRegistered = isRegistered;
	}
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", gender=" + gender + ", name=" + name + ", age=" + age
				+ ", tel=" + tel + ", address=" + address + ", lastMonthViewCount=" + lastMonthViewCount
				+ ", thisMonthViewCount=" + thisMonthViewCount + ", point=" + point + ", isAdmin=" + isAdmin
				+ ", cardNum=" + cardNum + ", registrationDate=" + registrationDate + ", grade=" + grade
				+ ", isRegistered=" + isRegistered + ", issuedDate=" + issuedDate + ", cardName=" + cardName + "]";
	}
	
	
	
	
}

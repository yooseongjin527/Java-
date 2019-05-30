package kr.co.sist.cinema.mypage;
/**
 * 구입물품 데이터에서 각 분류별로 데이터 저장 및 읽기
 * @author sehoon
 *
 */
public class PerchaseData {
	
	private String id;
	private String serialNum;
	private String productName;
	private String price;
	private String productNum;
	private String date;
	private String pointUse;
	private String branchOffice;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPointUse() {
		return pointUse;
	}
	public void setPointUse(String pointUse) {
		this.pointUse = pointUse;
	}
	public String getBranchOffice() {
		return branchOffice;
	}
	public void setBranchOffice(String branchOffice) {
		this.branchOffice = branchOffice;
	}
	
	
	
}

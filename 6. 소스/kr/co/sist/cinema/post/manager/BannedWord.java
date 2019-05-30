package kr.co.sist.cinema.post.manager;

/**
 * 금지어 데이터에서 각 데이터를 분류하여 저장하고 읽기
 * @author sehoon
 *
 */
public class BannedWord {
	
	private String content;
	private String serialNum;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
}

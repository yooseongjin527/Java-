package kr.co.sist.cinema.post.manager;
/**
 * 포스트 데이터에서 각 데이터를 분류하여 저장하고 읽기
 * @author sehoon
 *
 */
public class Post {
	
	private String postSerialNum;
	private String movieSerialNum;
	private String publishedDate;
	private String id;
	private String head;
	private String body;
	private String isPublic;
	private String rating;
	
	
	public String getPostSerialNum() {
		return postSerialNum;
	}
	public void setPostSerialNum(String postSerialNum) {
		this.postSerialNum = postSerialNum;
	}
	public String getMovieSerialNum() {
		return movieSerialNum;
	}
	public void setMovieSerialNum(String movieSerialNum) {
		this.movieSerialNum = movieSerialNum;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
}

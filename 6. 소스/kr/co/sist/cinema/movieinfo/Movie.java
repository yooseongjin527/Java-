package kr.co.sist.cinema.movieinfo;

/**
 * 영화 정보 데이터를 분류하여 저장 및 읽기
 * @author sehoon
 *
 */
public class Movie{
	
	private String serialNum;
	private String condition;//영화상태
	private String title;
	private String releaseDate;
	private String runTime;
	private String director;
	private String actors;
	private String trailer;
	private String rating;
	private String synopsis;
	private String post;
	
	
	@Override
	public String toString() {
		return "Movie [serialNum=" + serialNum + ", condition=" + condition + ", title=" + title + ", releaseDate="
				+ releaseDate + ", runTime=" + runTime + ", director=" + director + ", actors=" + actors + ", trailer="
				+ trailer + ", rating=" + rating + ", synopsis=" + synopsis + ", post=" + post + "]";
	}
	
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
}

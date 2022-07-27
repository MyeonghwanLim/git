package model;

public class CgvVO {
	private int cid; //기본키
	private String title;// 영화제목
	private String image;// 영화 이미지
	private String genre;// 장르
	private int bookcnt;//예매횟수
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getBookcnt() {
		return bookcnt;
	}
	public void setBookcnt(int cnt) {
		this.bookcnt = cnt;
	}
	@Override
	public String toString() {
		return "CgvVO [cid=" + cid + ", title=" + title + ", image=" + image + ", genre=" + genre + ", bookcnt=" + bookcnt + "]";
	}
}

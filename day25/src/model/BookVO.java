package model;

public class BookVO {
	private int bid; 
	private int cid;
	private String mid;
	
	private String title;
	private String image;
	private String genre;
	private int bookCnt;
	

	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}


	public int getCid() {
		return cid;
	}


	public void setCid(int cid) {
		this.cid = cid;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
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


	public int getBookCnt() {
		return bookCnt;
	}


	public void setBookCnt(int bookCnt) {
		this.bookCnt = bookCnt;
	}


	@Override
	public String toString() {
		return "BookVO [bid=" + bid + ", cid=" + cid + ", mid=" + mid + ", title=" + title + ", image=" + image
				+ ", genre=" + genre + ", bookCnt=" + bookCnt + "]";
	}




}

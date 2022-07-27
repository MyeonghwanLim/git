package model;

public class MemberVO {
	private String mid;
	private String mname;
	private String mpw;
	private String mbook;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMbook() {
		return mbook;
	}
	public void setMbook(String mbook) {
		this.mbook = mbook;
	}
	@Override
	public String toString() {
		return "MemberVO [mid=" + mid + ", mname=" + mname + ", mpw=" + mpw + ", mbook=" + mbook + "]";
	}


}
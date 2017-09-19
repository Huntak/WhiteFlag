package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable{
	private int nNumber;
	private String nTitle;
	private String nContent;
	private Date nDate;
	private String nImage;
	private int nReadcount;
	
	public Notice() {}
	
	public Notice(int nNumber, String nTitle, String nContent, Date nDate, String nImage, int nReadcount) {
		this.nNumber = nNumber;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nDate = nDate;
		this.nImage = nImage;
		this.nReadcount = nReadcount;
	}
	
	

	public Notice(String nTitle, String nContent, String nImage) {
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nImage = nImage;
	}


	public int getnNumber() {
		return nNumber;
	}

	public void setnNumber(int nNumber) {
		this.nNumber = nNumber;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	public String getnImage() {
		return nImage;
	}

	public void setnImage(String nImage) {
		this.nImage = nImage;
	}

	public int getnReadcount() {
		return nReadcount;
	}

	public void setnReadcount(int nReadcount) {
		this.nReadcount = nReadcount;
	}

	@Override
	public String toString() {
		return this.nNumber + ", " + this.nTitle + ", " + this.nContent + ", " + this.nDate + ", " + this.nImage 
				+ ", " + this.nReadcount; 
	}
	
}

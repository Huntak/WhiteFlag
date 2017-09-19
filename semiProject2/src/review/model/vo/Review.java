package review.model.vo;

import java.sql.Date;

public class Review {
	private int rNumber;
	private String rTitle;
	private String rContent;
	private Date rDate;
	private String rImage;
	private int rReadcount;
	private char rAnswer_YN;
	private String mId;
	private String pId;
	
public Review(){}

public Review(int rNumber, String rTitle, String rContent, Date rDate, String rImage, int rReadcount,
		char rAnswer_YN, String mId, String pId) {
	super();
	this.rNumber = rNumber;
	this.rTitle = rTitle;
	this.rContent = rContent;
	this.rDate = rDate;
	this.rImage = rImage;
	this.rReadcount = rReadcount;
	this.rAnswer_YN = rAnswer_YN;
	this.mId = mId;
	this.pId = pId;
}

public Review(String rTitle, String rContent, String rImage, String mId) {
	this.rTitle = rTitle;
	this.rContent = rContent;
	this.rImage = rImage;
	this.mId = mId;
}

public Review(String rTitle, String rContent, String rImage, String mId, String pId) {
	this.rTitle = rTitle;
	this.rContent = rContent;
	this.rImage = rImage;
	this.mId = mId;
	this.pId = pId;
}

public int getrNumber() {
	return rNumber;
}

public void setrNumber(int rNumber) {
	this.rNumber = rNumber;
}

public String getrTitle() {
	return rTitle;
}

public void setrTitle(String rTitle) {
	this.rTitle = rTitle;
}

public String getrContent() {
	return rContent;
}

public void setrContent(String rContent) {
	this.rContent = rContent;
}

public Date getrDate() {
	return rDate;
}

public void setrDate(Date rDate) {
	this.rDate = rDate;
}

public String getrImage() {
	return rImage;
}

public void setrImage(String rImage) {
	this.rImage = rImage;
}

public int getrReadcount() {
	return rReadcount;
}

public void setrReadcount(int rReadcount) {
	this.rReadcount = rReadcount;
}

public char getrAnswer_YN() {
	return rAnswer_YN;
}

public void setrAnswer_YN(char rAnswer_YN) {
	this.rAnswer_YN = rAnswer_YN;
}

public String getmId() {
	return mId;
}

public void setmId(String mId) {
	this.mId = mId;
}

public String getpId() {
	return pId;
}

public void setpId(String pId) {
	this.pId = pId;
}

@Override
public String toString() {
	return this.rNumber + ", " + this.rTitle + ", " + this.rContent + ", " +  this.rDate + 
			", " +  this.rImage + ", " +  this.rReadcount + ", " + this.rAnswer_YN + ", " + this.mId + ", " + this.pId;
	}

}

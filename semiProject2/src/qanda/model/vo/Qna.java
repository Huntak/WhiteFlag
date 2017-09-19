package qanda.model.vo;

import java.sql.Date;

public class Qna {
	
	private int qNumber;
	private String qTitle;
	private String qContent;
	private Date qDate;
	private String qImage;
	private int qReadcount;
	private char qAnswerYN;
	private String mId;
	private String pId;
	
	public Qna() {}
	
	public Qna(String qTitle, String qContent, String qImage, String mId){
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qImage = qImage;
		this.mId = mId;
	}

	public Qna(int qNumber, String qTitle, String qContent, String qImage, int qReadcount,
			char qAnswerYN, String mid, String pid) {
		super();
		this.qNumber = qNumber;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qImage = qImage;
		this.qReadcount = qReadcount;
		this.qAnswerYN = qAnswerYN;
		this.mId = mid;
		this.pId = pid;
	}

	public Qna(String qTitle, String qContent, String qImage,String mid, String pid) {
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qImage = qImage;
		this.pId = pid;
		this.mId = mid;
	}

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	public int getqNumber() {
		return qNumber;
	}


	public void setqNumber(int qNumber) {
		this.qNumber = qNumber;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public String getqImage() {
		return qImage;
	}

	public void setqImage(String qImage) {
		this.qImage = qImage;
	}

	public int getqReadcount() {
		return qReadcount;
	}

	public void setqReadcount(int qReadcount) {
		this.qReadcount = qReadcount;
	}

	public char getqAnswerYN() {
		return qAnswerYN;
	}

	public void setqAnswerYN(char qAnswerYN) {
		this.qAnswerYN = qAnswerYN;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getPid() {
		return pId;
	}

	public void setPid(String pid) {
		pId = pid;
	}

	@Override
	public String toString() {
		return	this.qNumber + ", " + this.qTitle + ", " + this.qContent + ", " + this.qDate + ", " + this.qImage + ", " + this.qReadcount + this.qAnswerYN + 
				", " + this.mId + ", " + this.pId;
	}


}

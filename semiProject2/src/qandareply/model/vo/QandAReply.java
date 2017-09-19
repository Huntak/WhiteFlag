package qandareply.model.vo;

import java.sql.Date;

public class QandAReply {
	
	public QandAReply(){};
	private int QRNUMBER;
	private int QNUMBER;
	private String QRCONTENT;
	private Date QRDATE;
	private String MID;
	
	public QandAReply(int qRNUMBER, int qNUMBER, String qRCONTENT, Date qRDATE, String mID) {
		super();
		QRNUMBER = qRNUMBER;
		QNUMBER = qNUMBER;
		QRCONTENT = qRCONTENT;
		QRDATE = qRDATE;
		MID = mID;
	}

	public QandAReply(int qnumber, String qrcontent, Date qrdate, String mid) {
		super();
		QRDATE = qrdate;
		QNUMBER = qnumber;
		QRCONTENT = qrcontent;
		MID = mid;
	}
	public QandAReply(int qnumber, String qrcontent, String mid) {
		super();
		QNUMBER = qnumber;
		QRCONTENT = qrcontent;
		MID = mid;
	}

	public int getQRNUMBER() {
		return QRNUMBER;
	}

	public void setQRNUMBER(int qRNUMBER) {
		QRNUMBER = qRNUMBER;
	}

	public int getQNUMBER() {
		return QNUMBER;
	}

	public void setQNUMBER(int qNUMBER) {
		QNUMBER = qNUMBER;
	}

	public String getQRCONTENT() {
		return QRCONTENT;
	}

	public void setQRCONTENT(String qRCONTENT) {
		QRCONTENT = qRCONTENT;
	}

	public Date getQRDATE() {
		return QRDATE;
	}

	public void setQRDATE(Date qRDATE) {
		QRDATE = qRDATE;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	@Override
	public String toString() {
		return "QandAReply [QRNUMBER=" + QRNUMBER + ", QNUMBER=" + QNUMBER + ", QRCONTENT=" + QRCONTENT + ", QRDATE="
				+ QRDATE + ", MID=" + MID + "]";
	}
	
	
	
}

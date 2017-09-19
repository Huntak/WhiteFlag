package reviewreply.model.vo;

import java.sql.Date;

public class ReviewReply {
	private int	RRNUMBER;
	private int	RNUMBER;
	private String	RRCONTENT;
	private Date RRDATE;
	private String	MID;
	
	public ReviewReply(){}

	public ReviewReply( int rNUMBER, String rRCONTENT, String mID) {
		super();
		
		RNUMBER = rNUMBER;
		RRCONTENT = rRCONTENT;
		MID = mID;
	}

	public ReviewReply(int rRNUMBER, int rNUMBER, String rRCONTENT, Date rRDATE, String mID) {
		super();
		RRNUMBER = rRNUMBER;
		RNUMBER = rNUMBER;
		RRCONTENT = rRCONTENT;
		RRDATE = rRDATE;
		MID = mID;
	}

	public ReviewReply(int rNUMBER, String rRCONTENT, Date rRDATE, String mID) {
		super();
		RNUMBER = rNUMBER;
		RRCONTENT = rRCONTENT;
		RRDATE = rRDATE;
		MID = mID;
	}

	public int getRRNUMBER() {
		return RRNUMBER;
	}

	public void setRRNUMBER(int rRNUMBER) {
		RRNUMBER = rRNUMBER;
	}



	public int getRNUMBER() {
		return RNUMBER;
	}

	public void setRNUMBER(int rNUMBER) {
		RNUMBER = rNUMBER;
	}

	public String getRRCONTENT() {
		return RRCONTENT;
	}

	public void setRRCONTENT(String rRCONTENT) {
		RRCONTENT = rRCONTENT;
	}

	public Date getRRDATE() {
		return RRDATE;
	}

	public void setRRDATE(Date rRDATE) {
		RRDATE = rRDATE;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	@Override
	public String toString() {
		return "ReviewReply [RRNUMBER=" + RRNUMBER + ", RNUMBER=" + RNUMBER + ", RRCONTENT=" + RRCONTENT + ", RRDATE="
				+ RRDATE + ", MID=" + MID + "]";
	}
	
}

package orderAndPay.model.vo;

import java.sql.Date;

public class OrderList {
	private String OID;
	private String TOTALID;
	private String MID;
	private String PID;
	private String OSNUMBER;
	private Date ODATE;
	private int OMILEAGE;
	private int OQUANTITY;
	private String NAME;
	private String POST1;
	private String POST2;
	private String ADDRESS1;
	private String ADDRESS2;
	private String PHONE1;
	private String PHONE2;
	private String PHONE3;
	private String EMAIL;
	private int TOTALPRICE;
	private String SODATE;

	public OrderList() {}

	

	public OrderList(String oID, int tOTALPRICE, String sODATE) {
		super();
		OID = oID;
		TOTALPRICE = tOTALPRICE;
		SODATE = sODATE;
	}



	public OrderList(String mID, String pID, String oSNUMBER, int oMILEAGE, int oQUANTITY) {
		super();

		MID = mID;
		PID = pID;
		OSNUMBER = oSNUMBER;
		OMILEAGE = oMILEAGE;
		OQUANTITY = oQUANTITY;
	}
	
	

	public OrderList(String oID, String tOTALID, String mID, String pID, String oSNUMBER, Date oDATE, int oMILEAGE,
			int oQUANTITY, int tOTALPRICE) {
		super();
		OID = oID;
		TOTALID = tOTALID;
		MID = mID;
		PID = pID;
		OSNUMBER = oSNUMBER;
		ODATE = oDATE;
		OMILEAGE = oMILEAGE;
		OQUANTITY = oQUANTITY;
		TOTALPRICE = tOTALPRICE;
	}

	public OrderList(String mID, String pID, String oSNUMBER, int oMILEAGE, int oQUANTITY, String nAME, String pOST1,
			String pOST2, String aDDRESS1, String aDDRESS2, String pHONE1, String pHONE2, String pHONE3, String eMAIL,
			int tOTALPRICE) {
		super();

		MID = mID;
		PID = pID;
		OSNUMBER = oSNUMBER;
		OMILEAGE = oMILEAGE;
		OQUANTITY = oQUANTITY;
		NAME = nAME;
		POST1 = pOST1;
		POST2 = pOST2;
		ADDRESS1 = aDDRESS1;
		ADDRESS2 = aDDRESS2;
		PHONE1 = pHONE1;
		PHONE2 = pHONE2;
		PHONE3 = pHONE3;
		EMAIL = eMAIL;
		TOTALPRICE = tOTALPRICE;
	}

	public OrderList(String mID, String pID, String oSNUMBER, Date oDATE, int oMILEAGE, int oQUANTITY, String nAME,
			String pOST1, String pOST2, String aDDRESS1, String aDDRESS2, String pHONE1, String pHONE2, String pHONE3,
			String eMAIL, int tOTALPRICE) {
		super();
		MID = mID;
		PID = pID;
		OSNUMBER = oSNUMBER;
		ODATE = oDATE;
		OMILEAGE = oMILEAGE;
		OQUANTITY = oQUANTITY;
		NAME = nAME;
		POST1 = pOST1;
		POST2 = pOST2;
		ADDRESS1 = aDDRESS1;
		ADDRESS2 = aDDRESS2;
		PHONE1 = pHONE1;
		PHONE2 = pHONE2;
		PHONE3 = pHONE3;
		EMAIL = eMAIL;
		TOTALPRICE = tOTALPRICE;
	}

	public OrderList(String tOTALID, String mID, String pID, String oSNUMBER, int oMILEAGE, int oQUANTITY, String nAME,
			String pOST1, String pOST2, String aDDRESS1, String aDDRESS2, String pHONE1, String pHONE2, String pHONE3,
			String eMAIL, int tOTALPRICE) {
		super();

		TOTALID = tOTALID;
		MID = mID;
		PID = pID;
		OSNUMBER = oSNUMBER;
		OMILEAGE = oMILEAGE;
		OQUANTITY = oQUANTITY;
		NAME = nAME;
		POST1 = pOST1;
		POST2 = pOST2;
		ADDRESS1 = aDDRESS1;
		ADDRESS2 = aDDRESS2;
		PHONE1 = pHONE1;
		PHONE2 = pHONE2;
		PHONE3 = pHONE3;
		EMAIL = eMAIL;
		TOTALPRICE = tOTALPRICE;
	}

	
	
	public OrderList(String oID, String tOTALID, String mID, String pID, String oSNUMBER, int oMILEAGE, int oQUANTITY,
			int tOTALPRICE, String sODATE) {
		super();
		OID = oID;
		TOTALID = tOTALID;
		MID = mID;
		PID = pID;
		OSNUMBER = oSNUMBER;
		OMILEAGE = oMILEAGE;
		OQUANTITY = oQUANTITY;
		TOTALPRICE = tOTALPRICE;
		SODATE = sODATE;
	}



	public String getSODATE() {
		return SODATE;
	}

	public void setSODATE(String sODATE) {
		SODATE = sODATE;
	}

	public String getTOTALID() {
		return TOTALID;
	}

	public void setTOTALID(String tOTALID) {
		TOTALID = tOTALID;
	}

	public int getTOTALPRICE() {
		return TOTALPRICE;
	}

	public void setTOTALPRICE(int tOTALPRICE) {
		TOTALPRICE = tOTALPRICE;
	}

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getOSNUMBER() {
		return OSNUMBER;
	}

	public void setOSNUMBER(String oSNUMBER) {
		OSNUMBER = oSNUMBER;
	}

	public Date getODATE() {
		return ODATE;
	}

	public void setODATE(Date oDATE) {
		ODATE = oDATE;
	}

	public int getOMILEAGE() {
		return OMILEAGE;
	}

	public void setOMILEAGE(int oMILEAGE) {
		OMILEAGE = oMILEAGE;
	}

	public int getOQUANTITY() {
		return OQUANTITY;
	}

	public void setOQUANTITY(int oQUANTITY) {
		OQUANTITY = oQUANTITY;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getPOST1() {
		return POST1;
	}

	public void setPOST1(String pOST1) {
		POST1 = pOST1;
	}

	public String getPOST2() {
		return POST2;
	}

	public void setPOST2(String pOST2) {
		POST2 = pOST2;
	}

	public String getADDRESS1() {
		return ADDRESS1;
	}

	public void setADDRESS1(String aDDRESS1) {
		ADDRESS1 = aDDRESS1;
	}

	public String getADDRESS2() {
		return ADDRESS2;
	}

	public void setADDRESS2(String aDDRESS2) {
		ADDRESS2 = aDDRESS2;
	}

	public String getPHONE1() {
		return PHONE1;
	}

	public void setPHONE1(String pHONE1) {
		PHONE1 = pHONE1;
	}

	public String getPHONE2() {
		return PHONE2;
	}

	public void setPHONE2(String pHONE2) {
		PHONE2 = pHONE2;
	}

	public String getPHONE3() {
		return PHONE3;
	}

	public void setPHONE3(String pHONE3) {
		PHONE3 = pHONE3;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	@Override
	public String toString() {
		return OID + ", " + TOTALID + ",  " + MID + ", " + PID + ", " + OSNUMBER + ", " + ODATE + ", " + OMILEAGE + ", "
				+ OQUANTITY + ", " + NAME + ", " + POST1 + ", " + POST2 + ", " + ADDRESS1 + ", " + ADDRESS2 + ", "
				+ PHONE1 + ", " + PHONE2 + ", " + PHONE3 + ", " + EMAIL + ", " + TOTALPRICE;
	}

}

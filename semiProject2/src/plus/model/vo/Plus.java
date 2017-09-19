package plus.model.vo;

import java.sql.Date;

public class Plus {
	private int plusNumber;
	private int pId;
	private String pName;
	private String pSize;
	private String pColor;
	private String pMaterial;
	private int pPrice;
	private String providercode;
	private String pSeason;
	private int plusPrice;
	private int plusQuantity;
	private int plusTotal;
	private Date plusDate;
	
	public Plus(){}

	public Plus(int plusNumber, int pId, String pName, String pSize, String pColor, String pMaterial, int pPrice,
			String providercode, String pSeason, int plusPrice, int plusQuantity, int plusTotal, Date plusDate) {
		super();
		this.plusNumber = plusNumber;
		this.pId = pId;
		this.pName = pName;
		this.pSize = pSize;
		this.pColor = pColor;
		this.pMaterial = pMaterial;
		this.pPrice = pPrice;
		this.providercode = providercode;
		this.pSeason = pSeason;
		this.plusPrice = plusPrice;
		this.plusQuantity = plusQuantity;
		this.plusTotal = plusTotal;
		this.plusDate = plusDate;
	}

	public Plus(String pId, String plusPrice, String plusQuantity, String plusTotal) {
		this.pId = Integer.parseInt(pId);
		this.plusPrice = Integer.parseInt(plusPrice);
		this.plusQuantity = Integer.parseInt(plusQuantity);
		this.plusTotal = Integer.parseInt(plusTotal);	
	}
	
	public Plus(String plusnumber, String plusPrice, String plusQuantity, String plusTotal,String pId) {
		this.plusNumber = Integer.parseInt(plusnumber);
		this.pId = Integer.parseInt(pId);
		this.plusPrice = Integer.parseInt(plusPrice);
		this.plusQuantity = Integer.parseInt(plusQuantity);
		this.plusTotal = Integer.parseInt(plusTotal);
		
		
	}
	
	
	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getPlusNumber() {
		return plusNumber;
	}

	public void setPlusNumber(int plusNumber) {
		this.plusNumber = plusNumber;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpSize() {
		return pSize;
	}

	public void setpSize(String pSize) {
		this.pSize = pSize;
	}

	public String getpColor() {
		return pColor;
	}

	public void setpColor(String pColor) {
		this.pColor = pColor;
	}

	public String getpMaterial() {
		return pMaterial;
	}

	public void setpMaterial(String pMaterial) {
		this.pMaterial = pMaterial;
	}

	public String getProvidercode() {
		return providercode;
	}

	public void setProvidercode(String providercode) {
		this.providercode = providercode;
	}

	public String getpSeason() {
		return pSeason;
	}

	public void setpSeason(String pSeason) {
		this.pSeason = pSeason;
	}

	public int getPlusPrice() {
		return plusPrice;
	}

	public void setPlusPrice(int plusPrice) {
		this.plusPrice = plusPrice;
	}

	public int getPlusQuantity() {
		return plusQuantity;
	}

	public void setPlusQuantity(int plusQuantity) {
		this.plusQuantity = plusQuantity;
	}

	public int getPlusTotal() {
		return plusTotal;
	}

	public void setPlusTotal(int plusTotal) {
		this.plusTotal = plusTotal;
	}

	public Date getPlusDate() {
		return plusDate;
	}

	public void setPlusDate(Date plusDate) {
		this.plusDate = plusDate;
	};
	@Override
	public String toString(){
		return plusNumber +", "+ this.pId +", "+ this.pName +", "+ this.pSize+", "+this.pColor +", "+ this.pMaterial +", "
				+ this.providercode+", "+this.pSeason+", "+this.plusPrice+", "+this.plusQuantity+", "+
				this.plusTotal+", "+this.plusDate;
	}
}

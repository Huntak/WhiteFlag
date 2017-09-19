package stock.model.vo;

public class Stock {
	private int pId;
	private String pName;
	private String pSize;
	private String pColor;
	private String pMaterial;
	private int pPrice;
	private String providercode;
	private String pSeason;
	private int sQuantity;
	
	public Stock(){}
	
	public Stock(int pId, int sQuantity){
		this.pId = pId;
		this.sQuantity = sQuantity;
	}

	public Stock(int pId, String pName, String pSize, String pColor, String pMaterial, int pPrice, String providercode,
			String pSeason, int sQuantity) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pSize = pSize;
		this.pColor = pColor;
		this.pMaterial = pMaterial;
		this.pPrice = pPrice;
		this.providercode = providercode;
		this.pSeason = pSeason;
		this.sQuantity = sQuantity;
	}
	
	

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
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

	public int getsQuantity() {
		return sQuantity;
	}

	public void setsQuantity(int sQuantity) {
		this.sQuantity = sQuantity;
	}
	
	@Override
	public String toString(){
		return this.pId +", "+ this.pName +", "+ this.pSize+", "+this.pColor +", "+ this.pMaterial +", "
				+ this.providercode+", "+this.pSeason+", " + this.sQuantity;
	}
}

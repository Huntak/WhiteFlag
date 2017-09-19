package product.model.vo;

public class Product implements java.io.Serializable{
	//Field
	private String pId ;
	private String pName;
	private int pPrice;
	private String pSize;
	private String pColor;
	private String pMaterial;
	private String pSeason;
	private String cId;
	private String providerCode;
	private int imageId;
	
	//Constructor
	public Product(){}
	
	

	public Product(String pId) {
		super();
		this.pId = pId;
	}



	public Product(String pId, String pName, int pPrice, String pSize, String pColor, String pMaterial,
			String pSeason, String cId, String providerCode, int imageId) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pSize = pSize;
		this.pColor = pColor;
		this.pMaterial = pMaterial;
		this.pSeason = pSeason;
		this.cId = cId;
		this.providerCode = providerCode;
		this.imageId = imageId;
	}
	public Product(String pName, int pPrice, String pSize, String pColor, String pMaterial,
			String pSeason, String cId, String providerCode, int imageid) {
		super();
		this.pName = pName;
		this.pPrice = pPrice;
		this.pSize = pSize;
		this.pColor = pColor;
		this.pMaterial = pMaterial;
		this.pSeason = pSeason;
		this.cId = cId;
		this.providerCode = providerCode;
		this.imageId = imageid;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
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

	public String getpSeason() {
		return pSeason;
	}

	public void setpSeason(String pSeason) {
		this.pSeason = pSeason;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	@Override
	public String toString(){
		return this.pId + " " + this.pName + " " + this.pPrice + " " + this.pSize + " " + this.pColor + " " + this.pMaterial + " " + this.pSeason + " " + this.cId + " " + this.providerCode + " " + this.imageId;
	}

	
}

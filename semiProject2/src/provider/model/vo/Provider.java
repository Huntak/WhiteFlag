package provider.model.vo;

public class Provider {
	
	private String providerCode;
	private String providerCompany;
	private String providerName;
	private String providerTell;
	private String providerPhone;
	private String providerAddress1;
	private String providerAddress2;
	private String providerAddress3;
	private String providerAddress4;
	private String providerETC;
	
	public Provider(){}

	public Provider(String providerCode, String providerCompany, String providerName, String providerTell,
			String providerPhone, String providerAddress1, String providerAddress2, String providerAddress3, String providerAddress4, String providerETC) {
		super();
		this.providerCode = providerCode;
		this.providerCompany = providerCompany;
		this.providerName = providerName;
		this.providerTell = providerTell;
		this.providerPhone = providerPhone;
		this.providerAddress1 = providerAddress1;
		this.providerAddress2 = providerAddress2;
		this.providerAddress3 = providerAddress3;
		this.providerAddress4 = providerAddress4;
		this.providerETC = providerETC;
	}

	public String getProviderAddress1() {
		return providerAddress1;
	}

	public void setProviderAddress1(String providerAddress1) {
		this.providerAddress1 = providerAddress1;
	}

	public String getProviderAddress2() {
		return providerAddress2;
	}

	public void setProviderAddress2(String providerAddress2) {
		this.providerAddress2 = providerAddress2;
	}

	public String getProviderAddress3() {
		return providerAddress3;
	}

	public void setProviderAddress3(String providerAddress3) {
		this.providerAddress3 = providerAddress3;
	}

	public String getProviderAddress4() {
		return providerAddress4;
	}

	public void setProviderAddress4(String providerAddress4) {
		this.providerAddress4 = providerAddress4;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderCompany() {
		return providerCompany;
	}

	public void setProviderCompany(String providerCompany) {
		this.providerCompany = providerCompany;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderTell() {
		return providerTell;
	}

	public void setProviderTell(String providerTell) {
		this.providerTell = providerTell;
	}

	public String getProviderPhone() {
		return providerPhone;
	}

	public void setProviderPhone(String providerPhone) {
		this.providerPhone = providerPhone;
	}


	public String getProviderETC() {
		return providerETC;
	}

	public void setProviderETC(String providerETC) {
		this.providerETC = providerETC;
	}
	
	@Override
	public String toString(){
		return this.providerCode + ", " + this.providerCompany + ", " + this.providerName + ", " + this.providerTell + ", " + this.providerPhone +", "+ this.providerAddress1+", "+ this.providerAddress2+", "+ this.providerAddress3+", "+ this.providerAddress4 +", "+ this.providerETC;
	}
	
	
}

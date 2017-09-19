package category.model.vo;

public class Category implements java.io.Serializable {
	private String cId;
	private String cCategory;
	
	public Category(){}

	public Category(String cId, String cCategory) {
		super();
		this.cId = cId;
		this.cCategory = cCategory;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcCategory() {
		return cCategory;
	}

	public void setcCategory(String cCategory) {
		this.cCategory = cCategory;
	}
	
	@Override
	public String toString(){
		return cId + " " + cCategory;
	}
}

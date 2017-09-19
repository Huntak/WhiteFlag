package orderstatus.model.vo;

public class OrderStatus {
	private String osnumber;
	private String osstatus;
	
	public OrderStatus(){}

	public OrderStatus(String osnumber, String osstatus) {
		super();
		this.osnumber = osnumber;
		this.osstatus = osstatus;
	}

	public String getOsnumber() {
		return osnumber;
	}

	public void setOsnumber(String osnumber) {
		this.osnumber = osnumber;
	}

	public String getOsstatus() {
		return osstatus;
	}

	public void setOsstatus(String osstatus) {
		this.osstatus = osstatus;
	}
	
	@Override
	public String toString(){
		return osnumber + ", " + osstatus;
	}

}

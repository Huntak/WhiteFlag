package cart.model.vo;

public class Cart {
	private String mId;
	private String pId;
	private int quantity;
	private String cartId;
	
	public Cart(){}
	
	public Cart(String mId, String pId, int quantity) {
		super();
		this.mId = mId;
		this.pId = pId;
		this.quantity = quantity;
	}

	public Cart(String mId, String pId, int quantity, String cartId) {
		super();
		this.mId = mId;
		this.pId = pId;
		this.quantity = quantity;
		this.cartId = cartId;
	}
	



	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString(){
		return pId + ", " + mId + ", " + quantity + ", " + cartId;
	}

}

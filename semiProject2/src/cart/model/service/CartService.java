package cart.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import cart.model.dao.CartDao;
import cart.model.vo.Cart;
import static common.JDBCTemplate.*;


public class CartService {
	public CartService(){}
	
	public int insertCart(Cart cart){
		Connection con = getConnection();
		int result = new CartDao().insertCart(con, cart);
		
		if(result > 0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return result;
	}

	public ArrayList<Cart> selectAll(String userId) {
		
		Connection con = getConnection();
		ArrayList<Cart> list = new CartDao().selectAll(con, userId);
		close(con);
		return list;
		
	}

	public int deleteCart(String cartId) {
		Connection con = getConnection();
		int result = new CartDao().deleteCart(con, cartId);
		if(result > 0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return result;
	}

	public int updateQuantity(String cartId, int quantity) {
		Connection con = getConnection();
		int result = new CartDao().updateCart(con, cartId, quantity);
		if(result > 0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return result;
	}

	public Cart selectOne(String userId, String cartid) {
		Connection con = getConnection();
		Cart cart = new CartDao().selectOne(con, userId, cartid);
		close(con);
		return cart;
	}

	public int deleteCart2(String mid, String pid) {
		Connection con = getConnection();
		int result = new CartDao().deleteCart2(con, mid, pid);
		
		if(result > 0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return result;
	}

	public Cart selectAll2(String cartid) {
		Connection con = getConnection();
		Cart cart = new CartDao().selectAll2(con, cartid);
		close(con);
		return cart;
	}

}

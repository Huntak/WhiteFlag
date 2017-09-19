package cart.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import member.model.vo.Member;
import cart.model.vo.Cart;

public class CartDao {

	public int insertCart(Connection con, Cart cart) {
		int result = 0;
		PreparedStatement pstmt = null;
		String insertQuery = "insert into cart values(cartid_seq.nextval,  ?, ?, ?)";
		String userid=cart.getmId();
		
		try {
			pstmt = con.prepareStatement(insertQuery);
			
			pstmt.setString(1, cart.getmId());
			pstmt.setString(2, cart.getpId());
			pstmt.setInt(3, cart.getQuantity());
		
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return result;
	}

	public ArrayList<Cart> selectAll(Connection con, String userId) {
		
		ArrayList<Cart>list = new ArrayList<Cart>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from cart where mid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				
				Cart cart = new Cart();
				
				cart.setmId(rset.getString("MID"));
				cart.setpId(rset.getString("PID"));
				cart.setQuantity(rset.getInt("CARTQUANTITY"));
				cart.setCartId(rset.getString("CARTID"));
				
				list.add(cart);
				
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int deleteCart(Connection con, String cartId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete cart where cartid = ?";
	
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, cartId);
		
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
		
	}

	public int updateCart(Connection con, String cartId, int quantity) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE CART SET CARTQUANTITY = ? WHERE CARTID = ? ";
	
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, quantity);
			pstmt.setString(2, cartId);
		
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return result;
	}
	public int[] cartId(Connection con, String userId) {
		
		int[] cartid = new int[5];
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select cartid from cart where mid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				int x = 0;
				cartid[x] = rset.getInt("cartid");
				x++;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return cartid;
	}

	public Cart selectOne(Connection con, String userId, String cartid) {
		Cart cart = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from cart where mid = ? and cartid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, cartid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				
				cart = new Cart();
				
				cart.setmId(rset.getString("MID"));
				cart.setpId(rset.getString("PID"));
				cart.setQuantity(rset.getInt("CARTQUANTITY"));
				cart.setCartId(rset.getString("CARTID"));
				
				}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return cart;
	}

	public int deleteCart2(Connection con, String mid, String pid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete cart where mid = ? and pid = ?";
	
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, pid);
		
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return result;
	}

	public Cart selectAll2(Connection con, String cartid) {

		Cart cart = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from cart where cartid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cartid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				
				cart = new Cart();
				
				cart.setmId(rset.getString("MID"));
				cart.setpId(rset.getString("PID"));
				cart.setQuantity(rset.getInt("CARTQUANTITY"));
				cart.setCartId(rset.getString("CARTID"));
				
				
				
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return cart;
	}


}

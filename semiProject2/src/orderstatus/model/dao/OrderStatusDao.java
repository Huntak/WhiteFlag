package orderstatus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import orderstatus.model.vo.OrderStatus;
import static common.JDBCTemplate.*;

public class OrderStatusDao {

	public OrderStatus selectOrderStatus(Connection con, String osnumber) {
		OrderStatus ostatus = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String query = "select * from orderstatus where osnumber = ?";
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, osnumber);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ostatus = new OrderStatus(rset.getString("osnumber"), rset.getString("osstatus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return ostatus;
	}

}

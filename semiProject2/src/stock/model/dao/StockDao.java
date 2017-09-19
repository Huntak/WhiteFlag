package stock.model.dao;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import stock.model.vo.Stock;

public class StockDao {

	public ArrayList<Stock> selectStockList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		String query = "SELECT PID,PNAME, PSIZE, PCOLOR,pmaterial,providercode,pseason,SQUANTITY,pprice"
							+ " FROM STOCK "
							+ "LEFT JOIN PRODUCT USING(PID)";
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				Stock s = new Stock();
				s.setpId(rset.getInt(1));
				s.setpName(rset.getString(2));
				s.setpSize(rset.getString(3));
				s.setpColor(rset.getString(4));
				s.setpMaterial(rset.getString(5));
				s.setProvidercode(rset.getString(6));
				s.setpSeason(rset.getString(7));
				s.setsQuantity(rset.getInt(8));
				s.setpPrice(rset.getInt(9));
				
				stockList.add(s);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockList;
	}

	public ArrayList<Stock> searchSelectStock(Connection con, String searchtype, String searchvalue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		String query = "";
		if(searchtype.equals("pid")){
			query = "SELECT PID,PNAME, PSIZE, PCOLOR,pmaterial,providercode,pseason,SQUANTITY,pprice"
					+ " FROM STOCK "
					+ "LEFT JOIN PRODUCT USING(PID) where pid like ?";
		}else if(searchtype.equals("pname")){
			query = "SELECT PID,PNAME, PSIZE, PCOLOR,pmaterial,providercode,pseason,SQUANTITY,pprice"
					+ " FROM STOCK "
					+ "LEFT JOIN PRODUCT USING(PID) where pname like ?";
		}else if(searchtype.equals("pmaterial")){
			query = "SELECT PID,PNAME, PSIZE, PCOLOR,pmaterial,providercode,pseason,SQUANTITY,pprice"
					+ " FROM STOCK "
					+ "LEFT JOIN PRODUCT USING(PID) where pmaterial like ?";
		}else if(searchtype.equals("pseason")){
			query = "SELECT PID,PNAME, PSIZE, PCOLOR,pmaterial,providercode,pseason,SQUANTITY,pprice"
					+ " FROM STOCK "
					+ "LEFT JOIN PRODUCT USING(PID) where pseason like ?";
		}else if(searchtype.equals("providercode")){
			query = "SELECT PID,PNAME, PSIZE, PCOLOR,pmaterial,providercode,pseason,SQUANTITY,pprice"
					+ " FROM STOCK "
					+ "LEFT JOIN PRODUCT USING(PID) where providercode like ?";
		}
			
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+searchvalue+"%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				Stock s = new Stock();
				s.setpId(rset.getInt(1));
				s.setpName(rset.getString(2));
				s.setpSize(rset.getString(3));
				s.setpColor(rset.getString(4));
				s.setpMaterial(rset.getString(5));
				s.setProvidercode(rset.getString(6));
				s.setpSeason(rset.getString(7));
				s.setsQuantity(rset.getInt(8));
				s.setpPrice(rset.getInt(9));
				
				stockList.add(s);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockList;
	}

	public int deleteRowStock(Connection con, String pid) {
		String query = "delete from stock where pid = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
				
		return result;
	}

	public Stock selectStockPid(Connection con, String pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Stock s = null;
		String query = "SELECT PID,PNAME, PSIZE, PCOLOR,pmaterial,providercode,pseason,SQUANTITY,pprice"
							+ " FROM STOCK "
							+ "LEFT JOIN PRODUCT USING(PID) WHERE PID = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pId);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				s = new Stock();
				s.setpId(rset.getInt(1));
				s.setpName(rset.getString(2));
				s.setpSize(rset.getString(3));
				s.setpColor(rset.getString(4));
				s.setpMaterial(rset.getString(5));
				s.setProvidercode(rset.getString(6));
				s.setpSeason(rset.getString(7));
				s.setsQuantity(rset.getInt(8));
				s.setpPrice(rset.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	public int updateStockQuantity(Connection con, String pid, int minus) {
		String query = "update stock set squantity = ? where pid = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, minus);
			pstmt.setString(2, pid);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
				
		return result;
	}

	public Stock selectOneStock(Connection con, String pid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Stock stock = null;
		String query = "select * from stock where pid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pid);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				stock = new Stock(Integer.parseInt(rset.getString("pid")), rset.getInt("squantity"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stock;
	}
	
	

}

package orderAndPay.model.dao;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import orderAndPay.model.vo.OrderList;

public class OrderListDao {

	public int insertOrderList(Connection con, OrderList ol) {
		int result = 0;
		PreparedStatement pstmt = null;
		String insertQuery = "insert into orderlist values (OID_SEQ.nextval, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println(ol+"오더리스트");
		try {
			pstmt = con.prepareStatement(insertQuery);
			
			pstmt.setString(1, ol.getTOTALID());
			pstmt.setString(2, ol.getMID());
			pstmt.setString(3, ol.getPID());
			pstmt.setString(4, ol.getOSNUMBER());
			pstmt.setInt(5, ol.getOMILEAGE());
			pstmt.setInt(6, ol.getOQUANTITY());
			pstmt.setString(7, ol.getNAME());
			pstmt.setString(8, ol.getPOST1());
			pstmt.setString(9, ol.getPOST2());
			pstmt.setString(10, ol.getADDRESS1());
			pstmt.setString(11, ol.getADDRESS2());
			pstmt.setString(12, ol.getPHONE1());
			pstmt.setString(13, ol.getPHONE2());
			pstmt.setString(14, ol.getPHONE3());
			pstmt.setString(15, ol.getEMAIL());
			pstmt.setInt(16, ol.getTOTALPRICE());
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//추가
	public int insertCancleOrderList(Connection con, OrderList ol) {
		int result = 0;
		PreparedStatement pstmt = null;
		String insertQuery = "insert into cancleorderlist values (?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(insertQuery);
			
			pstmt.setString(1, ol.getOID());
			pstmt.setString(2, ol.getTOTALID());
			pstmt.setString(3, ol.getMID());
			pstmt.setString(4, ol.getPID());
			pstmt.setString(5, ol.getOSNUMBER());
			pstmt.setInt(6, ol.getOMILEAGE());
			pstmt.setInt(7, ol.getOQUANTITY());
			pstmt.setString(8, ol.getNAME());
			pstmt.setString(9, ol.getPOST1());
			pstmt.setString(10, ol.getPOST2());
			pstmt.setString(11, ol.getADDRESS1());
			pstmt.setString(12, ol.getADDRESS2());
			pstmt.setString(13, ol.getPHONE1());
			pstmt.setString(14, ol.getPHONE2());
			pstmt.setString(15, ol.getPHONE3());
			pstmt.setString(16, ol.getEMAIL());
			pstmt.setInt(17, ol.getTOTALPRICE());
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String selectTotalId(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		String totalId = null;
		String query = "select TOTALOID_SEQ.nextval from dual";
		
		try {
			
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
					totalId = rset.getString(1);
			}
		
			System.out.println(totalId+"다오다오다오");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		return totalId ;
	}

	public OrderList selectOidTotalPrice(Connection con, String mid) {
		OrderList orderlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select oid, totalprice, TO_CHAR(ODATE, 'YYYY-MM-DD HH24:MI:SS') as sodate from orderlist where mid = ? and oid = (select max(oid) from orderlist)";
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				orderlist = new OrderList(rset.getString("oid"), rset.getInt("totalprice"), rset.getString("sodate"));
				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);	
			close(pstmt);
		}
		return orderlist;
	}

	public ArrayList<OrderList> orderSelectAllView(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		String query ="select oid, mid, pid, osnumber, odate, omileage, oquantity, name, "
				+ "post1, post2, address1, address2, phone1, phone2, phone3, "
				+ "email, totalprice from orderlist";
		OrderList o = null;
		ArrayList<OrderList> list = new ArrayList<OrderList>();
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				o = new OrderList();
				o.setOID(rset.getString(1));
				o.setMID(rset.getString(2));
				o.setPID(rset.getString(3));
				o.setOSNUMBER(rset.getString(4));
				o.setODATE(rset.getDate(5));
				o.setOMILEAGE(rset.getInt(6));
				o.setOQUANTITY(rset.getInt(7));
				o.setNAME(rset.getString(8));
				o.setPOST1(rset.getString(9));
				o.setPOST2(rset.getString(10));
				o.setADDRESS1(rset.getString(11));
				o.setADDRESS2(rset.getString(12));
				o.setPHONE1(rset.getString(13));
				o.setPHONE2(rset.getString(14));
				o.setPHONE3(rset.getString(15));
				o.setEMAIL(rset.getString(16));
				o.setTOTALPRICE(rset.getInt(17));
				System.out.println("DAO "+o.getOSNUMBER());
				list.add(o);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		
				
		return list;
	}
	
	//이부문 백기형이랑 상의
	public OrderList OrderListDetailView(Connection con, String oid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrderList o = null;
		String query = "select oid, totalid, mid, pid, osnumber, odate, omileage, oquantity, name, "
				+ "post1, post2, address1, address2, phone1, phone2, phone3, "
				+ "email, totalprice from orderlist where oid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, oid);
			rset = pstmt.executeQuery();
			if(rset.next()){
				o = new OrderList();
				o.setOID(rset.getString(1));
				o.setTOTALID(rset.getString(2));
				o.setMID(rset.getString(3));
				o.setPID(rset.getString(4));
				o.setOSNUMBER(rset.getString(5));
				o.setODATE(rset.getDate(6));
				o.setOMILEAGE(rset.getInt(7));
				o.setOQUANTITY(rset.getInt(8));
				o.setNAME(rset.getString(9));
				o.setPOST1(rset.getString(10));
				o.setPOST2(rset.getString(11));
				o.setADDRESS1(rset.getString(12));
				o.setADDRESS2(rset.getString(13));
				o.setPHONE1(rset.getString(14));
				o.setPHONE2(rset.getString(15));
				o.setPHONE3(rset.getString(16));
				o.setEMAIL(rset.getString(17));
				o.setTOTALPRICE(rset.getInt(18));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return o;
	}

	public int updateOrderList(Connection con,String oid, String name, String phone1, String phone2, String phone3, String post1,
			String post2, String address1, String address2, String email, String osnumber) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update orderlist set name= ?, phone1 = ?, phone2 = ?, phone3 = ?,"
				+ "post1 = ?, post2 = ?, address1 = ?, address2 = ?, email = ?, osnumber = ? where oid = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone1);
			pstmt.setString(3, phone2);
			pstmt.setString(4, phone3);
			pstmt.setString(5, post1);
			pstmt.setString(6, post2);
			pstmt.setString(7, address1);
			pstmt.setString(8, address2);
			pstmt.setString(9, email);
			pstmt.setString(10, osnumber);
			pstmt.setString(11, oid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}

	public ArrayList<OrderList> searchOrderList(Connection con, String keyword, String orderSearch, Date beforeDate,
			Date afterDate) {
		ArrayList<OrderList> list  = new ArrayList<OrderList>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		OrderList o = null;
		
		if(orderSearch.equals("ordernumber")){
			query = "select oid, mid, pid, osnumber, odate, omileage, oquantity, name, post1, post2, address1, address2, phone1, phone2, phone3, email, totalprice from orderlist where oid like ?";
		}else if(orderSearch.equals("pname")){
			query = "select oid, mid, pid, pname, osnumber, odate, omileage, oquantity, name, post1, post2, address1, address2, phone1, phone2, phone3, email, totalprice from orderList join product using(pid) where pname like ? ";
		}else if(orderSearch.equals("mid")){
			query = "select oid, mid, pid, osnumber, odate, omileage, oquantity, name, post1, post2, address1, address2, phone1, phone2, phone3, email, totalprice from orderlist where mid like ?";
		}else if(orderSearch.equals("orderdate")){
			query = "select oid, mid, pid, osnumber, odate, omileage, oquantity, name, post1, post2, address1, address2, phone1, phone2, phone3, email, totalprice from orderlist where odate between ? and ?";
		}else if(orderSearch.equals("orderstatus")){
			query = "select oid, mid, pid, osnumber, odate, omileage, oquantity, name, post1, post2, address1, address2, phone1, phone2, phone3, email, totalprice from orderlist where osnumber like ?";
		}
		
		try {
			pstmt = con.prepareStatement(query);
			if(orderSearch.equals("orderdate")){
				pstmt.setDate(1, beforeDate);
				pstmt.setDate(2, afterDate);
			}else{
				pstmt.setString(1, "%"+keyword+"%");
			}
			rset = pstmt.executeQuery();
			while(rset.next()){
				o = new OrderList();
				
				o.setOID(rset.getString("oid"));
				o.setMID(rset.getString("mid"));
				o.setPID(rset.getString("pid"));
				o.setOSNUMBER(rset.getString("osnumber"));
				o.setODATE(rset.getDate("odate"));
				o.setOMILEAGE(rset.getInt("omileage"));
				o.setOQUANTITY(rset.getInt("oquantity"));
				o.setNAME(rset.getString("name"));
				o.setPOST1(rset.getString("post1"));
				o.setPOST2(rset.getString("post2"));
				o.setADDRESS1(rset.getString("address1"));
				o.setADDRESS2(rset.getString("address2"));
				o.setPHONE1(rset.getString("phone1"));
				o.setPHONE2(rset.getString("phone2"));
				o.setPHONE3(rset.getString("phone3"));
				o.setEMAIL(rset.getString("email"));
				o.setTOTALPRICE(rset.getInt("totalprice"));
				list.add(o);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	//수정
	public OrderList selectOidTotalPrice2(Connection con, String userId, String getpId) {
		OrderList orderlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select oid, totalprice, TO_CHAR(ODATE, 'YYYY-MM-DD HH24:MI:SS') as sodate from orderlist where mid = ? and pid = ? ";
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, getpId);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				orderlist = new OrderList(rset.getString("oid"), rset.getInt("totalprice"), rset.getString("sodate"));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return orderlist;
	}

	public ArrayList<OrderList> selectOrderList(Connection con, String userid) {
		ArrayList<OrderList> olist = new ArrayList<OrderList>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from orderlist where mid = ? order by oid desc";
	
		try {
		
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			rset = pstmt.executeQuery();
			
		while(rset.next()){
				OrderList orderlist = new OrderList();
				
				orderlist.setOID(rset.getString("oid"));
				orderlist.setTOTALID(rset.getString("totalid"));
				orderlist.setMID(rset.getString("mid"));
				orderlist.setPID(rset.getString("pid"));
				orderlist.setOSNUMBER(rset.getString("osnumber"));
				orderlist.setODATE(rset.getDate("odate"));
				orderlist.setOMILEAGE(rset.getInt("omileage"));
				orderlist.setOQUANTITY(rset.getInt("oquantity"));
				orderlist.setTOTALPRICE(rset.getInt("totalprice"));
				
				olist.add(orderlist);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return olist;
	}
	//수정
	public ArrayList<OrderList> selectOrderList2(Connection con, String mid) {
		
		ArrayList<OrderList> olist = new ArrayList<OrderList>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT OID, TOTALID, MID, PID, OSNUMBER, OMILEAGE, OQUANTITY, " +
"TOTALPRICE, TO_CHAR(ODATE, 'YYYY-MM-DD HH24:MI:SS') sodate from orderlist "+
"WHERE MID = ? and totalid = (select max(totalid) from orderlist) order by oid desc";
	
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
			rset = pstmt.executeQuery();
			
		while(rset.next()){
				
				OrderList orderlist = new OrderList();
				
				orderlist.setOID(rset.getString("oid"));
				orderlist.setTOTALID(rset.getString("totalid"));
				orderlist.setMID(rset.getString("mid"));
				orderlist.setPID(rset.getString("pid"));
				orderlist.setOSNUMBER(rset.getString("osnumber"));
				orderlist.setOMILEAGE(rset.getInt("omileage"));
				orderlist.setOQUANTITY(rset.getInt("oquantity"));
				orderlist.setTOTALPRICE(rset.getInt("totalprice"));
				orderlist.setSODATE(rset.getString("sodate"));
				
				olist.add(orderlist);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return olist;
	}

//추가
public int updateOsnumber(Connection con, String oid, String osnumber) {
	int result = 0;
	PreparedStatement pstmt = null;
	String query = "update orderlist set osnumber = ? where oid = ?";
	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, osnumber);
		pstmt.setString(2, oid);
		result = pstmt.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return result;
}

}

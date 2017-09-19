package plus.model.dao;
import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import plus.model.vo.Plus;

public class PlusDao {

	public ArrayList<Plus> selectPlusList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Plus> plusList = new ArrayList<Plus>();
		String query = "SELECT PLUSNUMBER,PID, PNAME, PSIZE, PCOLOR,"
				+ "pmaterial,providercode,pseason,PLUSPRICE,PLUSQUANTITY, "
				+ "PLUSTOTAL, PLUSDATE, pPrice "
				+ "FROM PLUS "
				+ "LEFT JOIN PRODUCT USING(PID) "
				+ "ORDER BY PLUSDATE DESC";
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				Plus p = new Plus();
				p.setPlusNumber(rset.getInt(1));
				p.setpId(rset.getInt(2));
				p.setpName(rset.getString(3));
				p.setpSize(rset.getString(4));
				p.setpColor(rset.getString(5));
				p.setpMaterial(rset.getString(6));
				p.setProvidercode(rset.getString(7));
				p.setpSeason(rset.getString(8));
				p.setPlusPrice(rset.getInt(9));
				p.setPlusQuantity(rset.getInt(10));
				p.setPlusTotal(rset.getInt(11));
				p.setPlusDate(rset.getDate(12));
				p.setpPrice(rset.getInt(13));
				System.out.println(p);
				plusList.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plusList;
	}
	
	public int insertPlus(Connection con, ArrayList<Plus> plusArrayForPlus) {
		PreparedStatement pstmt = null;
		int finalresult = 0; 
		int result = 0;
		String query = "insert into plus values(PLUS_SEQ.NEXTVAL, ?, ?, ?, ?, to_date(sysdate,'yy/MM/dd')) ";
		for(int i=0; i<plusArrayForPlus.size(); i++ ){
			try{	
				pstmt = null;
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, plusArrayForPlus.get(i).getpId());	
				pstmt.setInt(2, plusArrayForPlus.get(i).getPlusPrice());	
				pstmt.setInt(3, plusArrayForPlus.get(i).getPlusQuantity());	
				pstmt.setInt(4, plusArrayForPlus.get(i).getPlusTotal());	
				result = pstmt.executeUpdate();
				if(result !=0){	finalresult++;	
				}				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				close(pstmt);
			}		
		}				
		return finalresult;
	}

	public ArrayList<Plus> searchSelectPlus(Connection con, String searchtype, String searchvalue, String searchDate1, String searchDate2 ) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Plus> plusList = new ArrayList<Plus>();
		String query = "";
			 
			if(searchtype.equals("plusDate")){		
				query ="SELECT PLUSNUMBER,PID, PNAME, PSIZE, PCOLOR,"
						+ "pmaterial,providercode,pseason,PLUSPRICE,PLUSQUANTITY, "
						+ "PLUSTOTAL, PLUSDATE, pPrice "
						+ "FROM PLUS "
						+ "LEFT JOIN PRODUCT USING(PID) where plusdate between ? and ? "
						+ "ORDER BY PLUSDATE DESC";			
				try {
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, searchDate1);	
					pstmt.setString(2, searchDate2);	
				} catch (SQLException e) {
					e.printStackTrace();
				}		
			}else if(searchtype.equals("plusPName")){
				query ="SELECT PLUSNUMBER,PID, PNAME, PSIZE, PCOLOR,"
						+ "pmaterial,providercode,pseason,PLUSPRICE,PLUSQUANTITY, "
						+ "PLUSTOTAL, PLUSDATE, pPrice "
						+ "FROM PLUS "
						+ "LEFT JOIN PRODUCT USING(PID) where PNAME like ?"
						+ "ORDER BY PLUSDATE DESC";				
				try {					
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, "%"+searchvalue+"%");	
				} catch (SQLException e) {
					e.printStackTrace();
				}		 
			}
			
		try {
			rset = pstmt.executeQuery();
			while(rset.next()){
				Plus p = new Plus();
				p.setPlusNumber(rset.getInt(1));
				p.setpId(rset.getInt(2));
				p.setpName(rset.getString(3));
				p.setpSize(rset.getString(4));
				p.setpColor(rset.getString(5));
				p.setpMaterial(rset.getString(6));
				p.setProvidercode(rset.getString(7));
				p.setpSeason(rset.getString(8));
				p.setPlusPrice(rset.getInt(9));
				p.setPlusQuantity(rset.getInt(10));
				p.setPlusTotal(rset.getInt(11));
				p.setPlusDate(rset.getDate(12));
				p.setpPrice(rset.getInt(13));
				plusList.add(p);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return plusList;	
	}

	public int deletePlus(Connection con, ArrayList<Plus> plusArrayForDelete) {
		PreparedStatement pstmt = null;
		int finalresult = 0; 
		int result = 0;
		String query = "delete from plus where plusnumber = ? ";
		for(int i=0; i<plusArrayForDelete.size(); i++ ){
			try{	
				pstmt = null;
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, plusArrayForDelete.get(i).getpId());	
				result = pstmt.executeUpdate();
				if(result !=0){	finalresult++;	
				}				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				close(pstmt);
			}		
		}				
		return finalresult;
	}

	public int updatePlus(Connection con, ArrayList<Plus> plusArrayForPlus) {
		PreparedStatement pstmt = null;
		int finalresult = 0; 
		int result = 0;
		String query = "update plus set plusprice = ?, plusquantity = ?, plustotal = ? where plusnumber = ? and pid = ?";
		for(int i=0; i<plusArrayForPlus.size(); i++ ){
			try{	
				pstmt = null;
				pstmt = con.prepareStatement(query);
	
				pstmt.setInt(1, plusArrayForPlus.get(i).getPlusPrice());	
				pstmt.setInt(2, plusArrayForPlus.get(i).getPlusQuantity());	
				pstmt.setInt(3, plusArrayForPlus.get(i).getPlusTotal());
				pstmt.setInt(4, plusArrayForPlus.get(i).getPlusNumber());	
				pstmt.setInt(5, plusArrayForPlus.get(i).getpId());	
				result = pstmt.executeUpdate();
				if(result !=0){	finalresult++;	
				}				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				close(pstmt);
			}		
		}				
		return finalresult;
	}
	
	
	
	
	
	

}

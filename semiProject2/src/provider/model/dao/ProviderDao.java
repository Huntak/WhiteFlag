package provider.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import provider.model.vo.Provider;

public class ProviderDao {

	public ArrayList<Provider> providerListView(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Provider> list = new ArrayList<Provider>();
		String query = "select * from provider";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				Provider p = new Provider();
				p.setProviderCode(rset.getString("PROVIDERCODE"));
				p.setProviderCompany(rset.getString("PROVIDERCOMPANY"));
				p.setProviderName(rset.getString("PROVIDERNAME"));
				p.setProviderTell(rset.getString("PROVIDERTELL"));
				p.setProviderPhone(rset.getString("PROVIDERPHONE"));
				p.setProviderAddress1(rset.getString("PROVIDERADDRESS1"));
				p.setProviderAddress2(rset.getString("PROVIDERADDRESS2"));
				p.setProviderAddress3(rset.getString("PROVIDERADDRESS3"));
				p.setProviderAddress4(rset.getString("PROVIDERADDRESS4"));
				p.setProviderETC(rset.getString("ETC"));
				System.out.println(p);
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	public int insertProvider(Connection con, Provider p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into provider values('p'||PROVIDER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, p.getProviderCompany());
			pstmt.setString(2, p.getProviderName());
			pstmt.setString(3, p.getProviderTell());
			pstmt.setString(4, p.getProviderPhone());
			pstmt.setString(5, p.getProviderAddress1());
			pstmt.setString(6, p.getProviderAddress2());
			pstmt.setString(7, p.getProviderAddress3());
			pstmt.setString(8, p.getProviderAddress4());
			pstmt.setString(9, p.getProviderETC());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateProvider(Connection con, Provider p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="update provider set providercompany=?, providername=?, providertell=?, providerphone=?,provideraddress1=?,provideraddress2=?,provideraddress3=?,provideraddress4=?,etc=? where providercode=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, p.getProviderCompany());
			pstmt.setString(2, p.getProviderName());
			pstmt.setString(3, p.getProviderTell());
			pstmt.setString(4, p.getProviderPhone());
			pstmt.setString(5, p.getProviderAddress1());
			pstmt.setString(6, p.getProviderAddress2());
			pstmt.setString(7, p.getProviderAddress3());
			pstmt.setString(8, p.getProviderAddress4());
			pstmt.setString(9, p.getProviderETC());
			pstmt.setString(10, p.getProviderCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Provider selectOneProvider(Connection con, String providercode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from provider where providercode = ?";
		Provider p = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, providercode);
			rset=pstmt.executeQuery();
			if(rset.next()){
				p= new Provider();
			p.setProviderCode(rset.getString("PROVIDERCODE"));
			p.setProviderCompany(rset.getString("PROVIDERCOMPANY"));
			p.setProviderName(rset.getString("PROVIDERNAME"));
			p.setProviderTell(rset.getString("PROVIDERTELL"));
			p.setProviderPhone(rset.getString("PROVIDERPHONE"));
			p.setProviderAddress1(rset.getString("PROVIDERADDRESS1"));
			p.setProviderAddress2(rset.getString("PROVIDERADDRESS2"));
			p.setProviderAddress3(rset.getString("PROVIDERADDRESS3"));
			p.setProviderAddress4(rset.getString("PROVIDERADDRESS4"));
			p.setProviderETC(rset.getString("ETC"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public ArrayList<Provider> searchProvider(Connection con, String keyword, String checkedItem) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Provider> list = new ArrayList<Provider>();
		String query = "";
		
		if(checkedItem.equals("code")){
			query="select * from provider where providercode like ?";
		}else if(checkedItem.equals("company")){
			query="select * from provider where providercompany like ?";
		}else if(checkedItem.equals("tell")){
			query="select * from provider where providertell like ?";
		}else if(checkedItem.equals("name")){
			query="select * from provider where providername like ?";
		}else if(checkedItem.equals("phone")){
			query="select * from provider where providerphone like ?";
		}else if(checkedItem.equals("address")){
			query="select * from provider where provideraddress1||provideraddress2||provideraddress3||provideraddress4 like ?";
		}
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				Provider p = new Provider();
				p.setProviderCode(rset.getString("PROVIDERCODE"));
				p.setProviderCompany(rset.getString("PROVIDERCOMPANY"));
				p.setProviderName(rset.getString("PROVIDERNAME"));
				p.setProviderTell(rset.getString("PROVIDERTELL"));
				p.setProviderPhone(rset.getString("PROVIDERPHONE"));
				p.setProviderAddress1(rset.getString("PROVIDERADDRESS1"));
				p.setProviderAddress2(rset.getString("PROVIDERADDRESS2"));
				p.setProviderAddress3(rset.getString("PROVIDERADDRESS3"));
				p.setProviderAddress4(rset.getString("PROVIDERADDRESS4"));
				p.setProviderETC(rset.getString("ETC"));
				System.out.println(p);
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	public ArrayList<Provider> providercodeView(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select providercode from provider";
		ArrayList<Provider> list = new ArrayList<Provider>();
		Provider p = null;
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				p = new Provider();
				p.setProviderCode(rset.getString(1));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int deleteProvider(Connection con, String providercode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from provider where providercode = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, providercode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}

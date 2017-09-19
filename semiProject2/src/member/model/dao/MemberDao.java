package member.model.dao;
import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;
import product.model.vo.Product;

public class MemberDao {

	public ArrayList<Member> selectMemberList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member order by menrolldate";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				Member m= new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));	
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public Member selectOneMember(String mid, Connection con) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where mid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
			rset = pstmt.executeQuery();
			if(rset.next()){
				m = new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				System.out.println(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	
	
	
	 
		
public int insertMember(Connection con, Member member) {
		
		
		int result= 0;	
		String query = "insert into member values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default, ?, ?, ?, to_date(sysdate,'yy/MM/dd'))";
 			
		 System.out.println(member);
		
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMID());
			pstmt.setString(2, member.getMPWD());
			pstmt.setString(3, member.getMNAME());
			pstmt.setString(4, member.getMBIRTH());
			pstmt.setString(5, member.getMEMAIL());
			pstmt.setString(6, member.getMPHONE1());
			pstmt.setString(7, member.getMPHONE2());
			pstmt.setString(8, member.getMPHONE3());
			pstmt.setString(9, member.getMADDRESS1());
			pstmt.setString(10, member.getMADDRESS2());
			pstmt.setString(11, member.getMADDRESS3());
			pstmt.setString(12, member.getMADDRESS4());
			
			pstmt.setLong(13, member.getMTOTALMILEAGE());
			pstmt.setLong(14, member.getMTOTALPURCHASE());
			pstmt.setString(15, String.valueOf(member.getMGENDER()));
				
			System.out.println(pstmt);
			result = pstmt.executeUpdate();
			 System.out.println(result);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}

public Member loginCheck(Connection con, String userId, String userPwd) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	Member me = null;	
		String query = "select * from member where "+ "mid = ? and mpwd = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset!=null){
				if(rset.next()){
					me = new Member(userId, userPwd, rset.getString("mNAME") , rset.getString("mBIRTH"), rset.getString("mEMAIL") ,
							rset.getString("mPHONE1"),rset.getString("mPHONE2"),rset.getString("mPHONE3"), rset.getString("mADDRESS1")  ,
							rset.getString("mADDRESS2")  , rset.getString("mADDRESS3")  ,rset.getString("mADDRESS4")  ,rset.getString("gRADE")  , 
							rset.getLong("mTOTALMILEAGE") , rset.getLong("mTOTALPURCHASE") ,rset.getString("mGENDER").charAt(0), rset.getDate("mENROLLDATE")) ;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return me;
}

	public int updateMemberManager(Connection con, Member m) {
		PreparedStatement pstmt = null;
		String query = "update member set mid=?, mpwd=?, mname=?, mbirth =? , memail = ?, mphone1 = ?, mphone2 = ? , mphone3 = ?, maddress1= ?, "
				+ "maddress2=?, maddress3 = ?, maddress4 = ?, grade= ?, mtotalmileage = ?, mtotalPURCHASE = ?, mgender = ?, menrolldate = ?"
				+ "where mid = ?";
		int result = 0;
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getMID());
			pstmt.setString(2, m.getMPWD());
			pstmt.setString(3, m.getMNAME());
			pstmt.setString(4, m.getMBIRTH());
			pstmt.setString(5, m.getMEMAIL());
			pstmt.setString(6, m.getMPHONE1());
			pstmt.setString(7, m.getMPHONE2());
			pstmt.setString(8, m.getMPHONE3());
			pstmt.setString(9, m.getMADDRESS1());
			pstmt.setString(10, m.getMADDRESS2());
			pstmt.setString(11, m.getMADDRESS3());
			pstmt.setString(12, m.getMADDRESS4());
			pstmt.setString(13, m.getGRADE());
			pstmt.setLong(14, m.getMTOTALMILEAGE());
			pstmt.setLong(15, m.getMTOTALPURCHASE());
			pstmt.setString(16, String.valueOf(m.getMGENDER()));
			pstmt.setDate(17, m.getMENROLLDATE());
			pstmt.setString(18, m.getMID());
				
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Member> searchMidManager(Connection con, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member where mid like ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m= new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Member> searchMnameManager(Connection con, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member where mname like ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m= new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Member> searchMphoneManager(Connection con, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member where mphone1||mphone2||mphone3 like ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m= new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Member> searchMaddressManager(Connection con, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member where maddress1||maddress2||maddress3||maddress4 like ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m= new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Member> searchMenrolldateManager(Connection con, String beforeDate, String afterDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member where menrolldate >= ? and menrolldate <= ?";
		Date before = Date.valueOf(beforeDate);
		Date after = Date.valueOf(afterDate);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setDate(1, before);
			pstmt.setDate(2, after);
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m= new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}	
	
	public int repeatYNId(Connection con, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int repeatCount = 0;	
			String query = "select count(*) from member where mid = ?";
			
			try{
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				rset = pstmt.executeQuery();
				
				if(rset!=null){
					if(rset.next()){
						repeatCount = rset.getInt(1);
						System.out.println(repeatCount);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				close(rset);
				close(pstmt);
			}
			return repeatCount;
	}
	
	public ArrayList<Member> searchMenrolldateManager(Connection con, String beforeDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member where menrolldate = ?";
		Date before = Date.valueOf(beforeDate);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setDate(1, before);
			
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m= new Member();
				m.setMID(rset.getString("mid"));
				m.setMPWD(rset.getString("mpwd"));
				m.setMNAME(rset.getString("mname"));
				m.setMADDRESS1(rset.getString("maddress1"));
				m.setMADDRESS2(rset.getString("maddress2"));
				m.setMADDRESS3(rset.getString("maddress3"));
				m.setMADDRESS4(rset.getString("maddress4"));
				m.setMPHONE1(rset.getString("mphone1"));
				m.setMPHONE2(rset.getString("mphone2"));
				m.setMPHONE3(rset.getString("mphone3"));
				m.setMBIRTH(rset.getString("mbirth"));
				m.setMEMAIL(rset.getString("memail"));
				m.setGRADE(rset.getString("grade"));
				m.setMTOTALMILEAGE(rset.getLong("mtotalmileage"));
				m.setMTOTALPURCHASE(rset.getLong("mtotalpurchase"));
				m.setMGENDER(rset.getString("mgender").charAt(0));
				m.setMENROLLDATE(rset.getDate("menrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}	
	
	public int updateMemberInfo(Connection con, Member me) {
		PreparedStatement pstmt = null;
		String query = "update member set  mpwd=?,  memail = ?, mphone1 = ?, mphone2 = ? , mphone3 = ?, maddress1= ?, "
				+ "maddress2=?, maddress3 = ?, maddress4 = ?"
				+ "where mid = ?";
		int result = 0;
		try{	
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, me.getMPWD());
			pstmt.setString(2, me.getMEMAIL());
			pstmt.setString(3, me.getMPHONE1());
			pstmt.setString(4, me.getMPHONE2());
			pstmt.setString(5, me.getMPHONE3());
			pstmt.setString(6, me.getMADDRESS1());
			pstmt.setString(7, me.getMADDRESS2());
			pstmt.setString(8, me.getMADDRESS3());
			pstmt.setString(9, me.getMADDRESS4());
			pstmt.setString(10, me.getMID());			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
		
	 
	public int deleteMember(Connection con, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		 
		String query = "delete from member where mid=? and mpwd=? ";
				 
		int result = 0;
		try{	
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);	
			
			System.out.println(userId + " " + userPwd);
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}	
		return result;
	}

	public String[] getId(String userName, String userEmail, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select mid, menrolldate  from member where mname = ? and  memail = ?";
		String id ="";
		String enrolldate ="";
		String[] result = new String[2];

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				id = rset.getString(1);
				enrolldate = rset.getString(2);
				 
			}
			result[0] = id;
			result[1] = enrolldate;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	
		return result;
	}

	public String[] getId(String userName, String userPhone1, String userPhone2, String userPhone3, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select mid, menrolldate from member where mname = ? and mphone1 = ?  and mphone2 = ?  and mphone3 = ?";
		String id ="";
		String enrolldate ="";
		String[] result = new String[2];

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, userPhone1);
			pstmt.setString(3, userPhone2);
			pstmt.setString(4, userPhone3);
			rset = pstmt.executeQuery();
			while(rset.next()){
				id = rset.getString(1);
				enrolldate = rset.getString(2);
			}	
			result[0] = id;
			result[1] = enrolldate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}

	public String beforeChangePwd(String userId, String userName, String userPhone1, String userPhone2,String userPhone3, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select mid from member where  mid = ? and mname = ? and mphone1 = ?  and mphone2 = ?  and mphone3 = ?";
		String result = "";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, userPhone1);
			pstmt.setString(4, userPhone2);
			pstmt.setString(5, userPhone3);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = rset.getString(1);	 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}

	public String beforeChangePwd(String userId, String userName, String userEmail, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select mid from member where mid = ? and mname = ? and  memail = ?";
		String result = "";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, userEmail);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = rset.getString(1);			 
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}

	public int changePwd(Connection con, String mId , String mPWD) {
		

		String query = "update member set mpwd = ? where mid = ?";
		int result = 0;

		PreparedStatement pstmt = null;
		try{	
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mPWD);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
			System.out.println("pstmt: " + pstmt);
			System.out.println(result);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public double selectMileage(Connection con, String mId) {
		double mileageRate = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select gmileagerate from member join grade using (grade) where mid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				mileageRate = rset.getDouble("gmileagerate");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return mileageRate;
	}

	public int deleteMember(Connection con, String mID) {
			PreparedStatement pstmt = null;
			 
			String query = "delete from member where mid=? ";
					 
			int result = 0;
			try{	
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, mID);
				 
				System.out.println(mID);
				result = pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				close(pstmt);
			}	
			return result;
		
	}

	public int updateMemberMileage(Connection con, String mid, int finalMileage) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set mtotalmileage= ? where mid = ? ";
				 
		try{	
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, finalMileage);
			pstmt.setString(2, mid);	
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}	
		return result;
	}
	
	
	
	 
}

package reviewreply.model.dao;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import reviewreply.model.vo.ReviewReply;

public class ReviewReplyDao {

	public int insertReply(Connection con, ReviewReply rr) {
		PreparedStatement pstmt = null;
		String query = "insert into reviewreply values(rreview_seq.nextval,?,?,sysdate,?)";
		int result = 0;
		try {
			pstmt= con.prepareStatement(query);
			
			pstmt.setInt(1, rr.getRNUMBER());
			pstmt.setString(2, rr.getRRCONTENT());
			pstmt.setString(3, rr.getMID());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<ReviewReply> selectReply(Connection con, int reviewNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from reviewreply where rnumber = ?";
		ArrayList<ReviewReply> rlist = new ArrayList<ReviewReply>();
		ReviewReply r = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();
			while(rset.next()){
				r= new ReviewReply();
				r.setRRNUMBER(rset.getInt("rrnumber"));
				r.setRNUMBER(rset.getInt("rnumber"));
				r.setRRCONTENT(rset.getString("rrcontent"));
				r.setRRDATE(rset.getDate("rrdate"));
				r.setMID(rset.getString("mid"));
				rlist.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return rlist;
	}

	public ReviewReply selectReply(int rnumber, String rrcontent, String mid, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select rrnumber, rnumber, rrcontent, rrdate, mid from REVIEWREPLY where rnumber = ? and rrcontent = ? and mid = ?";
		ReviewReply r = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rnumber);
			
			pstmt.setString(2, rrcontent);
			pstmt.setString(3, mid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				r= new ReviewReply();
				r.setRRNUMBER(rset.getInt(1));
				r.setMID(mid);
				r.setRNUMBER(rnumber);
				r.setRRCONTENT(rrcontent);
				r.setRRDATE(rset.getDate(4));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	

	public int deleteReviewReply(Connection con, int rrnumber, int rnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from reviewreply where (rnumber = ? and rrnumber = ?)";
		System.out.println("rrnumber:"+rrnumber + "  rnumber:"+rnumber);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rnumber);
			pstmt.setInt(2, rrnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(Connection con, int rrnumber, String rrcontent) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update reviewreply set rrcontent = ? where rrnumber = ?";
		System.out.println(rrnumber + ",  " + rrcontent);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, rrcontent);
			pstmt.setInt(2, rrnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}
}

package qandareply.model.dao;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import qandareply.model.vo.QandAReply;

public class QandAReplyDao {

	public int insertReply(Connection con, QandAReply qq) {
		PreparedStatement pstmt = null;
		String query = "insert into qandareply values(qanda_seq.nextval,?,?,sysdate,?)";
		int result = 0;
		try {
			pstmt= con.prepareStatement(query);
			
			pstmt.setInt(1, qq.getQNUMBER());
			pstmt.setString(2, qq.getQRCONTENT());
			pstmt.setString(3, qq.getMID());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<QandAReply> selectReply(Connection con, int qandaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from qandareply where qnumber = ?";
		ArrayList<QandAReply> rlist = new ArrayList<QandAReply>();
		QandAReply q = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qandaNo);
			rset = pstmt.executeQuery();
			while(rset.next()){
				q= new QandAReply();
				q.setQRNUMBER(rset.getInt("qrnumber"));
				q.setQNUMBER(rset.getInt("qnumber"));
				q.setQRCONTENT(rset.getString("qrcontent"));
				q.setQRDATE(rset.getDate("qrdate"));
				q.setMID(rset.getString("mid"));
				rlist.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return rlist;
	}

	public QandAReply selectReply(int qnumber, String qrcontent, String mid, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select qrnumber, qnumber, qrcontent, qrdate, mid from QandAReply where qnumber = ? and qrcontent = ? and mid = ?";
		QandAReply q = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnumber);
			
			pstmt.setString(2, qrcontent);
			pstmt.setString(3, mid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				q= new QandAReply();
				q.setQRNUMBER(rset.getInt(1));
				q.setMID(mid);
				q.setQNUMBER(qnumber);
				q.setQRCONTENT(qrcontent);
				q.setQRDATE(rset.getDate(4));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return q;
	}

	

	public int deleteQandAReply(Connection con, int qrnumber, int qnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from QandAReply where (qnumber = ? and qrnumber = ?)";
		System.out.println("qrnumber:"+qrnumber + "  qnumber:"+qnumber);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnumber);
			pstmt.setInt(2, qrnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplyQandA(Connection con, int qrnumber, String qrcontent) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update qandareply set qrcontent = ? where qrnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, qrcontent);
			pstmt.setInt(2, qrnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(Connection con, int qrnumber, String qrcontent) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update qandareply set qrcontent = ? where qrnumber = ?";
		System.out.println(qrnumber + ",  " + qrcontent);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, qrcontent);
			pstmt.setInt(2, qrnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}
}

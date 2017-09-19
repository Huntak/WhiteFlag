package review.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import review.model.vo.Review;

public class ReviewDao {

	public ArrayList<Review> selectList(Connection con) {
		ArrayList<Review>list = new ArrayList<Review>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from review order by rnumber desc";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				Review review = new Review();
				
				review.setrNumber(rset.getInt("rNumber"));
				review.setrTitle(rset.getString("rTitle"));
				review.setrContent(rset.getString("rContent"));
				review.setrDate(rset.getDate("rDate"));
				review.setrImage(rset.getString("rImage"));
				review.setrReadcount(rset.getInt("rReadCount"));
				review.setmId(rset.getString("mid"));
				review.setpId(rset.getString("pid"));
				
				list.add(review);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		return list;
	}

	public int insertReview(Connection con, Review review) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into review values (REVIEW_SEQ.nextval, ?, ?, sysdate, ?, 0, ?, ?, ?)";
		
		try {
				
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, review.getrTitle());
			pstmt.setString(2, review.getrContent());
			pstmt.setString(3, review.getrImage());
			pstmt.setString(4, "N");
			pstmt.setString(5, review.getmId());
			pstmt.setString(6, review.getpId());
			
			result= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Review selectOne(Connection con, int reviewNo) {
		Review review = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from review where rnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				review = new Review();
				review.setrNumber(rset.getInt("rNumber"));
				review.setrTitle(rset.getString("rTitle"));
				review.setrContent(rset.getString("rContent"));
				review.setrDate(rset.getDate("rDate"));
				review.setrImage(rset.getString("rImage"));
				review.setrReadcount(rset.getInt("rReadCount"));
				review.setmId(rset.getString("mId"));
				review.setpId(rset.getString("pId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return review;
	}

	public int deleteReview(Connection con, int rnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from review where rnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateReadCount(Connection con, int reviewNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update review set rreadcount = (select rreadcount from review where rnumber = ?) + 1 where rnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			pstmt.setInt(2, reviewNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}
	public int ReviewUpdate(Connection con, String rtitle, String rcontent, String rimage, int rnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update review set rtitle =?, rcontent=?, rimage=? where rnumber  = ?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, rtitle);
			pstmt.setString(2, rcontent);
			pstmt.setString(3, rimage);
			pstmt.setInt(4, rnumber);
			
			result= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection con) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from review";
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}
	
	// 페이지별로 조회하는 목록 조회 메소드
	public ArrayList<Review> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 최신글이 맨 위로 오게하고, 해당 페이지에 보여질 목록 10개를 조회
		String query = "select * from (select rownum rnum, rnumber, rtitle, rcontent, "
				+ "rdate, rimage, rreadcount, ranswer_yn, mid, pid "
				+ "from (select * from review order by rnumber desc)) where rnum >= ? and rnum <= ?";
		
		// 읽기 시작할 행 번호
		int startRow = (currentPage - 1) * limit + 1;
		// 읽을 마지막 행 번호
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Review review = new Review();
				
				review.setrNumber(rset.getInt("rNumber"));
				review.setrTitle(rset.getString("rTitle"));
				review.setrContent(rset.getString("rContent"));
				review.setrDate(rset.getDate("rDate"));
				review.setrImage(rset.getString("rImage"));
				review.setrReadcount(rset.getInt("rReadCount"));
				review.setmId(rset.getString("mid"));
				review.setpId(rset.getString("pid"));
				
				list.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCountTitle(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from review where rtitle like ?";
		
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, "%"+keyword+"%");
			rset = stmt.executeQuery();
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public int getListCountWriter(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from review where mid like ?";
		
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, "%"+keyword+"%");
			rset = stmt.executeQuery();
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}
	//글제목으로 조회하는 목록 조회 메소드
	public ArrayList<Review> selectTitleList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				// 읽기 시작할 행 번호
				int startRow = (currentPage - 1) * limit + 1;
				// 읽을 마지막 행 번호
				int endRow = startRow + limit - 1;
		//String query = "select * from notice where notice_title like ('%' || ? || '%') order by notice_no desc";
		String query = "select * from (select rownum rnum, rnumber, rtitle, rcontent, "
				+ "rdate, rimage, rreadcount, ranswer_yn, mid, pid "
				+ "from (select * from review where rtitle like ? order by rnumber desc)) where (rnum >= ? and rnum <= ?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Review review = new Review();
				
				review.setrNumber(rset.getInt("rNumber"));
				review.setrTitle(rset.getString("rTitle"));
				review.setrContent(rset.getString("rContent"));
				review.setrDate(rset.getDate("rDate"));
				review.setrImage(rset.getString("rImage"));
				review.setrReadcount(rset.getInt("rReadCount"));
				review.setmId(rset.getString("mid"));
				review.setpId(rset.getString("pid"));
				
				list.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;

	}
	//작성자로 조회하는 목록 조회 메소드
	public ArrayList<Review> selectWriterList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				// 읽기 시작할 행 번호
				int startRow = (currentPage - 1) * limit + 1;
				// 읽을 마지막 행 번호
				int endRow = startRow + limit - 1;
				String query = "select * from (select rownum rnum, rnumber, rtitle, rcontent, "
						+ "rdate, rimage, rreadcount, ranswer_yn, mid, pid "
						+ "from (select * from review where mid like ? order by rnumber desc)) where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Review review = new Review();
				
				review.setrNumber(rset.getInt("rNumber"));
				review.setrTitle(rset.getString("rTitle"));
				review.setrContent(rset.getString("rContent"));
				review.setrDate(rset.getDate("rDate"));
				review.setrImage(rset.getString("rImage"));
				review.setrReadcount(rset.getInt("rReadCount"));
				review.setmId(rset.getString("mid"));
				review.setpId(rset.getString("pid"));
				
				list.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int getListCountImageId(Connection con, int imageId) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from review join product using(pid) where pid in (select pid from product where imageid = ?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, imageId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	// 페이지별로 조회하는 목록 조회 메소드
	public ArrayList<Review> selectListImageId(Connection con, int currentPage, int limit, int imageId) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 최신글이 맨 위로 오게하고, 해당 페이지에 보여질 목록 10개를 조회
		String query = "select * from (select rownum rnum, rnumber, rtitle, rcontent, rdate, rimage, rreadcount, ranswer_yn, mid, pid "
				+ "from (select * from review join product using(pid) where pid in "
				+ "(select pid from product where imageid = ?) order by rnumber desc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		// 읽기 시작할 행 번호
		int startRow = (currentPage - 1) * limit + 1;
		// 읽을 마지막 행 번호
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, imageId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Review review = new Review();
				
				review.setrNumber(rset.getInt("rNumber"));
				review.setrTitle(rset.getString("rTitle"));
				review.setrContent(rset.getString("rContent"));
				review.setrDate(rset.getDate("rDate"));
				review.setrImage(rset.getString("rImage"));
				review.setrReadcount(rset.getInt("rReadCount"));
				review.setmId(rset.getString("mid"));
				review.setpId(rset.getString("pid"));
				
				list.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCountContent(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from review where rcontent like ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
		
	}

	public ArrayList<Review> selectContentList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Review>list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query =  "select * from (select rownum rnum, rnumber, rtitle, rcontent, "
				+ " rdate, rimage, rREADCOUNT, ranswer_yn, mid, pid"
				+ " from (select * from review where rcontent like ? order by rnumber desc)) where (rnum >= ? and rnum <= ?)";
		
		//읽기 시작할 행 번호
		int startRow =(currentPage - 1) * limit + 1;
		//읽을 마지막 행 번호
		int endRow = startRow + limit -1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			if(rset != null){
				list = new ArrayList<Review>();
			}
			while(rset.next()){
				
				Review review = new Review();
				
				review.setrNumber(rset.getInt("RNUMBER"));
				review.setrTitle(rset.getString("RTITLE"));
				review.setrContent(rset.getString("RCONTENT"));
				review.setrDate(rset.getDate("RDATE"));
				review.setrImage(rset.getString("RIMAGE"));
				review.setrReadcount(rset.getInt("RREADCOUNT"));
				review.setrAnswer_YN(rset.getString("RANSWER_YN").charAt(0));
				review.setmId(rset.getString("mid"));
				review.setpId(rset.getString("PID"));
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCountNumber(Connection con, String keyword) {
		int ListCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from review where rnumber like ? ";
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, "%" + Integer.parseInt(keyword)+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				ListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
			
		}
		return ListCount;
	}

	public ArrayList<Review> selectNumberList(Connection con, String keyword, int currentPage, int limit) {
			ArrayList<Review> list = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select * from (select rownum rnum, rnumber, rtitle, rcontent, "
					+ " rdate, rimage, rREADCOUNT, ranswer_yn, mid, pid"
					+ " from (select * from review where rnumber like ? order by rnumber desc)) where (rnum >= ? and rnum <= ?)";
			
			int startRow = (currentPage -1 ) * limit +1;
			int endRow = startRow + limit - 1;
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + keyword  + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				if(rset != null){
					list = new ArrayList<Review>();
				}
				
				while(rset.next()){
					Review review = new Review();
					
					review.setrNumber(rset.getInt("RNUMBER"));
					review.setrTitle(rset.getString("RTITLE"));
					review.setrContent(rset.getString("RCONTENT"));
					review.setrDate(rset.getDate("RDATE"));
					review.setrImage(rset.getString("RIMAGE"));
					review.setrReadcount(rset.getInt("RREADCOUNT"));
					review.setrAnswer_YN(rset.getString("RANSWER_YN").charAt(0));
					review.setmId(rset.getString("mid"));
					review.setpId(rset.getString("PID"));
					list.add(review);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return list;
	}

	public int getListCountPid(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from review where pid like ?  ";
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, "%" + Integer.parseInt(keyword) + "%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Review> selectPidList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Review> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum rnum, rnumber, rtitle, rcontent, "
				+ " rdate, rimage, rREADCOUNT, ranswer_yn, mid, pid"
				+ " from (select * from review where pid like ? order by rnumber desc)) where (rnum >= ? and rnum <= ?)";
		
		int startRow = (currentPage -1 ) * limit +1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword  + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			if(rset != null){
				list = new ArrayList<Review>();
			}
			
			while(rset.next()){
				Review review = new Review();
				
				review.setrNumber(rset.getInt("RNUMBER"));
				review.setrTitle(rset.getString("RTITLE"));
				review.setrContent(rset.getString("RCONTENT"));
				review.setrDate(rset.getDate("RDATE"));
				review.setrImage(rset.getString("RIMAGE"));
				review.setrReadcount(rset.getInt("RREADCOUNT"));
				review.setrAnswer_YN(rset.getString("RANSWER_YN").charAt(0));
				review.setmId(rset.getString("mid"));
				review.setpId(rset.getString("PID"));
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//추가
			public ArrayList<Review> selectMine(Connection con, String mId) {
				ArrayList<Review>list = new ArrayList<Review>();
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				String query = "select * from review where mid=? order by rnumber desc";
				
				
				try {
					
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, mId);
					rset = pstmt.executeQuery();
					
					while(rset.next()){
						Review review = new Review();
						
						review.setrNumber(rset.getInt("rNumber"));
						review.setrTitle(rset.getString("rTitle"));
						review.setrContent(rset.getString("rContent"));
						review.setrDate(rset.getDate("rDate"));
						review.setrImage(rset.getString("rImage"));
						review.setrReadcount(rset.getInt("rReadCount"));
						review.setrAnswer_YN(rset.getString("rAnswer_yn").charAt(0));
						review.setmId(rset.getString("mid"));
						review.setpId(rset.getString("pid"));
						
						list.add(review);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
				}
				return list;
			}
}

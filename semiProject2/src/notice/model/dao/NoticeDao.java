package notice.model.dao;

import java.sql.*;
import java.util.ArrayList;
import notice.model.vo.Notice;

import static common.JDBCTemplate.*;


public class NoticeDao {
	public NoticeDao(){}

	public ArrayList<Notice>selectList(Connection con) {
		ArrayList<Notice>list = new ArrayList<Notice>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice order by nnumber desc";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				Notice notice = new Notice();
				
				notice.setnNumber(rset.getInt("nNumber"));
				notice.setnTitle(rset.getString("nTitle"));
				notice.setnContent(rset.getString("nContent"));
				notice.setnDate(rset.getDate("nDate"));
				notice.setnImage(rset.getString("nImage"));
				notice.setnReadcount(rset.getInt("nReadCount"));
				
				list.add(notice);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		return list;
	}

	public int insertNotice(Connection con, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into notice values (notice_seq.nextval, ?, ?, sysdate, ?, 0)";
		
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, notice.getnTitle());
			pstmt.setString(2, notice.getnContent());
			pstmt.setString(3, notice.getnImage());
			
			result= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int deleteNotice(Connection con, int nnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from notice where nnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}

	public int updateReadCount(Connection con, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update notice set nreadcount = (select nreadcount from notice where nnumber = ?) + 1 where nnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			pstmt.setInt(2, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}

	public Notice selectOne(Connection con, int noticeNo) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from notice where nnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				notice = new Notice();
				notice.setnNumber(rset.getInt("nNumber"));
				notice.setnTitle(rset.getString("nTitle"));
				notice.setnContent(rset.getString("nContent"));
				notice.setnDate(rset.getDate("nDate"));
				notice.setnImage(rset.getString("nImage"));
				notice.setnReadcount(rset.getInt("nReadCount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}

	public int getListCount(Connection con) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from notice";
		try {
			
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	//페이지별로 조회하는 목록 조회 메소드
	public ArrayList<Notice> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Notice>list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum rnum, nnumber, ntitle, ncontent, ndate, nimage, nreadcount from (select * from notice order by nnumber desc)) where rnum >= ? and rnum <= ?";
		
		//읽기 시작할 행 번호
		int startRow = (currentPage - 1) * limit + 1;
		//읽을 마지막 행 번호
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice notice = new Notice();
			
				notice.setnNumber(rset.getInt("nNumber"));
				notice.setnTitle(rset.getString("nTitle"));
				notice.setnContent(rset.getString("nContent"));
				notice.setnDate(rset.getDate("nDate"));
				notice.setnImage(rset.getString("nImage"));
				notice.setnReadcount(rset.getInt("nReadCount"));
			
				list.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//글제목으로 조회하는 목록 조회 메소드
	public ArrayList<Notice> selectTitleList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query =  "select * from (select rownum rnum, nnumber, ntitle, ncontent, ndate, nimage, nreadcount from (select * from notice where ntitle like ? order by nnumber desc)) where (rnum >= ? and rnum <= ?)";
		
		// 읽기 시작할 행 번호
		int startRow = (currentPage - 1) * limit + 1;
		// 읽을 마지막 행 번호
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			if(rset != null){
				list = new ArrayList<Notice>();
			}
			
			while(rset.next()){
				Notice notice = new Notice();
				
				notice.setnNumber(rset.getInt("NNUMBER"));
				notice.setnTitle(rset.getString("NTITLE"));
				notice.setnContent(rset.getString("NCONTENT"));
				notice.setnDate(rset.getDate("NDATE"));
				notice.setnImage(rset.getString("NIMAGE"));
				notice.setnReadcount(rset.getInt("NREADCOUNT"));
				System.out.println(notice);
				list.add(notice);
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
	public ArrayList<Notice> selectWriterList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println("DAO 키워드" + keyword);
				// 읽기 시작할 행 번호
				int startRow = (currentPage - 1) * limit + 1;
				// 읽을 마지막 행 번호
				int endRow = startRow + limit - 1;
				String query = "select * from (select rownum rnum, nnumber, ntitle, ncontent, ndate, nimage, nreadcount from (select * from notice where nContent like ? order by nnumber desc)) where rnum >= ? and rnum <= ?";
						
	
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice notice = new Notice();
				
				notice.setnNumber(rset.getInt("nNumber"));
				notice.setnTitle(rset.getString("nTitle"));
				notice.setnContent(rset.getString("nContent"));
				notice.setnDate(rset.getDate("nDate"));
				notice.setnImage(rset.getString("nImage"));
				notice.setnReadcount(rset.getInt("nReadCount"));
				
				list.add(notice);
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
		
		String query = "select count(*) from notice where ntitle like ?";
		
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
		System.out.println("DAO 리스트카운트 라이터 실행");
		String query = "select count(*) from notice where ncontent like ?";
		
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
	public int NoticeUpdate(Connection con, String ntitle, String ncontent, String nimage, int nnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update notice set ntitle =?, ncontent=?, nimage=? where nnumber  = ?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, ntitle);
			pstmt.setString(2, ncontent);
			pstmt.setString(3, nimage);
			pstmt.setInt(4, nnumber);
			
			result= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Notice> selectDateList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				// 읽기 시작할 행 번호
				int startRow = (currentPage - 1) * limit + 1;
				// 읽을 마지막 행 번호
				int endRow = startRow + limit - 1;
				String query = "select * from (select rownum rnum, nnumber, ntitle, ncontent, "
						+ "ndate, nimage, nreadcount"
						+ "from (select * from notice where ndate like ? order by nnumber desc)) where rnum >= ? and rnum <= ?";
	
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice notice = new Notice();
				
				notice.setnNumber(rset.getInt("nNumber"));
				notice.setnTitle(rset.getString("nTitle"));
				notice.setnContent(rset.getString("nContent"));
				notice.setnDate(rset.getDate("nDate"));
				notice.setnImage(rset.getString("nImage"));
				notice.setnReadcount(rset.getInt("nReadCount"));
				
				list.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}

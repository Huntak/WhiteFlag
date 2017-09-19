package qanda.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import qanda.model.vo.Qna;

public class QnaDao {

	public int insertQna(Connection con, Qna qna) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into qanda values (QandA_SEQ.nextval, ?, ?, sysdate, ?, 0, ?, ?, ?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, qna.getqTitle());
			pstmt.setString(2, qna.getqContent());
			pstmt.setString(3, qna.getqImage());
			pstmt.setString(4, "N");
			pstmt.setString(5, qna.getmId());
			pstmt.setString(6, qna.getPid());
			result= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Qna> selectList(Connection con) {
		ArrayList<Qna>list = new ArrayList<Qna>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from qanda order by qnumber desc";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				Qna qna = new Qna();
				
				qna.setqNumber(rset.getInt("qNumber"));
				qna.setqTitle(rset.getString("qTitle"));
				qna.setqContent(rset.getString("qContent"));
				qna.setqDate(rset.getDate("qDate"));
				qna.setqImage(rset.getString("qImage"));
				qna.setqReadcount(rset.getInt("qReadCount"));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("pid"));
				
				list.add(qna);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		return list;
	}

	//공지글 상세 보기
	public Qna selectOne(Connection con, int qnaNo) {
		Qna qna = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from qanda where qnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				qna = new Qna();
				qna.setqNumber(rset.getInt("qNumber"));
				qna.setqTitle(rset.getString("qTitle"));
				qna.setqContent(rset.getString("qContent"));
				qna.setqDate(rset.getDate("qDate"));
				//System.out.println("이게 될까? 22" +qna.getqDate());
				qna.setqImage(rset.getString("qImage"));
				qna.setqReadcount(rset.getInt("qReadCount"));
				qna.setmId(rset.getString("mId"));
				qna.setPid(rset.getString("pid"));
				
				System.out.println("qna상세" + qna);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qna;
	}

	public int deleteQna(Connection con, int qnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from qanda where qnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnumber);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}


	public int updateReadCount(Connection con, int qnaNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update qanda set qreadcount = (select qreadcount from qanda where qnumber = ?) + 1 where qnumber = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			pstmt.setInt(2, qnaNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}

	public int QnaUpdate(Connection con, String qtitle, String qcontent, String qimage, int qnumber) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update qanda set qtitle =?, qcontent=?, qimage=? where qnumber  = ?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, qtitle);
			pstmt.setString(2, qcontent);
			pstmt.setString(3, qimage);
			pstmt.setInt(4, qnumber);
			
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
		
		String query = "select count(*) from qanda";
		
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

	public int getListCountTitle(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from qanda where qtitle like ?";
		
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
		
		String query = "select count(*) from qanda where mid like ?";
		
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
	// 페이지별로 조회하는 목록 조회 메소드
	public ArrayList<Qna> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 최신글이 맨 위로 오게하고, 해당 페이지에 보여질 목록 10개를 조회
		String query = "select * from (select rownum rnum, qnumber, qtitle, qcontent, "
				+ "qdate, qimage, qreadcount, qanswer_yn, mid, pid "
				+ "from (select * from qanda order by qnumber desc)) where rnum >= ? and rnum <= ?";
		
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
				Qna qna = new Qna();
				
				qna.setqNumber(rset.getInt("qNumber"));
				qna.setqTitle(rset.getString("qTitle"));
				qna.setqContent(rset.getString("qContent"));
				qna.setqDate(rset.getDate("qDate"));
				qna.setqImage(rset.getString("qImage"));
				qna.setqReadcount(rset.getInt("qReadCount"));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("pid"));
				
				list.add(qna);
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
	public ArrayList<Qna> selectWriterList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
			
				String query = "select * from (select rownum rnum, qnumber, qtitle, qcontent, "
						+ "qdate, qimage, qreadcount, qanswer_yn, mid, pid "
						+ "from (select * from qanda where mid like ? order by qnumber desc)) where rnum >= ? and rnum <= ?";
				
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
			
			while(rset.next()){
				Qna qna = new Qna();
				
				qna.setqNumber(rset.getInt("qNumber"));
				qna.setqTitle(rset.getString("qTitle"));
				qna.setqContent(rset.getString("qContent"));
				qna.setqDate(rset.getDate("qDate"));
				qna.setqImage(rset.getString("qImage"));
				qna.setqReadcount(rset.getInt("qReadCount"));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("pid"));
				
				list.add(qna);
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
	public ArrayList<Qna> selectTitleList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Qna> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println("여기오냐");
		//String query = "select * from notice where notice_title like ('%' || ? || '%') order by notice_no desc";
		String query =  "select * from (select rownum rnum, qnumber, qtitle, qcontent, "
				+ " qdate, qimage, QREADCOUNT, qanswer_yn, mid, pid"
				+ " from (select * from qanda where qtitle like ? order by qnumber desc)) where (rnum >= ? and rnum <= ?)";
		
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
				list = new ArrayList<Qna>();
			}
			
			while(rset.next()){
				Qna qna = new Qna();
			
				qna.setqNumber(rset.getInt("QNUMBER"));
				qna.setqTitle(rset.getString("QTITLE"));
				qna.setqContent(rset.getString("QCONTENT"));
				qna.setqDate(rset.getDate("QDATE"));
				qna.setqImage(rset.getString("QIMAGE"));
				qna.setqReadcount(rset.getInt("QREADCOUNT"));
				qna.setqAnswerYN(rset.getString("QANSWER_YN").charAt(0));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("PID"));
				System.out.println("qna : " + qna);
				list.add(qna);
				System.out.println("list : " + list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;

	}

	public ArrayList<Qna> selectSearchList(Connection con, String keyword, int currentPage, int limit,
			String selectedItem) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getListCountContent(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from qanda where qcontent like ?";
		
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

	public ArrayList<Qna> selectContentList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Qna> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query =  "select * from (select rownum rnum, qnumber, qtitle, qcontent, "
				+ " qdate, qimage, QREADCOUNT, qanswer_yn, mid, pid"
				+ " from (select * from qanda where qcontent like ? order by qnumber desc)) where (rnum >= ? and rnum <= ?)";
		
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
				list = new ArrayList<Qna>();
			}
			
			while(rset.next()){
				Qna qna = new Qna();
			
				qna.setqNumber(rset.getInt("QNUMBER"));
				qna.setqTitle(rset.getString("QTITLE"));
				qna.setqContent(rset.getString("QCONTENT"));
				qna.setqDate(rset.getDate("QDATE"));
				qna.setqImage(rset.getString("QIMAGE"));
				qna.setqReadcount(rset.getInt("QREADCOUNT"));
				qna.setqAnswerYN(rset.getString("QANSWER_YN").charAt(0));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("PID"));
				System.out.println("qna : " + qna);
				list.add(qna);
				System.out.println("list : " + list);
			}
		} catch (Exception e) {
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
		
		String query = "select count(*) from qanda where qnumber like ? ";		
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, "%"+Integer.parseInt(keyword)+"%");
			
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

	public ArrayList<Qna> selectNumberList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Qna> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//String query = "select * from notice where notice_title like ('%' || ? || '%') order by notice_no desc";
		String query =  "select * from (select rownum rnum, qnumber, qtitle, qcontent, "
				+ " qdate, qimage, QREADCOUNT, qanswer_yn, mid, pid"
				+ " from (select * from qanda where qnumber like ? order by qnumber desc)) where (rnum >= ? and rnum <= ?)";
		
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
				list = new ArrayList<Qna>();
			}
			
			while(rset.next()){
				Qna qna = new Qna();
			
				qna.setqNumber(rset.getInt("QNUMBER"));
				qna.setqTitle(rset.getString("QTITLE"));
				qna.setqContent(rset.getString("QCONTENT"));
				qna.setqDate(rset.getDate("QDATE"));
				qna.setqImage(rset.getString("QIMAGE"));
				qna.setqReadcount(rset.getInt("QREADCOUNT"));
				qna.setqAnswerYN(rset.getString("QANSWER_YN").charAt(0));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("PID"));
				System.out.println("qna : " + qna);
				list.add(qna);
				System.out.println("list : " + list);
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
		
		String query = "select count(*) from qanda join product using(pid) where pid in (select pid from product where imageid = ?)";
		
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
	public ArrayList<Qna> selectListImageId(Connection con, int currentPage, int limit, int imageId) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 최신글이 맨 위로 오게하고, 해당 페이지에 보여질 목록 10개를 조회
		String query = "select * from (select rownum qnum, qnumber, qtitle, qcontent, qdate, qimage, qreadcount, qanswer_yn, mid, pid "
				+ "from (select * from qanda join product using(pid) where pid in "
				+ "(select pid from product where imageid = ?) order by qnumber desc)) "
				+ "where qnum >= ? and qnum <= ?";
		
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
				Qna qna = new Qna();
				
				qna.setqNumber(rset.getInt("qNumber"));
				qna.setqTitle(rset.getString("qTitle"));
				qna.setqContent(rset.getString("qContent"));
				qna.setqDate(rset.getDate("qDate"));
				qna.setqImage(rset.getString("qImage"));
				qna.setqReadcount(rset.getInt("qReadCount"));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("pid"));
				
				list.add(qna);
			}
		} catch (Exception e) {
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
		
		String query = "select count(*) from qanda where pid like ? ";
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
	
	public ArrayList<Qna> selectPidList(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Qna> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum rnum, qnumber, qtitle, qcontent, "
				+ " qdate, qimage, qREADCOUNT, qanswer_yn, mid, pid"
				+ " from (select * from qanda where pid like ? order by qnumber desc)) where (rnum >= ? and rnum <= ?)";
		
		int startRow = (currentPage -1 ) * limit +1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword  + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			if(rset != null){
				list = new ArrayList<Qna>();
			}
			
			while(rset.next()){
				Qna qna = new Qna();
				
				qna.setqNumber(rset.getInt("QNUMBER"));
				qna.setqTitle(rset.getString("QTITLE"));
				qna.setqContent(rset.getString("QCONTENT"));
				qna.setqDate(rset.getDate("QDATE"));
				qna.setqImage(rset.getString("QIMAGE"));
				qna.setqReadcount(rset.getInt("QREADCOUNT"));
				qna.setqAnswerYN(rset.getString("QANSWER_YN").charAt(0));
				qna.setmId(rset.getString("mid"));
				qna.setPid(rset.getString("PID"));
				System.out.println("qna : " + qna);
				list.add(qna);
				System.out.println("list : " + list);
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
		public ArrayList<Qna> selectMine(Connection con, String userId) {
			ArrayList<Qna>qlist = new ArrayList<Qna>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select * from qanda where mid=? order by qnumber desc";
			
			
			try {
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, userId);
				rset = pstmt.executeQuery();
				
				while(rset.next()){
					Qna qna = new Qna();
					
					qna.setqNumber(rset.getInt("qNumber"));
					qna.setqTitle(rset.getString("qTitle"));
					qna.setqContent(rset.getString("qContent"));
					qna.setqDate(rset.getDate("qDate"));
					qna.setqImage(rset.getString("qImage"));
					qna.setqReadcount(rset.getInt("qReadCount"));
					qna.setqAnswerYN(rset.getString("qAnswer_yn").charAt(0));
					qna.setmId(rset.getString("mid"));
					qna.setmId(rset.getString("pid"));
					
					qlist.add(qna);
					 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
			}
			return qlist;
		}
}

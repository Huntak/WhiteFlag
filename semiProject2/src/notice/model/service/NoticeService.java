package notice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	public NoticeService(){}
	
	public ArrayList<Notice> selectList(){
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectList(con);
		close(con);
		return list;
	}

	public Notice selectOne(int noticeNo) {
		Connection con = getConnection();
		Notice notice = new NoticeDao().selectOne(con, noticeNo);
		close(con);
		return notice;
	}

	public int deleteNotice(int noticeNo) {
		Connection con = getConnection();
		int result = new NoticeDao().deleteNotice(con, noticeNo);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}
	
	public int updateReadCount(int noticeNo) {
		Connection con = getConnection();
		int result = new NoticeDao().updateReadCount(con, noticeNo);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int insertNotice(Notice notice) {
		Connection con = getConnection();
		int result = new NoticeDao().insertNotice(con, notice);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int getListCount() {
		Connection con = getConnection();
		int listCount = new NoticeDao().getListCount(con);
		close(con);
		return listCount;
	}

	public ArrayList<Notice> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}

	public ArrayList<Notice> selectTitleList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice>list = new NoticeDao().selectTitleList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public ArrayList<Notice> selectWriterList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice>list = new NoticeDao().selectWriterList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountTitle(String keyword) {
		Connection con = getConnection();
		int listCount = new NoticeDao().getListCountTitle(con, keyword);
		close(con);
		return listCount;
	}

	public int getListCountWriter(String keyword) {
		Connection con = getConnection();
		int listCount = new NoticeDao().getListCountWriter(con, keyword);
		close(con);
		return listCount;
	}

	public int NoticeUpdate(String ntitle, String ncontent, String nimage, int nnumber) {
		Connection con = getConnection();
		int result = new NoticeDao().NoticeUpdate(con, ntitle, ncontent, nimage, nnumber);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public ArrayList<Notice> selectDateList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice>list = new NoticeDao().selectDateList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}


}

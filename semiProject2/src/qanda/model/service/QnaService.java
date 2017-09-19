package qanda.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import qanda.model.dao.QnaDao;
import qanda.model.vo.Qna;

public class QnaService {
	public QnaService(){}
	
	public ArrayList<Qna> selectList(){
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectList(con);
		close(con);
		return list;
		
	}

	public Qna selectOne(int qnaNo) {
		Connection con = getConnection();
		Qna qna = new QnaDao().selectOne(con, qnaNo);
		close(con);
		return qna;
	}

	public int deleteQna(int QnaNo) {
		Connection con = getConnection();
		int result = new QnaDao().deleteQna(con, QnaNo);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}
	
	public int updateReadCount(int qnaNo) {
		Connection con = getConnection();
		int result = new QnaDao().updateReadCount(con, qnaNo);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int insertQna(Qna qna) {
		Connection con = getConnection();
		int result = new QnaDao().insertQna(con, qna);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int QnaUpdate(String qtitle, String qcontent, String qimage, int qnumber) {
		Connection con = getConnection();
		int result = new QnaDao().QnaUpdate(con, qtitle, qcontent, qimage, qnumber);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int getListCount() {
		Connection con = getConnection();
		int listCount = new QnaDao().getListCount(con);
		close(con);
		return listCount;
	}

	public int getListCountTitle(String keyword) {
		Connection con = getConnection();
		int listCount = new QnaDao().getListCountTitle(con,keyword);
		close(con);
		return listCount;
	}

	public int getListCountWriter(String keyword) {
		Connection con = getConnection();
		int listCount = new QnaDao().getListCountWriter(con,keyword);
		close(con);
		return listCount;
	}

	public ArrayList<Qna> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}


	public ArrayList<Qna> selectList(String selectedItem, String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectSearchList(con, keyword, currentPage, limit,selectedItem);
		close(con);
		return list;
	}

	public ArrayList<Qna> selectTitleList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectTitleList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public ArrayList<Qna> selectWriterList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectWriterList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountContent(String keyword) {
		Connection con = getConnection();
		int listCount = new QnaDao().getListCountContent(con,keyword);
		close(con);
		return listCount;
	}

	public ArrayList<Qna> selectContentList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectContentList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountNumber(String keyword) {
		Connection con = getConnection();
		int listCount = new QnaDao().getListCountNumber(con, keyword);
		close(con);
		return listCount;
	}

	public ArrayList<Qna> selectNumberList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectNumberList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountImageId(int imageId) {
		Connection con = getConnection();
		int listCount = new QnaDao().getListCountImageId(con, imageId);
		close(con);
		return listCount;
	}

	public ArrayList<Qna> selectListImageId(int currentPage, int limit, int imageId) {
		Connection con = getConnection();
		ArrayList<Qna> list = new QnaDao().selectListImageId(con, currentPage, limit, imageId);
		close(con);
		return list;
	}
	
	public int getListCountPid(String keyword) {
		Connection con = getConnection();
		int listCount = new QnaDao().getListCountPid(con, keyword);
		close(con);
		return listCount;
	}
	
	public ArrayList<Qna> selectPidList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Qna>list = new QnaDao().selectPidList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}
	
	//추가
			public ArrayList<Qna> selectListMine(String userId) {
				Connection con = getConnection();
				ArrayList<Qna> qlist = new QnaDao().selectMine(con, userId);
				close(con);
				return qlist;
			}
}

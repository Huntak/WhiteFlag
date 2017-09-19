package review.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import review.model.vo.Review;
import review.model.dao.ReviewDao;

public class ReviewService {

	public int updateReadCount(int reviewNo) {
		Connection con = getConnection();
		int result = new ReviewDao().updateReadCount(con, reviewNo);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public Review selectOne(int reviewNo) {
		Connection con = getConnection();
		Review review = new ReviewDao().selectOne(con, reviewNo);
		close(con);
		return review;
	}

	public ArrayList<Review> selectList() {
		Connection con = getConnection();
		ArrayList<Review> list = new ReviewDao().selectList(con);
		close(con);
		return list;
	}

	public int insertReview(Review review) {
		Connection con = getConnection();
		int result = new ReviewDao().insertReview(con, review);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int deleteReview(int rnumber) {
		Connection con = getConnection();
		int result = new ReviewDao().deleteReview(con, rnumber);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}
	public int ReviewUpdate(String rtitle, String rcontent, String rimage, int rnumber) {
		Connection con = getConnection();
		int result = new ReviewDao().ReviewUpdate(con, rtitle, rcontent, rimage, rnumber);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int getListCount() {
		Connection con = getConnection();
		int listCount = new ReviewDao().getListCount(con);
		close(con);
		return listCount;
	}

	public ArrayList<Review> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Review> list = new ReviewDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountTitle(String keyword) {
		Connection con = getConnection();
		int listCount = new ReviewDao().getListCountTitle(con,keyword);
		close(con);
		return listCount;
	}

	public int getListCountWriter(String keyword) {
		Connection con = getConnection();
		int listCount = new ReviewDao().getListCountWriter(con,keyword);
		close(con);
		return listCount;
	}

	public ArrayList<Review> selectTitleList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Review> list = new ReviewDao().selectTitleList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public ArrayList<Review> selectWriterList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Review> list = new ReviewDao().selectWriterList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountImageId(int imageId) {
		Connection con = getConnection();
		int listCount = new ReviewDao().getListCountImageId(con, imageId);
		close(con);
		return listCount;
	}

	public ArrayList<Review> selectListImageId(int currentPage, int limit, int imageId) {
		Connection con = getConnection();
		ArrayList<Review> list = new ReviewDao().selectListImageId(con, currentPage, limit, imageId);
		close(con);
		return list;
	}

	public int getListCountContent(String keyword) {
		Connection con = getConnection();
		int listCount = new ReviewDao().getListCountContent(con, keyword);
		close(con);
		return listCount;
	}

	public ArrayList<Review> selectContentList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Review> list = new ReviewDao().selectContentList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountNumber(String keyword) {
		Connection con = getConnection();
		int listCount = new ReviewDao().getListCountNumber(con, keyword);
		close(con);
		return listCount;
		
	}

	public ArrayList<Review> selectNumberList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Review>list = new ReviewDao().selectNumberList(con,keyword, currentPage, limit);
		close(con);
		return list;
	}

	public int getListCountPid(String keyword) {
		Connection con = getConnection();
		int listCount = new ReviewDao().getListCountPid(con, keyword);
		close(con);
		return listCount;
	}

	public ArrayList<Review> selectPidList(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Review>list = new ReviewDao().selectPidList(con, keyword, currentPage, limit);
		close(con);
		return list;
	}
	
	//추가
			public ArrayList<Review> selectListMine(String mId) {
				Connection con = getConnection();
				ArrayList<Review> review = new ReviewDao().selectMine(con, mId);
				close(con);
				return review;
			}
	
}

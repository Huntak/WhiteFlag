package reviewreply.model.service;

import reviewreply.model.dao.ReviewReplyDao;
import reviewreply.model.vo.ReviewReply;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import review.model.dao.ReviewDao;


public class ReviewReplyService {

	public int insertReply(ReviewReply rr) {
		Connection con = getConnection();
		int result = new ReviewReplyDao().insertReply(con,rr);
		if(result>0){
			commit(con);
		}else{
			rollback(con);
		}
		return result;
	}

	public ArrayList<ReviewReply> selectReply(int reviewNo) {
		Connection con = getConnection();
		ArrayList<ReviewReply> replylist = new ReviewReplyDao().selectReply(con,reviewNo);
		return replylist;
	}

	public ReviewReply selectReply(int rnumber,  String rrcontent, String mid) {
		Connection con = getConnection();
		ReviewReply r = new ReviewReplyDao().selectReply(rnumber, rrcontent, mid, con);
		return r;
	}


	public int deleteReviewReply(int rrnumber, int rnumber) {
		Connection con = getConnection();
		int result = new ReviewReplyDao().deleteReviewReply(con, rrnumber, rnumber);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int updateReply(int rrnumber, String rrcontent) {
		Connection con = getConnection();
		int result = new ReviewReplyDao().updateReply(con, rrnumber, rrcontent);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

}

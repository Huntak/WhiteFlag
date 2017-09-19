package qandareply.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import qandareply.model.dao.QandAReplyDao;
import qandareply.model.vo.QandAReply;
import reviewreply.model.dao.ReviewReplyDao;


public class QandAReplyService {

	public int insertReply(QandAReply qq) {
		Connection con = getConnection();
		int result = new QandAReplyDao().insertReply(con, qq);
		if(result>0){
			commit(con);
		}else{
			rollback(con);
		}
		return result;
	}

	public ArrayList<QandAReply> selectReply(int qandaNo) {
		Connection con = getConnection();
		ArrayList<QandAReply> replylist = new QandAReplyDao().selectReply(con,qandaNo);
		return replylist;
	}

	public QandAReply selectReply(int qnumber,  String qrcontent, String mid) {
		Connection con = getConnection();
		QandAReply q = new QandAReplyDao().selectReply(qnumber, qrcontent, mid, con);
		return q;
	}


	public int deleteQandAReply(int qrnumber, int qnumber) {
		Connection con = getConnection();
		int result = new QandAReplyDao().deleteQandAReply(con, qrnumber, qnumber);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int updateReplyQandA(int qrnumber, String qrcontent) {
		Connection con = getConnection();
		int result = new QandAReplyDao().updateReplyQandA(con, qrnumber, qrcontent);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}

	public int updateReply(int qrnumber, String qrcontent) {
		Connection con = getConnection();
		int result = new QandAReplyDao().updateReply(con, qrnumber, qrcontent);
		if(result > 0)
				commit(con);
		else
				rollback(con);
		close(con);
		return result;
	}
	

}

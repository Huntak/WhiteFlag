package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import product.model.dao.ProductDao;
import product.model.vo.Product;

import static common.JDBCTemplate.*;
public class MemberService {

	 

	public ArrayList<Member> selectMemberList() {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().selectMemberList(con);
		
		
		return list;
	}

	public Member selectOneMember(String mid) {
		Connection con = getConnection();
		Member m = new MemberDao().selectOneMember(mid, con);
		
		return m;
	}

	
	 
	public int insertMember(Member member){

		Connection con = getConnection();
		int result = new MemberDao().insertMember(con, member);
		if(result>0) commit(con);
		else rollback(con);
		return result;
	}

	public int checkID(String mID) {
		 
		return 0;
	}

	public Member loginCheck(String userId, String userPwd) {
		Connection con = getConnection();
		Member loginUser = new MemberDao().loginCheck(con, userId, userPwd);
		close(con);
		
		
		return loginUser;
	}


	public int updateMemberManager(Member m) {
		Connection con = getConnection();
		int result = new MemberDao().updateMemberManager(con,m);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}

	public ArrayList<Member> searchMidManager(String keyword) {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().searchMidManager(con,keyword);
		
		
		return list;
	}

	public ArrayList<Member> searchMnameManager(String keyword) {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().searchMnameManager(con,keyword);
		
		
		return list;
	}

	public ArrayList<Member> searchMphoneManager(String keyword) {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().searchMphoneManager(con,keyword);
		
		
		return list;
	}

	public ArrayList<Member> searchMaddressManager(String keyword) {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().searchMaddressManager(con,keyword);
		
		
		return list;
	}

	public ArrayList<Member> searchMenrolldateManager(String beforeDate, String afterDate) {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().searchMenrolldateManager(con,beforeDate, afterDate);
		
		
		return list;
	}
	
	public int repeatYNId(String id) {
		Connection con = getConnection();
		int result = new MemberDao().repeatYNId(con, id);
		return result;

	}
	
	public int updateMember(Member me) {
		Connection con = getConnection();
		int result = new MemberDao().updateMemberInfo(con,me);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
	
	public ArrayList<Member> searchMenrolldateManager(String beforeDate) {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().searchMenrolldateManager(con,beforeDate);
		
		
		return list;
	}
	 
	
	public int deleteMember(String userId, String userPwd) {
		Connection con = getConnection();
		int result = new MemberDao().deleteMember(con,userId,userPwd);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}

	public String[] getId(String userName, String userPhone1, String userPhone2, String userPhone3) {
		Connection con = getConnection();
		String[] result = new String[2];
		result=	new MemberDao().getId(userName, userPhone1, userPhone2, userPhone3, con);	
		close(con);
		return result;
	}

	public String[] getId(String userName, String userEmail) {
		Connection con = getConnection();
		String[] result = new String[2];
		result = new MemberDao().getId(userName, userEmail, con);	
		close(con);
		return result;
	}

	public String beforeChangePwd(String userId, String userName, String userPhone1, String userPhone2,String userPhone3) {
		Connection con = getConnection();
		String result = "";
		result=	new MemberDao().beforeChangePwd(userId, userName, userPhone1, userPhone2, userPhone3, con);	
		close(con);
		return result;
	}

	public String beforeChangePwd(String userId, String userName, String userEmail) {
		Connection con = getConnection();
		String result = "";
		result=	new MemberDao().beforeChangePwd(userId, userName, userEmail, con);	
		close(con);
		return result;
	}
	
	public int changePwd(String mId, String mPwd) {
		Connection con = getConnection();
		int result = new MemberDao().changePwd(con,mId, mPwd);  
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}

	public double selectMileage(String mId) {
		Connection con = getConnection();
		double mileageRate = 0;
		mileageRate = new MemberDao().selectMileage(con, mId);
		close(con);
		return mileageRate;
	}

	public int deleteMember(String mID) {
		Connection con = getConnection();
		int result = new MemberDao().deleteMember(con,mID);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}

	public int updateMemberMileage(String mid, int finalMileage) {
		System.out.println("서비스");
		Connection con = getConnection();
		int result = new MemberDao().updateMemberMileage(con, mid, finalMileage);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}

	
}

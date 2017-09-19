package plus.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import plus.model.dao.PlusDao;
import plus.model.vo.Plus;
import static common.JDBCTemplate.*;

public class PlusService {

	public ArrayList<Plus> selectPlusList() {
		Connection con = getConnection();
		ArrayList<Plus> plusList = new PlusDao().selectPlusList(con);
		return plusList;
	}

	public int insertPlus(ArrayList<Plus> plusArrayForPlus) {	
		Connection con = getConnection();
		int result = new PlusDao().insertPlus(con, plusArrayForPlus);
		if(result>0) commit(con);
		else rollback(con);
		return result;
	}

	public ArrayList<Plus> searchSelectPlus(String searchtype, String searchvalue, String searchDate1, String searchDate2) {
		Connection con = getConnection();
		ArrayList<Plus> plusList = new PlusDao().searchSelectPlus(con,searchtype,searchvalue,searchDate1,searchDate2 );
		return plusList;
	}

	public int deletePlus(ArrayList<Plus> plusArrayForDelete) {   /// 수정
		Connection con = getConnection();
		int result = new PlusDao().deletePlus(con, plusArrayForDelete);
		if(result>0) commit(con);
		else rollback(con);
		return result;
	}

	public int updatePlus(ArrayList<Plus> plusArrayForPlus) {
		Connection con = getConnection();
		int result = new PlusDao().updatePlus(con, plusArrayForPlus);
		if(result>0) commit(con);
		else rollback(con);
		return result;
	}

}

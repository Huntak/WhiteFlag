package orderAndPay.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import orderAndPay.model.dao.OrderListDao;
import orderAndPay.model.vo.OrderList;

public class OrderListService {

	public int insertOrderList(OrderList ol) {
		Connection con = getConnection();
		int result = new OrderListDao().insertOrderList(con, ol);
		
		if(result > 0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return result;
	}
	public int insertCancleOrderList(OrderList ol) {
		Connection con = getConnection();
		int result = new OrderListDao().insertCancleOrderList(con, ol);
		
		if(result > 0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return result;
	}

	public String selectTotalId() {
		Connection con = getConnection();
		String totalId = new OrderListDao().selectTotalId(con);
		close(con);
		return totalId;
	}

	public OrderList selectOidTotalPrice(String mid) {
		Connection con = getConnection();
		OrderList orderlist = new OrderListDao().selectOidTotalPrice(con, mid);
		close(con);
		return orderlist;
	}

	public ArrayList<OrderList> orderSelectAllView() {
		Connection con = getConnection();
		ArrayList<OrderList> list = new OrderListDao().orderSelectAllView(con);
		return list;
	}

	public OrderList OrderListDetailView(String oid) {
		Connection con = getConnection();
		OrderList o = new OrderListDao().OrderListDetailView(con,oid);
		return o;
	}

	public int updateOrderList(String oid,String name, String phone1, String phone2, String phone3, String post1, String post2,
			String address1, String address2, String email, String osnumber) {
		Connection con = getConnection();
		int result = new OrderListDao().updateOrderList(con,oid,name,phone1,phone2,phone3,post1,post2,address1,address2,email,osnumber);
		
		if(result>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		return result;
	}

	public ArrayList<OrderList> searchOrderList(String keyword, String orderSearch, Date beforeDate, Date afterDate) {
		Connection con = getConnection();
		ArrayList<OrderList> list = new OrderListDao().searchOrderList(con,keyword,orderSearch,beforeDate,afterDate);
		return list;
	}

	public OrderList selectOidTotalPrice2(String userId, String getpId) {
		Connection con = getConnection();
		OrderList orderlist = new OrderListDao().selectOidTotalPrice2(con, userId, getpId);
		close(con);
		return orderlist;
	}

	public ArrayList<OrderList> selectOrderList(String userid) {
		Connection con = getConnection();
		ArrayList<OrderList> olist = new OrderListDao().selectOrderList(con, userid);
		close(con);
		return olist;
	}
	public ArrayList<OrderList> selectOrderList2(String mid) {
		Connection con = getConnection();
		ArrayList<OrderList> orderlist = new OrderListDao().selectOrderList2(con, mid);
		close(con);
		return orderlist;
	}
	
	//추가
	public int updateOsnumber(String oid, String osnumber) {
		Connection con = getConnection();
		int result = new OrderListDao().updateOsnumber(con, oid, osnumber);
		
		if(result > 0){
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return result;
	}
		
}



package orderstatus.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import orderstatus.model.vo.OrderStatus;
import orderstatus.model.dao.OrderStatusDao;

public class OrderStatusService {

	public OrderStatus selectOrderStatus(String osnumber) {
		Connection con = getConnection();
		OrderStatus orderStatus = new OrderStatusDao().selectOrderStatus(con, osnumber);
		close(con);
		return orderStatus;
	}

}

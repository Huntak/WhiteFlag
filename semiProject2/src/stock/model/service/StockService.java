package stock.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import plus.model.dao.PlusDao;
import plus.model.vo.Plus;
import stock.model.dao.StockDao;
import stock.model.vo.Stock;

public class StockService {

	public ArrayList<Stock> selectStockList() {
		Connection con = getConnection();
		ArrayList<Stock> stockList = new StockDao().selectStockList(con);
		return stockList;
	}

	public ArrayList<Stock> searchSelectStock(String searchtype, String searchvalue) {
		Connection con = getConnection();
		ArrayList<Stock> stockList = new StockDao().searchSelectStock(con,searchtype,searchvalue);
		return stockList;
	}

	public int deleteRowStock(String pid) {
		Connection con = getConnection();
		int result = new StockDao().deleteRowStock(con,pid);
		if(result>0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public Stock selectStockPid(String pId) {
		Connection con = getConnection();
		Stock stock = new StockDao().selectStockPid(con, pId);
		return stock;
	}

	public int updateStockQuantity(String pid, int minus) {
		Connection con = getConnection();
		int result = new StockDao().updateStockQuantity(con, pid, minus);
		if(result>0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public Stock selectOneStock(String pid) {
		Connection con = getConnection();
		Stock stock = new StockDao().selectOneStock(con, pid);
		return stock;
	}
	
	

}

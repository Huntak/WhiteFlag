package provider.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import provider.model.dao.ProviderDao;
import provider.model.vo.Provider;

import static common.JDBCTemplate.*;

public class ProviderService {

	public ArrayList<Provider> providerListView() {
		Connection con = getConnection();
		
		ArrayList<Provider> list = new ProviderDao().providerListView(con);
		
		return list;
	}

	public int insertProvider(Provider p) {
		Connection con = getConnection();
		int result = new ProviderDao().insertProvider(con, p);
		if(result >0){
			commit(con);
		}else{
			rollback(con);
		}
		return result;
	}

	public int updateProvider(Provider p) {
		Connection con = getConnection();
		int result = new ProviderDao().updateProvider(con, p);
		if(result >0){
			commit(con);
		}else{
			rollback(con);
		}
		return result;
	}

	public Provider selectOneProvider(String providercode) {
		Connection con = getConnection();
		Provider p = new ProviderDao().selectOneProvider(con,providercode);
		return p;
	}

	public ArrayList<Provider> searchProvider(String keyword, String checkedItem) {
		Connection con = getConnection();
		
		ArrayList<Provider> list = new ProviderDao().searchProvider(con,keyword,checkedItem);
		
		return list;
	}
	
	public ArrayList<Provider> providercodeView() {
		Connection con = getConnection();
		
		ArrayList<Provider> list = new ProviderDao().providercodeView(con);
		
		return list;
	}

	public int deleteProvider(String providercode) {
		Connection con = getConnection();
		
		int result = new ProviderDao().deleteProvider(con, providercode);
		
		return result;
	}

}

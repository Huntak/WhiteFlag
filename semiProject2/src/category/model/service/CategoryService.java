package category.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import category.model.dao.CategoryDao;
import category.model.vo.Category;
import product.model.vo.Product;
import product.model.vo.ProductImage;

public class CategoryService {
	public ArrayList<Product> selectProductCId(String cId) {
		Connection con = getConnection();
		
		ArrayList<Product> list = new CategoryDao().selectProductCId(con, cId);
		
		close(con);
		
		return list;
	}

	public ArrayList<ProductImage> selectProductImageCId(String cId) {
		Connection con = getConnection();
		
		ArrayList<ProductImage> list = new CategoryDao().selectProductImageCId(con, cId);
		
		close(con);
		
		return list;
	}
	
	public ArrayList<Product> selectProduct(String searchCondition) {
		Connection con = getConnection();
		
		ArrayList<Product> list = new CategoryDao().selectProduct(con, searchCondition);
		
		close(con);
		
		return list;
	}

	public ArrayList<ProductImage> selectProductImage(String searchCondition) {
		Connection con = getConnection();
		
		ArrayList<ProductImage> list = new CategoryDao().selectProductImage(con, searchCondition);
		
		close(con);
		
		return list;
	}

	public ArrayList<Product> selectProduct(String cId, String searchCondition, int searchPrice1,
			int searchPrice2, String searchOrder) {
		Connection con = getConnection();
		
		ArrayList<Product> list = new CategoryDao().selectProduct(con, cId, searchCondition, searchPrice1, searchPrice2, searchOrder);
		
		close(con);
		
		return list;
	}

	public ArrayList<ProductImage> selectProductImage(String cId, String searchCondition, int searchPrice1,
			int searchPrice2, String searchOrder) {
		Connection con = getConnection();
		
		ArrayList<ProductImage> list = new CategoryDao().selectProductImage(con, cId, searchCondition, searchPrice1, searchPrice2, searchOrder);
		
		close(con);
		
		return list;
	}
	
	public ArrayList<Product> selectProductPName(String[] productNameList, String searchOrder) {
		Connection con = getConnection();
		
		ArrayList<Product> list = new CategoryDao().selectProductPName(con, productNameList, searchOrder);
		
		close(con);
		
		return list;
	}
	
	public ArrayList<ProductImage> selectProductImagePName(String[] productNameList, String searchOrder) {
		Connection con = getConnection();
		
		ArrayList<ProductImage> list = new CategoryDao().selectProductImagePName(con, productNameList, searchOrder);
		
		close(con);
		
		return list;
	}

	public ArrayList<Category> getCategoryId() {
		Connection con = getConnection();
		ArrayList<Category>categoryidlist = new CategoryDao().getCategoryId(con);
		
		return categoryidlist;
	}
}

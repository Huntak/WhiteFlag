package product.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import product.model.dao.ProductDao;
import product.model.vo.*;

public class ProductService {

	public int insertProduct(Product product) {
		Connection con = getConnection();
		
		int result = new ProductDao().insertProduct(con, product);
		
		if(result>0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	public int insertProductImage(ProductImage productImage) {
		Connection con = getConnection();
	
		int imageid = new ProductDao().insertProductImage(con, productImage);
		
		if(imageid > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return imageid;		
	}
	
	public ArrayList<Product> selectProductImageId(int imageId) {
		Connection con = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectProductImageId(con, imageId);
		
		close(con);
		
		return list;
	}

	public ProductImage selectProductImageImageId(int imageId) {
		Connection con = getConnection();
		
		ProductImage productImage = new ProductDao().selectProductImageImageId(con, imageId);
		
		close(con);
		
		return productImage;
	}

	public ArrayList<Product> selectAll(String userId) {
		
		Connection con = getConnection();
		ArrayList<Product> list = new ProductDao().selectAll(con, userId);
		close(con);
		return list;
		
	}
	
	public Product selectOne(String userId, String cartid) {
		Connection con = getConnection();
		Product product = new ProductDao().selectOne(con, userId, cartid);
		close(con);
		return product;
	}

	public Product selectProduct(String pname, String color, String size) {
		Connection con = getConnection();
		Product product = new ProductDao().selectProduct(con, pname, color, size);
		close(con);
		return product;
	}
	
	public ArrayList<ProductImage> selectMainImage(String userId) {
		Connection con = getConnection();
		ArrayList<ProductImage> list = new ProductDao().selectMainImage(con, userId);
		close(con);
		return list;
	}

	public ArrayList<Product> selectAllProduct() {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductDao().selectAllProduct(con);
		close(con);
		return list;
	}

	public ArrayList<Product> selectOneProduct(String pName) {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductDao().selectOneProduct(con,pName);
		close(con);
		return list;
	}

	public String getPid() {
		Connection con = getConnection();
		String getPid = new ProductDao().getPid(con);
		close(con);
		return getPid;
	}

	public int deleteRowProduct(String pid) {
		Connection con = getConnection();
		
		int result = new ProductDao().deleteRowProduct(pid,con);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public Product selectOneViewProduct(String pname) {
		Connection con = getConnection();
		Product p = new ProductDao().selectOneViewProduct(con,pname);
		return p;
	}

	public int updateProduct(Product p) {
		Connection con = getConnection();
		int result = new ProductDao().updateProduct(con,p);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public ArrayList<Product> searchProduct(String searchtype, String keyword) {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductDao().searchProduct(con,searchtype,keyword);
		close(con);
		return list;
	}

	public Product detailViewProduct(String pid) {
		Connection con = getConnection();
		Product p = new ProductDao(). detailViewProduct(con, pid);
		return p;
	}
	
	public Product selectAll2(String pid) {
		Connection con = getConnection();
		Product product = new ProductDao().selectAll2(con, pid);
		close(con);
		return product;
	}

	public ProductImage selectMainImage2(int imageId) {
		Connection con = getConnection();
		ProductImage pimage = new ProductDao().selectMainImage2(con, imageId);
		close(con);
		return pimage;
	}

	public Product selectOneProduct(String pname, String color, String size) {
		Connection con = getConnection();
		Product product = new ProductDao().selectOneProduct(con, pname, color, size);
		close(con);
		return product;
	}

	public ArrayList<Product> selectProductMidPid(String mid) {
		Connection con = getConnection();
		ArrayList<Product> list = new ProductDao().selectProductMidPid(con, mid);
		close(con);
		return list;
	}
	
	public Product selectProductPid(String pid) {
		Connection con = getConnection();
		Product product = new ProductDao().selectProductPid(con, pid);
		close(con);
		return product;
	}

}

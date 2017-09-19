package product.model.dao;

import static common.JDBCTemplate.*;

import java.sql.*;
import java.util.ArrayList;

import product.model.vo.*;

public class ProductDao {

	public int insertProduct(Connection con, Product product) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into product values(product_seq.nextval,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, product.getpName());
			pstmt.setInt(2, product.getpPrice());
			pstmt.setString(3, product.getpSize());
			pstmt.setString(4, product.getpColor());
			pstmt.setString(5, product.getpMaterial());
			pstmt.setString(6, product.getpSeason());
			pstmt.setString(7, product.getcId());
			pstmt.setString(8, product.getProviderCode());
			pstmt.setInt(9, product.getImageId());

			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		
		
		return result;
	}
	public int insertProductImage(Connection con, ProductImage productImage) {
		int imageid = 0;
		PreparedStatement pstmt = null;
		PreparedStatement stmt = null;
		String query = "insert into productimage values(productimage_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		String query2 = "select imageid from productimage where mainimage = ?";
		ResultSet rset = null;
		
		try {			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productImage.getMainImage());
			pstmt.setString(2, productImage.getImage1());
			pstmt.setString(3, productImage.getImage2());
			pstmt.setString(4, productImage.getImage3());
			pstmt.setString(5, productImage.getImage4());
			pstmt.setString(6, productImage.getImage5());
			pstmt.setString(7, productImage.getImage6());
			pstmt.setString(8, productImage.getImage7());
			pstmt.setString(9, productImage.getImage8());
			pstmt.setString(10, productImage.getImage9());
			pstmt.setString(11, productImage.getImage10());
			
			pstmt.executeUpdate();
			
			stmt = con.prepareStatement(query2);
			stmt.setString(1, productImage.getMainImage());
			rset = stmt.executeQuery();
			
			if(rset.next()){
				imageid = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
			close(stmt);
		}

		return imageid;
	}
	public ArrayList<Product> selectProductImageId(Connection con, int imageId) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from product where imageid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, imageId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Product product = new Product();
				
				product.setpId(rset.getString("pid"));
				product.setpName(rset.getString("pname"));
				product.setpPrice(rset.getInt("pprice"));
				product.setpSize(rset.getString("psize"));
				product.setpColor(rset.getString("pcolor"));
				product.setpMaterial(rset.getString("pmaterial"));
				product.setpSeason(rset.getString("pseason"));
				product.setcId(rset.getString("cid"));
				product.setProviderCode(rset.getString("providercode"));
				product.setImageId(rset.getInt("imageid"));
				
				list.add(product);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public ProductImage selectProductImageImageId(Connection con, int imageId) {
		ProductImage productImage = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from productimage where imageid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, imageId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				productImage = new ProductImage();
				
				productImage.setImageId(rset.getInt("imageid"));
				productImage.setMainImage(rset.getString("mainimage"));
				productImage.setImage1(rset.getString("image1"));
				productImage.setImage2(rset.getString("image2"));
				productImage.setImage3(rset.getString("image3"));
				productImage.setImage4(rset.getString("image4"));
				productImage.setImage5(rset.getString("image5"));
				productImage.setImage6(rset.getString("image6"));
				productImage.setImage7(rset.getString("image7"));
				productImage.setImage8(rset.getString("image8"));
				productImage.setImage9(rset.getString("image9"));
				productImage.setImage10(rset.getString("image10"));

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return productImage;
	}
	
	public ArrayList<Product> selectAll(Connection con, String userId) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM cart JOIN PRODUCT USING(PID) WHERE MID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset != null){
				while(rset.next()){
				
				Product product = new Product();
				
				product.setpId(rset.getString("PID"));
				product.setpName(rset.getString("PNAME"));
				product.setpPrice(rset.getInt("PPRICE"));
				product.setpSize(rset.getString("PSIZE"));
				product.setpColor(rset.getString("PCOLOR"));
				product.setpMaterial(rset.getString("PMATERIAL"));
				product.setpSeason(rset.getString("PSEASON"));
				product.setcId(rset.getString("CID"));
				product.setProviderCode(rset.getString("PROVIDERCODE"));
				product.setImageId(rset.getInt("IMAGEID"));
				
				
				
				list.add(product);
				
			}
				
			}else{
				System.out.println("실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
	
		return list;
	}

	public Product selectOne(Connection con, String userId, String cartid) {
		Product product = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, providercode, imageid  from cart join product using (pid) where mid = ? and cartid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, cartid);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				
				product = new Product();
				
				product.setpId(rset.getString("PID"));
				product.setpName(rset.getString("PNAME"));
				product.setpPrice(rset.getInt("PPRICE"));
				product.setpSize(rset.getString("PSIZE"));
				product.setpColor(rset.getString("PCOLOR"));
				product.setpMaterial(rset.getString("PMATERIAL"));
				product.setpSeason(rset.getString("PSEASON"));
				product.setcId(rset.getString("CID"));
				product.setProviderCode(rset.getString("PROVIDERCODE"));
				product.setImageId(rset.getInt("IMAGEID"));
				
				}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return product;
	}
	public Product selectProduct(Connection con, String pname, String color, String size) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;
		String query = "SELECT PID FROM PRODUCT WHERE PNAME = ? AND PCOLOR = ? AND PSIZE = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pname);
			pstmt.setString(2, color);
			pstmt.setString(3, size);
			
			rset = pstmt.executeQuery();
			
			if(rset != null){
				while(rset.next()){
				
				product = new Product(rset.getString("PID"));	
				
			}
				
			}else{
				System.out.println("실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
	
		return product;
	}

	
	public ArrayList<ProductImage> selectMainImage(Connection con, String userId) {
		ArrayList<ProductImage> list = new ArrayList<ProductImage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select mainimage, imageid from cart join product using (pid) join productimage using (imageid) where mid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ProductImage ProductImage = new ProductImage(rset.getString("MAINIMAGE"), rset.getInt("imageid"));
				
				list.add(ProductImage);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public ArrayList<Product> selectAllProduct(Connection con) {
		Product product = null;
		ArrayList<Product> list = new ArrayList<Product>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, providercode, imageid  from product order by pname";
		
		try {
			stmt = con.createStatement();			
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				
				product = new Product();
				
				product.setpId(rset.getString("PID"));
				product.setpName(rset.getString("PNAME"));
				product.setpPrice(rset.getInt("PPRICE"));
				product.setpSize(rset.getString("PSIZE"));
				product.setpColor(rset.getString("PCOLOR"));
				product.setpMaterial(rset.getString("PMATERIAL"));
				product.setpSeason(rset.getString("PSEASON"));
				product.setcId(rset.getString("cid"));
				product.setProviderCode(rset.getString("PROVIDERCODE"));
				product.setImageId(rset.getInt("IMAGEID"));
				list.add(product);
				
				}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		return list;
	}
	public ArrayList<Product> selectOneProduct(Connection con, String pName) {
		Product product = null;
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, providercode, imageid  from product  where pname = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pName);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				
				product = new Product();
				
				product.setpId(rset.getString("PID"));
				product.setpName(rset.getString("PNAME"));
				product.setpPrice(rset.getInt("PPRICE"));
				product.setpSize(rset.getString("PSIZE"));
				product.setpColor(rset.getString("PCOLOR"));
				product.setpMaterial(rset.getString("PMATERIAL"));
				product.setpSeason(rset.getString("PSEASON"));
				product.setcId(rset.getString("cid"));
				product.setProviderCode(rset.getString("PROVIDERCODE"));
				product.setImageId(rset.getInt("IMAGEID"));
				list.add(product);
				
				}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public String getPid(Connection con) {
		Statement stmt = null;
		ResultSet rset  = null;
		String query = "select product_seq.nextval from dual";
		String pid = null;
			try {
				stmt = con.createStatement();
				rset = stmt.executeQuery(query);
				if(rset.next()){
					pid = rset.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return pid;
	}
	public int deleteRowProduct(String pid, Connection con) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from product where pid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		
		return result;
	}
	public Product selectOneViewProduct(Connection con, String pname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String query = "select pname, pseason, pprice, providercode, pmaterial, cid, imageid from (select rownum as rnum,pseason, pname, pprice, psize, pcolor, providercode, pmaterial, cid, imageid from product where pname= ?) where rnum=1";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pname);
			rset = pstmt.executeQuery();
			if(rset.next()){
				p = new Product();
				p.setpName(rset.getString(1));
				p.setpSeason(rset.getString(2));
				p.setpPrice(rset.getInt(3));
				p.setProviderCode(rset.getString(4));
				p.setpMaterial(rset.getString(5));
				p.setcId(rset.getString(6));
				p.setImageId(rset.getInt(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	public int updateProduct(Connection con, Product p) {
		PreparedStatement pstmt = null;
		String query = "update product set pname=?, pmaterial=?, pprice=?, pseason=?, providercode=?, cid=? where pid=?";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, p.getpName());
			pstmt.setString(2, p.getpMaterial());
			pstmt.setInt(3, p.getpPrice());
			pstmt.setString(4, p.getpSeason());
			pstmt.setString(5, p.getProviderCode());
			pstmt.setString(6, p.getcId());
			pstmt.setString(7, p.getpId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public ArrayList<Product> searchProduct(Connection con, String searchtype, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = null;
		Product p = null;
		
		if(searchtype.equals("pname")){
			query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, providercode, imageid from product where pname like ?";
		}else if (searchtype.equals("pseason")){
			query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, providercode, imageid from product where pseason like ?";
		}else if (searchtype.equals("pmaterial")){			
			query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, providercode, imageid from product where pmaterial like ?";
		}else if (searchtype.equals("category")){
			keyword= keyword.toUpperCase();
			query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, ccategory, providercode, imageid from product join category using(cid) where ccategory like ?";
		}else if (searchtype.equals("providercode")){
			query = "select pid, pname, pprice, psize, pcolor, pmaterial, pseason, cid, providercode, imageid from product where providercode like ?";
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				p = new Product();
				p.setpId(rset.getString("PID"));
				p.setpName(rset.getString("PNAME"));
				p.setpPrice(rset.getInt("PPRICE"));
				p.setpSize(rset.getString("PSIZE"));
				p.setpColor(rset.getString("PCOLOR"));
				p.setpMaterial(rset.getString("PMATERIAL"));
				p.setpSeason(rset.getString("PSEASON"));
				p.setcId(rset.getString("cid"));
				p.setProviderCode(rset.getString("PROVIDERCODE"));
				p.setImageId(rset.getInt("IMAGEID"));
				list.add(p);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	public Product detailViewProduct(Connection con, String pid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String query = "select pname, pseason, pprice, providercode, pmaterial, cid, imageid,psize,pcolor from product where pid = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pid);
			rset = pstmt.executeQuery();
			if(rset.next()){
				p = new Product();
				p.setpName(rset.getString(1));
				p.setpSeason(rset.getString(2));
				p.setpPrice(rset.getInt(3));
				p.setProviderCode(rset.getString(4));
				p.setpMaterial(rset.getString(5));
				p.setcId(rset.getString(6));
				p.setImageId(rset.getInt(7));
				p.setpSize(rset.getString(8));
				p.setpColor(rset.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	public Product selectAll2(Connection con, String pid) {
		Product product = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM PRODUCT WHERE PID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
			
				product = new Product();
				
				product.setpId(rset.getString("PID"));
				product.setpName(rset.getString("PNAME"));
				product.setpPrice(rset.getInt("PPRICE"));
				product.setpSize(rset.getString("PSIZE"));
				product.setpColor(rset.getString("PCOLOR"));
				product.setpMaterial(rset.getString("PMATERIAL"));
				product.setpSeason(rset.getString("PSEASON"));
				product.setcId(rset.getString("CID"));
				product.setProviderCode(rset.getString("PROVIDERCODE"));
				product.setImageId(rset.getInt("IMAGEID"));
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
	
		return product;
	}
	public ProductImage selectMainImage2(Connection con, int imageId) {
		ProductImage pimage = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select mainimage, imageid from productimage where imageid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, imageId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				
			pimage = new ProductImage(rset.getString("mainimage"), rset.getInt("imageid"));
		}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return pimage;
	}
	
	public Product selectOneProduct(Connection con, String pname, String color, String size) {
		Product product = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM PRODUCT WHERE PNAME = ? AND PCOLOR = ? AND PSIZE = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pname);
			pstmt.setString(2, color);
			pstmt.setString(3, size);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
			
				product = new Product();
				
				product.setpId(rset.getString("PID"));
				product.setpName(rset.getString("PNAME"));
				product.setpPrice(rset.getInt("PPRICE"));
				product.setpSize(rset.getString("PSIZE"));
				product.setpColor(rset.getString("PCOLOR"));
				product.setpMaterial(rset.getString("PMATERIAL"));
				product.setpSeason(rset.getString("PSEASON"));
				product.setcId(rset.getString("CID"));
				product.setProviderCode(rset.getString("PROVIDERCODE"));
				product.setImageId(rset.getInt("IMAGEID"));
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
	
		return product;
	}
	public ArrayList<Product> selectProductMidPid(Connection con, String mid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "select * from product where pid in (select pid from orderlist where mid = ?) order by pname";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Product p = new Product();
				
				p.setpId(rset.getString("PID"));
				p.setpName(rset.getString("PNAME"));
				p.setpPrice(rset.getInt("PPRICE"));
				p.setpSize(rset.getString("PSIZE"));
				p.setpColor(rset.getString("PCOLOR"));
				p.setpMaterial(rset.getString("PMATERIAL"));
				p.setpSeason(rset.getString("PSEASON"));
				p.setcId(rset.getString("cid"));
				p.setProviderCode(rset.getString("PROVIDERCODE"));
				p.setImageId(rset.getInt("IMAGEID"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public Product selectProductPid(Connection con, String pid) {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from product where pid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pid);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				p = new Product();
				
				p.setpId(rset.getString("PID"));
				p.setpName(rset.getString("PNAME"));
				p.setpPrice(rset.getInt("PPRICE"));
				p.setpSize(rset.getString("PSIZE"));
				p.setpColor(rset.getString("PCOLOR"));
				p.setpMaterial(rset.getString("PMATERIAL"));
				p.setpSeason(rset.getString("PSEASON"));
				p.setcId(rset.getString("cid"));
				p.setProviderCode(rset.getString("PROVIDERCODE"));
				p.setImageId(rset.getInt("IMAGEID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return p;
	}
}

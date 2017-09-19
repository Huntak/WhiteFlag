package category.model.dao;

import static common.JDBCTemplate.*;

import java.sql.*;
import java.util.ArrayList;

import category.model.vo.Category;
import product.model.vo.*;

public class CategoryDao {
	public ArrayList<Product> selectProductCId(Connection con, String cId) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from product where cid like ? || '%' order by imageid";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cId);
			
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
	
	public ArrayList<ProductImage> selectProductImageCId(Connection con, String cId) {
		ArrayList<ProductImage> list = new ArrayList<ProductImage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from productimage where imageid in (select imageid from product where cid like ? || '%') order by imageid";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ProductImage productImage = new ProductImage();
				
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
				
				list.add(productImage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Product> selectProduct(Connection con, String searchCondition) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from product where pname like '%' || ? || '%' order by imageid";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchCondition);
			
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
				//System.out.println(product);
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

	public ArrayList<ProductImage> selectProductImage(Connection con, String searchCondition) {
		ArrayList<ProductImage> list = new ArrayList<ProductImage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from productimage where imageid in (select imageid from product where pname like '%' || ? || '%') order by imageid";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchCondition);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ProductImage productImage = new ProductImage();
				
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
				
				//System.out.println(productImage);
				list.add(productImage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Product> selectProduct(Connection con, String cId, String searchCondition, int searchPrice1,
			int searchPrice2, String searchOrder) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = null;
			
			if(searchPrice1 == 0 && searchPrice2 == 0 && searchOrder.equals("")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where cid like ? || '%'  "
						+ "and pname like '%' || ? || '%' order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
			}else if(searchPrice1 == 0 && searchPrice2 == 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by providercompany asc, imageid";
				}
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				//pstmt.setString(3, searchOrder);
			}else if(searchPrice1 != 0 && searchPrice2 == 0 && searchOrder.equals("")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where cid like ? || '%'  "
					+ "and pname like '%' || ? || '%' and pprice >= ? order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
			}else if(searchPrice1 != 0 && searchPrice2 == 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by providercompany asc, imageid";
				}
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
				//pstmt.setString(4, searchOrder);
			}else if(searchPrice1 == 0 && searchPrice2 != 0 && searchOrder.equals("")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where cid like ? || '%'  "
					+ "and pname like '%' || ? || '%' and pprice <= ? order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice2);
			}else if(searchPrice1 == 0 && searchPrice2 != 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by providercompany asc, imageid";
				}

				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice2);
				//pstmt.setString(4, searchOrder);
			}else if(searchPrice1 != 0 && searchPrice2 != 0 && searchOrder.equals("")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where cid like ? || '%'  "
					+ "and pname like '%' || ? || '%' and pprice between ? and ? order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
				pstmt.setInt(4, searchPrice2);
			}else if(searchPrice1 != 0 && searchPrice2 != 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from product left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by providercompany asc, imageid";
				}
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
				pstmt.setInt(4, searchPrice2);
				//pstmt.setString(5, searchOrder);
			}
			
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
				//System.out.println("***" + product);
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

	public ArrayList<ProductImage> selectProductImage(Connection con, String cId, String searchCondition,
			int searchPrice1, int searchPrice2, String searchOrder) {
		ArrayList<ProductImage> list = new ArrayList<ProductImage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//String query = "select * from product left join plus using(pid) left join provider using(providercode) where cid like ? || '%' and pname like '%' || ? || '%' and pprice between ? and ? order by ?;";
		//String query = "select * from productimage where imageid in (select imageid from product left join plus using(pid) left join provider using(providercode) where cid like ? || '%' and pname like '%' || ? || '%' and pprice between ? and ? order by ?) order by imageid";
		try {
			
			String query = null;
			
			if(searchPrice1 == 0 && searchPrice2 == 0 && searchOrder.equals("")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
						+ "left join provider using(providercode) where cid like ? || '%'  "
						+ "and pname like '%' || ? || '%' order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
			}else if(searchPrice1 == 0 && searchPrice2 == 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' order by providercompany asc, imageid";
				}
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				//pstmt.setString(3, searchOrder);
			}else if(searchPrice1 != 0 && searchPrice2 == 0 && searchOrder.equals("")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
					+ "left join provider using(providercode) where cid like ? || '%'  "
					+ "and pname like '%' || ? || '%' and pprice >= ? order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
			}else if(searchPrice1 != 0 && searchPrice2 == 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice >= ? order by providercompany asc, imageid";
				}
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
				//pstmt.setString(4, searchOrder);
			}else if(searchPrice1 == 0 && searchPrice2 != 0 && searchOrder.equals("")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
					+ "left join provider using(providercode) where cid like ? || '%'  "
					+ "and pname like '%' || ? || '%' and pprice <= ? order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice2);
			}else if(searchPrice1 == 0 && searchPrice2 != 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice <= ? order by providercompany asc, imageid";
				}
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice2);
				//pstmt.setString(4, searchOrder);
			}else if(searchPrice1 != 0 && searchPrice2 != 0 && searchOrder.equals("")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
					+ "left join provider using(providercode) where cid like ? || '%'  "
					+ "and pname like '%' || ? || '%' and pprice between ? and ? order by imageid";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
				pstmt.setInt(4, searchPrice2);
			}else if(searchPrice1 != 0 && searchPrice2 != 0 && !searchOrder.equals("")){
				if(searchOrder.equals("plusdate desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by plusdate desc, imageid";
				}else if(searchOrder.equals("pname asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pname asc, imageid";
				}else if(searchOrder.equals("pname desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pname desc, imageid";
				}else if(searchOrder.equals("pprice asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pprice asc, imageid";
				}else if(searchOrder.equals("pprice desc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by pprice desc, imageid";
				}else if(searchOrder.equals("providercompany asc")){
					query = "select * from productimage left join product using(imageid) left join plus using(pid) "
							+ "left join provider using(providercode) where cid like ? || '%'  "
							+ "and pname like '%' || ? || '%' and pprice between ? and ? order by providercompany asc, imageid";
				}
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, cId);
				pstmt.setString(2, searchCondition);
				pstmt.setInt(3, searchPrice1);
				pstmt.setInt(4, searchPrice2);
				//pstmt.setString(5, searchOrder);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ProductImage productImage = new ProductImage();
				
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
				
				//System.out.println("^^^" + productImage);
				list.add(productImage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Product> selectProductPName(Connection con, String[] productNameList, String searchOrder) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = null;
			
			if(searchOrder.equals("plusdate desc")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by plusdate desc, imageid";
			}else if(searchOrder.equals("pname asc")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pname asc, imageid";
			}else if(searchOrder.equals("pname desc")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pname desc, imageid";
			}else if(searchOrder.equals("pprice asc")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pprice asc, imageid";
			}else if(searchOrder.equals("pprice desc")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pprice desc, imageid";
			}else if(searchOrder.equals("providercompany asc")){
				query = "select * from product left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by providercompany asc, imageid";
			}
			
			pstmt = con.prepareStatement(query);
			
			for(int i = 1; i <= productNameList.length; i++){
				pstmt.setString(i, productNameList[i - 1]);
			}

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
	
	public ArrayList<ProductImage> selectProductImagePName(Connection con, String[] productNameList, String searchOrder) {
		ArrayList<ProductImage> list = new ArrayList<ProductImage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = null;
			
			if(searchOrder.equals("plusdate desc")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by plusdate desc, imageid";
			}else if(searchOrder.equals("pname asc")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pname asc, imageid";
			}else if(searchOrder.equals("pname desc")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pname desc, imageid";
			}else if(searchOrder.equals("pprice asc")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pprice asc, imageid";
			}else if(searchOrder.equals("pprice desc")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by pprice desc, imageid";
			}else if(searchOrder.equals("providercompany asc")){
				query = "select * from productimage left join product using(imageid) left join plus using(pid) "
						+ "left join provider using(providercode) where pname in (?";
				for(int i = 0; i < productNameList.length - 2; i++)
					query += ",?";
				query += ",?) order by providercompany asc, imageid";
			}
			
			pstmt = con.prepareStatement(query);
			
			for(int i = 1; i <= productNameList.length; i++){
				pstmt.setString(i, productNameList[i - 1]);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ProductImage productImage = new ProductImage();
				
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
				
				list.add(productImage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Category> getCategoryId(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select cid from category";
		ArrayList<Category> categorylistid = new ArrayList<Category>();
		Category c = null;
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				c = new Category();
				c.setcId(rset.getString(1));
				
				categorylistid.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}	
		
		return categorylistid;
	}
}

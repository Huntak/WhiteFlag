package category.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.model.service.CategoryService;
import product.model.vo.Product;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		/*String cId = request.getParameter("cId");
		
		ArrayList<ProductImage> productImageList = new CategoryService().selectProductImageCId(cId);
		ArrayList<Product> productList = new CategoryService().selectProductCId(cId);*/
		
		String searchCondition = request.getParameter("searchCondition");
		ArrayList<ProductImage> productImageList = new ArrayList<ProductImage>();
		ArrayList<Product> productList = new ArrayList<Product>();
		
		/*if(searchCondition != null && !searchCondition.equals("")){
			searchCondition = new String(searchCondition.getBytes("8859_1"), "utf-8");
		}*/
		
		productImageList = new CategoryService().selectProductImage(searchCondition);
		productList = new CategoryService().selectProduct(searchCondition);
		
		
		// 4. 받은 결과에 대한 성공/실패 페이지 내보내기
		RequestDispatcher rd = null;
		
			request.setAttribute("productImageList", productImageList);
			request.setAttribute("productList", productList);
			rd = request.getRequestDispatcher("view/category/search.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

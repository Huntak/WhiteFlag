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
 * Servlet implementation class DetailSearchServlet
 */
@WebServlet("/dsearch")
public class DetailSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		String cId = request.getParameter("cId");
		if(cId == null || cId.equals(""))
			cId = "";
			
		String searchCondition = request.getParameter("searchCondition");
		if(searchCondition == null || searchCondition.equals(""))
			searchCondition = "";
		
		int searchPrice1 = 0;
		if(request.getParameter("searchPrice1") != null && !request.getParameter("searchPrice1").equals(""))
			searchPrice1 = Integer.parseInt(request.getParameter("searchPrice1"));
		
		int searchPrice2 = 0;
		if(request.getParameter("searchPrice2") != null && !request.getParameter("searchPrice2").equals(""))
			searchPrice2 = Integer.parseInt(request.getParameter("searchPrice2"));
		
		String searchOrder = request.getParameter("searchOrder");
		if(searchOrder == null || searchOrder.equals(""))
			searchOrder = "";
		
		ArrayList<ProductImage> productImageList = new ArrayList<ProductImage>();
		ArrayList<Product> productList = new ArrayList<Product>();
		
		/*System.out.println("여기");
		System.out.println(cId);
		System.out.println(searchCondition);
		System.out.println(searchPrice1);
		System.out.println(searchPrice2);
		System.out.println(searchOrder);*/
		
		if(searchCondition != null && !searchCondition.equals("")){
			searchCondition = new String(searchCondition.getBytes("8859_1"), "utf-8");
		}
			productImageList = new CategoryService().selectProductImage(cId, searchCondition, searchPrice1, searchPrice2, searchOrder);
			productList = new CategoryService().selectProduct(cId, searchCondition, searchPrice1, searchPrice2, searchOrder);
		
		
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

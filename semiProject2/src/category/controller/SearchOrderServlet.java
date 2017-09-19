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
 * Servlet implementation class SearchOrderServlet
 */
@WebServlet("/sorder")
public class SearchOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		String[] productNameList = request.getParameterValues("pNameList");
		String searchOrder = request.getParameter("searchOrder");
		if(searchOrder == null || searchOrder.equals(""))
			searchOrder = "";
		
		
		for(int i = 0; i < productNameList.length; i++)
			productNameList[i] = new String(productNameList[i].getBytes("8859_1"), "utf-8");
		
		
		ArrayList<ProductImage> productImageList = new CategoryService().selectProductImagePName(productNameList, searchOrder);
		ArrayList<Product> productList = new CategoryService().selectProductPName(productNameList, searchOrder);

		
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

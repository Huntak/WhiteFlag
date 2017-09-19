package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.model.service.CategoryService;
import category.model.vo.Category;
import product.model.service.ProductService;
import product.model.vo.Product;
import provider.model.service.ProviderService;
import provider.model.vo.Provider;

/**
 * Servlet implementation class ProductSelectOneServlet
 */
@WebServlet("/productselectone")
public class ProductSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		String pName = request.getParameter("productname");
		if(request.getAttribute("productname") !=null)
			pName= (String) request.getAttribute("productname");
		ArrayList<Product> list = new ProductService().selectOneProduct(pName);
		ArrayList<Provider> providercodelist = new ProviderService().providercodeView();
		ArrayList<Category> categoryidlist = new CategoryService().getCategoryId();
		System.out.println(pName);
		RequestDispatcher view = request.getRequestDispatcher("view/manager/productSelectOne.jsp");
		request.setAttribute("list", list);
		request.setAttribute("providercodelist", providercodelist);
		request.setAttribute("categoryidlist", categoryidlist);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

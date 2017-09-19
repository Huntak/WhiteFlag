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
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String cId = request.getParameter("cId");
		
		ArrayList<ProductImage> productImageList = new CategoryService().selectProductImageCId(cId);
		ArrayList<Product> productList = new CategoryService().selectProductCId(cId);
		
		/*for(int i = 0; i < productList.size(); i++){
			System.out.println(productImageList.get(i));
			System.out.println(productList.get(i));
		}*/
		
		String category = cId.substring(0, 1);
		
		// 4. 받은 결과에 대한 성공/실패 페이지 내보내기
		RequestDispatcher rd = null;
		
			request.setAttribute("productImageList", productImageList);
			request.setAttribute("productList", productList);
			request.setAttribute("category", category);
			rd = request.getRequestDispatcher("view/category/category.jsp");
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

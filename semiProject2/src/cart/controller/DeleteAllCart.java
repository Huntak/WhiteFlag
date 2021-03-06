package cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import cart.model.vo.Cart;
import member.model.service.MemberService;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class DeleteAllCart
 */
@WebServlet("/deleteAllcart")
public class DeleteAllCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAllCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] cartid = request.getParameterValues("checkbox");
		String userId = request.getParameter("userid");
	
		for(int i = 0; i<cartid.length; i++){
		int result = new CartService().deleteCart(cartid[i]);
		}
	
		ArrayList<Product> ProdutList = new ProductService().selectAll(userId);
		ArrayList<Cart> CartList = new CartService().selectAll(userId);
		ArrayList<ProductImage> ProductImage = new ProductService().selectMainImage(userId);
		double mileageRate = new MemberService().selectMileage(userId);
		RequestDispatcher view = null;
		
			view = request.getRequestDispatcher("view/cart/cart.jsp");
			request.setAttribute("ProdutList", ProdutList);
			request.setAttribute("CartList", CartList);
			request.setAttribute("ProductImage", ProductImage);
			request.setAttribute("mileageRate", mileageRate);
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

package cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import cart.model.vo.Cart;
import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class SelectOrder
 */
@WebServlet("/order")
public class SelectOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 하나 주문하기
		response.setContentType("text/html; charset=utf-8");
		
		String cartid = request.getParameter("cartid");
		String userId = request.getParameter("userid");
		
		Product product = new ProductService().selectOne(userId, cartid);
		Cart cart = new CartService().selectOne(userId, cartid);

		
		RequestDispatcher view = null;

	
		
		view = request.getRequestDispatcher("view/orderAndPay/OrderAndPay.jsp");
		request.setAttribute("product", product);
		request.setAttribute("cart", cart);
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

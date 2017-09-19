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
 * Servlet implementation class DeleteCart
 */
@WebServlet("/delete")
public class DeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카트에서 삭제하기 버튼 서비스
		String cartid = request.getParameter("cartid");
		String userId = request.getParameter("userid");
	
		int result = new CartService().deleteCart(cartid);
	
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

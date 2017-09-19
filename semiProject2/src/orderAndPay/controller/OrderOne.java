package orderAndPay.controller;

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
import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class OrderOne
 */
@WebServlet("/ordero")
public class OrderOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cartId = request.getParameter("cartid");
		String userId = request.getParameter("userid");
		String mainimage = request.getParameter("mainimage");
		String imageid = request.getParameter("imageid");
		Product Product = new ProductService().selectOne(userId, cartId);
		Cart Cart = new CartService().selectOne(userId, cartId);
		double mileageRate = new MemberService().selectMileage(userId);
		ArrayList<ProductImage> ProductImage = new ProductService().selectMainImage(userId);
		Member member = new MemberService().selectOneMember(userId);
		
		RequestDispatcher view = null;

		view = request.getRequestDispatcher("view/orderAndPay/OrderAndPay.jsp");
		request.setAttribute("Product", Product);
		request.setAttribute("Cart", Cart);
		request.setAttribute("ProductImage", ProductImage);
		request.setAttribute("mainimage", mainimage);
		request.setAttribute("imageid", imageid);
		request.setAttribute("mileageRate", mileageRate);
		request.setAttribute("member", member);
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

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
 * Servlet implementation class OrderAllServlet
 */
@WebServlet("/orderall")
public class OrderAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		
		Member member = new MemberService().selectOneMember(mid);
		ArrayList<Product> pl = new ProductService().selectAll(mid);
		ArrayList<Cart> cl = new CartService().selectAll(mid);
		ArrayList<ProductImage> pimage = new ProductService().selectMainImage(mid);
		double mileageRate = new MemberService().selectMileage(mid);
		
		RequestDispatcher view = null;

		view = request.getRequestDispatcher("view/orderAndPay/orderAll.jsp");
		request.setAttribute("pl", pl);
		request.setAttribute("cl", cl);
		request.setAttribute("pimage", pimage);
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

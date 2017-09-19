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
 * Servlet implementation class SelectOrderServlet
 */
@WebServlet("/selectorder")
public class SelectOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] cartid = request.getParameterValues("checkbox"); 
		String mid = request.getParameter("mid");
		
		Member member = new MemberService().selectOneMember(mid);
		ArrayList<Cart> cl = new ArrayList<Cart>();
		ArrayList<Product> pl = new ArrayList<Product>();
		ArrayList<ProductImage> pimage = new ArrayList<ProductImage>();
		double mileageRate = new MemberService().selectMileage(mid);
		
		for(int i = 0; i<cartid.length; i++){
			cl.add(new CartService().selectAll2(cartid[i]));
			pl.add(new ProductService().selectAll2(cl.get(i).getpId()));
			pimage.add(new ProductService().selectMainImage2(pl.get(i).getImageId()));
		
		}
		
		RequestDispatcher view = null;
		
		view = request.getRequestDispatcher("view/orderAndPay/orderAll.jsp");
		request.setAttribute("pl", pl);
		request.setAttribute("cl", cl);
		request.setAttribute("pimage", pimage);
		request.setAttribute("mileageRate", mileageRate);
		request.setAttribute("member", member);
		view.forward(request, response);
		
	}
		
}

package orderAndPay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class OrderFromDetail
 */
@WebServlet("/orderDetail")
public class OrderFromDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderFromDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String userid = request.getParameter("userid");
		String pname = request.getParameter("pname");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int imageid = Integer.parseInt(request.getParameter("imageid"));
		
		Member member = new MemberService().selectOneMember(userid);
		Product product = new ProductService().selectOneProduct(pname, color, size);
		ProductImage pimage = new ProductService().selectProductImageImageId(imageid);
		float mileageRate = (float) new MemberService().selectMileage(userid);
		
		int mileage = Math.round(mileageRate * product.getpPrice());
		
		
		
		RequestDispatcher view = null;

		view = request.getRequestDispatcher("view/orderAndPay/orderFromDetail.jsp");
		request.setAttribute("userid", userid);
		request.setAttribute("product", product);
		request.setAttribute("pimage", pimage);
		request.setAttribute("quantity", quantity);
		request.setAttribute("mileage", mileage);
		request.setAttribute("member", member);
		view.forward(request, response);
		
	}

}

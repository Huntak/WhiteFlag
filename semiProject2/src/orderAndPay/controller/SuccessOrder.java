package orderAndPay.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import member.model.service.MemberService;
import member.model.vo.Member;
import orderAndPay.model.service.OrderListService;
import orderAndPay.model.vo.OrderList;
import product.model.service.ProductService;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class SuccessOrder
 */
@WebServlet("/success")
public class SuccessOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("하나 주문 성공 서블릿");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String pid = request.getParameter("pid");
		String pname = request.getParameter("pname");
		String psize = request.getParameter("psize");
		String pcolor = request.getParameter("pcolor");
		String quantity = request.getParameter("quantity");
		String mid = request.getParameter("mid");
		String mainimage = request.getParameter("mainimage");
		int imageid = Integer.parseInt(request.getParameter("imageid"));
		
		OrderList orderlist = new OrderListService().selectOidTotalPrice(mid);
		
	
		/*Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String odate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderlist.getODATE()));*/
        
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("view/orderAndPay/successOrder.jsp");
		request.setAttribute("mainimage", mainimage);
		request.setAttribute("pname", pname);
		request.setAttribute("psize", psize);
		request.setAttribute("pcolor", pcolor);
		request.setAttribute("quantity", quantity);
		request.setAttribute("orderlist", orderlist);
		//request.setAttribute("odate", odate);
		request.setAttribute("imageid", imageid);
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

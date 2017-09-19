package orderAndPay.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import cart.model.vo.Cart;
import orderAndPay.model.service.OrderListService;
import orderAndPay.model.vo.OrderList;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class SuccessAllOrder
 */
@WebServlet("/successAll")
public class SuccessAllOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessAllOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String mid = request.getParameter("mid");
		System.out.println(mid);
		ArrayList<OrderList> orderlist = new OrderListService().selectOrderList2(mid);
		System.out.println(orderlist.size());
		ArrayList<Product> plist = new ArrayList<Product>();
		ArrayList<ProductImage> pimage = new ArrayList<ProductImage>();
		
		for(int i = 0; i<orderlist.size(); i++){
			System.out.println(orderlist.get(i));
			plist.add(new ProductService().selectAll2(orderlist.get(i).getPID()));
			pimage.add(new ProductService().selectMainImage2(plist.get(i).getImageId()));
		}
		
		
		/*Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String odate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderlist.get(0).getODATE()));*/
		
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("view/orderAndPay/successOrderAll.jsp");
		request.setAttribute("plist", plist);
		request.setAttribute("pimage", pimage);
		request.setAttribute("orderlist", orderlist);
		//request.setAttribute("odate", odate);
		view.forward(request, response);
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
}

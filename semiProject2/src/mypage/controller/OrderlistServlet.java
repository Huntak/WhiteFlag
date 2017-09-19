package mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orderAndPay.model.service.OrderListService;
import orderAndPay.model.vo.OrderList;
import orderstatus.model.service.OrderStatusService;
import orderstatus.model.vo.OrderStatus;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class OrderlistServlet
 */
@WebServlet("/olist")
public class OrderlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		String userid = request.getParameter("mid");
		
		ArrayList<OrderList> olist = new OrderListService().selectOrderList(userid);
		ArrayList<Product> plist = new ArrayList<Product>();
		ArrayList<ProductImage> pimage = new ArrayList<ProductImage>();
		ArrayList<OrderStatus> ostatus = new ArrayList<OrderStatus>();
		for(int i= 0; i<olist.size(); i++){
			plist.add(new ProductService().detailViewProduct(olist.get(i).getPID()));
			ostatus.add(new OrderStatusService().selectOrderStatus(olist.get(i).getOSNUMBER()));
			pimage.add(new ProductService().selectMainImage2(plist.get(i).getImageId()));
		}
			
		RequestDispatcher view = null;
			
		view = request.getRequestDispatcher("view/myPage/orderList.jsp");
		request.setAttribute("olist", olist);
		request.setAttribute("plist", plist);
		request.setAttribute("pimage", pimage);
		request.setAttribute("ostatus", ostatus);
		request.setAttribute("userid", userid);
		view.forward(request, response);
	}

}

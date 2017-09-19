package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import orderAndPay.model.service.OrderListService;
import orderAndPay.model.vo.OrderList;
import orderstatus.model.service.OrderStatusService;
import orderstatus.model.vo.OrderStatus;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;

/**
 * Servlet implementation class CancleOrder
 */
@WebServlet("/cancleorder")
public class CancleOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancleOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String oid = request.getParameter("oid");
		String osnumber = "";
		String check = request.getParameter("check");
		
		if(check.equals("cancle")){
			osnumber = "8";
		}else if(check.equals("Completion")){
			osnumber = "7";
		}else if(check.equals("back")){
			osnumber = "10";
		}else if(check.equals("cback")){
			osnumber = "5";
		}
		
		
		OrderList orderlist = new OrderListService().OrderListDetailView(oid);
		
		
		
		
			int update = new OrderListService().updateOsnumber(oid, osnumber);
		
		
		
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
		/*OrderStatus ostatus = new OrderStatusService().selectOrderStatus(orderlist.getOSNUMBER());
		
		JSONObject list = new JSONObject();
		list.put("ostatus", ostatus.getOsstatus());
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(list.toString());*/

	}

}

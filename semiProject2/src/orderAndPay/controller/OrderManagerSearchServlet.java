package orderAndPay.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orderAndPay.model.service.OrderListService;
import orderAndPay.model.vo.OrderList;

/**
 * Servlet implementation class OrderManagerSearchServlet
 */
@WebServlet("/omsearch")
public class OrderManagerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderManagerSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		String orderSearch = request.getParameter("orderSearch");
		Date beforeDate = null;
		Date afterDate = null;
		if(orderSearch.equals("orderdate")){
		beforeDate = Date.valueOf(request.getParameter("beforeDate"));
		afterDate = Date.valueOf(request.getParameter("afterDate"));
		}
		
		ArrayList<OrderList> list = new OrderListService().searchOrderList(keyword,orderSearch,beforeDate,afterDate);
		
		RequestDispatcher view = request.getRequestDispatcher("view/manager/orderManager.jsp");
		request.setAttribute("list", list);
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

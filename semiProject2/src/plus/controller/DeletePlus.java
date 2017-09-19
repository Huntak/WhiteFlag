package plus.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import plus.model.service.PlusService;
import plus.model.vo.Plus;
import stock.model.service.StockService;
import stock.model.vo.Stock;

/**
 * Servlet implementation class DeletePlus
 */
@WebServlet("/deleteplusselect")
public class DeletePlus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePlus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입고 재고 페이지 리스트뷰
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		ArrayList<Plus> plusList = new PlusService().selectPlusList();
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("view/manager/deletePlus.jsp");
		request.setAttribute("plusList", plusList);
		view.forward(request, response);
	}

}

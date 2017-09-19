package qanda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class QnaToWrite
 */
@WebServlet("/qnaToWrite")
public class QnaToWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaToWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("hext/html; charset=utf-8");
		
		HttpSession session = request.getSession(false);
		Member loginUser = (Member)session.getAttribute("loginUser");
		ArrayList<Product> productList = new ProductService().selectProductMidPid(loginUser.getMID());

		// 4. 받은 결과에 대한 성공/실패 페이지 내보내기
		RequestDispatcher rd = null;
		/*if(productList.size() > 0 && productImage != null){*/
			rd = request.getRequestDispatcher("view/qnaBoard/qnaWr.jsp");
			request.setAttribute("productList", productList);
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

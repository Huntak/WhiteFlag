package mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.Review;
import qanda.model.service.QnaService;
import qanda.model.vo.Qna;

/**
 * Servlet implementation class MyReviewListServlet
 */
@WebServlet("/myList")
public class MyReviewQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewQnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf8");
		String userId = request.getParameter("userId");
		ArrayList<Review>rlist = new ReviewService().selectListMine(userId);
		ArrayList<Qna>qlist = new QnaService().selectListMine(userId);
		RequestDispatcher view = null;
		view =request.getRequestDispatcher("view/myPage/myBoard.jsp");
			if(rlist != null){
			request.setAttribute("rlist", rlist);
			}		
			if(qlist != null){
			request.setAttribute("qlist", qlist);
			}				
			if(rlist== null && qlist ==null){
				response.sendRedirect("/semi/view/myPage/myPage.jsp");
			}
			view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package review.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.Review;


/**
 * Servlet implementation class UpdateFormServlet
 */
@WebServlet("/rupform")
public class ReviewUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// detailV에 있는 값들을 servlet으로 부름
		int Rnumber = Integer.parseInt(request.getParameter("rnumber"));
		
		Review review = new ReviewService().selectOne(Rnumber);
		
		RequestDispatcher view = null;
			//detail의 값들을 reviewUpdate로 값들을 보냄
			view = request.getRequestDispatcher("view/reviewBoard/reviewUpdate.jsp");
			request.setAttribute("review", review);
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

package reviewreply.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import qanda.model.service.QnaService;
import review.model.service.ReviewService;
import reviewreply.model.service.ReviewReplyService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/redel")
public class ReviewReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 삭제 서버시 처리용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
	   System.out.println("777" + request.getParameter("rrnumber"));
		int rrnumber = Integer.parseInt(request.getParameter("rrnumber"));
		int rnumber = Integer.parseInt(request.getParameter("rnumber"));
		
		int result = new ReviewReplyService().deleteReviewReply(rrnumber, rnumber);
		
			
			RequestDispatcher view = null;
				view = request.getRequestDispatcher("rdetail");
				request.setAttribute("rnumber", rnumber);
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

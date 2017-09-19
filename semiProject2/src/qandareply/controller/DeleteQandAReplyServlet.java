package qandareply.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qandareply.model.service.QandAReplyService;
import reviewreply.model.service.ReviewReplyService;

/**
 * Servlet implementation class DeleteQandAReplyServlet
 */
@WebServlet("/delqnare")
public class DeleteQandAReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQandAReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		int qrnumber = Integer.parseInt(request.getParameter("qrnumber"));
		int qnumber = Integer.parseInt(request.getParameter("qnumber"));
		
		int result = new QandAReplyService().deleteQandAReply(qrnumber, qnumber);
		
			
			RequestDispatcher view = null;
				view = request.getRequestDispatcher("qdetail");
				request.setAttribute("qnumber", qnumber);
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

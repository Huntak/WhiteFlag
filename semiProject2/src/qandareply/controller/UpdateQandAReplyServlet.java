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
 * Servlet implementation class UpdateQandAReplyServlet
 */
@WebServlet("/updateqre")
public class UpdateQandAReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQandAReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		int qrnumber = Integer.parseInt(request.getParameter("qrnumber"));
		String qrcontent = request.getParameter("qrcontent2");
		if(qrcontent != null && !qrcontent.equals("")){
			qrcontent = new String(qrcontent.getBytes("8859_1"), "utf-8");
		}
		int qnumber = Integer.parseInt(request.getParameter("qnumber"));
		int result = new QandAReplyService().updateReply(qrnumber, qrcontent);
		RequestDispatcher view = request.getRequestDispatcher("qdetail");
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

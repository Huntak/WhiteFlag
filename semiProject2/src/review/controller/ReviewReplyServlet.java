package review.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import review.model.service.ReviewService;
import review.model.vo.Review;
import reviewreply.model.vo.*;
import stock.model.vo.Stock;
import reviewreply.model.service.*;
import reviewreply.model.dao.*;

/**
 * Servlet implementation class ReviewReplyServlet
 */
@WebServlet("/rreply")
public class ReviewReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 request.setCharacterEncoding("utf-8");
	 
	 int rnumber = Integer.parseInt(request.getParameter("rnumber"));
	
	 String mid = request.getParameter("mid");
	 String rrcontent = request.getParameter("rrcontent");
	 
	 
	 ReviewReply rr = new ReviewReply(rnumber, rrcontent, mid);
	 
	
	 int result = new ReviewReplyService().insertReply(rr);
	 
	 rr= new ReviewReplyService().selectReply(rnumber, rrcontent, mid);
			System.out.println("+" + rr);
			JSONObject j = new JSONObject();
			j.put("mid", rr.getMID());
			
			j.put("rrcontent", rr.getRRCONTENT());
			j.put("rrdate",rr.getRRDATE());
			
		response.setContentType("application/json; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.print(j);
		out.flush();
		out.close();
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

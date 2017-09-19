package qanda.controller;

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

import qandareply.model.service.QandAReplyService;
import qandareply.model.vo.QandAReply;
import review.model.service.ReviewService;
import review.model.vo.Review;
import reviewreply.model.vo.*;
import stock.model.vo.Stock;
import reviewreply.model.service.*;
import reviewreply.model.dao.*;

/**
 * Servlet implementation class ReviewReplyServlet
 */
@WebServlet("/qreply")
public class QandAReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QandAReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 request.setCharacterEncoding("utf-8");
	 
	 int qnumber = Integer.parseInt(request.getParameter("qnumber"));
	
	 String mid = request.getParameter("mid");
	 String qrcontent = request.getParameter("qrcontent");
	 
	 
	 QandAReply qq = new QandAReply(qnumber, qrcontent, mid);
	 
	
	 int result = new QandAReplyService().insertReply(qq);
	 
	 qq = new QandAReplyService().selectReply(qnumber, qrcontent, mid);
			System.out.println(qq);
			JSONObject j = new JSONObject();
			j.put("mid", qq.getMID());
			
			j.put("qrcontent", qq.getQRCONTENT());
			j.put("qrdate",qq.getQRDATE());
			
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

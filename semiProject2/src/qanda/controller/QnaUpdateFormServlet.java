package qanda.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qanda.model.service.QnaService;
import qanda.model.vo.Qna;


/**
 * Servlet implementation class UpdateFormServlet
 */
@WebServlet("/qupform")
public class QnaUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// detailV에 있는 값들을 servlet으로 부름
		int Qnumber = Integer.parseInt(request.getParameter("qnumber"));
		
		Qna qna = new QnaService().selectOne(Qnumber);
		
		RequestDispatcher view = null;
			//detail의 값들을 qnaUpdate로 값들을 보냄
			view = request.getRequestDispatcher("view/qnaBoard/qnaUpdate.jsp");
			request.setAttribute("qna", qna);
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

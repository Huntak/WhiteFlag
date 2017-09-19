package qanda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;
import qanda.model.service.QnaService;
import qanda.model.vo.Qna;
import qandareply.model.service.QandAReplyService;
import qandareply.model.vo.QandAReply;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/qdetail")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		int qnaNo = Integer.parseInt(request.getParameter("qnumber"));
		

		//조회수 1증가처리
		QnaService qservice = new QnaService();
		int result = qservice.updateReadCount(qnaNo);
		
		//글번호로 공지글 조회
		Qna qna = new QnaService().selectOne(qnaNo);
		Product product = new Product();
		ProductImage productimage = new ProductImage();
		if(qna.getPid() != null){
			product = new ProductService().selectProductPid(qna.getPid());
			productimage = new ProductService().selectProductImageImageId(product.getImageId());
		}
		ArrayList<QandAReply> qq = new QandAReplyService().selectReply(qnaNo);
		
		RequestDispatcher view = null;
		if(qna != null){
			view = request.getRequestDispatcher("view/qnaBoard/qnaDetailV.jsp");
			request.setAttribute("qna", qna);
			request.setAttribute("product", product);
			request.setAttribute("productimage", productimage);
			request.setAttribute("qrlist", qq);
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

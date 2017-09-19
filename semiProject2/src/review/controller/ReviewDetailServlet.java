package review.controller;

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
import review.model.service.ReviewService;
import review.model.vo.Review;
import reviewreply.model.service.ReviewReplyService;
import reviewreply.model.vo.ReviewReply;

/**
 * Servlet implementation class ReviewDetailServlet
 */
@WebServlet("/rdetail")
public class ReviewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		int reviewNo = Integer.parseInt(request.getParameter("rnumber"));
		

		//조회수 1증가처리
		ReviewService rservice = new ReviewService();
		int result = rservice.updateReadCount(reviewNo);
		
		//글번호로 공지글 조회
		Review review = new ReviewService().selectOne(reviewNo);
		Product product = new Product();
		ProductImage productimage = new ProductImage();
		if(review.getpId() !=null){
			product = new ProductService().selectProductPid(review.getpId());
			productimage = new ProductService().selectProductImageImageId(product.getImageId());
		}
		
		ArrayList<ReviewReply> rr = new ReviewReplyService().selectReply(reviewNo);
		
		RequestDispatcher view = null;
		if(review != null){
			view = request.getRequestDispatcher("view/reviewBoard/reviewDetailV.jsp");
			request.setAttribute("review", review);
			request.setAttribute("product", product);
			request.setAttribute("productimage", productimage);
			request.setAttribute("rrlist", rr);
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

package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;
import qanda.model.service.QnaService;
import qanda.model.vo.Qna;
import review.model.service.ReviewService;
import review.model.vo.Review;
import stock.model.service.StockService;
import stock.model.vo.Stock;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/detail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("hext/html; charset=utf-8");
		
		int imageId = 0;
		if(request.getParameter("imageId") != null)
			imageId = Integer.parseInt(request.getParameter("imageId"));

		//System.out.println("ima" + imageId);
		ArrayList<Product> productList = new ProductService().selectProductImageId(imageId);
		ProductImage productImage = new ProductService().selectProductImageImageId(imageId);
		
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		for(int i = 0; i < productList.size(); i++){
			stockList.add(new StockService().selectStockPid(productList.get(i).getpId()));
			/*System.out.println("p." + productList.get(i).getpId());
			System.out.println("s." + stockList.get(i).getpId());*/
		}
		
		HttpSession session = request.getSession(false);
		Member loginUser = null;
		double mileageRate = 0;
		loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser != null)
			mileageRate = new MemberService().selectMileage(loginUser.getMID());
		
		//System.out.println("ima" + imageId);
		int listCountR = new ReviewService().getListCountImageId(imageId);
		int listCountQ = new QnaService().getListCountImageId(imageId);

		
		// 4. 받은 결과에 대한 성공/실패 페이지 내보내기
		RequestDispatcher rd = null;
		/*if(productList.size() > 0 && productImage != null){*/
			rd = request.getRequestDispatcher("view/category/detail.jsp");
			
			request.setAttribute("productList", productList);
			request.setAttribute("stockList", stockList);
			request.setAttribute("productImage", productImage);
			request.setAttribute("mileageRate", mileageRate);
			request.setAttribute("listCountR", listCountR);
			request.setAttribute("listCountQ", listCountQ);
			
			rd.forward(request, response);
		/*}else {
			request.setAttribute("message", "product정보, productImage정보 조회 실패");
			rd = request.getRequestDispatcher("view/error.jsp");
			rd.forward(request, response);
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

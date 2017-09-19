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

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/rsearch")
public class ReviewSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 제목/작성자로 검색 처리하는 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		ReviewService qservice = new ReviewService();
		int currentPage = 1;
		int limit = 15;		// 한 페이지에 10개씩 출력
		String keyword = request.getParameter("keyword");
		String selectedItem = request.getParameter("selectedItem");
		
		System.out.println("keyword " + keyword + "selectedItem " + selectedItem);
		ArrayList<Review> list2 = null;
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		int listCount = 0;
		if(keyword == null){
			listCount = qservice.getListCount();
		}else {
			if(selectedItem.equals("title")){
				// 제목으로 검색어 선택된 경우
				listCount = qservice.getListCountTitle(keyword);
				list2 = qservice.selectTitleList(keyword, currentPage, limit);
			} else if(selectedItem.equals("mid")){
				// 작성자로 검색이 선택된 경우
				listCount = qservice.getListCountWriter(keyword);
				list2 = qservice.selectWriterList(keyword, currentPage, limit);
				System.out.println("아이디로 검색 : "+list2.size());
			}else if(selectedItem.equals("content")){
				listCount = qservice.getListCountContent(keyword);
				list2 = qservice.selectContentList(keyword, currentPage, limit);
			}else if(selectedItem.equals("number")){
				listCount = qservice.getListCountNumber(keyword);
				list2 = qservice.selectNumberList(keyword, currentPage, limit);
			}else if(selectedItem.equals("pid")){
				listCount = qservice.getListCountPid(keyword);
				list2 = qservice.selectPidList(keyword, currentPage, limit);
			}
		}
		ArrayList<Product> listP = new ArrayList<Product>();
		ArrayList<ProductImage> listPi = new ArrayList<ProductImage>();
		
		Product p = null;
		ProductImage pi = null;
		for(int i = 0; i < list2.size(); i++){
			if(list2.get(i).getpId() !=null){
				p = new ProductService().selectProductPid(list2.get(i).getpId());
				listP.add(p);
				pi = new ProductService().selectProductImageImageId(listP.get(i).getImageId());
				listPi.add(pi);
			}else{
				p = new Product();
				listP.add(p);
				pi = new ProductImage();
				listPi.add(pi);
			}
		}
				// 총 페이지 수 계산 : 목록이 최소 1개일 때 1page로 처리
				// 0.9를 더함
				int maxPage = (int)((double)listCount / limit +0.9);
				// 현재 페이지에 보여질 시작 페이지 값 (1, 16, 32, 48, ...)
				int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
				// 현재 페이지에 보여질 마지막 페이지 값 (10, 20, 30, ...)
				int endPage =  startPage + limit - 1;
				
				if(maxPage < endPage)
					endPage = maxPage;
		System.out.println(keyword);
		System.out.println("list2 " + list2);
		RequestDispatcher view = null;
		
			view = request.getRequestDispatcher("view/reviewBoard/review.jsp");
			 request.setAttribute("list", list2);
			 request.setAttribute("listP", listP);
			 request.setAttribute("listPi", listPi);
			 request.setAttribute("listCount", new Integer(listCount));
			 request.setAttribute("currentPage",currentPage);
			 request.setAttribute("maxPage", maxPage);
			 request.setAttribute("startPage", startPage);
			 request.setAttribute("endPage", endPage);
			 request.setAttribute("keyword", keyword);
			 request.setAttribute("selectedItem", selectedItem);
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

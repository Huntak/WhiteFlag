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
import qanda.model.vo.Qna;
import qanda.model.service.QnaService;

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/qsearch")
public class QnaSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaSearchServlet() {
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
		QnaService qservice = new QnaService();
		int currentPage = 1;
		int limit = 15;		// 한 페이지에 10개씩 출력
		String keyword = request.getParameter("keyword");
		String selectedItem = request.getParameter("selectedItem");
		System.out.println("서취서블릿 실행 : " + "keyword : " + keyword + " selectedItem : " +selectedItem);
		
		ArrayList<Qna> list2 = null;
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
			}else if(selectedItem.equals("content")){
				listCount = qservice.getListCountContent(keyword);
				list2 = qservice.selectContentList(keyword, currentPage, limit);
			}else if(selectedItem.equals("number")){
				listCount = qservice.getListCountNumber(keyword);
				System.out.println("리스트카운트 : " + listCount);
				list2 = qservice.selectNumberList(keyword, currentPage, limit);
			}
		}

		ArrayList<Product> listP = new ArrayList<Product>();
		ArrayList<ProductImage> listPi = new ArrayList<ProductImage>();
		
		Product p = null;
		ProductImage pi = null;
		for(int i = 0; i < list2.size(); i++){
			if(list2.get(i).getPid() !=null){
				p = new ProductService().selectProductPid(list2.get(i).getPid());
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
		
		RequestDispatcher view = null;
		
			view = request.getRequestDispatcher("view/qnaBoard/qna.jsp");
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

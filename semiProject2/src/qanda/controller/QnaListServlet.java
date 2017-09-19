package qanda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductImage;
import qanda.model.service.QnaService;
import qanda.model.vo.Qna;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/qlist")
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 전체 조회용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		
		QnaService qservice = new QnaService();
		
		int currentPage = 1;
		int limit = 15;		// 한 페이지에 10개씩 출력
		
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		int listCount = qservice.getListCount();
		
		ArrayList<Qna> list = qservice.selectList(currentPage, limit);
		
		
		ArrayList<Product> listP = new ArrayList<Product>();
		ArrayList<ProductImage> listPi = new ArrayList<ProductImage>();
		
		Product p = null;
		ProductImage pi = null;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getPid() !=null){
				p = new ProductService().selectProductPid(list.get(i).getPid());
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
		int maxPage = (int)((double)listCount / limit + 1.4);
		// 현재 페이지에 보여질 시작 페이지 값 (1, 11, 21, 31, ...)
		int startPage = (((int)((double)currentPage / limit + 1.4)) - 1) * limit + 1;
		// 현재 페이지에 보여질 마지막 페이지 값 (10, 20, 30, ...)
		int endPage =  startPage + limit - 1;
		
		if(maxPage < endPage)
			endPage = maxPage;
	RequestDispatcher view = null;
		
			 view = request.getRequestDispatcher("view/qnaBoard/qna.jsp");
			 request.setAttribute("list", list);
			 request.setAttribute("listP", listP);
			 request.setAttribute("listPi", listPi);
			 request.setAttribute("listCount", new Integer(listCount));
			 request.setAttribute("currentPage",currentPage);
			 request.setAttribute("maxPage", maxPage);
			 request.setAttribute("startPage", startPage);
			 request.setAttribute("endPage", endPage);
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

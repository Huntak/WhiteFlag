package notice.controller;

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

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/nlist")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 전체 조회용 컨트롤러
		System.out.println("노티스리스트서블릿 실행");
		response.setContentType("text/html; charset=utf-8");
		
		NoticeService nservice  = new NoticeService();

		int currentPage = 1;
		int limit = 10;		// 한 페이지에 10개씩 출력
		
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		int listCount = nservice.getListCount();
		
		ArrayList<Notice> list = nservice.selectList(currentPage, limit);
		
		// 총 페이지 수 계산 : 목록이 최소 1개일 때 1page로 처리
		// 0.9를 더함
		int maxPage = (int)((double)listCount / limit + 0.9);
		// 현재 페이지에 보여질 시작 페이지 값 (1, 11, 21, 31, ...)
		int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
		// 현재 페이지에 보여질 마지막 페이지 값 (10, 20, 30, ...)
		int endPage =  startPage + limit - 1;
		
		if(maxPage < endPage)
			endPage = maxPage;
	RequestDispatcher view = null;
	
		if(list.size() >= 0){
			 view = request.getRequestDispatcher("view/noticeBoard/notice.jsp");
			 request.setAttribute("list", list);
			 request.setAttribute("listCount", new Integer(listCount));
			 request.setAttribute("currentPage",currentPage);
			 request.setAttribute("maxPage", maxPage);
			 request.setAttribute("startPage", startPage);
			 request.setAttribute("endPage", endPage);
			 view.forward(request, response);
		} else{
			//값이 없을때
			 view = request.getRequestDispatcher("view/noticeBoard/noticeDetailV.jsp");
			 request.setAttribute("list", list);
			 request.setAttribute("listCount", new Integer(listCount));
			 request.setAttribute("currentPage",currentPage);
			 request.setAttribute("maxPage", maxPage);
			 request.setAttribute("startPage", startPage);
			 request.setAttribute("endPage", endPage);
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

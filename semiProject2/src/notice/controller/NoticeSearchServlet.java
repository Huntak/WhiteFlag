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
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/nsearch")
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 제목/작성자로 검색 처리하는 컨트롤러
		System.out.println("노티스서취서블릿 실행");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		NoticeService qservice = new NoticeService();
		int currentPage = 1;
		int limit = 10;		// 한 페이지에 10개씩 출력
		String keyword = request.getParameter("keyword");
		String selectedItem = request.getParameter("selectedItem");
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		System.out.println("커런트페이지 : " +currentPage);
		System.out.println("셀렉트 아이템 : " + selectedItem);
		System.out.println("키워드 : " + keyword);
		int listCount = 0;
		if(keyword == null){
			listCount = qservice.getListCount();
		}else {
			if(selectedItem.equals("title")){
				// 제목으로 검색어 선택된 경우
				listCount = qservice.getListCountTitle(keyword);
			} else if(selectedItem.equals("content")){				// 작성자로 검색이 선택된 경우
				
				listCount = qservice.getListCountWriter(keyword);
			}
		}
		ArrayList<Notice> list2 = null;
		
				// 총 페이지 수 계산 : 목록이 최소 1개일 때 1page로 처리
				// 0.9를 더함
				int maxPage = (int)((double)listCount / limit + 0.9);
				// 현재 페이지에 보여질 시작 페이지 값 (1, 16, 32, 48, ...)
				int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
				// 현재 페이지에 보여질 마지막 페이지 값 (10, 20, 30, ...)
				int endPage =  startPage + limit - 1;
				
				if(maxPage < endPage)
					endPage = maxPage;
		
		if(keyword == null){
			list2 = new NoticeService().selectList(currentPage, limit);
		}else {
			if(selectedItem.equals("title")){
				// 제목으로 검색어 선택된 경우
				list2 = new NoticeService().selectTitleList(keyword, currentPage, limit);
			} else if(selectedItem.equals("content")){
				// 작성자로 검색이 선택된 경우
				list2 = new NoticeService().selectWriterList(keyword, currentPage, limit);
			} 
		}
		
	
		RequestDispatcher view = null;
		
			view = request.getRequestDispatcher("view/noticeBoard/notice.jsp");
			 request.setAttribute("list2", list2);
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

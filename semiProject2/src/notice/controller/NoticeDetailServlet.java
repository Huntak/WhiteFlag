package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/ndetail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 상세조회 서비스용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		int noticeNo = Integer.parseInt(request.getParameter("nnumber"));
		
		//조회수 1증가처리
		NoticeService nservice = new NoticeService();
		int result = nservice.updateReadCount(noticeNo);
		
		//글번호로 공지글 조회
		Notice notice = new NoticeService().selectOne(noticeNo);
		
		RequestDispatcher view = null;
		if(notice != null){
			view = request.getRequestDispatcher("view/noticeBoard/noticeDetailV.jsp");
			request.setAttribute("notice", notice);
			view.forward(request, response);
			
		}else{
				view = request.getRequestDispatcher("view/noticeBoard/noticeError.jsp");
				request.setAttribute("message", "공지글 상세조회 실패");
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

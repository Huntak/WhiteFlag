package notice.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import qanda.model.service.QnaService;
import qanda.model.vo.Qna;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/nupd")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
		  
		      int limitSize = 1024 * 1024 * 20;
		   
		  	  String savePath = request.getSession().getServletContext().getRealPath("/noticeUploadFiles/");
		      //System.out.println(savePath);
		      
		  	 String nimage = null;
		      long currentTime = System.currentTimeMillis();

		      SimpleDateFormat formatFileName = new SimpleDateFormat("yyyyMMddHHmmss");

		      MultipartRequest mrequest = new MultipartRequest(request, savePath, limitSize, 
                      "utf-8", new DefaultFileRenamePolicy());

		      if(mrequest.getFilesystemName("nimage") != null){
		    	  nimage = mrequest.getFilesystemName("nimage");
		      }else{
		    	  nimage = mrequest.getParameter("nimage");
		      }
	      String ntitle = mrequest.getParameter("ntitle");
	      String ncontent = mrequest.getParameter("ncontent");
	      int Nnumber = Integer.parseInt(mrequest.getParameter("nnumber"));
	      
	      System.out.println("nimage " + nimage);
	      System.out.println("ntitle " + ntitle);
	      System.out.println("ncontent " + ncontent);
	      
	      int result = new NoticeService().NoticeUpdate(ntitle,ncontent,nimage,Nnumber );
	 
	      if(result > 0){
	         response.sendRedirect("/semi/nlist");
	      } else{
	         RequestDispatcher view = request.getRequestDispatcher("view/noticeBoard/noticeError.jsp");
	         request.setAttribute("message", "이벤트수정 실패");
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

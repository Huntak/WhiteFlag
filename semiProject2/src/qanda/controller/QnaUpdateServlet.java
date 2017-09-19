package qanda.controller;

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

import qanda.model.service.QnaService;
import qanda.model.vo.Qna;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/qupd")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
		  
		      int limitSize = 1024 * 1024 * 20;
		   
		  	  String savePath = request.getSession().getServletContext().getRealPath("/qnaUploadFiles/");
		      //System.out.println(savePath);
		      
		  	 String qimage = null;
		      long currentTime = System.currentTimeMillis();

		      SimpleDateFormat formatFileName = new SimpleDateFormat("yyyyMMddHHmmss");

		      MultipartRequest mrequest = new MultipartRequest(request, savePath, limitSize, 
                      "utf-8", new DefaultFileRenamePolicy());

		      if(mrequest.getFilesystemName("qimage") != null){
		    	  qimage = mrequest.getFilesystemName("qimage");
		      }else{
		    	  qimage = mrequest.getParameter("qimage");
		      }
	      String qtitle = mrequest.getParameter("qtitle");
	      String qcontent = mrequest.getParameter("qcontent");
	      int Qnumber = Integer.parseInt(mrequest.getParameter("qnumber"));
	      
	      System.out.println("qimage " + qimage);
	      System.out.println("qtitle " + qtitle);
	      System.out.println("qcontent " + qcontent);
	      
	      int result = new QnaService().QnaUpdate(qtitle,qcontent,qimage,Qnumber );
	 
	      if(result > 0){
	         response.sendRedirect("/semi/qlist");
	      } else{
	         RequestDispatcher view = request.getRequestDispatcher("view/qnaBoard/qnaError.jsp");
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

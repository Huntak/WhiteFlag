package review.controller;

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
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/rupd")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
		  
		      int limitSize = 1024 * 1024 * 20;
		   
		  	  String savePath = request.getSession().getServletContext().getRealPath("/reviewUploadFiles/");
		      //System.out.println(savePath);
		      
		  	 String rimage = null;
		      long currentTime = System.currentTimeMillis();

		      SimpleDateFormat formatFileName = new SimpleDateFormat("yyyyMMddHHmmss");

		      MultipartRequest mrequest = new MultipartRequest(request, savePath, limitSize, 
                    "utf-8", new DefaultFileRenamePolicy());

		      if(mrequest.getFilesystemName("rimage") != null){
		    	  rimage = mrequest.getFilesystemName("rimage");
		      }else{
		    	  rimage = null;
		      }
	      String rtitle = mrequest.getParameter("rtitle");
	      String rcontent = mrequest.getParameter("rcontent");
	      int Rnumber = Integer.parseInt(mrequest.getParameter("rnumber"));
	      
	      System.out.println("rimage " + rimage);
	      System.out.println("rtitle " + rtitle);
	      System.out.println("rcontent " + rcontent);
	      
	      int result = new ReviewService().ReviewUpdate(rtitle,rcontent,rimage,Rnumber );
	 
	         response.sendRedirect("/semi/rlist");
	}					

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FindingId
 */
@WebServlet("/findId")
public class FindingId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindingId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				String userName = request.getParameter("userName");  
				String userEmail = request.getParameter("userEmail");
				String userPhone1 = request.getParameter("userPhone1");
				String userPhone2 = request.getParameter("userPhone2");
				String userPhone3 = request.getParameter("userPhone3");
				String[] result = new String[2];
				if(userEmail =="" || userEmail == null ) { System.out.println("폰검검색넘어옴");    result = new MemberService().getId(userName, userPhone1, userPhone2, userPhone3 );  }
				else {  System.out.println("이메일검색넘어옴");  result = new MemberService().getId(userName, userEmail);    }

				 
				
				
			    JSONObject job = new JSONObject();
			    job.put("Id", result[0]);
			    job.put("Edate", result[1]);
			    PrintWriter out = response.getWriter();
			    out.print(job.toJSONString());
			    out.flush();
			    out.close();
			   
			}

}
